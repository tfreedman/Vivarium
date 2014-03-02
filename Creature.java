import java.awt.*;

/**
 * The Creature Class, used for creating Creature objects. Creature should be overridden to
 * create colorful characters, but the default implementation will create a basic block character.
 * 
 * @author Tyler Freedman 
 * @version April 15, 2011
 */
public class Creature extends Moveable2DShape
{
    boolean flip; //Change me to flip the image horizontally
    int deltaH = 1; //Default horizontal direction
    int deltaV = -1; //Default vertical direction
    Rectangle data;
    Color color;

    /**
     * Constructs a generic Creature with a set color.
     * @param int X Position, YPosition, shape Width, shape Height, Bounding Box Width, Bounding Box Height. Double X Speed, Y Speed. Color color.
     */
    public Creature (int xPos, int yPos, int shapeWidth, int shapeHeight, int bwidth, int bheight, double xSpeed, double ySpeed, Color color) {
        super(xPos, yPos, shapeWidth, shapeHeight, bwidth, bheight, xSpeed, ySpeed);
        data = new Rectangle (xPos, yPos, shapeWidth, shapeHeight); //Constructs a rectangle object.
        this.color = color; //Sets the color of the creature
    }

    /**
     * Constructs a creature with a pre-set color
     * @param int X Position, YPosition, shape Width, shape Height, Bounding Box Width, Bounding Box Height. Double X Speed, Y Speed.
     */
    public Creature (int xPos, int yPos, int shapeWidth, int shapeHeight, int bwidth, int bheight, double xSpeed, double ySpeed) {
        super(xPos, yPos, shapeWidth, shapeHeight, bwidth, bheight, xSpeed, ySpeed);
    }

    /**
     * Reports the names of each creature involved in a collision, and returns the status code.
     * @param Moveable2DShape other, the other shape to check in a collision.
     * @return int collision code. 0 = harmless, 3 = removes object, 4 = death for Kirby.
     */
    public int collisionResponse(Moveable2DShape other) {
        return 3;
    }

    /**
     * Draws the creature on screen.
     * @param Graphics2D g
     */
    public void draw(Graphics2D g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.fill(data);
    }

    /**
     * Moves the creature
     */
    public void move() {
        assemble(); //Assemble the rectangles to be drawn
        if (((getXPos() <= 0) && deltaH == -1) || ((getXPos() + getBoundingBoxWidth() >= Vivarium.getFrameWidth()) && deltaH == 1)) //Prevents objects from going off-screen
            deltaH *= -1; //Multiply direction by -1 to reverse it.
        if (((getYPos() + getBoundingBoxHeight() >= Vivarium.getFrameHeight()) && deltaV == 1) || ((getYPos() <= 0) && deltaV == -1)) //Prevents objects from going off-screen
            deltaV *= -1;   //Multiply direction by -1 to reverse it.
        moveTo(getXPos() + (deltaH * getXSpeed()), getYPos() + (deltaV * getYSpeed())); //Move the object to the new location
    }

    /**
     * Assembles the rectangle objects needed to create the creature
     */
    public void assemble() {
        data = new Rectangle ((int)getXPos(), (int)getYPos(), (int)getBoundingBoxWidth(), (int)getBoundingBoxHeight());
    }


    /**
     * Takes an array of Rectangles, and draws them backwards.
     * @param Rectangle[] data, the original array, and int width, the creature's width
     * @return Rectangle[], an array of all the shapes to create a creature.
     */
    public Rectangle[] flipH (Rectangle[] data, int width) {
        for (int i = 0; i < data.length; i++)
            data[i].setLocation((int)(width - data[i].getX() - data[i].getWidth()), (int)data[i].getY());
        return data;
    }

    /**
     * Determines if a collision between two objects occurred.
     * @param Moveable2DShape other, the other shape to check in a collision.
     * @return boolean true for yes, false if no collision has occurred.
     */
    public boolean collisionDetect(Moveable2DShape other) {
        return this.intersectsBoundingBox(other.getBoundingBox());
    }

    /**
     * Forces the creature to be rendered backwards.
     */
    public void flip() {
        flip = !flip;
    }

    /**
     * Reports the name of the object involved in a collision.
     * 
     * @return the name of the Class, as a String
     */
    public String getType() {
        return "Creature";
    }
}