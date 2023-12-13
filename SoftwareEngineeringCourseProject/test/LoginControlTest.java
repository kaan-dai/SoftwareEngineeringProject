package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.controllers.LoginController;

public class LoginControlTest {
    
    //Kaan Dai 71651
    
    LoginController logincontrol;
	
	@BeforeEach
	void logincontrolCaller() {
		logincontrol = new LoginController();

    }

    @Test
	void trueLoginTest() {

		assertTrue(logincontrol.loginCheck("kdai19","1234567")); // BB true case
        assertTrue(logincontrol.loginCheck("tonal19","123")); // BB true case
        assertTrue(logincontrol.loginCheck("yhizir19","1234")); // BB true case
		
	}

    @Test
	void falseLoginTest() {

		assertFalse(logincontrol.loginCheck("yhizir19","123")); // BB false case
        assertFalse(logincontrol.loginCheck("1234567","kdai19")); // BB false case
        assertFalse(logincontrol.loginCheck("dganioglu19","passwordnotknown")); // BB falase case
        assertFalse(logincontrol.loginCheck("123456789","123")); // BB false case
        assertFalse(logincontrol.loginCheck("*!'^+%&/()=?","bkoken19")); // BB false case
		
	}
    
}

