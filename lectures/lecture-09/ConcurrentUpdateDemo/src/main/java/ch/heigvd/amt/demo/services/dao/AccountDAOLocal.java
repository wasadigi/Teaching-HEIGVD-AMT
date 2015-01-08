package ch.heigvd.amt.demo.services.dao;

import ch.heigvd.amt.demo.model.Account;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Olivier Liechti
 */
@Local
public interface AccountDAOLocal {

  void create(Account account);
  
  void update(Account account);

  Account findById(long id);

  Account findByIdForUpdate(long id);

  List<Account> findAll();

  void deleteAll();
  
}
