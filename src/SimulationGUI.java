
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;


public class SimulationGUI extends JPanel {
    //These are the constants and can be changed to get different results
    private final int WIDTH = 800, HEIGHT = 600, LAG_TIME = 50, PERSON_DIAM = 10;

    private final Timer time;
    //The People array to hold the arrays
    private final Person[] people;

    private Dashboard dash;
    private int cycleTotal = 0;

    //This is a debug Constructor, easy to use
    public SimulationGUI() {
        int ARRAY_SIZE = 600;
        people = new Person[ARRAY_SIZE];
        this.time = new Timer(LAG_TIME, new BounceListener());

        people[0] = new Person(PERSON_DIAM, WIDTH, HEIGHT, 0);
        people[0].setColor(Color.red);
        people[0].setInfected(true);
        people[0].setWasInfected(true);
        people[0].setYMovePositive(false);

        for (int i = 1; i < people.length; i++) {
            Random r = new Random();
            int stats = r.nextInt(4);
            people[i] = new Person(PERSON_DIAM, WIDTH, HEIGHT, stats);
            people[i].setInfected(false);
            people[i].setColor(Color.BLUE);
        }

        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.white);

        this.time.start();

    }

    //This constructor is to communicate with other GUI widow
    //This constructor takes 5 parameters
    //1. population: number
    //2, 3, 4, 5: percentage of people with different immunity status

    public SimulationGUI(int population, int dose0Percent, int dose1Percent, int dose2Percent, int naturalImmunePercent, Dashboard dash) {
        people = new Person[population + 1];
        //create a timer using the inner class event handler
        this.time = new Timer(LAG_TIME, new BounceListener());

        //This takes care of the situation which the percentages are higher than 100
        //THIS SHOULD NOT HAPPEN THOUGH!!!!!!
        while (dose0Percent + dose1Percent + dose2Percent + naturalImmunePercent > 100) {
            dose0Percent = (int) (dose0Percent * 0.9);
            dose1Percent = (int) (dose1Percent * 0.9);
            dose2Percent = (int) (dose2Percent * 0.9);
            naturalImmunePercent = (int) (naturalImmunePercent * 0.9);
        }

        //Calculate the number based on percentage of each group
        int numberDose0, numberDose1, numberDose2, numberNaturalImmune;
        numberDose0 = (int) (0.01 * population * dose0Percent);
        numberDose1 = (int) (0.01 * population * dose1Percent);
        numberDose2 = (int) (0.01 * population * dose2Percent);
        numberNaturalImmune = (int) (0.01 * population * naturalImmunePercent);

        // if numberDose0 + numberDose1 + numberDose2 + numberNaturalImmune is less than population,
        // the rest will be non-vaccinated
        if (population > numberDose0 + numberDose1 + numberDose2 + numberNaturalImmune)
            numberDose0 += population - (numberDose0 + numberDose1 + numberDose2 + numberNaturalImmune);


        //index to add the right number of people to the people array
        int index = 0;

        for (int i = 0; i < numberDose0; i++)
            people[index++] = new Person(PERSON_DIAM, WIDTH, HEIGHT, 0);

        for (int i = 0; i < numberDose1; i++)
            people[index++] = new Person(PERSON_DIAM, WIDTH, HEIGHT, 1);

        for (int i = 0; i < numberDose2; i++)
            people[index++] = new Person(PERSON_DIAM, WIDTH, HEIGHT, 2);

        for (int i = 0; i < numberNaturalImmune; i++)
            people[index++] = new Person(PERSON_DIAM, WIDTH, HEIGHT, 3);

        //Adding the infected person to the array
        people[index] = new Person(PERSON_DIAM, WIDTH, HEIGHT, 0);
        people[index].setColor(Color.red);
        people[index].setInfected(true);
        people[index].setWasInfected(true);
        people[index].setYMovePositive(false);

        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.white);

        this.dash = dash;
        //starting the timer object
        this.time.start();

    }

    //This method will calculate the condition of each object
    //For example it will take care of color and new immunity status
    //Also takes care of the possibility of death
    public void calcObjectsStatus(Person object) {
        //if the object is infected, the object will count the cycles until it reaches 150
        if (object.isInfected()) {
            object.setCycleCounter(object.getCycleCounter() + 1);

            //After the 150 cycles based on the random number some people die and some recover
            if (object.getCycleCounter() % 150 == 0) {

                Random randObject = new Random();
                //Generates a random number in the range 1 to 1000
                int random = randObject.nextInt(1000) + 1;

                //if they have not been vaccinated
                if (object.getImmunityStatus() == 0) {
                    //10% chance of death
                    if (random <= 100) {
                        object.setAlive(false);
                        object.setInfected(false);
                        object.setCycleCounter(0);
                        object.setColor(Color.BLACK);
                    } else {
                        object.setInfected(false);
                        object.setImmunityStatus(3);
                        object.setColor(Color.green);
                        //Not sure what the person's status would be if they already had vaccines before
                    }
                }
                //if they are partially vaccinated
                else if (object.getImmunityStatus() == 1) {
                    //5% chance of death
                    if (random <= 50) {
                        object.setAlive(false);
                        object.setInfected(false);
                        object.setCycleCounter(0);
                        object.setColor(Color.BLACK);
                    } else {
                        object.setInfected(false);
                        object.setImmunityStatus(3);
                        object.setColor(Color.green);
                    }
                }
                //if they are fully vaccinated
                else if (object.getImmunityStatus() == 2) {
                    // 1% chance of death
                    if (random <= 10) {
                        object.setAlive(false);
                        object.setInfected(false);
                        object.setCycleCounter(0);
                        object.setColor(Color.BLACK);
                    } else {
                        object.setInfected(false);
                        object.setImmunityStatus(3);
                        object.setColor(Color.green);
                    }
                }
                //if they are naturally immune
                else if (object.getImmunityStatus() == 3) {
                    // 0.3% chance of death
                    if (random <= 3) {
                        object.setAlive(false);
                        object.setInfected(false);
                        object.setCycleCounter(0);
                        object.setColor(Color.BLACK);
                    } else {
                        object.setInfected(false);
                        object.setImmunityStatus(3);
                        object.setColor(Color.green);
                    }


                }

            }

        }
    }

    //This paints each component.
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        //gets the color of each person and then paints it
        for (Person person : people) {
            g.setColor(person.getColor());
            g.fillOval(person.getXCoordinate(), person.getYCoordinate(), person.getDiameter(), person.getDiameter());
        }

    }

    private class BounceListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (Person person : people)
                calcPosition(person);

            //Takes care of the collision
            for (int i = 0; i < people.length - 1; i++)
                for (int j = i + 1; j < people.length; j++)
                    people[i].checkCollision(people[j]);

            //Calculates the object status
            for (Person person : people)
                calcObjectsStatus(person);

            //stop exactly after 450 cycles
            if (++cycleTotal == 450) {
              time.stop();
              printFinalPresentation();
            }


            dash.lblNumDied.setText(String.valueOf(getNumberOfDead()));
            dash.lblNumFullVax.setText(String.valueOf(getNumberOfFullyVaccinatedInfected()));
            dash.lblNumPartVax.setText(String.valueOf(getNumberOfPartiallyVaccinatedInfected()));
            dash.lblNumNonVax.setText(String.valueOf(getNumberOfNonVaccinatedInfected()));
            dash.lblNumRecovered.setText(String.valueOf(getNumberOfNaturalImmuneRecovered()));
            dash.lblNumInfected.setText(String.valueOf(getNumberOfCurrentlyInfected()));

            //Debug purpose
            //System.out.println(cycleTotal);
