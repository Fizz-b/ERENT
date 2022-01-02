package se.project.model.store;

import javafx.scene.image.Image;

public class Store {

	private String name;
	private String address;
	private int available;
	private int id;
	private Image store;
	private int bike;
	private int electricBike;
	private int twinBike;
	public int getElectricBike() {
		return electricBike;
	}

	public void setElectricBike(int electricBike) {
		this.electricBike = electricBike;
	}

	public Image getImage() {
		return store;
	}

	public void setImage(Image store) {
		this.store = store;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBike() {
		return bike;
	}

	public void setBike(int bike) {
		this.bike = bike;
	}

	public int getTwinBike() {
		return twinBike;
	}

	public void setTwinBike(int twinBike) {
		this.twinBike = twinBike;
	}

	




}
