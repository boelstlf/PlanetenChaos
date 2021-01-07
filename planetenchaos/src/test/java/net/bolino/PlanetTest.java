/**
 * 
 */
package net.bolino;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Assert;
import org.junit.Test;

import net.bolino.physics.multipleplanetemulation.Planet;

/**
 * @author boelstlf
 *
 */
public class PlanetTest {

	/**
	 * Test method for
	 * {@link net.bolino.physics.multipleplanetemulation.Planet#calcForce(java.util.Vector)}.
	 */
	@Test
	public void testCalcForceHorizontal() {
		// 1kg
		Planet p1 = new Planet("1kg", 6.371E6, 0, 0, 1, 10);
		// Earth
		Planet p2 = new Planet("earth", 0, 0, 0, 5.972e24, 10);

		Vector<Planet> planets = new Vector<Planet>();
		planets.add(p1);
		planets.add(p2);

		p1.calcForce(planets);
		p2.calcForce(planets);
		// test if calc force is correct
		Assert.assertEquals(-9.81, p1.getFx(), 0.009);
		Assert.assertEquals(9.81, p2.getFx(), 0.009);
	}

	@Test
	public void testCalcForce() {
		// 1kg
		Planet p1 = new Planet("1kg", 5.5174478E6, -3.1855E6, 0, 1, 10);
		// Earth
		Planet p2 = new Planet("earth", 0, 0, 0, 5.972e24, 10);

		Vector<Planet> planets = new Vector<Planet>();
		planets.add(p1);
		planets.add(p2);

		p1.calcForce(planets);
		p2.calcForce(planets);
		// test if calc force is correct
		Assert.assertEquals(9.81, Math.sqrt(p1.getFx() * p1.getFx() + p1.getFy() * p1.getFy()), 0.009);
		Assert.assertEquals(4.906, p1.getFy(), 0.009);
		Assert.assertEquals(9.81, Math.sqrt(p2.getFx() * p2.getFx() + p2.getFy() * p2.getFy()), 0.009);
		Assert.assertEquals(-4.906, p2.getFy(), 0.009);
	}

	/**
	 * Test method for
	 * {@link net.bolino.physics.multipleplanetemulation.Planet#calcNewPosition()}.
	 */
	@Test
	public void testCalcNewPosition() {
		fail("Not yet implemented");
	}

}
