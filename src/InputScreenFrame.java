/**
 * Program Name: InputScreenFrame.java
 * Purpose: Allows the user to input the simulation values
 * Author: Dylan Mahyuddin
 * Date: Jul. 31, 2021
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class InputScreenFrame extends JFrame
{
	// CLASS SCOPE VARIABLES HERE
	private JLabel dose0Label, dose1Label, dose2Label, naturalLabel, titleLabel;
	private JTextField sizeField;
	private JButton numDose0ButtonUp, numDose1ButtonUp, numDose2ButtonUp, numNaturalButtonUp, numDose0ButtonDown,
			numDose1ButtonDown, numDose2ButtonDown, numNaturalButtonDown;
	private JComboBox incrementalBox;
	private JPanel orderviewPanel, inputPanel, pencentagePanel, sizePanel, buttonPanel, titlePanel, decisionPanel,
			incrementPanel;
	public JButton startButton, clearButton;
	Container contentPane;
	String[] increment = { "1", "5", "10", "100" };
	JFrame frame = this;

	public InputScreenFrame()
	{
		// boilerplate code
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setSize(800, 350);
		this.setLocationRelativeTo(null);


		// Set up the JPanel
		titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		titlePanel.setBorder(new EmptyBorder(20, 2, 0, 2));
		orderviewPanel = new JPanel();
		orderviewPanel.setLayout(new GridLayout(1, 2));
		orderviewPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(2, 1));
		inputPanel.setBorder(new EmptyBorder(0, 5, 0, 0));
		decisionPanel = new JPanel();
		decisionPanel.setLayout(new GridLayout(2, 1));
		decisionPanel.setBorder(new EmptyBorder(0, 5, 0, 0));
		pencentagePanel = new JPanel();
		pencentagePanel.setLayout(new GridLayout(5, 4));
		pencentagePanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		sizePanel = new JPanel();
		sizePanel.setLayout(new GridLayout(1, 2));
		sizePanel.setBorder(new EmptyBorder(100, 0, 0, 0));
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(2, 1));
		buttonPanel.setBorder(new EmptyBorder(0, 0, 31, 0));
		incrementPanel = new JPanel();
		incrementPanel.setLayout(new GridLayout(1, 2));
		incrementPanel.setBorder(new EmptyBorder(100, 0, 0, 0));
		
		//set up menu
    JMenuBar menubar = new JMenuBar();
    this.setJMenuBar(menubar);
    JMenu about = new JMenu("About");
    menubar.add(about);
    JMenuItem mahan, olivia, michael, dylan, golbahar, start, stop;
    mahan = about.add("Mahan Mehdipour");
    olivia = about.add("Olivia Stemp");
    michael = about.add("Michael Ivanov");
    dylan = about.add("Dylan Mahyuddin");
    golbahar = about.add("Golbahar Poirier-Mozun");
    
		//Set the MNEMONICS for each menu so that they can be opened from the keyboard
    about.setMnemonic('A');
    	

		// Set up the JLabel
		titleLabel = new JLabel("The Infected Coders' Simulation");
		//System.out.println(titleLabel.getFont());
		titleLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		
		JLabel totalSize = new JLabel("<html>Total Population:</html>");
		JLabel peopleWith0 = new JLabel("<html>% of people with 0 dose:</html>");
		JLabel peopleWith1 = new JLabel("<html>% of people with 1 dose:</html>");
		JLabel peopleWith2 = new JLabel("<html>% of people with 2 dose:</html>");
		JLabel peopleWithNatural = new JLabel("<html>% of people with Natural Immunity:</html>");
		JLabel increaseDecrease = new JLabel("<html>Add or Minus By:</html>");

		dose0Label = new JLabel("0");
		dose1Label = new JLabel("0");
		dose2Label = new JLabel("0");
		naturalLabel = new JLabel("0");

		// Set up the field
		sizeField = new JTextField();
		// Set up the JButton
		numDose0ButtonUp = new JButton("+");
		numDose1ButtonUp = new JButton("+");
		numDose2ButtonUp = new JButton("+");
		numNaturalButtonUp = new JButton("+");
		numDose0ButtonDown = new JButton("-");
		numDose1ButtonDown = new JButton("-");
		numDose2ButtonDown = new JButton("-");
		numNaturalButtonDown = new JButton("-");

		startButton = new JButton("Start");
		clearButton = new JButton("Clear");

		// Set up the JComboBox
		incrementalBox = new JComboBox(increment);

		// add Listeners
		clearButton.addActionListener(new ButtonHandler());
		startButton.addActionListener(new ButtonHandler());

		numDose0ButtonUp.addActionListener(new ButtonHandler());
		numDose1ButtonUp.addActionListener(new ButtonHandler());
		numDose2ButtonUp.addActionListener(new ButtonHandler());
		numNaturalButtonUp.addActionListener(new ButtonHandler());
		numDose0ButtonDown.addActionListener(new ButtonHandler());
		numDose1ButtonDown.addActionListener(new ButtonHandler());
		numDose2ButtonDown.addActionListener(new ButtonHandler());
		numNaturalButtonDown.addActionListener(new ButtonHandler());

		// add to the size
		sizePanel.add(totalSize);
		sizePanel.add(sizeField);

		// add to the Percentages
		pencentagePanel.add(peopleWith0);
		pencentagePanel.add(dose0Label);
		pencentagePanel.add(numDose0ButtonUp);
		pencentagePanel.add(numDose0ButtonDown);
		pencentagePanel.add(peopleWith1);
		pencentagePanel.add(dose1Label);
		pencentagePanel.add(numDose1ButtonUp);
		pencentagePanel.add(numDose1ButtonDown);
		pencentagePanel.add(peopleWith2);
		pencentagePanel.add(dose2Label);
		pencentagePanel.add(numDose2ButtonUp);
		pencentagePanel.add(numDose2ButtonDown);
		pencentagePanel.add(peopleWithNatural);
		pencentagePanel.add(naturalLabel);
		pencentagePanel.add(numNaturalButtonUp);
		pencentagePanel.add(numNaturalButtonDown);

		// add to the button
		buttonPanel.add(startButton);
		buttonPanel.add(clearButton);

		// add to the increment
		incrementPanel.add(increaseDecrease);
		incrementPanel.add(incrementalBox);

		// add to the input
		inputPanel.add(incrementPanel);
		inputPanel.add(pencentagePanel);

		// add to the decisions
		decisionPanel.add(sizePanel);
		decisionPanel.add(buttonPanel);

		// add to the overview
		orderviewPanel.add(inputPanel);
		orderviewPanel.add(decisionPanel);

		// add to the title
		titlePanel.add(titleLabel);

		// add everything
		this.add(titlePanel, BorderLayout.NORTH);
		this.add(orderviewPanel, BorderLayout.SOUTH);
		this.setVisible(true);
		// String comboSelected = (String) incrementBox.getSelectedItem();

	}

	// CREATE NESTED INNER CLASS HERE
	private class ButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String comboSelected = (String) incrementalBox.getSelectedItem();
			int numberSelected = Integer.parseInt(comboSelected);

			
			if (e.getSource().equals(clearButton))
			{
				dose0Label.setText("0");
				dose1Label.setText("0");
				dose2Label.setText("0");
				naturalLabel.setText("0");
				sizeField.setText("");
			} 
			else if (e.getSource().equals(startButton))
			{
				int population;
				
				if( Integer.parseInt(dose0Label.getText())+ Integer.parseInt(dose1Label.getText())+
							Integer.parseInt(dose2Label.getText())+ Integer.parseInt(naturalLabel.getText()) <= 100) {
					
					try {	
								int result = JOptionPane.showConfirmDialog(contentPane, "Confirm your choice");
								if(result == JOptionPane.YES_OPTION){
									new SimulationProcessGUI(Integer.parseInt(sizeField.getText()), Integer.parseInt(dose0Label.getText()), Integer.parseInt(dose1Label.getText()),
											Integer.parseInt(dose2Label.getText()), Integer.parseInt(naturalLabel.getText()));
											frame.setVisible(false);
		            }
								
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(contentPane, "Error: NAN please put in a Number for the population");
				
					}
					
				}
				else {
					JOptionPane.showMessageDialog(contentPane, "Error: Please make sure that the percentages entered add up to 100 or less");
				}

			} 
			
			// add and minus
			else if (e.getSource().equals(numDose0ButtonUp))
			{
				if (Integer.parseInt(dose0Label.getText()) + numberSelected > 100)
				{
					JOptionPane.showMessageDialog(contentPane, "Error: number must not be higher than 100%");
				} 
				else
					dose0Label.setText(Integer.toString(Integer.parseInt(dose0Label.getText()) + numberSelected));
			} 
			else if (e.getSource().equals(numDose1ButtonUp))
			{
				if (Integer.parseInt(dose1Label.getText()) + numberSelected > 100)
				{
					JOptionPane.showMessageDialog(contentPane, "Error: number must not be higher than 100%");
				} 
				else
					dose1Label.setText(Integer.toString(Integer.parseInt(dose1Label.getText()) + numberSelected));
			} 
			else if (e.getSource().equals(numDose2ButtonUp))
			{
				if (Integer.parseInt(dose2Label.getText()) + numberSelected > 100)
				{
					JOptionPane.showMessageDialog(contentPane, "Error: number must not be higher than 100%");
				} 
				else
					dose2Label.setText(Integer.toString(Integer.parseInt(dose2Label.getText()) + numberSelected));
			} 
			else if (e.getSource().equals(numNaturalButtonUp))
			{
				if (Integer.parseInt(naturalLabel.getText()) + numberSelected > 100)
				{
					JOptionPane.showMessageDialog(contentPane, "Error: number must not be higher than 100%");
				} 
				else
					naturalLabel.setText(Integer.toString(Integer.parseInt(naturalLabel.getText()) + numberSelected));
			} 
			else if (e.getSource().equals(numDose0ButtonDown))
			{
				if (Integer.parseInt(dose0Label.getText()) - numberSelected < 0)
				{
					JOptionPane.showMessageDialog(contentPane, "Error: number must not be Lower than 0%");
				} 
				else
					dose0Label.setText(Integer.toString(Integer.parseInt(dose0Label.getText()) - numberSelected));

			} 
			else if (e.getSource().equals(numDose1ButtonDown))
			{
				if (Integer.parseInt(dose1Label.getText()) - numberSelected < 0)
				{
					JOptionPane.showMessageDialog(contentPane, "Error: number must not be Lower than 0%");
				} 
				else
					dose1Label.setText(Integer.toString(Integer.parseInt(dose1Label.getText()) - numberSelected));
			} 
			else if (e.getSource().equals(numDose2ButtonDown))
			{
				if (Integer.parseInt(dose2Label.getText()) - numberSelected < 0)
				{
					JOptionPane.showMessageDialog(contentPane, "Error: number must not be Lower than 0%");
				} 
				else
					dose2Label.setText(Integer.toString(Integer.parseInt(dose2Label.getText()) - numberSelected));
			} 
			else if (e.getSource().equals(numNaturalButtonDown))
			{
				if (Integer.parseInt(naturalLabel.getText()) - numberSelected < 0)
				{
					JOptionPane.showMessageDialog(contentPane, "Error: number must not be Lower than 0%");
				} 
				else
					naturalLabel.setText(Integer.toString(Integer.parseInt(naturalLabel.getText()) - numberSelected));
			}

			/*
			 * 
			 * dose0Label = new JLabel("0"); dose1Label = new JLabel("0"); dose2Label = new
			 * JLabel("0"); naturalLabel = new JLabel("0");
			 * 
			 */

		}



	}// End inner class

	public static void main(String[] args)
	{
		new InputScreenFrame();
	}
}
// end class