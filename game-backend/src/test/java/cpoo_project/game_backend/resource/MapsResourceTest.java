package cpoo_project.game_backend.resource;

import com.github.hanleyt.JerseyExtension;
import cpoo_project.game_backend.model.CityTile;
import cpoo_project.game_backend.model.GameMap;
import cpoo_project.game_backend.model.GameMapBuilder;
import cpoo_project.game_backend.model.GameMove;
import cpoo_project.game_backend.model.GameReplay;
import cpoo_project.game_backend.model.NatureTile;
import cpoo_project.game_backend.service.GameMapStorage;
import cpoo_project.game_backend.service.GameReplayStorage;
import cpoo_project.game_backend.test_utils.Reflection;
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

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class MapsResourceTest {
  static {
    System.setProperty("jersey.config.test.container.port", "0");
  }

  @RegisterExtension
  JerseyExtension jerseyExtension = new JerseyExtension(this::configureJersey);

  @Spy
  private GameMapStorage mapStorage;
  @Mock
  private GameReplayStorage replayStorage;
  @Spy
  private GameMapBuilder mapBuilder;

  private Application configureJersey(ExtensionContext extensionContext) {
    return new ResourceConfig(MapsResource.class)
      .register(MoxyJsonFeature.class)
      .register(new AbstractBinder() {
        @Override
        protected void configure() {
          bind(mapStorage).to(GameMapStorage.class);
          bind(replayStorage).to(GameReplayStorage.class);
          bind(mapBuilder).to(GameMapBuilder.class);
        }
      });
  }

  @AfterEach
  public void clearMocks() {
    Mockito.clearInvocations(mapStorage, replayStorage, mapBuilder);
  }

  @Test
  public void testGetMapNames(WebTarget target) {
    Mockito.doReturn(Stream.of(
      new GameMapBuilder().withName("map1").build(),
      new GameMapBuilder().withName("map2").build()
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
      Optional.of(new GameMapBuilder().withName("map1").build())
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

  @Test
  public void testGetMapReplays(WebTarget target) {
    final var map = new GameMapBuilder().withName("map1").build();
    map.registerReplay(new GameReplay(null, "player1", List.of()));
    map.registerReplay(new GameReplay(null, "player2", List.of()));
    Mockito.doReturn(Optional.of(map)).when(mapStorage).get("map1");

    final Response res = target
      .path("/maps/map1/replays")
      .request()
      .get();
    Assertions.assertEquals(Response.Status.OK.getStatusCode(), res.getStatus());
    final List<String> playerNames = res.readEntity(new GenericType<>() {
    });
    Assertions.assertArrayEquals(new String[]{"player1", "player2"}, playerNames.toArray());

    final var map2 = new GameMapBuilder().withName("map2").build();
    map2.registerReplay(new GameReplay(null, "b", List.of()));
    map2.registerReplay(new GameReplay(null, "a", List.of()));
    map2.registerReplay(new GameReplay(null, "f", List.of()));
    map2.registerReplay(new GameReplay(null, "c", List.of()));
    map2.registerReplay(new GameReplay(null, "d", List.of()));
    map2.registerReplay(new GameReplay(null, "e", List.of()));
    Mockito.doReturn(Optional.of(map2)).when(mapStorage).get("map2");

    final Response res2 = target
      .path("/maps/map2/replays")
      .queryParam("sortBy", "playerName")
      .queryParam("limit", "2")
      .request()
      .get();
    Assertions.assertEquals(Response.Status.OK.getStatusCode(), res.getStatus());
    Assertions.assertArrayEquals(
      new String[]{"a", "b"},
      res2.readEntity(new GenericType<List<String>>() {
      }).toArray());
  }

  @Test
  public void testGetMapReplay(WebTarget target) throws ReflectiveOperationException {
    final var map = new GameMapBuilder().withName("map1").build();
    map.registerReplay(new GameReplay(null, "player1", List.of(
      new GameMove(0, 0, CityTile.CIRCUS)
    )));
    map.registerReplay(new GameReplay(null, "player2", List.of()));
    Mockito.doReturn(Optional.of(map)).when(mapStorage).get("map1");

    final Response res = target
      .path("/maps/map1/replays/player1")
      .request()
      .get();
    Assertions.assertEquals(Response.Status.OK.getStatusCode(), res.getStatus());
    final var replay = res.readEntity(GameReplay.class);
    Reflection.setGameReplayMap(replay, map);
    Assertions.assertEquals(8, replay.getScore());
  }

  @Test
  public void testGenerateMap(WebTarget target) throws IOException {
    Mockito.doNothing().when(mapStorage).put(Mockito.any());
    mapBuilder.withName("map1");
    final Response res = target
      .path("/maps")
      .request()
      .post(null);
    Assertions.assertEquals(Response.Status.CREATED.getStatusCode(), res.getStatus());
    Mockito.verify(mapBuilder, Mockito.times(2)).build();
    Mockito.verify(mapStorage).put(Mockito.any());
    final var map = res.readEntity(GameMap.class);
    Assertions.assertEquals("map1", map.getName());
  }

  @Test
  public void testRegisterReplay(WebTarget target) throws IOException {
    final var map = Mockito.mock(GameMap.class);
    Mockito.doReturn(Optional.of(map)).when(mapStorage).get("map1");
    Mockito.doNothing().when(replayStorage).put(Mockito.any());

    Mockito.doAnswer(invocation -> {
      Reflection.setGameReplayMap(invocation.getArgument(0, GameReplay.class), map);
      return true;
    }).when(map).registerReplay(Mockito.any());
    final Response res = target
      .path("/maps/map1/replays/player1")
      .request()
      .put(Entity.json(new GameReplay(map, null, List.of())));
    Assertions.assertEquals(Response.Status.CREATED.getStatusCode(), res.getStatus());
    Mockito.verify(replayStorage).put(Mockito.any());

    Mockito.clearInvocations(replayStorage);

    Mockito.doReturn(false).when(map).registerReplay(Mockito.any());
    final Response res2 = target
      .path("/maps/map1/replays/player2")
      .request()
      .put(Entity.json(new GameReplay(map, null, List.of())));
    Assertions.assertEquals(Response.Status.NO_CONTENT.getStatusCode(), res2.getStatus());
    Mockito.verify(replayStorage, Mockito.never()).put(Mockito.any());
  }
}
