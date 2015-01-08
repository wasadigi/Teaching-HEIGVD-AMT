package ch.heigvd.amt.demo.services.business;

import ch.heigvd.amt.demo.dto.TransactionDTO;
import javax.ejb.Local;

/**
 *
 * @author Olivier Liechti
 */
@Local
public interface TransactionProcessorLocal {

  public void processTransaction(TransactionDTO transaction);
  
  public void createAccountIfNotExists(long id);

  
}
