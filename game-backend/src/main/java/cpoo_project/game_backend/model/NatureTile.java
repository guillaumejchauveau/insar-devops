package cpoo_project.game_backend.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum NatureTile implements Tile {
  @XmlEnumValue(value = "GRASS")
  GRASS("grass"),
  @XmlEnumValue(value = "TREE")
  TREE("tree"),
  @XmlEnumValue(value = "WATER")
  WATER("water");

  private String name;

  NatureTile() {
  }

  NatureTile(final String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }
}
