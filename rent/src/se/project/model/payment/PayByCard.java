package se.project.model.payment;

public class PayByCard implements PayStrategy {

  // update balance
  private CreditCard card;

  public PayByCard(CreditCard card) {
    this.card = card;
  }

  public CreditCard getCard() {
    return card;
  }

  public void setCard(CreditCard card) {
    this.card = card;
  }

  @Override
  public boolean updateBalance(double money) {
    // TODO Auto-generated method stub
    return card.debit(money);
  }

  @Override  // true thanh cong
  public boolean updateBalance(int deposit, double total) {
    // TODO Auto-generated method stub
    if(card.debit((int)total)) {
    card.credit(deposit);
    return true;
   }
     return false;
}}
