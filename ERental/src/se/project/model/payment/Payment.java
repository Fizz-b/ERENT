package se.project.model.payment;

public class Payment {
   private PayStrategy payType;

   // select payment method
   public void selectPayment(PayStrategy strategy) {
	   this.payType = strategy;
   }
   
   //  update balance in card
   public void payRent(int money) {
	   payType.updateBalance(money);
   }
   
   public void payReturn(int deposit,float money) {
	   payType.updateBalance(deposit, money);
   }
   
   
}
