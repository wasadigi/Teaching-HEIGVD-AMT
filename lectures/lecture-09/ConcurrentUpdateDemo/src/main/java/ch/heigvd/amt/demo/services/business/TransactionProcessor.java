package ch.heigvd.amt.demo.services.business;

import ch.heigvd.amt.demo.dto.TransactionDTO;
import ch.heigvd.amt.demo.model.Account;
import ch.heigvd.amt.demo.services.dao.AccountDAOLocal;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Olivier Liechti
 */
@Stateless
public class TransactionProcessor implements TransactionProcessorLocal {

  private static final Logger LOG = Logger.getLogger(TransactionProcessor.class.getName());

  @EJB
  AccountDAOLocal accountDAO;

  @EJB
  TransactionProcessorLocal selfViaContainer;

  @PersistenceContext
  EntityManager em;

  @Override
  public void processTransaction(TransactionDTO transaction) {
    selfViaContainer.createAccountIfNotExists(transaction.getAccountId());
    
    /*
     * Try the difference between findByIdForUpdate, which locks the account record
     * and findById, which does not.
     */
    Account account = accountDAO.findById(transaction.getAccountId());
    //Account account = accountDAO.findByIdForUpdate(transaction.getAccountId());
    
    
    double bal = account.getBalance();
    bal = bal + transaction.getAmount();
    account.setBalance(bal);
    account.setNumberOfTransactions(account.getNumberOfTransactions() + 1);
  }

  @Override
  public void createAccountIfNotExists(long id) {
    Account account = accountDAO.findById(id);
    if (account == null) {
      account = new Account();
      account.setId(id);
      account.setBalance(0);
      account.setHolderName(generateRandomHolderName());
      try {
        LOG.info("*** Attempt to create an account...");
        accountDAO.create(account);
      } catch (Exception e) {
        LOG.info("*** Exception while creating an account, probably a DUPLICATE key (concurrent issue)");
      }
    }

  }
  
  private String generateRandomHolderName() {
    String[] firstNames = {"John", "Carla", "Hans", "Yuki", "Sacha", "Tomoe", "Bernard", "Heinz", "Kurt", "Dani"};
    String[] lastNames = {"Smith", "Mueller", "Jones", "Dupond", "Martin", "Baecker", "Braig", "Ichikawa", "Nomura", "Simpson"};
    return pickRandomArrayItem(firstNames) + " " + pickRandomArrayItem(lastNames);
  }
  
  private Object pickRandomArrayItem(Object[] array) {
    int index = (int)(Math.random() * array.length);
    return array[index];
  }
  

}
