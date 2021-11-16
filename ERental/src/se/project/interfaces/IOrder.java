package se.project.interfaces;

import se.project.model.order.Order;

public interface IOrder {

  public Order getOrder(int userId);  // get order detail by cust id

  public int getOrderId(int custId); // get orderid by cust id
}
