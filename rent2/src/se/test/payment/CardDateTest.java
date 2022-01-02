package se.test.payment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import se.project.model.payment.CreditCard;
public class CardDateTest {
    CreditCard card;
    @BeforeEach                                         
    void setUp() {
        card = new CreditCard();
    }
    
    // Equivalence Partitioning
    @Test                                               
    @DisplayName("Check invalid date")   
    void testInvalidDate() {
        assertEquals(true,card.validateDate(LocalDate.of(2020, Month.JANUARY, 8)),     
                "InvalidDate");  
    }
    
    @Test                                               
    @DisplayName("Check valid date")   
    void testValidDate() {
        assertEquals(false,card.validateDate(LocalDate.of(2023, Month.DECEMBER, 10)),     
                "ValidDate");  
    }
} 
