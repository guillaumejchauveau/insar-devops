package cpoo_project.game_backend;

import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public final class Main {
  private Main() {

  }

  // Base URI the Grizzly HTTP server will listen on
  public static final String BASE_URI = "http://0.0.0.0:4444/";

  public static void main(final String[] args) throws Exception {
    final var rc = new ResourceConfig()
      .packages("cpoo_project.game_backend.resource")
      .register(WebApplicationExceptionMapper.class)
      .register(ApiListingResource.class)
      .register(SwaggerSerializers.class)
      .register(ApplicationBinder.class);

    GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    Thread.currentThread().join();
  }
}
