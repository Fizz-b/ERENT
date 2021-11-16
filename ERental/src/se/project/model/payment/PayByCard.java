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
  public void updateBalance(int money) {
    // TODO Auto-generated method stub
    card.setMoney(card.getMoney() - money);

  }

  @Override
  public void updateBalance(int deposit, float total) {
    // TODO Auto-generated method stub
    card.setMoney(card.getMoney() + (deposit - total));
  }

}
