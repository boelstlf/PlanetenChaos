/**
 * 
 */
package net.muriel.planeten;

import java.util.Random;

/**
 * @author boelstlf
 *
 */
public class MultiplePlanetChaos {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {

		Random rand = new Random();
		PlanetenView planetenView = new PlanetenView();
		planetenView.setSize(1400, 800);
		planetenView.setLocation(10, 10);
		planetenView.setVisible(true);

		double t = 0.005; // Zeitscheiben in Sekunden
		// MAX 10
		int numPlanets = 5;

		Planet[] planets = new Planet[numPlanets];

		planets[0] = new Planet("Sun", t);
		planets[0].masse = 1.0E18;
		planets[0].vx = 0;
		planets[0].vy = 0;
		planets[0].posX = 700;
		planets[0].posY = 400;
		planets[0].size = 25;

		for (int i = 1; i < numPlanets; i++) {
			planets[i] = new Planet("planet" + i, t);
			int masse = (rand.nextInt(6) + 4);
			planets[i].masse = 1.0E13 * masse;
			planets[i].vx = plusminus(rand) * (rand.nextInt(100) + 150);
			planets[i].vy = plusminus(rand) * (rand.nextInt(100) + 150);
			planets[i].posX = 50 * (rand.nextInt(20) + 1);
			planets[i].posY = 50 * (rand.nextInt(10) + 1);
			planets[i].size = 3 * masse;
		}

		planetenView.setPlanetList(planets);

		while (true) {

			for (Planet planet : planets) {
				planet.calc(planets);
			}
			System.out.println("--------------");

			planetenView.updatePlaneten();

			Thread.sleep(50);
		}
	}
	
	public static int plusminus(Random rand)
	{
		if(rand.nextGaussian()<0)
			return -1;
		else
			return +1;
	}

}
