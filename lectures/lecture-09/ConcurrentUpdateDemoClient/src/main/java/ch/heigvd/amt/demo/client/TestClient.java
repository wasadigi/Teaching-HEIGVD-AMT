package ch.heigvd.amt.demo.client;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.jackson.JacksonFeature;

/**
 *
 * @author Olivier Liechti
 */
public class TestClient {

    private static final Logger LOG = Logger.getLogger(TestClient.class.getName());

  private class TransactionDTO {
    private final long accountId;
    private final double amount;
    
    public TransactionDTO(long accountId, double amount) {
      this.accountId = accountId;
      this.amount = amount;
    }

    public long getAccountId() {
      return accountId;
    }

    public double getAmount() {
      return amount;
    }
    
  }
  
  private int numberOfResponses = 0;

  private synchronized void incCounter() {
    numberOfResponses++;
  }

  private void test() {
    ExecutorService executor = Executors.newFixedThreadPool(10);
    
    Client client = ClientBuilder.newClient().register(JacksonFeature.class);
    final WebTarget target = client.target("http://localhost:8080/ConcurrentUpdateDemo/api").path("transactions");
        
    
    for (int account=1; account<=50; account++) {
      for (int transaction=0; transaction<200; transaction++) {
        final String counter = account + ", " + transaction;
        final int accountId = account;
        Runnable task = new Runnable(){
          @Override
          public void run() {
            Response response = target.request().post(Entity.json(new TransactionDTO(accountId, 1)));
            incCounter();
            if (response.getStatus() < 200 || response.getStatus() >= 300) {
              LOG.log(Level.INFO, "{0} -- {1}  {2}", new Object[]{numberOfResponses, counter, response.toString()});
            } else {
              //LOG.info("counter: " + numberOfResponses);
            }
          }
        };
        executor.execute(task);
      }
    }
    
    LOG.info("Submitted.");
      try {    
        executor.shutdown();
        LOG.log(Level.INFO, "Are we done yet? {0}", executor.isTerminated());
        LOG.log(Level.INFO, "counter: {0}", numberOfResponses);
        executor.awaitTermination(1, TimeUnit.HOURS);
        LOG.log(Level.INFO, "counter: {0}", numberOfResponses);
        LOG.log(Level.INFO, "And now? {0}", executor.isTerminated());
      } catch (InterruptedException ex) {
        Logger.getLogger(TestClient.class.getName()).log(Level.SEVERE, null, ex);
      }
    LOG.info("Done.");
    
  }
  

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    TestClient client = new TestClient();
    client.test();
  }

}
