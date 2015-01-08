package ch.heigvd.amt.demo.dto;

/**
 *
 * @author Olivier Liechti
 */
public class TransactionDTO {
  
  private double amount;
  
  private long accountId;

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public long getAccountId() {
    return accountId;
  }

  public void setAccountId(long accountId) {
    this.accountId = accountId;
  }
  
 
}