//            if (cycleTotal % 20 == 0) {
//                System.out.println("Number of Infected: " + getPercentOfInfected());
//                System.out.println("Number of non-vaccinated Infected: " + getPercentOfNonVaccinatedInfected());
//                System.out.println("Number of part-vaccinated Infected: " + getPercentOfPartiallyVaccinatedInfected());
//                System.out.println("Number of full-vaccinated Infected: " + getPercentOfFullyVaccinatedInfected());
//
//                System.out.println("Number of natural immune: " + getPercentOfNaturalImmuneRecovered());
//                System.out.println("Number of dead: " + getPercentOfDead());
//            }
            repaint();
        }
    }


    public void printFinalPresentationText()
    {
     System.out.println("\npercentage of total population who contracted the disease: " + getPercentOfCurrentlyInfected() + "%");
   	 System.out.println("percentage of unvaccinated people who contracted the disease: " + getPercentOfNonVaccinatedInfected() + "%");
   	 System.out.println("percentage of partially-vaccinated people who contracted the disease: " + getPercentOfPartiallyVaccinatedInfected() + "%");
   	 System.out.println("percentage of fully-vaccinated people who contracted the disease: " + getPercentOfFullyVaccinatedInfected() + "%");
   	 System.out.println("percentage of all people who contracted the disease that recovered: " + getPercentOfNaturalImmuneRecovered() + "%");
   	 System.out.println("percentage of all people who contracted the disease and died: " + getPercentOfDead() + "%"); 
    }
    
    public void printFinalPresentation() {
  		
		// create a JFrame to hold the JPanel
        JFrame frame = new JFrame("Final Data Presentation");

        //boilerplate
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout() );//ANONYMOUS object
        frame.setSize(600,400);
        frame.setLocationRelativeTo(null);

        //set background color of contentPane
        frame.getContentPane().setBackground(Color.gray);

        // create labels to display text
        JLabel l1 = new JLabel();
        JLabel l2 = new JLabel();
        JLabel l3 = new JLabel();
        JLabel l4 = new JLabel();
        JLabel l5 = new JLabel();
        JLabel l6 = new JLabel();
 
        // add text to labels
        l1.setText("percentage of total population who contracted the disease: " + getPercentOfCurrentlyInfected() + "%");
        l2.setText("percentage of unvaccinated people who contracted the disease: " + getPercentOfNonVaccinatedInfected() + "%");
        l3.setText("percentage of partially-vaccinated people who contracted the disease: " + getPercentOfPartiallyVaccinatedInfected() + "%");
        l4.setText("percentage of fully-vaccinated people who contracted the disease: " + getPercentOfFullyVaccinatedInfected() + "%");
        l5.setText("percentage of all people who contracted the disease that recovered: " + getPercentOfNaturalImmuneRecovered() + "%");
        l6.setText("percentage of all people who contracted the disease and died: " + getPercentOfDead() + "%");
      	
        // create panels for each value 
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
        JPanel p5 = new JPanel();
        JPanel p6 = new JPanel();
 
        // add labels to panels
        p1.add(l1);
        p2.add(l2);
        p3.add(l3);
        p4.add(l4);
        p5.add(l5);
        p6.add(l6);
 
        // add panels to frame
        frame.add(p1);
        frame.add(p2);
        frame.add(p3);
        frame.add(p4);
        frame.add(p5);
        frame.add(p6);

        frame.setVisible(true);
	}
    
    public void calcPosition(Person person) {
        //Making sure the person bounces when they hit the walls
        if (person.getXCoordinate() >= WIDTH - person.getDiameter() * 2)
            person.setXMovePositive(false);
        if (person.getXCoordinate() <= 0)
            person.setXMovePositive(true);

        //Making sure the person bounces when they hit the ceiling or the ground
        if (person.getYCoordinate() >= HEIGHT - person.getDiameter() * 2)
            person.setYMovePositive(false);
        if (person.getYCoordinate() <= 0)
            person.setYMovePositive(true);

        person.move();


    }

    //to pause the process when called
    public void pause() {
        time.stop();
    }

    //to continue the process when called
    public void resume() {
      if(cycleTotal < 450) {
      	time.start();
      }

        
    }

    //This is for real time data

    //THIS IS FOR VERSION 1.4
    //Use this for the number of people who are currently infected.
    public int getNumberOfCurrentlyInfected() {
        int total = 0;
        for (Person person : people) {
            if (person.isInfected())
                total++;
        }

        return total;
    }

    //THIS IS FOR VERSION 1.4
    //User this for the number of people who have ever been infected.
    public int getNumberOfAllTimeInfected() {
        int total = 0;
        for (Person person : people) {
            if (person.wasInfected())
                total++;
        }
        return total;
    }

    public int getNumberOfNonVaccinatedInfected() {
        int total = 0;
        for (Person person : people) {
            if (person.isInfected() && person.getOriginalStatus() == 0)
                total++;
        }
        return total;
    }

    public int getNumberOfPartiallyVaccinatedInfected() {
        int total = 0;
        for (Person person : people) {
            if (person.isInfected() && person.getOriginalStatus() == 1)
                total++;
        }
        return total;
    }

    public int getNumberOfFullyVaccinatedInfected() {
        int total = 0;
        for (Person person : people) {
            if (person.isInfected() && person.getOriginalStatus() == 2)
                total++;
        }
        return total;
    }

    public int getNumberOfNaturalImmuneRecovered() {
        int total = 0;
        for (Person person : people) {
            if (!person.isInfected() && person.getImmunityStatus() == 3)
                total++;
        }
        return total;
    }

    public int getNumberOfDead() {
        int total = 0;
        for (Person person : people) {
            if (!person.isAlive())
                total++;
        }
        return total;
    }





    //For final data
    //THIS IS FOR VERSION 1.4
    //This one is used to get the percentage of people who are currently infected
    public double getPercentOfCurrentlyInfected() {
        return Math.round(1000.0 / people.length * getNumberOfCurrentlyInfected()) / 10.00;
    }

    //THIS IS FOR VERSION 1.4
    //This one is used to get the percentage of people who have been infected
    public double getPercentOfAllTimeInfected() {
        return Math.round(1000.0 / people.length * getNumberOfAllTimeInfected()) / 10.00;
    }

    public double getPercentOfNonVaccinatedInfected() {
        return Math.round(1000.0 / people.length * getNumberOfNonVaccinatedInfected()) / 10.00;
    }

    public double getPercentOfPartiallyVaccinatedInfected() {
        return Math.round(1000.0 / people.length * getNumberOfPartiallyVaccinatedInfected()) / 10.00;
    }

    public double getPercentOfFullyVaccinatedInfected() {
        return Math.round(1000.0 / people.length * getNumberOfFullyVaccinatedInfected()) / 10.00;
    }

    public double getPercentOfNaturalImmuneRecovered() {
        return Math.round(1000.0 / people.length * getNumberOfNaturalImmuneRecovered()) / 10.00;
    }

    public double getPercentOfDead() {
        return Math.round(1000.0 / people.length * getNumberOfDead()) / 10.00;
    }

}
