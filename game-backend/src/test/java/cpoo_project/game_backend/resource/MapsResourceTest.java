package cpoo_project.game_backend.resource;

import com.github.hanleyt.JerseyExtension;
import cpoo_project.game_backend.ApplicationBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

public class MapsResourceTest {
  static {
    System.setProperty("jersey.config.test.container.port", "0");
  }

  @RegisterExtension
  JerseyExtension jerseyExtension = new JerseyExtension(this::configureJersey);

  private Application configureJersey(ExtensionContext extensionContext) {
    return new ResourceConfig()
      .packages("cpoo_project.game_backend.resource")
      .register(ApplicationBinder.class);
  }

  @Test
  public void testGetNames(WebTarget target) {
    final Response res = target
      .path("/maps")
      .request()
      .get();
    Assertions.assertEquals(Response.Status.OK.getStatusCode(), res.getStatus());
  }
}
