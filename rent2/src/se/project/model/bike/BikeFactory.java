package se.project.model.bike;

public class BikeFactory {
	  public static BikeType getBike(String type) {
		    switch (type) {
		      case "EBike":
		        return new EBike();

		      case "Bike":
		        return new Bike();
                    
		      case "TwinBike":
		        return new TwinBike();
		       default:
		    	   return null;
                
		    }
	   }
}
