package cpoo_project.game_backend.model;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameMapBuilder {
  private Supplier<String> nameSupplier;
  private Integer width;
  private Integer height;
  private BiFunction<Integer, Integer, List<NatureTile>> startTilesSupplier;
  public static final BiFunction<Integer, Integer, List<NatureTile>> DEFAULT_START_TILES_SUPPLIER =
    (w, h) -> Stream.generate(() -> NatureTile.GRASS).limit((long) w * h).collect(Collectors.toList());

  public GameMapBuilder() {
    nameSupplier = () -> null;
    startTilesSupplier = DEFAULT_START_TILES_SUPPLIER;
  }

  public GameMapBuilder withNameSupplier(final Supplier<String> nameSupplier) {
    this.nameSupplier = nameSupplier;
    return this;
  }

  public GameMapBuilder withName(final String name) {
    return this.withNameSupplier(() -> name);
  }

  public GameMapBuilder withDimensions(final Integer width, final Integer height) {
    this.width = width;
    this.height = height;
    return this;
  }

  public GameMapBuilder withStartTilesSupplier(final BiFunction<Integer, Integer, List<NatureTile>> startTilesSupplier) {
    this.startTilesSupplier = startTilesSupplier;
    return this;
  }

  public GameMapBuilder withStartTiles(final List<NatureTile> startTiles) {
    return this.withStartTilesSupplier((w, h) -> startTiles);
  }

  public GameMap build() {
    final var name = nameSupplier.get();
    if (name == null) {
      throw new IllegalStateException("Name must not be null");
    }
    if (width == null || height == null) {
      return this.withDimensions(10, 10).build();
    }
    return new GameMap(name, width, height, startTilesSupplier.apply(width, height));
  }
}
