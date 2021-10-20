package cpoo_project.game_backend.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Player {
  private String name;

  public Player() {
  }

  public Player(final String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
