package game.resource;

import com.github.hanleyt.JerseyExtension;
import java.net.URI;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.Assertions;

public class TestGameResource {
	static {
		System.setProperty("jersey.config.test.container.port", "0");
	}

	@SuppressWarnings("unused") @RegisterExtension JerseyExtension jerseyExtension = new JerseyExtension(this::configureJersey);

//	GameData data;

	Application configureJersey() {
//		data = Mockito.mock(GameData.class);
//		data = new MapData();
		return new ResourceConfig() // GameResource.class)
			.register(JacksonFeature.class);
//			.register(new AbstractBinder() {
//				@Override
//				protected void configure() {
//					bind(data).to(MapData.class);
//				}
//			});
	}

	@Test
	void testGetNames(final Client client, final URI baseUri) {
		final Response res = client
			.target(baseUri)
			.path("/")
			.request()
			.get();
		// Assertions.assertEquals(res.getStatus(), Response.Status.OK.getStatusCode());
	}
}

