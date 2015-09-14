package ch.heigvd.amt.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Version;

/**
 *
 * @author Olivier Liechti
 */

@Entity
@NamedQueries({
    @NamedQuery(name="Account.findAll", query="SELECT a FROM Account a"),
    @NamedQuery(name="Account.deleteAll", query="DELETE FROM Account")
}) 
public class Account {
  
  @Id
  private long id;
  
  //@Version 
  //int version;
  
  private String holderName;
  
  private double balance;
  
  private int numberOfTransactions;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getHolderName() {
    return holderName;
  }

  public void setHolderName(String holderName) {
    this.holderName = holderName;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public int getNumberOfTransactions() {
    return numberOfTransactions;
  }

  public void setNumberOfTransactions(int numberOfTransactions) {
    this.numberOfTransactions = numberOfTransactions;
  }

}
