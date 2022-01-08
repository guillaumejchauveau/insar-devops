package cpoo_project.game_backend.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class GameMap {
  private String name;
  private Integer width;
  private Integer height;
  private List<NatureTile> startTiles;
  @XmlTransient
  private final Map<String, GameReplay> replays;

  GameMap() {
    this.replays = new HashMap<>();
  }

  GameMap(final String name,
          final Integer width,
          final Integer height,
          final List<NatureTile> startTiles) {
    if (startTiles.size() != width * height) {
      throw new IllegalArgumentException();
    }
    this.name = name;
    this.width = width;
    this.height = height;
    this.startTiles = List.copyOf(startTiles);
    this.replays = new HashMap<>();
  }

  public String getName() {
    return name;
  }

  public Integer getWidth() {
    return width;
  }

  public Integer getHeight() {
    return height;
  }

  public List<NatureTile> getStartTiles() {
    if (startTiles == null) {
      return List.of();
    }
    return Collections.unmodifiableList(startTiles);
  }

  public Map<String, GameReplay> getReplays() {
    return Collections.unmodifiableMap(replays);
  }

  public Boolean registerReplay(final GameReplay replay) throws IllegalArgumentException {
    if (replay.getMap() == null) {
      replay.setMap(this);
    }
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
