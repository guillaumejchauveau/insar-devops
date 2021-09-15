package game.resource;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("game")
public class GameResource {
//	private final GameData data;

	// to remove if you use the other constructor
	public GameResource() {
		super();
	}

//	Useful for testing purpose: can inject fake/controlled data
//	@Inject
//	public MapResource(final GameData data) {
//		super();
//		this.data = data;
//	}

	@GET
	@Path("foo/bar")
	@Produces(MediaType.APPLICATION_JSON)
	public void foobar() {
	}
}
