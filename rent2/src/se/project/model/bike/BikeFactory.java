package se.project.model.bike;

public class BikeFactory {
	  public static BikeType getBike(String type) {
		    switch (type) {
		      case "ElectricBike":
		        return new ElectricBike();

		      case "Bike":
		        return new Bike();
                    
		      case "TwinBike":
		        return new TwinBike();
		       default:
		    	   return null;
                
		    }
	   }
}
