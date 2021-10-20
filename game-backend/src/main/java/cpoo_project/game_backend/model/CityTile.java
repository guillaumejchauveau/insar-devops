package cpoo_project.game_backend.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import java.util.Map;

import static cpoo_project.game_backend.model.NatureTile.TREE;
import static cpoo_project.game_backend.model.NatureTile.WATER;

@XmlEnum
public enum CityTile implements Tile {
  @XmlEnumValue(value = "CIRCUS")
  CIRCUS("circus", 8, 3),
  @XmlEnumValue(value = "FOUNTAIN")
  FOUNTAIN("fountain", 6, 1),
  @XmlEnumValue(value = "HOUSE")
  HOUSE("house", 6, 1),
  @XmlEnumValue(value = "WINDMILL")
  WINDMILL("windmill", 15, 1);

  private String name;
  private Integer points;
  private Integer neighbourPointsRadius;

  private static final Map<CityTile, Map<Tile, Integer>> neighbourPoints = Map.of(
    CIRCUS, Map.of(
      CIRCUS, -25,
      HOUSE, 15
    ),
    FOUNTAIN, Map.of(
      CIRCUS, 6,
      HOUSE, 8
    ),
    HOUSE, Map.of(
      CIRCUS, 10,
      FOUNTAIN, 8,
      HOUSE, -1,
      WINDMILL, -12,
      TREE, 5
      ),
    WINDMILL, Map.of(
      HOUSE, -8,
      TREE, -4,
      WATER, 10
    )
  );

  CityTile() {
  }

  CityTile(final String name, final Integer points, final Integer neighbourPointsRadius) {
    this.name = name;
    this.points = points;
    this.neighbourPointsRadius = neighbourPointsRadius;
  }

  @Override
  public String getName() {
    return name;
  }

  public Integer getPoints() {
    return points;
  }

  public Integer getNeighbourPointsRadius() {
    return neighbourPointsRadius;
  }

  public Integer getNeighbourPointsFor(final Tile tile) {
    return neighbourPoints.getOrDefault(this, Map.of()).getOrDefault(tile, 0);
  }
}
