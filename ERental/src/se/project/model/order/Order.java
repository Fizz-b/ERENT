package se.project.model.order;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import se.project.interfaces.MoneyCharge;
import se.project.model.bike.BikeType;
import se.project.model.user.Customer;
import se.project.util.DateUtils;

public class Order implements MoneyCharge{
	
	private int id =0;
	

	private BikeType bike;
    private Customer cust;  // should have cust id
	private int time;  // k can biet
    private String status;
    private String timeCreate;
    private String timeFinish;
   
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTimeCreate() {
		return timeCreate;
	}

	public void setTimeCreate(String timeCreate) {
		this.timeCreate = timeCreate;
	}

	public String getTimeFinish() {
		return timeFinish;
	}

	public void setTimeFinish(String timeFinish) {
		this.timeFinish = timeFinish;
	}

	public BikeType getBike() {
		return bike;
	}

	public void setBike(BikeType bike) {
		this.bike = bike;
	}

	public Customer getCust() {
		return cust;
	}

	public void setCust(Customer cust) {
		this.cust = cust;
	}
	
	 public static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  // create date util
    public String getTotalTime(LocalDateTime time) {
	    //LocalDateTime now = LocalDateTime.now(); // current time
	    //now.format(format);
	    //LocalDateTime dateTime = LocalDateTime.parse(timeCreate, format);
        
	    LocalDateTime dateTime = LocalDateTime.parse(timeCreate, format);
	    Duration duration = Duration.between(dateTime, time);
	    /*
	    System.out.println(duration.toDays() + " days");
	    System.out.println(duration.toHours() + " hours");
	 // minutes between from and to
        System.out.println(duration.toMinutes() + " minutes");
        // seconds between from and to
        System.out.println(duration.toSeconds() + " seconds");
        */
	    
       return  DateUtils.date(duration.toSeconds());
       

        
    }
    
	public  long getTime() {
		return time;
	}

	public  void setTime(int l) {
	  this.time = l;
	}
      
	
	// tinh dua tren time create va finish
	public float getTotal() {
		float total = 0;
		if(time < 10 ) {total = 0;}
		else if(time > 30) {
		    int c = (int) (time-30)/15;
			total = 10000+ c*3000;
		    
		} else if(time >10 && time<=30) {
			total =  10000;
		}
		return total;
		
	}

 
    
}
