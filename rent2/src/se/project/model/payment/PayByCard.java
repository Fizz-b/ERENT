package se.project.model.payment;

public class PayByCard implements ICard{

  // update balance
  private CreditCard card;

  public PayByCard(CreditCard card) {
    this.card = card;
  }

 

  
  public boolean updateBalance(double money) {
    // TODO Auto-generated method stub
    return card.debit(money);
  }

   // true thanh cong
  public boolean updateBalance(int deposit, double total) {
    // TODO Auto-generated method stub
    if(card.debit((int)total)) {
    card.credit(deposit);
    return true;
   }
     return false;
}}
