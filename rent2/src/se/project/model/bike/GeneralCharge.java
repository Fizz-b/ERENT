package se.project.model.bike;

public class GeneralCharge implements IMoneyCharge{

	@Override
	public double calTotalCost(int time) {
		 double total=0;
		    if (time>=0 && time <=30) {
		       total = 10000;
		    } else if(time>30){
		      double part =  Math.ceil((time - 30)/15.0);
		      System.out.print(part);
		      total =  (10000 + part * 3000);
		   }
		   return total;
	}

}
