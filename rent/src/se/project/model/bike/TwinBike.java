package se.project.model.bike;

public class TwinBike extends BikeType {

  public TwinBike() {
    super();
  }

  public TwinBike(String name, String type, String manu, String producer, int cost) {
    super(name, type, manu, producer, cost);
  }
  public double getTotalCost(int time) {
		double bikeRate = 1.5; 
	    return bikeRate*super.getTotalCost(time);
	}
  @Override
  public int getDeposit() {
    // TODO Auto-generated method stub
    return 550000;
  }
}
