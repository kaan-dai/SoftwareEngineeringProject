package test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.controllers.BuildingController;



class AsteroidNumberTest {
	//Yesim Hizir 71836


	BuildingController bc;
	
	@BeforeEach
	void bcCaller() {
		//bc = new BuildingController();

	}

	@Test
	void trueTest() {

		assertTrue(bc.check("75", "10", "5", "10"));    // BB corner case
		assertTrue(bc.check("120", "30", "10", "20"));  // BB true case
	}
	@Test
	void falseTest() {
		
		assertFalse(bc.check("-5", "-2", "-1", "-4"));			// BB false case
		assertFalse(bc.check("74", "9", "4", "9")); 				// BB corner case
		assertFalse(bc.check("999999999999999999", "999999999999999999", "999999999999999999", "999999999999999999")); 		// GB Overflow
		assertFalse(bc.check("-999999999999999999", "-999999999999999999", "-999999999999999999", "-999999999999999999"));	// GB Underflow
		assertFalse(bc.check("nfe", "nfe", "nfe", "nfe"));			// GB NumberFormatException
		assertFalse(bc.check("", "", "", ""));						//GB NullPointerException
	}

}