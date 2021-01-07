/**
 * 
 */
package net.bolino.physics.multipleplanetemulation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

/**
 * @author boelstlf
 *
 */
public class MultiplePlanetEmulation extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Universe universe = new Universe();

	private JPanel panel_universeSettings = new JPanel();
	private FlowLayout layout_universeSettings = new FlowLayout();

	private JPanel panel_timeScale = new JPanel();
	private JLabel labelTimeScale = new JLabel("time step");
	private JTextField fieldTimeScale = new JTextField();
	private JLabel labelTimeScaleUnit = new JLabel("sec");
	private JButton plusTimeScale = new JButton("+");
	private JButton minusTimeScale = new JButton("-");

	private JPanel panel_geoScale = new JPanel();
	private JLabel labelGeoScale = new JLabel("zoom");
	private JTextField fieldGeoScale = new JTextField();
	private JLabel labelGeoScaleUnit = new JLabel("");
	private JButton plusGeoScale = new JButton("+");
	private JButton minusGeoScale = new JButton("-");
	private float zoomFactor = 1.0f;

	private JPanel panel_sleepStep = new JPanel();
	private JLabel labelSleepStep = new JLabel("calc speed");
	private JTextField fieldSleepStep = new JTextField();
	private JLabel labelSleepStepUnit = new JLabel("");
	private JButton plusSleepStep = new JButton("+");
	private JButton minusSleepStep = new JButton("-");
	private float calcSpeedFactor = 1.0f;

	private JPanel panel_buttons = new JPanel();
	private JButton buttonStart = new JButton("Start");
	private JButton buttonStop = new JButton("Stop");
	private JButton buttonRandom = new JButton("Random Init");
	private JButton buttonAddPlanet = new JButton("Add Planet");

	private JLabel dummy = new JLabel("   ");

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		MultiplePlanetEmulation emu = new MultiplePlanetEmulation();
		int width = Toolkit.getDefaultToolkit().getScreenSize().width - 100;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height - 100;
		emu.setSize(width, height);
		emu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		emu.setLocation(50, 50);
		emu.setVisible(true);

		// Sun
		Planet p1 = new Planet(emu.getUniverse(), "sun", 0, 0, 0, 1.989e30, 25);
		p1.setColor(Color.YELLOW);
		// Venus
		Planet p2 = new Planet(emu.getUniverse(), "venus", 1.08e11, 0, -1e9, 6.39e23, 10);
		p2.setColor(Color.MAGENTA);
		p2.setVy(-35020);
		// Earth
		Planet p3 = new Planet(emu.getUniverse(), "earth", 1.496e11, 0, 1e9, 5.972e24, 10);
		p3.setColor(Color.BLUE);
		p3.setVy(-29780);
		// Mars
		Planet p4 = new Planet(emu.getUniverse(), "mars", 2.279e11, 0, -1e9, 6.39e23, 10);
		p4.setColor(Color.RED);
		p4.setVy(-24130);
		// Jupiter
		Planet p5 = new Planet(emu.getUniverse(), "jupiter", 7.78e11, 0, 1e9, 1.898e27, 10);
		p5.setColor(Color.CYAN);
		p5.setVy(-13070);

		emu.getUniverse().addPlanet(p1);
		emu.getUniverse().addPlanet(p2);
		emu.getUniverse().addPlanet(p3);
		emu.getUniverse().addPlanet(p4);
		emu.getUniverse().addPlanet(p5);

		emu.getUniverse().setGeoScale(p5.getPx() * 2.5 / height);
	}

	public MultiplePlanetEmulation() {
		buttonStart.addActionListener(this);
		buttonStop.addActionListener(this);
		plusTimeScale.addActionListener(this);
		minusTimeScale.addActionListener(this);
		plusGeoScale.addActionListener(this);
		minusGeoScale.addActionListener(this);
		plusSleepStep.addActionListener(this);
		minusSleepStep.addActionListener(this);
		buttonAddPlanet.addActionListener(this);
		buttonRandom.addActionListener(this);

		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("action = " + e.getActionCommand());
		if (e.getSource() == buttonStart) {
			this.start();
		}
		if (e.getSource() == buttonStop) {
			this.stop();
		}
		if (e.getSource() == plusTimeScale) {
			this.changeTimeScale(true);
		}
		if (e.getSource() == minusTimeScale) {
			this.changeTimeScale(false);
		}
		if (e.getSource() == plusGeoScale) {
			this.changeGeoScale(true);
		}
		if (e.getSource() == minusGeoScale) {
			this.changeGeoScale(false);
		}
		if (e.getSource() == plusSleepStep) {
			this.changeSleepStep(true);
		}
		if (e.getSource() == minusSleepStep) {
			this.changeSleepStep(false);
		}
		if (e.getSource() == buttonRandom) {
			this.randomPlanetInit();
		}
		if (e.getSource() == buttonAddPlanet) {
			this.addPlanet();
		}
	}

	private void addPlanet() {
		Random rand = new Random(System.currentTimeMillis());
		Planet planet = new Planet(universe, "", 0, 0, 0, 0, 10);
		planet.setPx(3 * rand.nextGaussian() * 1e10);
		planet.setPy(3 * rand.nextGaussian() * 1e10);
		planet.setPz(0);
		planet.setMass(Math.abs(2 * rand.nextGaussian() * 1e27));
		planet.setVx(7 * rand.nextGaussian() * 1e4);
		planet.setVy(7 * rand.nextGaussian() * 1e4);
		planet.setVz(0);
		planet.setColor(Color.BLACK);
		int num = universe.addPlanet(planet);
		planet.setName("planet" + num);
		universe.updateUI();
		System.out.format("add planet '%s'     \tpx=%e \tpy=%e \tpz=%e \tmass=%e%n", planet.getName(), planet.getPx(),
				planet.getPy(), planet.getPz(), planet.getMass());
	}

	private void randomPlanetInit() {
		System.out.println("random planet init");
		// stop current run
		universe.setRunning(false);
		// init new planets with random values
		universe.randomPlanetInit();
		universe.updateUI();

	}

	private void changeSleepStep(boolean increase) {
		int sleepStep = universe.getSleepStep();
		if (!increase) {
			if (calcSpeedFactor > 0.05) {
				sleepStep *= 2;
				calcSpeedFactor /= 2;
			}
		} else {
			if (sleepStep > 1) {
				sleepStep /= 2;
				calcSpeedFactor *= 2;
			}
		}
		fieldSleepStep.setText(String.format("%2.2f", calcSpeedFactor));
		universe.setSleepStep(sleepStep);
	}

	private void changeTimeScale(boolean increase) {
		double timeScale = universe.getTimeScale();
		if (increase) {
			timeScale *= 2;
		} else {
			timeScale /= 2;
		}
		fieldTimeScale.setText(String.valueOf(timeScale));
		universe.setTimeScale(timeScale);
	}

	private void changeGeoScale(boolean increase) {
		double geoScale = universe.getGeoScale();
		if (!increase) {
			geoScale *= 1.1;
			zoomFactor /= 1.1;
		} else {
			if (geoScale > 1) {
				geoScale /= 1.1;
				zoomFactor *= 1.1;
			}
		}
		fieldGeoScale.setText(String.format("%2.2f", zoomFactor));
		universe.setGeoScale(geoScale);
		universe.updateUI();
	}

	private void jbInit() throws Exception {
		this.setTitle("Multi Planet Emulation");
		// init buttons
		buttonStop.setEnabled(false);
		// init settings
		fieldTimeScale.setText(String.format("%10.3f", universe.getTimeScale()));
		fieldGeoScale.setText(String.format("%2.2f", zoomFactor));
		fieldSleepStep.setText(String.format("%2.2f", calcSpeedFactor));
		fieldTimeScale.setEditable(false);
		fieldGeoScale.setEditable(false);
		fieldSleepStep.setEditable(false);
		// settings layout
		panel_universeSettings.setLayout(layout_universeSettings);
		// add time scale setting
		Border border_timeScale = BorderFactory.createCompoundBorder(
				new EtchedBorder(EtchedBorder.RAISED, Color.black, new Color(178, 140, 0)),
				BorderFactory.createEmptyBorder(1, 1, 1, 1));
		panel_timeScale.setBorder(border_timeScale);
		panel_timeScale.add(labelTimeScale);
		panel_timeScale.add(fieldTimeScale);
		panel_timeScale.add(labelTimeScaleUnit);
		panel_timeScale.add(plusTimeScale);
		panel_timeScale.add(minusTimeScale);
		// add time step setting
		Border border_sleepStep = BorderFactory.createCompoundBorder(
				new EtchedBorder(EtchedBorder.RAISED, Color.black, new Color(178, 140, 0)),
				BorderFactory.createEmptyBorder(1, 1, 1, 1));
		panel_sleepStep.setBorder(border_sleepStep);
		panel_sleepStep.add(labelSleepStep);
		panel_sleepStep.add(fieldSleepStep);
		panel_sleepStep.add(labelSleepStepUnit);
		panel_sleepStep.add(plusSleepStep);
		panel_sleepStep.add(minusSleepStep);
		// add geo scale setting
		Border border_geoScale = BorderFactory.createCompoundBorder(
				new EtchedBorder(EtchedBorder.RAISED, Color.black, new Color(178, 140, 0)),
				BorderFactory.createEmptyBorder(1, 1, 1, 1));
		panel_geoScale.setBorder(border_geoScale);
		panel_geoScale.add(labelGeoScale);
		panel_geoScale.add(fieldGeoScale);
		panel_geoScale.add(labelGeoScaleUnit);
		panel_geoScale.add(plusGeoScale);
		panel_geoScale.add(minusGeoScale);
		// action buttons
		Border border_buttons = BorderFactory.createCompoundBorder(
				new EtchedBorder(EtchedBorder.RAISED, Color.black, new Color(178, 140, 0)),
				BorderFactory.createEmptyBorder(1, 1, 1, 1));
		panel_buttons.setBorder(border_buttons);
		panel_buttons.add(buttonStart);
		panel_buttons.add(buttonStop);
		panel_buttons.add(buttonRandom);
		panel_buttons.add(buttonAddPlanet);

		panel_universeSettings.add(panel_timeScale);
		panel_universeSettings.add(panel_sleepStep);
		panel_universeSettings.add(panel_geoScale);
		panel_universeSettings.add(dummy);
		panel_universeSettings.add(panel_buttons);

		this.getContentPane().add(panel_universeSettings, BorderLayout.PAGE_START);
		this.getContentPane().add(universe, BorderLayout.CENTER);
	}

	private void start() {
		buttonRandom.setEnabled(false);
		buttonAddPlanet.setEnabled(false);
		buttonStart.setEnabled(false);
		buttonStop.setEnabled(true);
		universe.setRunning(true);
		Thread t1 = new Thread(universe);
		t1.start();
	}

	private void stop() {
		buttonRandom.setEnabled(true);
		buttonAddPlanet.setEnabled(true);
		buttonStart.setEnabled(true);
		buttonStop.setEnabled(false);

		universe.setRunning(false);
	}

	private Universe getUniverse() {
		return universe;
	}
}
