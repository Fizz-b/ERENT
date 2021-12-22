package se.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import se.project.database.Context;
import se.project.database.MySQLConnection;

import se.project.util.TransactionUtils;

public class CardUseTest {

	@BeforeEach
	void setUp() {
		 Context.selectDataBase(new MySQLConnection());
	}
	// test using parameter
	@ParameterizedTest
	@ValueSource(strings = { "1234567891111111", "1234567891111112" })
	@DisplayName("Check card used")
	void testCard(String card) {
		assertEquals(false,TransactionUtils.checkCard(card, "1") , "Card have been used");
		assertEquals(true,TransactionUtils.checkTransact(card, "1") , "Card have been used");
	}
}
