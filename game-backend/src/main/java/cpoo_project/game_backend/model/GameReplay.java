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
  private Player player;
  private List<GameMove> moves;

  public GameReplay() {
  }

  public GameReplay(final GameMap map, final Player player, final List<GameMove> moves) {
    this.map = map;
    this.player = player;
    this.moves = List.copyOf(moves);
  }

  public GameMap getMap() {
    return map;
  }

  @XmlElement
  public Player getPlayer() {
    return player;
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
