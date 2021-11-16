package se.project.model.store;

import javafx.scene.image.Image;

public class Store {

  private String name;
  private String address;
  private int available;
  private int rent;
  private int id;
  private String status;
  private Image store;
  public void setImage(Image store) {
	this.store = store;
}

public Image getImage() {
	return store;
}


public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setId(Integer valueOf) {
    // TODO Auto-generated method stub
    this.id = valueOf;
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

  public int getRent() {
    return rent;
  }

  public void setRent(int rent) {
    this.rent = rent;
  }
    
	/*
	public String getStatusFormat() {
		if(this.getStatus() == StoreStatus.FULL) {
			return "Full";
		}else if(this.getStatus() == StoreStatus.available) {
			return "Available";
		}else if(this.getStatus() ==StoreStatus.EMPTY) {
			return "Empty";
		}else return "Invalid Status";
	}*/

}
