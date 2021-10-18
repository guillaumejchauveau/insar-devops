package cpoo_project.game_backend.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class GameMap {
  private String name;
  private Integer width;
  private Integer height;
  private List<NatureTile> startTiles;
  //private Map<Player, GameReplay> replays;

  public GameMap() {

  }

  public GameMap(final String name, final Integer width, final Integer height, final List<NatureTile> startTiles) {
    this.name = name;
    this.width = width;
    this.height = height;
    this.startTiles = List.copyOf(startTiles);
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public Integer getWidth() {
    return width;
  }

  public void setWidth(final Integer width) {
    this.width = width;
  }

  public Integer getHeight() {
    return height;
  }

  public void setHeight(final Integer height) {
    this.height = height;
  }

  public List<NatureTile> getStartTiles() {
    return Collections.unmodifiableList(startTiles);
  }

  public void setStartTiles(final List<NatureTile> startTiles) {
    this.startTiles = List.copyOf(startTiles);
  }
}
