package se.project.model.payment;

public interface ICard extends PayStrategy{

  public boolean updateBalance(int deposit, double total);

}
