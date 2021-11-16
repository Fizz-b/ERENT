package se.project.interfaces;

public interface ITransaction {

  public void saveTransaction(int orID, String msg, float money); //

  public void updateReturn(int orId, int bikeId, float totMoney, String timeFinish);
}
