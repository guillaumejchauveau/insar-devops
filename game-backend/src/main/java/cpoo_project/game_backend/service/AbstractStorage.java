package cpoo_project.game_backend.service;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractStorage<T> {
  protected final Path rootDirectory;

  protected final ObjectMapper jsonProvider;
  protected final Class<T> clazz;

  protected final Map<String, T> entities;

  protected AbstractStorage(final Path rootDirectory, final Class<T> clazz) throws IOException {
    this.rootDirectory = rootDirectory;
    this.clazz = clazz;
    if (this.rootDirectory.toFile().mkdirs()) {
      System.out.println("Created dir " + this.rootDirectory);
    }
    this.jsonProvider = new ObjectMapper();
    this.jsonProvider.configure(MapperFeature.PROPAGATE_TRANSIENT_MARKER, true);
    this.jsonProvider.registerModule(new JaxbAnnotationModule());

    this.entities = new HashMap<>();

    Files.walk(this.rootDirectory)
      .map(path -> this.rootDirectory.relativize(path).toString())
      .filter(path -> path.endsWith(".json"))
      .map(path -> path.replaceAll("(?<!^)[.][^.]*$", ""))
      .forEach(this::get);
  }

  protected Path getEntityPath(final String key) {
    return this.rootDirectory.resolve(key + ".json");
  }

  protected void put(final String key, final T entity) throws IOException {
    this.entities.put(key, entity);
    final var path = this.getEntityPath(key);
    if (path.toFile().getParentFile().mkdirs()) {
      System.out.println("Created dir " + path.getParent());
    }
    this.jsonProvider
      .writerWithDefaultPrettyPrinter()
      .writeValue(Files.newOutputStream(path), entity);
  }

  protected Optional<T> get(final String key) {
    this.entities.computeIfAbsent(key, k -> {
      try {
        final var path = this.getEntityPath(key);
        if (path.toFile().getParentFile().mkdirs()) {
          System.out.println("Created dir " + path.getParent());
        }
        return this.jsonProvider.readValue(Files.newInputStream(path), clazz);
      } catch (IOException ignored) {
        return null;
      }
    });
    return Optional.ofNullable(this.entities.get(key));
  }
}
