package cpoo_project.game_backend.resource;

import com.github.hanleyt.JerseyExtension;
import cpoo_project.game_backend.model.GameMap;
import cpoo_project.game_backend.model.GameReplay;
import cpoo_project.game_backend.model.NatureTile;
import cpoo_project.game_backend.service.GameMapStorage;
import cpoo_project.game_backend.service.GameReplayStorage;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class MapsResourceTest {
  static {
    System.setProperty("jersey.config.test.container.port", "0");
    try {
      Files.delete(FileSystems.getDefault().getPath("maps"));
      Files.delete(FileSystems.getDefault().getPath("replays"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RegisterExtension
  JerseyExtension jerseyExtension = new JerseyExtension(this::configureJersey);

  @Spy
  private GameMapStorage mapStorage;
  @Mock
  private GameReplayStorage replayStorage;

  private Application configureJersey(ExtensionContext extensionContext) {
    return new ResourceConfig(MapsResource.class)
      .register(MoxyJsonFeature.class)
      .register(new AbstractBinder() {
        @Override
        protected void configure() {
          bind(mapStorage).to(GameMapStorage.class);
          bind(replayStorage).to(GameReplayStorage.class);
        }
      });
  }

  @AfterEach
  public void clearMocks() {
    Mockito.clearInvocations(mapStorage, replayStorage);
  }

  @Test
  public void testGetMapNames(WebTarget target) {
    Mockito.doReturn(Stream.of(
      new GameMap("map1", 10, 10, List.of(), Map.of()),
      new GameMap("map2", 10, 10, List.of(), Map.of())
    )).when(mapStorage).stream();

    final Response res = target
      .path("/maps")
      .request()
      .get();
    Assertions.assertEquals(Response.Status.OK.getStatusCode(), res.getStatus());
    final List<String> mapNames = res.readEntity(new GenericType<>() {
    });
    Assertions.assertArrayEquals(new String[]{"map1", "map2"}, mapNames.toArray());
  }

  @Test
  public void testGetMap(WebTarget target) {
    Mockito.doReturn(
      Optional.of(new GameMap("map1", 10, 10,
        Stream.generate(() -> NatureTile.GRASS).limit(100).collect(Collectors.toList()),
        Map.of()))
    ).when(mapStorage).get("map1");

    final Response res = target
      .path("/maps/map1")
      .request()
      .get();
    Assertions.assertEquals(Response.Status.OK.getStatusCode(), res.getStatus());
    final var map = res.readEntity(GameMap.class);
    Assertions.assertEquals("map1", map.getName());
    Assertions.assertEquals(10, map.getHeight());
    Assertions.assertEquals(10, map.getWidth());
    Assertions.assertEquals(100, map.getStartTiles().size());
    Assertions.assertEquals(NatureTile.GRASS, map.getStartTiles().get(0));
  }

  /*@Test
  public void testGetMapReplays(WebTarget target) {
    Mockito.doReturn(
      Optional.of(new GameMap("map1", 10, 10,
        Stream.generate(() -> NatureTile.GRASS).limit(100).collect(Collectors.toList()),
        Map.of(
          "player1", new GameReplay(),
          "player2", new GameReplay()
        )))
    ).when(mapStorage).get("map1");

    final Response res = target
      .path("/maps/map1/replays")
      .request()
      .get();
    Assertions.assertEquals(Response.Status.OK.getStatusCode(), res.getStatus());
    final List<String> playerNames = res.readEntity(new GenericType<>() {
    });
    Assertions.assertArrayEquals(new String[]{"player1", "player2"}, playerNames.toArray());
  }*/
}
