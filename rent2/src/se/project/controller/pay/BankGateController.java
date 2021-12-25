package se.project.controller.pay;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import se.project.dao.BikeDao;
import se.project.dao.OrderDao;
import se.project.dao.StoreDao;
import se.project.dao.TransactionDAO;
import se.project.dao.UserDao;
import se.project.interfaces.IBike;
import se.project.interfaces.IMessageService;
import se.project.interfaces.IOrder;
import se.project.interfaces.IStore;
import se.project.interfaces.ITransaction;
import se.project.interfaces.IUser;
import se.project.model.bike.BikeType;
import se.project.model.order.Order;
import se.project.model.payment.CreditCard;

import se.project.model.payment.PayByCard;
import se.project.model.payment.PayService;
import se.project.model.user.Customer;
import se.project.util.DateUtils;
import se.project.util.EmailService;

import se.project.util.TransactionUtils;

public class BankGateController implements Initializable {

	@FXML
	private Button payBtn;
	@FXML
	private Button backButton;
	@FXML
	private TextArea messageA;
	@FXML
	private TextField bank;
	@FXML
	private TextField cardNum;
	@FXML
	private DatePicker date;
	@FXML
	private TextField name;
	private IOrder iOrder = new OrderDao();
	private ITransaction iTransaction = new TransactionDAO();
	private IStore iStore = new StoreDao();
	private Order order; // need to pass order from
	String formatDateTime;
	CreditCard card = new CreditCard();  // k khoi tao the moi
	IMessageService service;

    private	BikeType bike;
    private Customer customer;
	public void setOrder(Order order2) {
		this.order = order2;

	}
     
	// return to pay scene
	@FXML
	public void backPay(MouseEvent event) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/se/project/gui/pay/pay.fxml"));
		try {
			Parent root = loader.load();
			
			PayController controller= loader.getController();
			controller.setOrder(order);
			  IBike iBike = new BikeDao();
			  bike = iBike.getBikeById(Integer.toString(order.getBikeId()));
			controller.initData(order);
	
			Stage stage = (Stage) (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// handle pay update balance
	@FXML
	public void pay() {
		boolean isSuccess = false;
		// can check xem the da su dung cho tk nao chua
		// check length = 16 contain only digit
		if (name.getText() == "" || cardNum.getText() == "" || bank.getText() == ""
				|| card.validateDate(date.getValue()) || cardNum.getText().replaceAll("\\s", "").length() != 16
				|| !cardNum.getText().replaceAll("\\s", "").matches("[0-9]+")) {
			JOptionPane.showMessageDialog(null, "Enter full infomation and card number is 16 digit !");

		} else if (!TransactionUtils.checkTransact(cardNum.getText().replaceAll("\\s", ""),Integer.toString(order.getId()))) {
			
			JOptionPane.showMessageDialog(null, "You need to same card as first transaction");

		} else if (TransactionUtils.checkCard(cardNum.getText().replaceAll("\\s", ""),Integer.toString(order.getId()))) {
			
			JOptionPane.showMessageDialog(null, "Card in used");

		} else {
			 IBike iBike = new BikeDao();
			 bike = iBike.getBikeById(Integer.toString(order.getBikeId()));
			 
			 IUser iUser = new UserDao();
			 customer = iUser.getUserById(Integer.toString(order.getCustId()));
			// get input

			card = new CreditCard(bank.getText(), cardNum.getText().replaceAll("\\s", ""), date.getValue(),name.getText());
			PayService payService = new PayService(new PayByCard(card));// can luu the vao bang transaction
			formatDateTime = LocalDateTime.now().format(DateUtils.format);
                 
			if (order.getId() == 0) {
				// thread save transaction - function // to insert and update bike Table
				isSuccess = payService.pay(bike.getDeposit());  // rent
				if (isSuccess) {
					order.setTimeCreate(formatDateTime);
					threadRent();
					//threadEmail(bike.getDeposit());

				}
			} else if (order.getId() != 0) {
				isSuccess = payService.pay(bike.getDeposit(), order.getTotal());
				if (isSuccess) {
					threadReturn();  
					//threadEmail(order.getTotal());
				}
			}
			
			if (isSuccess) {
				System.out.print(card.getMoney());
				createResultPane();
			} else
				JOptionPane.showMessageDialog(null, "Not enough money");
		}

	}

	public void createResultPane() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/se/project/gui/pay/payResult.fxml"));
		try {
			Parent root = loader.load();
			
			ResultController result = loader.getController();

			result.setMsg(messageA.getText());
			if (order.getTimeFinish()==null) {
				result.setMoney(DateUtils.formatter.format(bike.getDeposit()));
			} else {
				result.setMoney(DateUtils.formatter.format(order.getTotal()));/// format
			}
			result.setTime(formatDateTime);
			result.setCustId(order.getCustId());
			Stage stage = (Stage) payBtn.getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		date.setEditable(false);
	}
	
	public void threadRent() {
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				TransactionUtils.saveTransacToDB(order);
				int orderId = iOrder.getOrderId(order.getCustId());
				order.setId(orderId);
				iTransaction.saveTransaction(orderId, messageA.getText(),bike.getDeposit(),
						cardNum.getText().replaceAll("\\s", ""));
			}

		});
		t2.start(); // end thread 2
		// tinh tien phan rent
	}
   
	public void threadReturn() {
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				iTransaction.saveTransaction(order.getId(), messageA.getText(), order.getTotal(),
						cardNum.getText().replaceAll("\\s", ""));

				/* TO DO IMPLEMENT ORDER.GETTOTAL */
				iTransaction.updateReturn(order.getId(), bike.getId(), order.getTotal(),
						order.getTimeFinish(), order.getReturnId()); // order.get total thay cho 0 // them returnId vao
				/* TO DO IMPLEMENT pick store return o ORDER CONTROLLER */
				iStore.updateStoreReturn(order.getBikeId(), Integer.valueOf(order.getReturnId())); // store id
																											// from
							 																				// return
																											// return ve
																											// store nao
				// order.setTimeFinish(formatDateTime); // chi tinh khi hoan thanh thanh toan
			}
		});
		t2.start(); // end thread 2

	}
	
	public void threadEmail(double money) {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() { // need to implement overall
				service = new EmailService();
				service.sendEmail(customer.getEmail(),bike.getName(), order.getTimeCreate(),
						money);
				
			}

		});
		t1.start(); // end thread
	}


}
