package ch.heigvd.amt.demo.api;

import ch.heigvd.amt.demo.dto.TransactionDTO;
import ch.heigvd.amt.demo.services.business.TransactionProcessorLocal;
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
@Path("transactions")
public class TransactionResource {
  
  @EJB
  TransactionProcessorLocal transactionProcessor;


  /**
   * Creates a new instance of TransactionResource
   */
  public TransactionResource() {
  }

  /**
   * POST method for creating an instance of TransactionResource
   *
   * @param content representation for the resource
   * @return an HTTP response with content of the updated or created resource.
   */
  @POST
  @Consumes("application/json")
  public void postTransaction(TransactionDTO transaction) {
    transactionProcessor.processTransaction(transaction);
  }
}
