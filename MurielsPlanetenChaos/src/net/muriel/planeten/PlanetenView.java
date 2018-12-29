package net.muriel.planeten;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class PlanetenView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GridBagLayout gridBagLayout = new GridBagLayout();
	private ScenePanel scene = null;
	
	public PlanetenView()
	{
		addWindowListener(new WindowAdapter() {
			/**
			 * Description of the Method
			 * 
			 * @param e
			 *            Description of Parameter
			 */
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setPlanetList(Planet[] planetList)
	{
		scene.setPlanetList(planetList);
	}
	
	public void updatePlaneten()
	{
		//scene.updatePlaneten(planets);
		scene.repaint();
	}

	private void jbInit() throws Exception {
		System.out.println("init");
		this.setTitle("Muriel Planeten Chaos");
		this.getContentPane().setLayout(gridBagLayout);
		scene = new ScenePanel();

		this.getContentPane().add(
				scene,
				new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(4, 4, 4, 4), 0, 0));
	}

}
