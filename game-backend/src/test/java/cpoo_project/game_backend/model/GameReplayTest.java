package cpoo_project.game_backend.model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameReplayTest {
  @Test
  public void testScoreComputationStateConversion() {
    final var map = new GameMap("map1", 5, 5, List.of(
      NatureTile.TREE, NatureTile.GRASS, NatureTile.GRASS, NatureTile.GRASS, NatureTile.WATER,
      NatureTile.GRASS, NatureTile.GRASS, NatureTile.GRASS, NatureTile.GRASS, NatureTile.GRASS,
      NatureTile.GRASS, NatureTile.WATER, NatureTile.GRASS, NatureTile.GRASS, NatureTile.GRASS,
      NatureTile.TREE, NatureTile.GRASS, NatureTile.GRASS, NatureTile.GRASS, NatureTile.GRASS,
      NatureTile.GRASS, NatureTile.GRASS, NatureTile.GRASS, NatureTile.GRASS, NatureTile.GRASS
    ), Map.of());
    final var comp = new GameReplay.ScoreComputation(map);
    Assertions.assertEquals(NatureTile.TREE, comp.state[0][0]);
    Assertions.assertEquals(NatureTile.WATER, comp.state[0][4]);
    Assertions.assertEquals(NatureTile.WATER, comp.state[2][1]);
    Assertions.assertEquals(NatureTile.TREE, comp.state[3][0]);
  }

  @Test
  public void testScoreComputation() {
    final var map = new GameMap("map1", 5, 5,
      Stream.generate(() -> NatureTile.GRASS).limit(5 * 5).collect(Collectors.toList()),
      Map.of());
    final var replay = new GameReplay(map, "player1", List.of(
      new GameMove(0, 0, CityTile.HOUSE)
    ));
    Assertions.assertEquals(6, replay.getScore());
    final var replay2 = new GameReplay(map, "player1", List.of(
      new GameMove(0, 0, CityTile.HOUSE),
      new GameMove(2, 0, CityTile.HOUSE)
    ));
    Assertions.assertEquals(12, replay2.getScore());
    final var replay3 = new GameReplay(map, "player1", List.of(
      new GameMove(0, 0, CityTile.HOUSE),
      new GameMove(1, 0, CityTile.HOUSE)
    ));
    Assertions.assertEquals(11, replay3.getScore());
  }
}
