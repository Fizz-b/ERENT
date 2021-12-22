package se.project.model.bike;

public class BikeFactory {
	  public BikeType getBike(String type) {
		    switch (type) {
		      case "Ebike":
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
