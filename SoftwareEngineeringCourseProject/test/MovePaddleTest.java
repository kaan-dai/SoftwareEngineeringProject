package test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.objects.Paddle;

public class MovePaddleTest {

    //Tuna Onal 72024
  
double a,b,c,d,e,f;
    Paddle paddle = new Paddle(); 

    @BeforeEach
    void setUp() {
        //AsteroidFactory.getInstance();
        a = paddle.getCenterX();
        paddle.move(2);  
        b = paddle.getCenterX();     

        c = paddle.getCenterX();
        paddle.move(1);
        d = paddle.getCenterX();

        paddle.move(3);
        e = paddle.getCenterX();
        paddle.move(-1);
        e = paddle.getCenterX();

    }


    @Test
    void movePaddleTest() {
       
       
        assertEquals(a + 15,b);  //GB Testing for right movement of the paddle
        assertEquals(c - 15,d);  //GB Testing for Left Movement of the paddle
        assertEquals(e, d);      //BB Testing for if an invalid input is given (bigger than 0)
        assertEquals(e, d);      //BB Testing for if an invalid input is given (less then 0)   
        assertNotNull(paddle);   //BB Testing for if the paddle is null

        
       
    }

}
