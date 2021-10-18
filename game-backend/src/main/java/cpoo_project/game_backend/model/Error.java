package cpoo_project.game_backend.model;

import javax.ws.rs.WebApplicationException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Error {
  private Integer code;
  private String message;

  public Error() {

  }

  public Error(final Integer code, final String message) {
    this.code = code;
    this.message = message;
  }

  public Error(final WebApplicationException e) {
    this(e.getResponse().getStatus(), e.getMessage());
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(final Integer code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(final String message) {
    this.message = message;
  }
}
