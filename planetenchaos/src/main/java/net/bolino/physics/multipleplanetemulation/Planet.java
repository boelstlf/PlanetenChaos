/**
 * 
 */
package net.bolino.physics.multipleplanetemulation;

import java.awt.Color;
import java.util.Vector;

/**
 * @author boelstlf
 *
 */
public class Planet implements Cloneable{
	private String name;
	private double px;
	private double py;
	private double pz;
	private double mass;
	private int size;
	private Color color = Color.BLACK; // default

	private double vx;
	private double vy;
	private double vz;
	private double fx;
	/**
	 * @return the fx
	 */
	public double getFx() {
		return fx;
	}

	/**
	 * @return the fy
	 */
	public double getFy() {
		return fy;
	}

	/**
	 * @return the fz
	 */
	public double getFz() {
		return fz;
	}

	private double fy;
	private double fz;

	public Planet(String name, double px, double py, double pz, double mass, int size) {
		this.name = name;
		this.px = px;
		this.py = py;
		this.pz = pz;
		this.mass = mass;
		this.size = size;
	}

	public void calcForce(Vector<Planet> planets) {
		fx = 0;
		fy = 0;
		fz = 0;
		for (Planet planet : planets) {
			if (planet != this) {
				double dx = planet.getPx() - this.px;
				double dy = planet.getPy() - this.py;
				double dz = planet.getPz() - this.pz;
				double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);

				System.out.println("'" + name + "' \td=" + distance + "\tdx=" + dx + "\tdy=" + dy + "\tdz=" + dz);

				double F = Universe.GRAVITATION_CONST * (this.mass * planet.getMass()) / (distance * distance);

				fx = fx + F * dx / distance;
				fy = fy + F * dy / distance;
				fz = fz + F * dz / distance;
				System.out.println("'" + name + "' \tF=" + F + "\tfx=" + fx + "\tfy=" + fy + "\tdz=" + dz);
			}
		}
	}

	public void calcNewPosition(double timeScale) {
		double ax = fx / mass;
		double ay = fy / mass;
		double az = fz / mass;

		vx = vx + ax * timeScale;
		vy = vy + ay * timeScale;
		vz = vz + az * timeScale;

		px = px + vx * timeScale;
		py = py + vy * timeScale;
		pz = pz + vz * timeScale;
		System.out.format("%s     \tvx=%e \tvy=%e \tvz=%e \tpx=%e \tpy=%e \tpz=%e%n", name, vx, vy, vz, px, py, pz);
	}

	/**
	 * @return the px
	 */
	public double getPx() {
		return px;
	}

	/**
	 * @return the py
	 */
	public double getPy() {
		return py;
	}

	/**
	 * @return the pz
	 */
	public double getPz() {
		return pz;
	}

	/**
	 * @param px the px to set
	 */
	public void setPx(double px) {
		this.px = px;
	}

	/**
	 * @param py the py to set
	 */
	public void setPy(double py) {
		this.py = py;
	}

	/**
	 * @param mass the mass to set
	 */
	public void setMass(double mass) {
		this.mass = mass;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @param pz the pz to set
	 */
	public void setPz(double pz) {
		this.pz = pz;
	}

	/**
	 * @return the mass
	 */
	public double getMass() {
		return mass;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param vx the vx to set
	 */
	public void setVx(double vx) {
		this.vx = vx;
	}

	/**
	 * @param vy the vy to set
	 */
	public void setVy(double vy) {
		this.vy = vy;
	}

	/**
	 * @param vz the vz to set
	 */
	public void setVz(double vz) {
		this.vz = vz;
	}

	/**
	 * @return the vx
	 */
	public double getVx() {
		return vx;
	}

	/**
	 * @return the vy
	 */
	public double getVy() {
		return vy;
	}

	/**
	 * @return the vz
	 */
	public double getVz() {
		return vz;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	  @Override
	  public Planet clone()
	  {
	    try
	    {
	      return (Planet) super.clone();
	    }
	    catch ( CloneNotSupportedException e ) {

	      throw new InternalError();
	    }
	  }
}
