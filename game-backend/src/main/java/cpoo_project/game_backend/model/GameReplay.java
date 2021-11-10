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
    this.map = map;
    this.playerName = playerName;
    this.moves = List.copyOf(moves);
  }

  public GameMap getMap() {
    return map;
  }

  @XmlElement
  public String getPlayerName() {
    return playerName;
  }

  @XmlElement
  public List<GameMove> getMoves() {
    if (moves == null) {
      return List.of();
    }
    return Collections.unmodifiableList(moves);
  }

  public Integer getScore() {
    return 0;
  }
}
