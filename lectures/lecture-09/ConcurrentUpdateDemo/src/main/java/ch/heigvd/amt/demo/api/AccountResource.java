package ch.heigvd.amt.demo.api;

import ch.heigvd.amt.demo.dto.AccountDTO;
import ch.heigvd.amt.demo.model.Account;
import ch.heigvd.amt.demo.services.dao.AccountDAOLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author Olivier Liechti
 */
@Stateless
@Path("accounts")
public class AccountResource {

  @EJB
  AccountDAOLocal accountDAO;

  /**
   * Creates a new instance of TransactionResource
   */
  public AccountResource() {
  }

  
  @GET
  @Produces("application/json")
  public List<AccountDTO> getAccounts() {
    List<AccountDTO> dto = new ArrayList<>();
    List<Account> accounts = accountDAO.findAll();
    for (Account account : accounts) {
      dto.add(toDTO(account));
    }
    return dto;
  }
  
  /**
   * GET method for retrieving an instance of AccountResource
   *
   * @param content representation for the resource
   * @return an HTTP response with content of the updated or created resource.
   */
  @GET
  @Path("{accountId}")
  @Produces("application/json")
  public AccountDTO getAccount(@PathParam("accountId") long accountId) {
    Account account = accountDAO.findById(accountId);
    if (account == null) {
      throw new NotFoundException("The requested account does not exist");
    }
    return toDTO(account);
  }

  private AccountDTO toDTO(Account account) {
    AccountDTO dto = new AccountDTO();
    dto.setId(account.getId());
    dto.setBalance(account.getBalance());
    dto.setHolderName(account.getHolderName());
    dto.setNumberOfTransactions(account.getNumberOfTransactions());
    return dto;
  }
}
