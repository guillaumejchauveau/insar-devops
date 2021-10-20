package cpoo_project.game_backend.resource;

import cpoo_project.game_backend.model.GameMap;
import cpoo_project.game_backend.model.GameReplay;
import cpoo_project.game_backend.model.Error;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.inject.Singleton;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Api
@Singleton
@Path("maps")
public class MapsResource {
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @ApiResponses({
    @ApiResponse(code = 200, message = "OK", response = String.class, responseContainer = "List")
  })
  public List<String> getMapNames() {
    return new ArrayList<>();
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @ApiResponses({
    @ApiResponse(code = 201, message = "Created", response = GameMap.class)
  })
  public Response generateMap() {
    return Response
      .status(Response.Status.CREATED)
      .entity(new GameMap("test", 0, 0, List.of(), Map.of()))
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
    throw new NotFoundException();
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
    throw new NotFoundException();
  }

  @POST
  @Path("/{mapName}/replays")
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
    @NotNull @Valid final GameReplay gameReplay)
    throws NotFoundException, BadRequestException {
    throw new NotFoundException();
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
    throw new NotFoundException();
  }
}
