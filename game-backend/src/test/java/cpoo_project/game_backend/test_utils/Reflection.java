package cpoo_project.game_backend.test_utils;

import cpoo_project.game_backend.model.GameMap;
import cpoo_project.game_backend.model.GameReplay;

public class Reflection {
  public static void setGameReplayMap(GameReplay replay, GameMap map) throws ReflectiveOperationException {
    final var mapField = replay.getClass().getDeclaredField("map");
    mapField.setAccessible(true);
    mapField.set(replay, map);
  }
}
