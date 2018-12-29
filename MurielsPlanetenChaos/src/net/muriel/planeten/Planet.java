package net.muriel.planeten;

public class Planet {

	double timeScale = 0.005; // Zeitscheiben in Sekunden
	double g = 6.67E-11; // Gravitations-Konstante
	
	public String name = "";
	
	public double masse = 0; // in kg
	public double posX = 20;
	public double posY = 20;
	
	public double vx = 0.0;
	public double vy = 0.0;
	
	public int size = 5;
	
	/**
	 * Constructor to set timescale of each calculation cycle.
	 * 
	 * @param name Name of the planet
	 * @param timeScale time scale for each calc
	 */
	public Planet(String name, double timeScale)
	{
		this.name = name;
		this.timeScale= timeScale;
	}
	
	/**
	 * Call calc for all planets in the list, except if the planet is this planet.
	 * 
	 * @param planets
	 */
	public void calc(Planet[] planets)
	{
		for(Planet planet : planets)
		{
			if(planet.name != this.name)
			{
				this.calc(planet);
			}
		}
	}

	/**
	 * Calculation of force and acceleration vector based on the other planet and thus the new position.
	 * 
	 * @param otherPlanet
	 */
	public void calc(Planet otherPlanet)
	{
		// Abstand zwischen den Planeten berechnen
		double rx = this.posX - otherPlanet.posX;
		double ry = this.posY - otherPlanet.posY;
		double r = Math.sqrt(rx * rx + ry * ry);

		// Beschleunigung durch Anziehungskraft
		double a = g * otherPlanet.masse / (r * r);
		// Geschwindigkeit aus dieser Beschleunigung
		double v = a * timeScale;
		// Aufspaltung des Vektors in seine X,Y-Komponenten
		// und hinzuaddieren zu der bisherigen Geschwindigkeit
		// ergibt die neue Geschwindigkeitsrichtung
		this.vx = this.vx - v * rx / r;
		this.vy = this.vy - v * ry / r;
		// Berechnen der neuen Position
		this.posX = this.posX + this.vx * timeScale;
		this.posY = this.posY + this.vy * timeScale;
		System.out.println(name + ": x=" + this.posX + "\t y=" + this.posY + "\t vx=" + this.vx + "\t vy=" + this.vy);

	}
	
}
