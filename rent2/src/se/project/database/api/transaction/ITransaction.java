package se.project.database.api.transaction;

import se.project.model.order.Order;

public interface ITransaction {

  public void saveTransaction(int orID, String msg, double money,String card); //
  public void saveTransacToDB(Order order);
  public void updateReturn(int orId, int bikeId, double totMoney, String timeFinish,String returnI);
}
