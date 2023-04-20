//People will enter info about
//1. 1 shot's
//2. 2 shots'
//3. natural immunity
// The rest will be calculated

import java.awt.*;
import java.util.*;


public class Person {

    //Person constructor
    public Person(int diam, int width, int height, int immunityStatus) {
        this.setAlive(true);
        this.diameter = diam;
        this.setImmunityStatus(immunityStatus);
        this.originalImmunityStatus = immunityStatus;
        
        //changes the color based on immunity status
        if(this.immunityStatus == 0){
            this.setColor(Color.BLUE);
        }
        else if(this.immunityStatus == 1){
            this.setColor(Color.CYAN);
        }
        else if(this.immunityStatus ==2){
            this.setColor(Color.ORANGE);
        }
        else if(this.immunityStatus == 3){
            this.setColor(Color.GREEN);
        }


        Random rand = new Random();

        //randomly generates a position for the person
        int randomX = rand.nextInt(width - this.diameter) + 1;
        int randomY = rand.nextInt(height - this.diameter) + 1;

        this.xCoordinate = randomX;
        this.yCoordinate = randomY;

    }

    public void move() {
        //move if alive
        if (this.isAlive()) {
            setXCoordinate(getXCoordinate() + getXIncrement());
            setYCoordinate(getYCoordinate() + getYIncrement());
        }
    }
    //checks the collision and decides if person should get infected or not
    public void checkCollision(Person person2) {

        int deltaX = this.getXCoordinate() - person2.getXCoordinate();
        int deltaY = this.getYCoordinate() - person2.getYCoordinate();

        //This is where the collision happens
        if (Math.sqrt(deltaX * deltaX + deltaY * deltaY) < this.getDiameter()) {
            this.setXMovePositive(!this.isxMovePositive());
            this.setYMovePositive(!this.isyMovePositive());

            person2.setXMovePositive(!person2.isxMovePositive());
            person2.setYMovePositive(!person2.isyMovePositive());

            //they have to both be alive to infect each other
            if(this.isAlive() && person2.isAlive()) {
                //one has to be infected and the other has to be not infected to infect each other
                if (!this.isInfected() && person2.isInfected()) {
                    Random rand = new Random();
                    int randNum = rand.nextInt(10) + 1;

                    if (this.getImmunityStatus() == 0) {
                        // 80% chance of getting infected
                        if (randNum <= 8) {
                            this.setInfected(true);
                            this.setWasInfected(true);
                            this.setCycleCounter(0);
                            this.setColor(Color.red);
                        }
                    } else if (this.getImmunityStatus() == 1) {
                        // 40% chance of getting infected
                        if (randNum <= 4) {
                            this.setInfected(true);
                            this.setWasInfected(true);
                            this.setCycleCounter(0);
                            this.setColor(Color.red);
                        }
                    } else if (this.getImmunityStatus() == 2) {
                        // 10% chance of getting infected
                        if (randNum <= 1) {
                            this.setInfected(true);
                            this.setWasInfected(true);
                            this.setCycleCounter(0);
                            this.setColor(Color.red);
                        }
                    } else if (this.getImmunityStatus() == 3) {
                        // 10% chance of getting infected
                        if (randNum <= 1) {
                            this.setInfected(true);
                            this.setWasInfected(true);
                            this.setCycleCounter(0);
                            this.setColor(Color.red);
                        }
                    }
                } else if (this.isInfected() && !person2.isInfected()) {
                    Random rand = new Random();
                    int randNum = rand.nextInt(10) + 1;

                    if (person2.getImmunityStatus() == 0) {
                        if (randNum <= 8) {
                            person2.setInfected(true);
                            person2.setWasInfected(true);
                            person2.setCycleCounter(0);
                            person2.setColor(Color.red);
                        }
                    } else if (person2.getImmunityStatus() == 1) {
                        if (randNum <= 4) {
                            person2.setInfected(true);
                            person2.setWasInfected(true);
                            person2.setCycleCounter(0);
                            person2.setColor(Color.red);
                        }
                    } else if (person2.getImmunityStatus() == 2) {
                        if (randNum <= 1) {
                            person2.setInfected(true);
                            person2.setWasInfected(true);
                            person2.setCycleCounter(0);
                            person2.setColor(Color.red);
                        }
                    } else if (person2.getImmunityStatus() == 3) {
                        if (randNum <= 1) {
                            person2.setInfected(true);
                            person2.setWasInfected(true);
                            person2.setCycleCounter(0);
                            person2.setColor(Color.red);
                        }
                    }
                }
            }

        }

    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean isInfected() {
        return isInfected;
    }

    public void setInfected(boolean infected) {
        isInfected = infected;
    }

    public int getImmunityStatus() {
        return immunityStatus;
    }

    public void setImmunityStatus(int immunityStatus) {
        this.immunityStatus = immunityStatus;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public int getXIncrement() {
        return xIncrement;
    }

    public void setXIncrement(int xIncrement) {
        Random r = new Random();
        int random = r.nextInt(6);

        this.xIncrement = xIncrement * random;
    }

    public int getYIncrement() {
        return yIncrement;
    }

    public void setYIncrement(int yIncrement) {
        Random r = new Random();
        int random = r.nextInt(6);

        this.yIncrement = yIncrement * random;
    }

    public int getCycleCounter() {
        return cycleCounter;
    }

    public void setCycleCounter(int cycleCounter) {
        this.cycleCounter = cycleCounter;
    }

    public boolean isxMovePositive() {
        return xMovePositive;
    }

    public void setXMovePositive(boolean xPositive) {
        if (!xMovePositive)
            setXIncrement(-1);
        else
            setXIncrement(1);

        this.xMovePositive = xPositive;
    }

    public boolean isyMovePositive() {
        return yMovePositive;
    }

    public void setYMovePositive(boolean yPositive) {
        if (!yPositive)
            setYIncrement(-1);
        else
            setYIncrement(1);

        this.yMovePositive = yPositive;
    }

    public int getDiameter() {
        return diameter;
    }

    //THIS IS FOR VERSION 1.4
    public boolean wasInfected() {
        return wasInfected;
    }

    //THIS IS FOR VERSION 1.4
    public void setWasInfected(boolean wasInfected) {
        this.wasInfected = wasInfected;
    }
    
    public int getOriginalStatus()
    {
    	return this.originalImmunityStatus;
    }


    //If false
    //COLOR BLACK
    //No moving
    private boolean isAlive;

    //An infected person has 10% chance of dying
    //COLOR RED
    private boolean isInfected;

    //After time passed and alive
    //wasInfected true isInfected false
    //private boolean wasInfected;

    //immunity 0: 0 vaccine, 80% infection chance, BLUE
    //immunity 1: 1 vaccine, 40% infection chance, CYAN
    //immunity 2: 2 vaccine, 10% infection chance, YELLOW
    //immunity 3: was infected, is not infected, 10% infection chance, GREEN
    private int immunityStatus;
    
    private int originalImmunityStatus;
    //Colour of each object
    private Color color;

    //coordinates of each object
    private int xCoordinate, yCoordinate;

    //The amount of movement
    private int xIncrement = 1, yIncrement = 1;

    //Takes care of the time of being infected
    private int cycleCounter = 0;

    //Takes care of the direction of the movement
    private boolean xMovePositive = true, yMovePositive = true;

    //gets the diameter of the circle
    private final int diameter;


    //THIS IS FOR VERSION 1.4
    //will say if someone was ever infected
    private boolean wasInfected;

}
