package cpoo_project.game_backend.utils;

import cpoo_project.game_backend.model.GameMapBuilder;
import cpoo_project.game_backend.model.NatureTile;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomEllipticalPatchGameMapStartTilesSupplier implements BiFunction<Integer, Integer, List<NatureTile>> {
  private final Random random = new Random();

  @Override
  public List<NatureTile> apply(final Integer width, final Integer height) {
    final var startTiles = GameMapBuilder.DEFAULT_START_TILES_SUPPLIER.apply(width, height);
    final var points = IntStream.range(0, width * height)
      .mapToObj(i -> new Point2D.Float(i % width, Math.floorDiv(i, width)))
      .collect(Collectors.toList());
    final var surface = width * height;
    random
      .ints(0, 2)
      .mapToObj(v -> v == 0 ? NatureTile.WATER : NatureTile.TREE)
      .limit(surface / 50 + random.nextInt(surface / 50 + 1))
      .forEach(tile -> points.stream()
        .filter(createPatchFilter(Float.valueOf(width), Float.valueOf(height)))
        .map(p -> (int) (p.x + p.y * width))
        .forEach(i -> startTiles.set(i, tile)));
    return startTiles;
  }

  private Predicate<Point2D> createPatchFilter(final Float width, final Float height) {
    return new Ellipse2D.Float(
      random.nextFloat() * width - width / 2,
      random.nextFloat() * height - height / 2,
      width / 3 + random.nextFloat() * width / 2,
      height / 3 + random.nextFloat() * height / 2)::contains;
  }
}
