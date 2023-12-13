package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import domain.asteroids.Asteroid;
import domain.asteroids.AsteroidFactory;
import domain.asteroids.AsteroidList;
import domain.asteroids.ExplosiveAsteroid;
import domain.asteroids.FirmAsteroid;
import domain.asteroids.GiftAsteroid;
import domain.asteroids.SimpleAsteroid;

public class GroupTest {
    // Tests for AsteroidList.

    AsteroidList aList = AsteroidList.getInstance();

    Asteroid simple= new SimpleAsteroid();
    Asteroid firm = new FirmAsteroid();

    Asteroid exp = new ExplosiveAsteroid();
    Asteroid gift = new GiftAsteroid();

    AsteroidFactory factory = AsteroidFactory.getInstance();

        @BeforeEach
        void setUp() {

            simple.setX(100);
            simple.setY(100);
            aList.addAsteroid(simple);

            firm.setX(150);
            firm.setY(150);
            aList.addAsteroid(firm);

            exp.setX(200);
            exp.setY(200);
            aList.addAsteroid(exp);

            exp.setX(300);
            exp.setY(300);
            aList.addAsteroid(gift);
        }

        /*
        @BeforeEach
        void simpleAstCaller() {
            simple = new SimpleAsteroid();
            aList.addAsteroid(simple);
            simple.setX(100);
            simple.setY(100);
        }
    
        @BeforeEach
        void firmAstCaller() {
            firm = new FirmAsteroid();
            aList.addAsteroid(firm);
            firm.setX(100);
            firm.setY(100);
        }
    
        @BeforeEach
        void expAstCaller() {
            exp = new ExplosiveAsteroid();
            aList.addAsteroid(exp);
            exp.setX(100);
            exp.setY(100);
        }

        @BeforeEach
        void giftAstCaller() {
            gift = new GiftAsteroid();
            aList.addAsteroid(gift);
            exp.setX(100);
            exp.setY(100);
        }
        */
        

        @AfterEach
        void test() {
            assertTrue(aList.repOk());
        }

        @Test
        public void testAdd(){
            //simple.aa();
            assertTrue(aList.repOk());
            aList.addAsteroid(simple);
            assertTrue(aList.inList(simple));
            assertTrue(aList.repOk());

            aList.addAsteroid(firm);
            assertTrue(aList.inList(firm));
            assertTrue(aList.repOk());

            aList.addAsteroid(exp);
            assertTrue(aList.inList(exp));
            assertTrue(aList.repOk());

            aList.addAsteroid(gift);
            assertTrue(aList.inList(gift));
            assertTrue(aList.repOk()); 
        }

        @Test
        public void testSetters(){
            aList.setSimple(20);
            assertEquals(aList.getSimple(), 20);

            aList.setFirm(20);
            assertEquals(aList.getFirm(), 20);

            aList.setExplosive(20);
            assertEquals(aList.getExplosive(), 20);

            aList.setGift(20);
            assertEquals(aList.getGift(), 20);
        }

        @Test
        public void testinFrame() {
            assertTrue(aList.isInFrame(simple));
            simple.setX(-3);
            assertFalse(aList.isInFrame(simple));   
        }
    
        @Test
        public void testRemove(){
            // assertFalse(aList.repOk());

            // aList.remove(simple);
            assertFalse(aList.inList(simple));

            aList.remove(firm);
            assertFalse(aList.inList(firm));

            aList.remove(exp);
            assertFalse(aList.inList(exp));

            aList.remove(gift);
            assertFalse(aList.inList(gift));
        }

        @Test
        public void nullPointerException(){
            // assertFalse(aList.repOk());
		    assertThrows(NullPointerException.class,() -> {
			aList.addAsteroid(null);
		});
        }
        

}
