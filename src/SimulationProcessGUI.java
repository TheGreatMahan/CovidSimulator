/**
 * Program Name: filSimulationProcessGUI.java
 * Purpose:
 * Author: Golbahar Poirier-Mozun
 * Date: Jul. 31, 2021
 */

import javax.swing.*;
import java.awt.*;

public class SimulationProcessGUI extends JFrame
{
	
	public SimulationProcessGUI(int population, int dose0Percent, int dose1Percent, int doese2Percent, int naturalImmune)
	{
		//boilerplate
		super("Project 2 Epidemic Simulation");
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLayout(new FlowLayout() );//ANONYMOUS object
	    this.setSize(800,600);
	    this.setLocationRelativeTo(null);
	
	    //set background color of contentPane
	    this.getContentPane().setBackground(Color.CYAN);
	    
	    JMenuBar menubar = new JMenuBar();
	    this.setJMenuBar(menubar);
	    JMenu about = new JMenu("About");
	    JMenu power = new JMenu("Power");
	    menubar.add(about);
	    menubar.add(power);
	    JMenuItem mahan, olivia, michael, dylan, golbahar, start, stop;
	    mahan = about.add("Mahan Mehdipour");
	    olivia = about.add("Olivia Stemp");
	    michael = about.add("Michael Ivanov");
	    dylan = about.add("Dylan Mahyuddin");
	    golbahar = about.add("Golbahar Poirier-Mozun");
	    start = power.add("Start");
	    stop = power.add("Stop");
	    
			//Set the MNEMONICS for each menu so that they can be opened from the keyboard
	    about.setMnemonic('A');
	    power.setMnemonic('P');
	    
	
	    
	    
	    Dashboard dash = new Dashboard();
	    SimulationGUI simGui = new SimulationGUI(population, dose0Percent, dose1Percent, doese2Percent, naturalImmune, dash);
	    //create an ANONYMOUS object of the class and add the JPanel to the JFrame
	    
	    start.addActionListener(e -> simGui.resume());
	    stop.addActionListener(e -> simGui.pause());
	    
			//set some ACCELERATOR KEYS or HOT KEYS for new and open menu items
	    start.setAccelerator(KeyStroke.getKeyStroke('S', Event.CTRL_MASK)); // start
	    stop.setAccelerator(KeyStroke.getKeyStroke('E', Event.CTRL_MASK)); // end
	    
	    this.add(simGui);
	    this.add(dash);
	
	    this.pack();//shrinks the JFrame to the smallest size possible to conserve
	    //screen real estate. Comment it out to see its effect
	    this.setVisible(true);
	}
	//end main
}
 //end class