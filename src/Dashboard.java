/**
 * Program Name: filDashboard.java
 * Purpose:
 * Author: Golbahar Poirier-Mozun
 * Date: Jul. 30, 2021
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;


public class Dashboard extends JPanel
{

	public Label lblNumInfected;
  public Label lblNumNonVax;
  public Label lblNumPartVax;
  public Label lblNumFullVax;
  public Label lblNumRecovered;
  public Label lblNumDied;
	//SimulationGUI gui;
	public Dashboard()
	{
		//this.gui = gui;
		
		this.setLayout(new FlowLayout());
		this.setPreferredSize(new Dimension(200, 600));
		JPanel stats = new JPanel(new GridLayout(6, 2, 10, 5));
		stats.setPreferredSize(new Dimension(200, 400));
    stats.setBackground(Color.lightGray);
    stats.setBorder(new EmptyBorder(0, 2, 0, 2));
    
    JPanel legend = new JPanel(new GridLayout(6, 1, 70, 10));
    legend.setMaximumSize(new Dimension(200, 200));
    legend.setMinimumSize(new Dimension(200, 200));

    //stats.setBorder(new EmptyBorder(0, 0, 180, 0));
    /*
     * 1)	Number of infected persons.
2)	Number of non-vaccinated persons infected.
3)	Number of partially-vaccinated people infected.
4)	Number of fully-vaccinated people infected.
5)	Number of infected people who have recovered.
6)	Number of infected people who have died.


     */
    Font font = new Font("Calibri", Font.BOLD, 16);
    
    JLabel lblInfected = new JLabel("<html>Number of infected people</html>");
    //lblInfected.setMaximumSize(new Dimension(200, 20));
    //Label lblInfects = new Label(this, SWT.WRAP);
    JLabel lblNonVax = new JLabel("<html>Number of non-vaccinated people infected</html>");
    JLabel lblPartVax = new JLabel("<html>Number of partially- vaccinated people infected</html>");
    JLabel lblFullVax = new JLabel("<html>Number of fully-vaccinated people infected</html>");
    JLabel lblRecovered = new JLabel("<html>Number of infected people who have recovered</html>");
    JLabel lblDied = new JLabel("<html>Number of infected people who have died</html>");
    lblNumInfected = new Label("");
    lblNumInfected.setFont(font);
    lblNumNonVax = new Label("");
    lblNumNonVax.setFont(font);
    lblNumPartVax = new Label("");
    lblNumPartVax.setFont(font);
    lblNumFullVax = new Label("");
    lblNumFullVax.setFont(font);
    lblNumRecovered = new Label("");
    lblNumRecovered.setFont(font);
    lblNumDied = new Label("");
    lblNumDied.setFont(font);
    //lblNumPartVax.setMaximumSize(new Dimension(20, 20));
    
    stats.add(lblInfected);
    stats.add(lblNumInfected);
    stats.add(lblNonVax);
    stats.add(lblNumNonVax);
    stats.add(lblPartVax);
    stats.add(lblNumPartVax);
    stats.add(lblFullVax);
    stats.add(lblNumFullVax);
    stats.add(lblRecovered);
    stats.add(lblNumRecovered);
    stats.add(lblDied);
    stats.add(lblNumDied);
    
    
    Label lblTypeInfected = new Label("Infected");
    Label lblTypeUnVax = new Label("Un-Vaccinated");
    Label lblTypePartVax = new Label("Partially-Vaccinated");
    Label lblTypeFullVax = new Label("Fully-Vaccinated");
    Label lblNaturallyImmune = new Label("Naturally-Immune");
    Label lblTypeDead = new Label("Dead");
    

    legend.add(lblTypeUnVax);
    legend.add(lblTypePartVax);
    legend.add(lblTypeFullVax);
    legend.add(lblNaturallyImmune);
    legend.add(lblTypeInfected);
    legend.add(lblTypeDead);
    this.add(stats);
    this.add(legend);
    		
    //repaint();
	}
	
	public void paintComponent(Graphics g) {

    super.paintComponent(g);

		Color [] colours = {Color.BLUE, Color.CYAN, Color.ORANGE, Color.GREEN, Color.RED, Color.BLACK};
    //gets the color of each person and then paints it
    for (int i= 0; i < colours.length; i++) {
        g.setColor(colours[i]);
        g.fillOval(15, 410 + i * 32, 19, 19);
    }

}
	//end main
}
 //end class