package se.project.model.bike;

public class ElectricBike extends BikeType {

  public ElectricBike() {
    super();
  }


  public ElectricBike(String name, String type, String manu, String producer, int cost) {
    super(name, type, manu, producer, cost);
  }

	public double calTotalCost(int time) {
		double bikeRate = 1.5; 
	    return bikeRate*super.calTotalCost(time);
	}
  @Override
  public int getDeposit() {
    // TODO Auto-generated method stub
    return 700000;
  }
}
