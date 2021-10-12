package game;

import java.net.URI;

import game.resource.GameResource;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public final class Main {
  private Main() {
    super();
  }

  /**
   * Takes as argument the HTTP address of the server.
   * Should be http://localhost:4444/ for testing purpose.
   * http://0.0.0.0:4444/ for a Docker image.
   */
  public static void main(final String[] args) throws InterruptedException {
    final String httpAddress = args[0];
    final ResourceConfig rc = new ResourceConfig(GameResource.class)
      .register(io.swagger.jaxrs.listing.ApiListingResource.class)
      .register(io.swagger.jaxrs.listing.SwaggerSerializers.class);
    GrizzlyHttpServerFactory.createHttpServer(URI.create(httpAddress), rc);
    Thread.currentThread().join();
  }
}

