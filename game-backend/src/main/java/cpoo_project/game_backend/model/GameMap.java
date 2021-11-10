package cpoo_project.game_backend.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class GameMap {
  private String name;
  private Integer width;
  private Integer height;
  private List<NatureTile> startTiles;
  private Map<String, GameReplay> replays;

  public GameMap() {
  }

  public GameMap(final String name,
                 final Integer width,
                 final Integer height,
                 final List<NatureTile> startTiles,
                 final Map<String, GameReplay> replays) {
    this.name = name;
    this.width = width;
    this.height = height;
    this.startTiles = List.copyOf(startTiles);
    this.replays = new HashMap<>(replays);
  }

  @XmlAttribute
  public String getName() {
    return name;
  }

  @XmlAttribute
  public Integer getWidth() {
    return width;
  }

  @XmlAttribute
  public Integer getHeight() {
    return height;
  }

  @XmlElement
  public List<NatureTile> getStartTiles() {
    if (startTiles == null) {
      return List.of();
    }
    return Collections.unmodifiableList(startTiles);
  }

  public Map<String, GameReplay> getReplays() {
    if (replays == null) {
      return Map.of();
    }
    return Collections.unmodifiableMap(replays);
  }

  public Boolean registerReplay(final GameReplay replay) throws IllegalArgumentException {
    if (replay.getMap() != this) {
      throw new IllegalArgumentException();
    }
    final var bestPlayerReplay = replays.merge(replay.getPlayerName(), replay, (oldValue, newValue) -> {
      if (newValue.getScore() > oldValue.getScore()) {
        return newValue;
      }
      return oldValue;
    });
    return bestPlayerReplay == replay;
  }
}
