package application;
	
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.application.Application;
import javafx.stage.Stage;
import se.project.model.payment.CreditCard;
import se.project.model.payment.PayByCard;
import se.project.model.payment.Payment;
import se.project.util.TransactionUtils;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main {
	

	
	public static void main(String[] args) {
		LocalDateTime now = LocalDateTime.now();  
        System.out.println("Before Formatting: " + now);  
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
        String formatDateTime = now.format(format);  
        System.out.println("After Formatting: " + formatDateTime);
        
        CreditCard card = new CreditCard();
    	card.setName("a");
		card.setDate(LocalDate.now());
		card.setBank("a");
		card.setMoney(1000000);
		card.setCardNum("a");
	    
	       Payment pay = new Payment();
	       pay.selectPayment(new PayByCard(card));
		   pay.payRent(400000);
		   System.out.print(card.getMoney());
		
	}
}
