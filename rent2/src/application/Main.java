package application;

import se.project.model.payment.CreditCard;
import se.project.model.payment.PayByCard;
import se.project.util.TransactionUtils;

public class Main {


  public static void main(String[] args) {
	  
	    int c= 2;
	     int a = c;
	    System.out.print(a);
	     a =3;
	    System.out.print(c);
		/*
		LocalDateTime now = LocalDateTime.now();  
        System.out.println("Before Formatting: " + now);  
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
        String formatDateTime = now.format(format);  
        System.out.println("After Formatting: " + formatDateTime);
        */
        CreditCard card = new CreditCard();
    	/*card.setName("a");
		card.setDate(LocalDate.now());
		card.setBank("a");
		card.setMoney(1000000);
		card.setCardNum("a");*/
	    /*
	       Payment pay = new Payment();
	       pay.selectPayment(new PayByCard(card));
		   
		   System.out.println(pay.payReturn(40,1200000));*/
    
  }
}
