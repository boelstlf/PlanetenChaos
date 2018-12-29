package net.muriel.planeten;

public class MurielsPlanetenChaos {

	public static void main(String[] args) throws InterruptedException {

		PlanetenView planetenView = new PlanetenView();
		planetenView.setSize(1400, 800);
		planetenView.setLocation(50, 50);
		planetenView.setVisible(true);

		double t = 0.005; // Zeitscheiben in Sekunden

		Planet pama = new Planet("pama", t);
		pama.masse = 5.0E15;
		pama.vx = 0.0;
		pama.vy = -20;
		pama.posX = 700;
		pama.posY = 400;
		pama.size = 20;

		Planet suma = new Planet("suma", t);
		suma.masse = 5.0E15;
		suma.posX = 450;
		suma.posY = 300;
		suma.vx = 0;
		suma.vy = 30;
		suma.size = 20;

		Planet[] planets = { pama, suma };

		planetenView.setPlanetList(planets);

		while(true) {
			
			suma.calc(pama);
			pama.calc(suma);
			
/*			// Abstand zwischen den Planeten berechnen
			double rx = pama.posX - suma.posX;
			double ry = pama.posY - suma.posY;
			double r = Math.sqrt(rx * rx + ry * ry);

			// Beschleunigung durch Anziehungskraft
			double aPama = g * suma.masse / (r * r);
			// Geschwindigkeit aus dieser Beschleunigung
			double vPama = aPama * t;
			// Aufspaltung des Vektors in seine X,Y-Komponenten
			// und hinzuaddieren zu der bisherigen Geschwindigkeit
			// ergibt die neue Geschwindigkeitsrichtung
			pama.vx = pama.vx - vPama * rx / r;
			pama.vy = pama.vy - vPama * ry / r;
			// Berechnen der neuen Position
			pama.posX = pama.posX + pama.vx * t;
			pama.posY = pama.posY + pama.vy * t;
			System.out.println("Pama: x=" + pama.posX + "\t y=" + pama.posY + "\t vx=" + pama.vx + "\t vy=" + pama.vy);

			// die selben Berechnungen für Sunma
			double aSuma = g * pama.masse / (r * r);
			//
			double vSuma = aSuma * t;
			//
			suma.vx = suma.vx + vSuma * rx / r;
			suma.vy = suma.vy + vSuma * ry / r;
			// 
			suma.posX = suma.posX + suma.vx*t;
			suma.posY = suma.posY + suma.vy*t;
			System.out.println("Suma: x=" + suma.posX + "\t y=" + suma.posY + "\t vx=" + suma.vx + "\t vy=" + suma.vy);
*/
			System.out.println("--------------");

			planetenView.updatePlaneten();

			Thread.sleep(5);
		}

	}

}
