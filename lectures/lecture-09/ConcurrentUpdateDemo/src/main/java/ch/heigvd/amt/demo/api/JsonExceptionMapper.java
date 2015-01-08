package ch.heigvd.amt.demo.api;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Olivier Liechti
 */
@Provider
public class JsonExceptionMapper implements ExceptionMapper<NotFoundException> {

  private class Body {
    private String message;

    public Body(String message) {
      this.message = message;
    }
    
    public String getMessage() {
      return message;
    }
  
  }
  
  @Override
  public Response toResponse(NotFoundException e) {
    return Response
      .status(404)
      .entity(new Body(e.getMessage()))
      .type("application/json")
      .build();
  }
  
}
