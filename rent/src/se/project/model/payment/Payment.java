package se.project.model.payment;

public class Payment {

  private PayStrategy payType;

  // select payment method
  public void selectPayment(PayStrategy strategy) {
    this.payType = strategy;
  }

  //  update balance in card
  public boolean payRent(double d) {
    return payType.updateBalance(d);
  }

 

public boolean payReturn(int deposit, double total) {
	// TODO Auto-generated method stub
	return  payType.updateBalance(deposit,total);
}




}
