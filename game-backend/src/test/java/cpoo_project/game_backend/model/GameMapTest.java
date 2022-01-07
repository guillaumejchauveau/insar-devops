package cpoo_project.game_backend.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameMapTest {
  @Test
  public void testReplayRegistration() {
    final var map = new GameMap("map1", 5, 5,
      Stream.generate(() -> NatureTile.GRASS).limit(5 * 5).collect(Collectors.toList()),
      Map.of());
    final var replay = new GameReplay(map, "player1", List.of(
      new GameMove(0, 0, CityTile.HOUSE)
    ));
    final var replay2 = new GameReplay(map, "player1", List.of(
      new GameMove(0, 0, CityTile.HOUSE),
      new GameMove(2, 0, CityTile.HOUSE)
    ));
    final var replay3 = new GameReplay(map, "player2", List.of(
      new GameMove(0, 0, CityTile.HOUSE)
    ));
    final var replay4 = new GameReplay(map, "player1", List.of(
      new GameMove(0, 0, CityTile.HOUSE),
      new GameMove(1, 0, CityTile.HOUSE)
    ));
    Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(map.registerReplay(replay)));
    Assertions.assertEquals(map, replay.getMap());
    Assertions.assertTrue(map.getReplays().containsKey("player1"));
    Assertions.assertEquals(replay, map.getReplays().get("player1"));
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      map.registerReplay(new GameReplay(new GameMap("map2", 0, 0, List.of(), Map.of()), "player1", List.of()));
    });
    Assertions.assertTrue(map.registerReplay(replay2));
    Assertions.assertTrue(map.getReplays().containsKey("player1"));
    Assertions.assertEquals(replay2, map.getReplays().get("player1"));
    Assertions.assertTrue(map.registerReplay(replay3));
    Assertions.assertTrue(map.getReplays().containsKey("player2"));
    Assertions.assertEquals(replay3, map.getReplays().get("player2"));
    Assertions.assertFalse(map.registerReplay(replay4));
    Assertions.assertTrue(map.getReplays().containsKey("player1"));
    Assertions.assertEquals(replay2, map.getReplays().get("player1"));
  }
}
