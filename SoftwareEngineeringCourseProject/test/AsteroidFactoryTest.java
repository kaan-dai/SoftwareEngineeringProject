package test;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.asteroids.Asteroid;
import domain.asteroids.AsteroidFactory;
import domain.asteroids.AsteroidList;
import domain.asteroids.ExplosiveAsteroid;
import domain.asteroids.FirmAsteroid;
import domain.asteroids.SimpleAsteroid;
import domain.handlers.BuildingHandler;


public class AsteroidFactoryTest {	
	// Duha Emir Ganioğlu 71753
	AsteroidList aList = AsteroidList.getInstance();
	AsteroidFactory aFact = AsteroidFactory.getInstance();
	BuildingHandler gh;
	Asteroid astSimple = new SimpleAsteroid();
	Asteroid astFirm = new FirmAsteroid();
	Asteroid astExp = new ExplosiveAsteroid();

	@BeforeEach
	void setUp(){
		String string1 = "80";
		String string2 = "80";
		String string3 = "80";
		String string4 = "80";
		gh = BuildingHandler.getInstance();
		gh.setNumbers(string1, string2, string3, string4);
		aList.addAsteroid(astSimple);
		aList.addAsteroid(astFirm);
		aList.addAsteroid(astExp);
		astExp.setX(10);
		astExp.setY(10);
		astSimple.setX(500);
		astSimple.setY(500);
		astFirm.setX(1000);
		astFirm.setY(1000);
	}

	@Test
	void createAsteroidTest() {
		assertNotNull(aFact.createExplosive());
		assertNotNull(aFact.createSimple());
		assertNotNull(aFact.createFirm());
	}

	
	@Test
	void SimpleBoundaryTest() {
		Asteroid astTestSimp = new SimpleAsteroid();
		assertTrue(aFact.checkAsteroidBoundaries(astTestSimp, 10,10)); //Center: (30,20)
		assertTrue(aFact.checkAsteroidBoundaries(astTestSimp, 510,510));
		assertFalse(aFact.checkAsteroidBoundaries(astTestSimp, 568,568));
		assertTrue(aFact.checkAsteroidBoundaries(astTestSimp, 1010,1010));
		assertFalse(aFact.checkAsteroidBoundaries(astTestSimp, 1080,1080));
	}


	@Test
	void FirmBoundaryTest() {
		Asteroid astTestFirm = new FirmAsteroid();
		assertTrue(aFact.checkAsteroidBoundaries(astTestFirm, 10,10));
		assertTrue(aFact.checkAsteroidBoundaries(astTestFirm, 510,510));
		assertFalse(aFact.checkAsteroidBoundaries(astTestFirm, 580,580));
		assertTrue(aFact.checkAsteroidBoundaries(astTestFirm, 1010,1010));
		assertFalse(aFact.checkAsteroidBoundaries(astTestFirm, 1080,1080));
	}


	@Test
	void ExplosiveBoundaryTest() {
		Asteroid astTestExp = new ExplosiveAsteroid();
		assertTrue(aFact.checkAsteroidBoundaries(astTestExp, 10,10));
		assertFalse(aFact.checkAsteroidBoundaries(astTestExp, 78,78));// Corner case
		assertTrue(aFact.checkAsteroidBoundaries(astTestExp, 510,510));
		assertFalse(aFact.checkAsteroidBoundaries(astTestExp, 568,568));// Corner case
		assertTrue(aFact.checkAsteroidBoundaries(astTestExp, 1010,1010));
		assertFalse(aFact.checkAsteroidBoundaries(astTestExp, 1068,1068));// Corner case		
	}


}