package se.project.model.bike;

public class Bike extends BikeType {


  public Bike() {
    super();
  }


  public Bike(String name, String type, String manu, String producer, int cost) {
    super(name, type, manu, producer, cost);
  }


  @Override
  public int getDeposit() {
    // TODO Auto-generated method stub
    return 400000;
  }

}
