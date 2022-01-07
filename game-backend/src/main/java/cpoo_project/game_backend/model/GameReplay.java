package cpoo_project.game_backend.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.awt.Point;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class GameReplay {
  @XmlTransient
  private GameMap map;
  private String playerName;
  private List<GameMove> moves;

  public GameReplay() {
  }

  public GameReplay(final GameMap map, final String playerName, final List<GameMove> moves) {
    this.setMap(map);
    this.setPlayerName(playerName);
    this.moves = List.copyOf(moves);
  }

  public GameMap getMap() {
    return map;
  }

  void setMap(final GameMap map) {
    this.map = map;
  }

  public String getPlayerName() {
    return playerName;
  }

  public void setPlayerName(final String playerName) {
    this.playerName = playerName;
  }

  static class ScoreComputation {
    Tile[][] state;
    Integer score = 0;

    ScoreComputation(final GameMap map) {
      final var startTiles = map.getStartTiles().toArray(new Tile[0]);
      this.state = IntStream.iterate(0, i -> i + map.getWidth())
        .limit((long) Math.ceil((double) startTiles.length / map.getWidth()))
        .mapToObj(j -> Arrays.copyOfRange(startTiles, j, Math.min(j + map.getWidth(), startTiles.length)))
        .toArray(Tile[][]::new);
    }
  }

  public Integer getScore() {
    return moves.stream().collect(() -> new ScoreComputation(map),
      (comp, move) -> {
        final var radius = move.getTile().getNeighbourPointsRadius();
        final var origin = new Point(Math.max(move.getX() - radius, 0), Math.max(move.getY() - radius, 0));
        final var rightBound = Math.min(move.getX() + radius, this.map.getWidth() - 1);
        final var bottomBound = Math.min(move.getY() + radius, this.map.getHeight() - 1);
        comp.score += Stream.iterate(origin, p -> p.x <= rightBound && p.y <= bottomBound, p -> {
          if (p.x == rightBound) {
            return new Point(origin.x, p.y + 1);
          }
          return new Point(p.x + 1, p.y);
        }).reduce(
          move.getTile().getPoints(),
          (points, p) -> points + move.getTile().getNeighbourPointsFor(comp.state[p.y][p.x]),
          Integer::sum);
        comp.state[move.getY()][move.getX()] = move.getTile();
      }, (comp1, comp2) -> {
        // Should not enter this function, or computation will be incorrect.
        throw new RuntimeException();
      }).score;
  }
}
