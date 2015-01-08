package ch.heigvd.amt.demo.dto;

/**
 *
 * @author Olivier Liechti
 */
public class AccountDTO {
  
  private long id;
  
  private double balance;
  
  private long numberOfTransactions;
  
  private String holderName;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public long getNumberOfTransactions() {
    return numberOfTransactions;
  }

  public void setNumberOfTransactions(long numberOfTransactions) {
    this.numberOfTransactions = numberOfTransactions;
  }

  public String getHolderName() {
    return holderName;
  }

  public void setHolderName(String holderName) {
    this.holderName = holderName;
  }
 
}
