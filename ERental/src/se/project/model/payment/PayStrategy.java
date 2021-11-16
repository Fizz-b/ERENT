package se.project.model.payment;

public interface PayStrategy {

  public void updateBalance(int deposit, float total);  // tru tien khi return

  public void updateBalance(int money);  // tru tien khi rent

}
