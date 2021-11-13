package se.project.controller.pay;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import se.project.controller.ShopController;
import se.project.interfaces.IOrder;
import se.project.model.order.Order;
public class ResultController {
     
	private Order order;
    public Order getOrder() {
		return order;
	}
    
	public void setOrder(Order order) {
		this.order = order;
	}

	@FXML
    private Button homeBtn;

    @FXML
    private Label money;

    @FXML
    private TextArea msg ;

    @FXML
    private Label time;

    @FXML
    private Label transacCode;
   
    @FXML
    void backHome(MouseEvent event) {
    	FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/se/project/gui/shop.fxml"));
		try {
			Parent root = loader.load();
			ShopController shop = loader.getController();
			/*
			// can reset order ve null neu thanh toan xog
			if(order.getTimeFinish()!=null) {
			    shop.init(null,order.getCust(), null);
				shop.setOrder(null);
			}else {
				shop.setOrder(order);
				shop.init(order, order.getCust(), order.getBike());
			}
			*/
			shop.setOrder(order);
			shop.init(order, order.getCust(), order.getBike());
			
			Stage stage = (Stage) (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	
	public void setMoney(String m) {
		money.setText(m);
	}

	
	public void setMsg(String string) {
		msg.setText(string);
	}

	

	public void setTime(String t) {
	  time.setText(t);
	}
	    
	
}
