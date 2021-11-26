package se.project.model.payment;

public interface PayStrategy {

  public boolean updateBalance(int deposit, double d);  // tru tien khi return

  

  public boolean updateBalance(double d);

}
