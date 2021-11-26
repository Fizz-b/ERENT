package se.project.interfaces;

public interface ITransaction {

  public void saveTransaction(int orID, String msg, double money); //

  public void updateReturn(int orId, int bikeId, double totMoney, String timeFinish);
}
