package cpoo_project.game_backend.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class GameMove {
  private Integer x;
  private Integer y;
  private CityTile tile;

  public GameMove() {
  }

  public GameMove(final Integer x, final Integer y, final CityTile tile) {
    this.x = x;
    this.y = y;
    this.tile = tile;
  }

  public Integer getX() {
    return x;
  }

  public Integer getY() {
    return y;
  }

  public CityTile getTile() {
    return tile;
  }
}
