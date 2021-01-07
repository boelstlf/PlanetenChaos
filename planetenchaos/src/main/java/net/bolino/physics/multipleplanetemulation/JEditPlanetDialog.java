/**
 * 
 */
package net.bolino.physics.multipleplanetemulation;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author boelstlf
 *
 */
public class JEditPlanetDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel owner = null;
	public boolean buttonOK = false;
	public boolean isDelete = false;

	private GridBagLayout gridbagLayout = new GridBagLayout();

	private Planet planet;
	// edit settings
	private JLabel labelName = new JLabel("name:");
	private JTextField fieldName = new JTextField();
	private JLabel labelMass = new JLabel("mass:");
	private JTextField fieldMass = new JTextField();
	private JLabel labelMassUnit = new JLabel("kg");
	private JLabel labelPosX = new JLabel("posX:");
	private JTextField fieldPosX = new JTextField();
	private JLabel labelPosY = new JLabel("posY:");
	private JTextField fieldPosY = new JTextField();
	private JLabel labelPosZ = new JLabel("posZ:");
	private JTextField fieldPosZ = new JTextField();
	private JLabel labelVX = new JLabel("vx:");
	private JTextField fieldVX = new JTextField();
	private JLabel labelVxUnit = new JLabel("m/s");
	private JLabel labelVY = new JLabel("vy:");
	private JTextField fieldVY = new JTextField();
	private JLabel labelVyUnit = new JLabel("m/s");
	private JLabel labelVZ = new JLabel("vz:");
	private JTextField fieldVZ = new JTextField();
	private JLabel labelVzUnit = new JLabel("m/s");

	private JButton jButton_random = new JButton("randomize");
	private JButton jButton_color = new JButton("choose color");
	//
	JPanel panel_buttons = new JPanel();
	private JButton jButton_ok = new JButton("ok");
	private JButton jButton_cancel = new JButton("cancel");
	private JButton jButton_delete = new JButton("delete");

	public JEditPlanetDialog(JPanel owner, Planet planet) {
		this.owner = owner;
		// super(owner, "Edit Planet '" + planet.getName() + "'", true);
		this.planet = planet;
		fieldMass.setHorizontalAlignment(JTextField.RIGHT);
		fieldPosX.setHorizontalAlignment(JTextField.RIGHT);
		fieldPosY.setHorizontalAlignment(JTextField.RIGHT);
		fieldVX.setHorizontalAlignment(JTextField.RIGHT);
		fieldVY.setHorizontalAlignment(JTextField.RIGHT);
		fieldVZ.setHorizontalAlignment(JTextField.RIGHT);

		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		setSize(300, 425);
		this.setLocation(150, 150);
	}

	private void jButton_random_actionPerformed(ActionEvent e) {
		Random rand = new Random(System.currentTimeMillis());
		planet.setPx(9 * rand.nextGaussian() * 1e10);
		planet.setPy(9 * rand.nextGaussian() * 1e10);
		planet.setPz(9 * rand.nextGaussian() * 1e6);
		planet.setMass(Math.abs(9 * rand.nextGaussian() * 1e27));
		planet.setVx(9 * rand.nextGaussian() * 1e3);
		planet.setVy(9 * rand.nextGaussian() * 1e3);
		planet.setVz(7 * rand.nextGaussian() * 1e2);
		System.out.format("%s     \tpx=%e \tpy=%e \tpz=%e \tmass=%e%n", planet.getName(), planet.getPx(),
				planet.getPy(), planet.getPz(), planet.getMass());
		initFields();
		owner.updateUI();
	}

	private void jButton_delete_actionPerformed(ActionEvent e) {
		isDelete = true;
		setVisible(false);
	}
	
	private void jButton_color_actionPerformed(ActionEvent e)
	{
		Color color = JColorChooser.showDialog(null, 
	            "Color chooser", null);
		planet.setColor(color);
		owner.updateUI();
	}

	private void jButton_ok_actionPerformed(ActionEvent e) {
		buttonOK = true;
		planet.setMass(Double.parseDouble(fieldMass.getText().replace(",", ".")));
		planet.setPx(Double.parseDouble(fieldPosX.getText().replace(",", ".")));
		planet.setPy(Double.parseDouble(fieldPosY.getText().replace(",", ".")));
		planet.setPz(Double.parseDouble(fieldPosZ.getText().replace(",", ".")));
		planet.setVx(Double.parseDouble(fieldVX.getText().replace(",", ".")));
		planet.setVy(Double.parseDouble(fieldVY.getText().replace(",", ".")));
		planet.setVz(Double.parseDouble(fieldVZ.getText().replace(",", ".")));
		planet.setName(fieldName.getText());
		setVisible(false);
	}

	private void initFields() {
		// init values
		fieldName.setText(planet.getName());
		fieldMass.setText(String.format("%e", planet.getMass()));
		fieldPosX.setText(String.format("%e", planet.getPx()));
		fieldPosY.setText(String.format("%e", planet.getPy()));
		fieldPosZ.setText(String.format("%e", planet.getPz()));
		fieldPosZ.setHorizontalAlignment(JTextField.RIGHT);
		fieldVX.setText(String.format("%+8.0f", planet.getVx()));
		fieldVY.setText(String.format("%+8.0f", planet.getVy()));
		fieldVZ.setText(String.format("%+8.0f", planet.getVz()));
	}

	private void jButton_cancel_actionPerformed(ActionEvent e) {
		buttonOK = false;
		setVisible(false);
	}

	public void jbInit() throws Exception {
		this.setTitle("edit planet '" + planet.getName() + "'");
		this.getContentPane().setLayout(gridbagLayout);
		// ok
		jButton_ok.addActionListener(new java.awt.event.ActionListener() {
			/**
			 * Description of the Method
			 *
			 * @param e Description of Parameter
			 */
			public void actionPerformed(ActionEvent e) {
				jButton_ok_actionPerformed(e);
			}
		});
		// cancel
		jButton_cancel.addActionListener(new java.awt.event.ActionListener() {
			/**
			 * Description of the Method
			 *
			 * @param e Description of Parameter
			 */
			public void actionPerformed(ActionEvent e) {
				jButton_cancel_actionPerformed(e);
			}
		});
		// randomize
		jButton_random.addActionListener(new java.awt.event.ActionListener() {
			/**
			 * Description of the Method
			 *
			 * @param e Description of Parameter
			 */
			public void actionPerformed(ActionEvent e) {
				jButton_random_actionPerformed(e);
			}
		});
		// delete
		jButton_delete.addActionListener(new java.awt.event.ActionListener() {
			/**
			 * Description of the Method
			 *
			 * @param e Description of Parameter
			 */
			public void actionPerformed(ActionEvent e) {
				jButton_delete_actionPerformed(e);
			}
		});
		// color chooser
		jButton_color.addActionListener(new java.awt.event.ActionListener() {
			/**
			 * Description of the Method
			 *
			 * @param e Description of Parameter
			 */
			public void actionPerformed(ActionEvent e) {
				jButton_color_actionPerformed(e);
			}
		});

		this.initFields();
		// layout
		this.getContentPane().add(jButton_random, new GridBagConstraints(0, 0, 3, 1, 1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));
		this.getContentPane().add(jButton_color, new GridBagConstraints(0, 1, 3, 1, 1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));
		// settings
		this.getContentPane().add(labelName, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));
		this.getContentPane().add(fieldName, new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));
		this.getContentPane().add(labelMass, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));
		this.getContentPane().add(fieldMass, new GridBagConstraints(1, 3, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));
		this.getContentPane().add(labelMassUnit, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));
		this.getContentPane().add(labelPosX, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));
		this.getContentPane().add(fieldPosX, new GridBagConstraints(1, 4, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));
		this.getContentPane().add(labelPosY, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));
		this.getContentPane().add(fieldPosY, new GridBagConstraints(1, 5, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));
		this.getContentPane().add(labelPosZ, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));
		this.getContentPane().add(fieldPosZ, new GridBagConstraints(1, 6, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));

		this.getContentPane().add(labelVX, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));
		this.getContentPane().add(fieldVX, new GridBagConstraints(1, 7, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));
		this.getContentPane().add(labelVxUnit, new GridBagConstraints(2, 7, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));
		this.getContentPane().add(labelVY, new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));
		this.getContentPane().add(fieldVY, new GridBagConstraints(1, 8, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));
		this.getContentPane().add(labelVyUnit, new GridBagConstraints(2, 8, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));
		this.getContentPane().add(labelVZ, new GridBagConstraints(0, 9, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));
		this.getContentPane().add(fieldVZ, new GridBagConstraints(1, 9, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));
		this.getContentPane().add(labelVzUnit, new GridBagConstraints(2, 9, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));

		this.getContentPane().add(panel_buttons, new GridBagConstraints(0, 10, 3, 1, 1.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));
		panel_buttons.add(jButton_ok);
		panel_buttons.add(jButton_delete);
		panel_buttons.add(jButton_cancel);
	}
}
