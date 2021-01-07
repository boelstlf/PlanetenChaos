/**
 * 
 */
package net.bolino.physics.multipleplanetemulation;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.Vector;

import javax.swing.JPanel;

/**
 * @author boelstlf
 *
 */
public class Universe extends JPanel implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final double GRAVITATION_CONST = 6.67E-11;
	private double timeScale = 64000; // seconds
	private double geoScale = 1.5e9; // distance scale
	private int sleepStep = 32;

	private int offsetX = 0;
	private int offsetY = 0;

	private boolean isRunning = false;

	private Vector<Planet> planets = new Vector<Planet>();

	public Universe() {
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				checkPlanets(me);
			}
		});
	}

	private void checkPlanets(MouseEvent me) {
		offsetX = this.getWidth() / 2;
		offsetY = this.getHeight() / 2;
		Planet editPlanet = null;
		for (Planet planet : planets) {
			int posX = (int) (planet.getPx() / geoScale) + offsetX;
			int posY = (int) (planet.getPy() / geoScale) + offsetY;

			if (posX > (me.getX() - 5) && posX < (me.getX() + 5) && posY > (me.getY() - 5) && posY < (me.getY() + 5)) {
				System.out.println("hit planet " + planet.getName());
				editPlanet = planet;
				break;
			}
		}
		if (editPlanet != null) {
			Planet c = editPlanet.clone();
			JEditPlanetDialog dialog = new JEditPlanetDialog(this, editPlanet);
			dialog.setModal(true);
			dialog.setVisible(true);
			if (dialog.isDelete) {
				this.delPlanet(editPlanet);
				this.updateUI();
			} else {
				if (dialog.buttonOK) {
					System.out.format(
							"edit planet '%s'     \tvx=%e \tvy=%e \tvz=%e \tpx=%e \tpy=%e \tpz=%e%n",
							editPlanet.getName(), editPlanet.getVx(), editPlanet.getVy(), editPlanet.getVz(),
							editPlanet.getPx(), editPlanet.getPy(), editPlanet.getPz());
					this.updateUI();
				} else {
					// cancel pressed, reset values to previous
					editPlanet.setName(c.getName());
					editPlanet.setPx(c.getPx());
					editPlanet.setPy(c.getPy());
					editPlanet.setPz(c.getPz());
					editPlanet.setVx(c.getVx());
					editPlanet.setVy(c.getVy());
					editPlanet.setVz(c.getVz());
					editPlanet.setMass(c.getMass());
				}
			}
		}
	}

	public void run() {
		while (isRunning) {
			for (Planet planet : planets) {
				planet.calcForce(planets);
			}
			for (Planet planet : planets) {
				planet.calcNewPosition(timeScale);
			}
			this.updateUI();

			try {
				Thread.sleep(sleepStep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public int addPlanet(Planet planet) {
		planets.add(planet);
		return planets.size();
	}

	public int delPlanet(Planet planet) {
		planets.remove(planet);
		return planets.size();
	}

	/**
	 * Update the panel.
	 *
	 * @param g graphics handler
	 */
	public synchronized void paintComponent(Graphics g) {
		offsetX = this.getWidth() / 2;
		offsetY = this.getHeight() / 2;

		for (Planet planet : planets) {
			int size = (int) (planet.getSize() * (planet.getPz() + 1.5 * geoScale) / (1.5 * geoScale));
			if (size < 1) {
				size = 1;
			}
			int posX = (int) (planet.getPx() / geoScale) + offsetX - size / 2;
			int posY = (int) (planet.getPy() / geoScale) + offsetY - size / 2;

			g.setColor(planet.getColor());
			g.fillOval(posX, posY, size, size);
		}

	}

	/**
	 * @return the timeScale
	 */
	public double getTimeScale() {
		return timeScale;
	}

	/**
	 * @param timeScale the timeScale to set
	 */
	public void setTimeScale(double timeScale) {
		this.timeScale = timeScale;
	}

	/**
	 * @return the geoScale
	 */
	public double getGeoScale() {
		return geoScale;
	}

	/**
	 * @param geoScale the geoScale to set
	 */
	public void setGeoScale(double geoScale) {
		this.geoScale = geoScale;
	}

	/**
	 * @return the sleepStep
	 */
	public int getSleepStep() {
		return sleepStep;
	}

	/**
	 * @param sleepStep the sleepStep to set
	 */
	public void setSleepStep(int sleepStep) {
		this.sleepStep = sleepStep;
	}

	/**
	 * @return the isRunning
	 */
	public boolean isRunning() {
		return isRunning;
	}

	/**
	 * @param isRunning the isRunning to set
	 */
	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public void randomPlanetInit() {
		Random rand = new Random(System.currentTimeMillis());
		//
		Planet planet = planets.get(0);
		planet.setPx(0);
		planet.setPy(0);
		planet.setPz(0);
		planet.setVx(0);
		planet.setVy(0);
		planet.setVz(0);
		for (int i = 1; i < planets.size(); i++) {
			planet = planets.get(i);
			planet.setPx(2 * rand.nextGaussian() * 1e11);
			planet.setPy(2 * rand.nextGaussian() * 1e11);
			planet.setPz(0);
			planet.setMass(Math.abs(7 * rand.nextGaussian() * 1e24));
			planet.setSize(10);
			planet.setVx(3 * rand.nextGaussian() * 1e4);
			planet.setVy(3 * rand.nextGaussian() * 1e4);
			planet.setVz(3 * rand.nextGaussian() * 1e1);
			System.out.format("%s     \tpx=%+20.3f \tpy=%+20.3f \tpz=%+20.3f \tmass=%+20.3f%n", planet.getName(),
					planet.getPx(), planet.getPy(), planet.getPz(), planet.getMass());
		}
	}
}
