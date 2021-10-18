package cpoo_project.game_backend;

import cpoo_project.game_backend.model.Error;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {
  @Override
  public Response toResponse(final WebApplicationException exception) {
    return Response
      .status(exception.getResponse().getStatus())
      .entity(new Error(exception))
      .build();
  }
}
