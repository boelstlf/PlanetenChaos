package net.muriel.planeten;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

public class ScenePanel extends Panel {

	private Image offscreen;
	private Dimension offscreensize;
	private Graphics offgraphics;
	private Planet[] planetList = null;
	private final Color[] planetColors = { Color.YELLOW, Color.BLUE, Color.CYAN, Color.RED, Color.GREEN, Color.PINK,
			Color.ORANGE, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.MAGENTA };

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void setPlanetList(Planet[] planetList) {
		this.planetList = planetList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#update(java.awt.Graphics)
	 */
	public synchronized void update(Graphics g) {
		if (planetList == null) {
			System.out.println("no planet to draw");
			return;
		}
		System.out.println("update scene");
		Dimension d = getSize();
		if ((offscreen == null) || (d.width != offscreensize.width) || (d.height != offscreensize.height)) {
			offscreen = createImage(d.width, d.height);
			offscreensize = d;
			offgraphics = offscreen.getGraphics();
		}

		offgraphics.setColor(getBackground());
		offgraphics.fillRect(0, 0, d.width, d.height);

		// draw objects
		int index = 0;
		for (Planet planet : planetList) {
			offgraphics.setColor(planetColors[index]);
			offgraphics.fillOval((int) planet.posX, (int) planet.posY, planet.size, planet.size);
			index++;
		}

		g.drawImage(offscreen, 0, 0, null);
	}

}
