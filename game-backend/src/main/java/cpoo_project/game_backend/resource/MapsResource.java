package cpoo_project.game_backend.resource;

import cpoo_project.game_backend.model.GameMap;
import cpoo_project.game_backend.model.GameReplay;
import cpoo_project.game_backend.model.Error;
import cpoo_project.game_backend.model.NatureTile;
import cpoo_project.game_backend.service.GameMapStorage;
import cpoo_project.game_backend.service.GameReplayStorage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Api
@Singleton
@Path("maps")
public class MapsResource {
  private final Random random = new Random();

  @Inject
  protected GameMapStorage mapStorage;
  @Inject
  protected GameReplayStorage replayStorage;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @ApiResponses({
    @ApiResponse(code = 200, message = "OK", response = String.class, responseContainer = "List")
  })
  public List<String> getMapNames() {
    return mapStorage.stream().map(GameMap::getName).collect(Collectors.toList());
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @ApiResponses({
    @ApiResponse(code = 201, message = "Created", response = GameMap.class)
  })
  public Response generateMap() {
    final var map = new GameMap(String.valueOf(random.nextInt()), 10, 10, Stream.generate(() -> NatureTile.GRASS).limit(100).collect(Collectors.toList()), Map.of());
    try {
      mapStorage.put(map);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return Response
      .status(Response.Status.CREATED)
      .entity(map)
      .build();
  }

  @GET
  @Path("/{mapName}")
  @Produces(MediaType.APPLICATION_JSON)
  @ApiResponses({
    @ApiResponse(code = 200, message = "OK", response = GameMap.class),
    @ApiResponse(code = 404, message = "The specified resource was not found", response = Error.class)
  })
  public GameMap getMapByName(
    @PathParam("mapName") @NotNull final String mapName)
    throws NotFoundException {
    return mapStorage.get(mapName).orElseThrow(NotFoundException::new);
  }

  @GET
  @Path("/{mapName}/replays")
  @Produces(MediaType.APPLICATION_JSON)
  @ApiResponses({
    @ApiResponse(code = 200, message = "OK", response = String.class, responseContainer = "List"),
    @ApiResponse(code = 404, message = "The specified resource was not found", response = Error.class)
  })
  public List<String> getReplayPlayerNamesByMapName(
    @PathParam("mapName") @NotNull final String mapName,
    @QueryParam("sortBy") final String sortBy,
    @QueryParam("limit") final Integer limit)
    throws NotFoundException {
    return mapStorage.getReplays(mapName)
      .orElseThrow(NotFoundException::new)
      .sorted((a, b) -> "playerName".equals(sortBy) ? a.getPlayerName().compareTo(b.getPlayerName()) : a.getScore().compareTo(b.getScore()))
      .limit(limit == null ? Long.MAX_VALUE : limit)
      .map(GameReplay::getPlayerName)
      .collect(Collectors.toList());
  }

  @GET
  @Path("/{mapName}/replays/{playerName}")
  @Produces(MediaType.APPLICATION_JSON)
  @ApiResponses({
    @ApiResponse(code = 200, message = "OK", response = GameReplay.class),
    @ApiResponse(code = 404, message = "The specified resource was not found", response = Error.class)
  })
  public GameReplay getReplayByMapNameAndPlayerName(
    @PathParam("mapName") @NotNull final String mapName,
    @PathParam("playerName") @NotNull final String playerName)
    throws NotFoundException {
    return mapStorage.getReplay(mapName, playerName)
      .orElseThrow(NotFoundException::new);
  }

  @PUT
  @Path("/{mapName}/replays/{playerName}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @ApiResponses({
    @ApiResponse(code = 201, message = "The new replay was registered as it was better than the last.", response = GameReplay.class),
    @ApiResponse(code = 204, message = "The last registered replay was better than the new one and was kept instead."),
    @ApiResponse(code = 400, message = "Bad request", response = Error.class),
    @ApiResponse(code = 404, message = "The specified resource was not found", response = Error.class)
  })
  public Response registerReplayByMapName(
    @PathParam("mapName") @NotNull final String mapName,
    @PathParam("playerName") @NotNull final String playerName,
    @NotNull @Valid final GameReplay gameReplay)
    throws NotFoundException, BadRequestException {
    gameReplay.setPlayerName(playerName);
    final var map = mapStorage.get(mapName).orElseThrow(NotFoundException::new);
    if (map.registerReplay(gameReplay)) {
      try {
        replayStorage.put(gameReplay);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      return Response.status(Response.Status.CREATED).entity(gameReplay).build();
    }
    return Response.status(Response.Status.NO_CONTENT).build();
  }
}
