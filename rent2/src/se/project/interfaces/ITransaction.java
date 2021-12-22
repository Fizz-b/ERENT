package se.project.interfaces;

public interface ITransaction {

  public void saveTransaction(int orID, String msg, double money,String card); //
  public boolean checkTransactFinish(int custId);
  public void updateReturn(int orId, int bikeId, double totMoney, String timeFinish,String returnI);
}
