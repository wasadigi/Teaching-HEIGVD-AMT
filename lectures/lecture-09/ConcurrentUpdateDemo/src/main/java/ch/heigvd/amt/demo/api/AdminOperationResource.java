package ch.heigvd.amt.demo.api;

import ch.heigvd.amt.demo.services.dao.AccountDAOLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * REST Web Service
 *
 * @author Olivier Liechti
 */
@Stateless
@Path("operations")
public class AdminOperationResource {

  @EJB
  AccountDAOLocal accountDAO;

  public AdminOperationResource() {
  }
  
  @POST
  @Path("reset")
  @Consumes("application/json")
  public void postResetOperation(Object operation) {
    accountDAO.deleteAll();
  }
  

}
