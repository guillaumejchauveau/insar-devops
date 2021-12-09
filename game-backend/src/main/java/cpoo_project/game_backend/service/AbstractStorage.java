package cpoo_project.game_backend.service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.eclipse.persistence.jaxb.JAXBContextProperties;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractStorage<T> {
  protected final Path rootDirectory;

  protected final JAXBContext jaxbContext;
  protected final Unmarshaller unmarshaller;
  protected final Marshaller marshaller;

  protected final Map<String, T> entities;

  protected AbstractStorage(final Path rootDirectory, final Collection<Class<?>> classesToBeBound) throws JAXBException, IOException {
    this.rootDirectory = rootDirectory;
    if (this.rootDirectory.toFile().mkdirs()) {
      System.out.println("Created dir " + this.rootDirectory);
    }

    final var properties = new HashMap<>();
    properties.put(JAXBContextProperties.MEDIA_TYPE, "application/json");

    this.jaxbContext = JAXBContextFactory.createContext(classesToBeBound.toArray(new Class[0]), properties);
    this.unmarshaller = jaxbContext.createUnmarshaller();
    this.marshaller = jaxbContext.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

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

  protected void put(final String key, final T entity) throws IOException, JAXBException {
    this.entities.put(key, entity);
    final var path = this.getEntityPath(key);
    if (path.toFile().getParentFile().mkdirs()) {
      System.out.println("Created dir " + path.getParent());
    }
    this.marshaller.marshal(entity, Files.newOutputStream(path));
  }

  @SuppressWarnings("unchecked")
  protected Optional<T> get(final String key) {
    this.entities.computeIfAbsent(key, k -> {
      try {
        final var path = this.getEntityPath(key);
        if (path.toFile().getParentFile().mkdirs()) {
          System.out.println("Created dir " + path.getParent());
        }
        return (T) this.unmarshaller.unmarshal(Files.newInputStream(path));
      } catch (JAXBException | IOException ignored) {
        return null;
      }
    });
    return Optional.ofNullable(this.entities.get(key));
  }
}
