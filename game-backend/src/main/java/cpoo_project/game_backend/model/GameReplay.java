package cpoo_project.game_backend.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class GameReplay {
  private GameMap map;
  private String playerName;
  private List<GameMove> moves;

  public GameReplay() {
  }

  public GameReplay(final GameMap map, final String playerName, final List<GameMove> moves) {
    this.setMap(map);
    this.setPlayerName(playerName);
    this.setMoves(moves);
  }

  public GameMap getMap() {
    return map;
  }

  void setMap(final GameMap map) {
    this.map = map;
  }

  @XmlElement
  public String getPlayerName() {
    return playerName;
  }

  void setPlayerName(final String playerName) {
    this.playerName = playerName;
  }

  @XmlElement
  public List<GameMove> getMoves() {
    if (moves == null) {
      return List.of();
    }
    return Collections.unmodifiableList(moves);
  }

  void setMoves(final List<GameMove> moves) {
    this.moves = List.copyOf(moves);
  }

  public Integer getScore() {
    return 0;
  }
}
