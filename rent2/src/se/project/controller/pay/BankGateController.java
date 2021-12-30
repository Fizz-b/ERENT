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
import se.project.database.api.bike.BikeDao;
import se.project.database.api.bike.IBike;
import se.project.database.api.order.IOrder;
import se.project.database.api.order.OrderDao;
import se.project.database.api.store.IStore;
import se.project.database.api.store.StoreDao;
import se.project.database.api.transaction.ITransaction;
import se.project.database.api.transaction.IValidTransact;
import se.project.database.api.transaction.TransactionDAO;
import se.project.database.api.transaction.ValidTransact;
import se.project.database.api.user.IUser;
import se.project.database.api.user.UserDao;
import se.project.interfaces.IMessageService;
import se.project.model.bike.BikeType;
import se.project.model.order.Order;
import se.project.model.payment.CreditCard;

import se.project.model.payment.PayByCard;
import se.project.model.payment.PayService;
import se.project.model.user.Customer;
import se.project.util.DateUtils;
import se.project.util.EmailService;

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
	private IMessageService service;

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
		if(validateCard()) {
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
			
            String money = null;
			if (order.getTimeFinish()==null) {
				money = DateUtils.formatter.format(bike.getDeposit());
			} else {
				money = DateUtils.formatter.format(order.getTotal());
			}
			
			result.initResultPane(money,messageA.getText() ,formatDateTime);
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
				ITransaction iTransact = new TransactionDAO();
				iTransact.saveTransacToDB(order);
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
				iStore.updateStoreReturn(order.getBikeId(), Integer.valueOf(order.getReturnId())); 
	
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

   public boolean validateCard() {
	   IValidTransact iCheck = new ValidTransact();
	   if (name.getText() == "" || cardNum.getText() == "" || bank.getText() == ""
				|| card.validateDate(date.getValue()) 
				|| cardNum.getText().replaceAll("\\s", "").length() != 16
				|| !cardNum.getText().replaceAll("\\s", "").matches("[0-9]+")) {
			JOptionPane.showMessageDialog(null, "Enter full infomation and card number is 16 digit !");
		}
	   
	   else if (!iCheck.checkSameCard(cardNum.getText().replaceAll("\\s", ""),Integer.toString(order.getId()))) { // check same card
			JOptionPane.showMessageDialog(null, "You need to same card as first transaction");
		} 
	   
	   else if (iCheck.checkCardUsed(cardNum.getText().replaceAll("\\s", ""),Integer.toString(order.getId()))) {// check cardUsed
			JOptionPane.showMessageDialog(null, "Card in used");
	   } else return true;
	   
	  return false;
   }
}
