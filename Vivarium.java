import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The Vivarium Class, responsible for rendering all objects and tracking collisions.
 * 
 * @author Tyler Freedman 
 * @version April 15, 2011
 */
public class Vivarium extends JComponent
{  
    ArrayList<Creature> creatures = new ArrayList<Creature>(); //ArrayList of Creatures
    int status = 0; //collision code. 0 = harmless, 3 = removes object, 4 = death for Kirby.

    /**
     * Constructs the Vivarium
     */
    public Vivarium () {
        Block o1 = new Block(658, 48, 48, 16); //Platform 1
        Block o2 = new Block(738, 80, 48, 16); //Platform 2
        Block o3 = new Block(674, 64, 16, 64); //Support Beam for Platform 1
        Block o4 = new Block(754, 96, 16, 32); //Support Beam for Platform 2
        Block o5 = new Block(0, 96, 562, 32);  //Bottom Spanner
        Block o6 = new Block(0, 80, 185, 16);  //Leftmost Surface
        Block o7 = new Block(377, 80, 201, 16);//Rightmost Surface
        Block o8 = new Block(657, 47, 1, 1);  //Island Platform 1, Stopper
        Block o9 = new Block(706, 47, 1, 1); //Island Platform 1, Stopper
        Block o10 = new Block(737, 79, 1, 1); //Island Platform 2, Stopper
        Block o11 = new Block(786, 79, 1, 1); //Island Platform 2, Stopper
        Block o12 = new Block(184, 79, 1, 1);  //Leftmost Surface, Stopper
        Block o13 = new Block(578, 79, 1, 1); //Rightmost Surface, Stopper
        Block o14 = new Block(376, 79, 1, 1); //Rightmost Surface, Stopper
        Kirby o15 = new Kirby(0, 39, 24, 24, 24, 24, 1, 2, Color.WHITE);
        WaddleDee o16 = new WaddleDee(30, 64, 16, 16, 16, 16, 1, 0, Color.PINK);
        WaddleDee o17 = new WaddleDee(60, 64, 16, 16, 16, 16, 1, 0, Color.PINK);
        WaddleDee o18 = new WaddleDee(160, 64, 16, 16, 16, 16, 1, 0, Color.PINK);
        WaddleDee o19 = new WaddleDee(200, 80, 16, 16, 16, 16, 1, 0, Color.PINK);
        WaddleDee o20 = new WaddleDee(400, 64, 16, 16, 16, 16, 1, 0, Color.PINK);
        WaddleDee o21 = new WaddleDee(360, 80, 16, 16, 16, 16, 1, 0, Color.PINK);
        WaddleDee o22 = new WaddleDee(658, 32, 16, 16, 16, 16, 1, 0, Color.PINK);
        WaddleDee o23 = new WaddleDee(759, 64, 16, 16, 16, 16, 1, 0, Color.PINK);
        Squishy o24 = new Squishy(320, 25, 16, 16, 16, 16, 2, 1);
        Squishy o25 = new Squishy(160, 30, 16, 16, 16, 16, 1, 1);
        Squishy o26 = new Squishy(700, 75, 16, 16, 16, 16, 1, 2);
        Gordo o27 = new Gordo (800, 40, 16, 16, 16, 16, 0, 2, Color.BLACK);
        Gordo o28 = new Gordo (600, 120, 16, 16, 16, 16, 0, 2, Color.BLACK);

        creatures.add(o1);   //Adds the Creatures to the ArrayList
        creatures.add(o2);   
        creatures.add(o3);   
        creatures.add(o4);   
        creatures.add(o5);   
        creatures.add(o6);   
        creatures.add(o7);   
        creatures.add(o8);   
        creatures.add(o9);   
        creatures.add(o10);   
        creatures.add(o11);   
        creatures.add(o12);   
        creatures.add(o13);   
        creatures.add(o14);   
        creatures.add(o15);   
        creatures.add(o16);   
        creatures.add(o17);   
        creatures.add(o18);   
        creatures.add(o19);   
        creatures.add(o20);   
        creatures.add(o21);   
        creatures.add(o22);   
        creatures.add(o23);   
        creatures.add(o24);   
        creatures.add(o25);   
        creatures.add(o26);   
        creatures.add(o27);   
        creatures.add(o28);   
    }

    /**
     * Updates the frame by determining what happened to every object.
     * @param Graphics2D g
     */
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        for (int i = 0; i < creatures.size(); i++) {
            for (int j = i + 1; j < creatures.size(); j++) {
                if (creatures.get(i).collisionDetect(creatures.get(j))) { //If there's a collision
                    status = creatures.get(i).collisionResponse(creatures.get(j)); //Get the status code
                    if (status == 3 && creatures.get(i).getType().equals("Kirby"))  //If Kirby hit something
                        creatures.remove(j); //Remove that thing
                    else if (status == 3 && !creatures.get(i).getType().equals("Kirby") && !creatures.get(i).getType().equals("Creature")) 
                        creatures.remove(i); //If something hit Kirby, remove it.
                    else if (creatures.get(i).getType().equals("Creature"))
                        creatures.remove(i); //Kill Creature
                    else if (creatures.get(j).getType().equals("Creature"))
                        creatures.remove(j); //Kill Creature again
                    else if (status == 4) { //Ends the game, by playing the game over music
                        try {Thread.sleep (3400);}
                        catch (InterruptedException ie) {} //We want the game to end. If it crashes, that's considered a success.
                        System.exit(0); //End the game
                    }
                }
            }
        }

        for (int i = 0; i < creatures.size(); i++) { //Cycle through all creatures
            creatures.get(i).move(); //Moves the surviving objects into position
            creatures.get(i).draw(g2); //Draws the surviving objects
        }
    }


    /**
     * Provides the creatures with the viewer's width. Used for collisions against walls.
     * @return the width, as an integer.
     */
    public static int getFrameWidth () {
        return VivariumViewer.FRAME_WIDTH;
    }

    /**
     * Provides the creatures with the viewer's height. Used for collisions against walls.
     * @return the height, as an integer.
     */
    public static int getFrameHeight () {
        return VivariumViewer.FRAME_HEIGHT;
    }
}
