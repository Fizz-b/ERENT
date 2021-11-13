package se.project.model.bike;

public class EBike extends BikeType {
	
	public EBike() {
		super();
	}
	
	
	public EBike(String name,String type,String manu,String producer,int cost) {
		super(name,type,manu,producer,cost);
	   }


	@Override
	public int getDeposit() {
		// TODO Auto-generated method stub
		return 700000;
	}
}
