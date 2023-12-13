package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.asteroids.Asteroid;
import domain.asteroids.AsteroidFactory;
import domain.handlers.BuildingHandler;

public class MouseOverlapTest {
    //Bora Köken 71950
    //Worked on branch BTest

    
    Asteroid simpleAst;
    Asteroid firmAst;
    Asteroid expAst;
    Asteroid giftAst;
    BuildingHandler gh = BuildingHandler.getInstance();


    @BeforeEach
    void simpleAstCaller() {
        simpleAst = AsteroidFactory.getInstance().createSimple(); 
        simpleAst.setX(100);
        simpleAst.setY(100);
    }

    @BeforeEach
    void firmAstCaller() {
        firmAst = AsteroidFactory.getInstance().createFirm(); 
        firmAst.setX(100);
        firmAst.setY(100);
    }

    @BeforeEach
    void expAstCaller() {
        expAst = AsteroidFactory.getInstance().createExplosive(); 
        expAst.setX(100);
        expAst.setY(100);
    }

    @BeforeEach
    void giftAstCaller() {
        giftAst = AsteroidFactory.getInstance().createGift(); 
        giftAst.setX(100);
        giftAst.setY(100);
    }



    @Test
	public void trueTest() {
        assertTrue(gh.checkMouseOverlap(simpleAst, simpleAst.getX(), simpleAst.getY()));   // BB Corner Case    
        assertTrue(gh.checkMouseOverlap(simpleAst, 100, 100));                   
		assertTrue(gh.checkMouseOverlap(simpleAst, 105, 105));              /* BB True Cases */    
        assertTrue(gh.checkMouseOverlap(simpleAst, 110, 110)); 

        assertTrue(gh.checkMouseOverlap(firmAst, firmAst.getX(), firmAst.getY()));         // BB Corner Case    
        assertTrue(gh.checkMouseOverlap(firmAst, 100, 100));   
		assertTrue(gh.checkMouseOverlap(firmAst, 105, 105));                /* BB True Cases */     
        assertTrue(gh.checkMouseOverlap(firmAst, 110, 110)); 

        assertTrue(gh.checkMouseOverlap(expAst, expAst.getX(), expAst.getY()));            // BB Corner Case      
        assertTrue(gh.checkMouseOverlap(expAst, 100, 100));   
		assertTrue(gh.checkMouseOverlap(expAst, 105, 105));                 /* BB True Cases */    
        assertTrue(gh.checkMouseOverlap(expAst, 110, 110)); 

        assertTrue(gh.checkMouseOverlap(giftAst, giftAst.getX(), giftAst.getY()));         // BB Corner Case      
        assertTrue(gh.checkMouseOverlap(giftAst, 100, 100));   
		assertTrue(gh.checkMouseOverlap(giftAst, 105, 105));                /* BB True Cases */    
        assertTrue(gh.checkMouseOverlap(giftAst, 110, 110)); 
       
        assertTrue(gh.checkMouseOverlap(simpleAst, 130, 110));
        assertTrue(gh.checkMouseOverlap(firmAst, 105, 110));                /* GB True Cases */
        assertTrue(gh.checkMouseOverlap(expAst, 107, 108));
        assertTrue(gh.checkMouseOverlap(giftAst, 130, 110));
	

	}

    @Test
    public void falseTest() {
        assertFalse(gh.checkMouseOverlap(simpleAst, -1, -1));                                    
        assertFalse(gh.checkMouseOverlap(simpleAst, 10, 10));                     
        assertFalse(gh.checkMouseOverlap(simpleAst, 98, 98));                /* BB False Cases */    
        assertFalse(gh.checkMouseOverlap(simpleAst, 121, 121));                  
        assertFalse(gh.checkMouseOverlap(simpleAst, 9999, 9999));                

        assertFalse(gh.checkMouseOverlap(firmAst, -1, -1));  
        assertFalse(gh.checkMouseOverlap(firmAst, 10, 10));  
        assertFalse(gh.checkMouseOverlap(firmAst, 98, 98));                  /* BB False Cases */  
        assertFalse(gh.checkMouseOverlap(firmAst, 9999, 9999)); 

        assertFalse(gh.checkMouseOverlap(expAst, -1, -1)); 
        assertFalse(gh.checkMouseOverlap(expAst, 10, 10));    
        assertFalse(gh.checkMouseOverlap(expAst, 98, 98));                   /* BB False Cases */
        assertFalse(gh.checkMouseOverlap(expAst, 121, 121)); 
        assertFalse(gh.checkMouseOverlap(expAst, 99999, 9999)); 

        assertFalse(gh.checkMouseOverlap(giftAst, -1, -1)); 
        assertFalse(gh.checkMouseOverlap(giftAst, 10, 10));    
        assertFalse(gh.checkMouseOverlap(giftAst, 98, 98));                  /* BB False Cases */
        assertFalse(gh.checkMouseOverlap(giftAst, 121, 121)); 
        assertFalse(gh.checkMouseOverlap(giftAst, 99999, 9999)); 

        assertFalse(gh.checkMouseOverlap(simpleAst, 150, 110));                  
        assertFalse(gh.checkMouseOverlap(firmAst, 110, 80));                 /* GB False Cases */
        assertFalse(gh.checkMouseOverlap(expAst, 90, 115));
        assertFalse(gh.checkMouseOverlap(giftAst, 150, 110)); 
    }




   

}
