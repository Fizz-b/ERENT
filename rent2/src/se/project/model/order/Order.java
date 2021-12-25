package se.project.model.order;

import java.time.Duration;
import java.time.LocalDateTime;

import se.project.dao.BikeDao;
import se.project.interfaces.IBike;
import se.project.model.bike.BikeFactory;
import se.project.model.bike.BikeType;
import se.project.model.user.Customer;
import se.project.util.DateUtils;

public class Order {

	private int id = 0; // id = 0 chua thue
	private int bikeId;
	private int custId; // should have cust id
	private int time; // k can biet
	private String timeCreate;
	private String timeFinish;
	private String returnId;

	public int getBikeId() {
		return bikeId;
	}

	public void setBikeId(int bikeId) {
		this.bikeId = bikeId;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getReturnId() {
		return returnId;
	}

	public void setReturnId(String returnId) {
		this.returnId = returnId;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

    

	// input:current Time
	// output totalTime use: current - timeCreate
	public String getTotalTime(LocalDateTime tim) {
		LocalDateTime dateTime = LocalDateTime.parse(timeCreate, DateUtils.format);
		Duration duration = Duration.between(dateTime, tim);
		this.time = (int) duration.toSeconds();
		return DateUtils.date(getTime());
	}

	public int getTime() {
		return time;
	}

	public void setTime(long l) {
		this.time = (int) l;
	}

	// tinh dua tren time create va finish
	public double getTotal() {
		IBike iBike = new BikeDao();
		String bikeType = iBike.getBikeType(Integer.toString(bikeId));
		BikeType bike = BikeFactory.getBike(bikeType);
		return bike.calTotalCost(time);
	}
}
