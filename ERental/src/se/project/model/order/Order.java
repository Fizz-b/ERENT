package se.project.model.order;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import se.project.interfaces.MoneyCharge;
import se.project.model.bike.BikeType;
import se.project.model.user.Customer;
import se.project.util.DateUtils;

public class Order implements MoneyCharge {

 
  private int id = 0; // id = 0 chua thue
  private BikeType bike;
  private Customer cust;  // should have cust id
  private int time;  // k can biet
  private String status;
  private String timeCreate;
  private String timeFinish;
  public Order() {

	 
  }
  public Order(Customer cust) {

	  this.cust = cust;
  }
  
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

  // input:current Time
  // output total: current - timeCreate
  public String getTotalTime(LocalDateTime time) {
   

    LocalDateTime dateTime = LocalDateTime.parse(timeCreate, DateUtils.format);
    Duration duration = Duration.between(dateTime, time);
    setTime(duration.toSeconds());
    return DateUtils.date(getTime());


  }
   
  public long getTime() {
    return time;
  }

  public void setTime(long l) {
    this.time = (int)l;
  }


  // tinh dua tren time create va finish
  public float getTotal() {
    float total = 0;
    if (time < 10) {
      total = 0;
    } else if (time > 30) {
      int c = (int) (time - 30) / 15;
      total = 10000 + c * 3000;

    } else if (time > 10 && time <= 30) {
      total = 10000;
    }
    return total;
  }
  
}
