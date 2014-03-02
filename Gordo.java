import java.awt.*;

/**
 * The Gordo Class, used for creating Gordo objects. Gordo is the spikey thing in Kirby games,
 * that will instantly kill him on contact.
 * 
 * @author Tyler Freedman 
 * @version April 15, 2011
 */
public class Gordo extends Creature
{
    boolean trigger; //Change me to flip the image horizontally
    Rectangle data[] = new Rectangle[48];
    int deltaH = 1; //Default horizontal direction
    int deltaV = -1;//Default vertical direction
    Color color;

    /**
     * Constructs a Gordo object
     * @param int X Position, YPosition, shape Width, shape Height, Bounding Box Width, Bounding Box Height. Double X Speed, Y Speed. Color color.
     */
    public Gordo(int xPos, int yPos, int shapeWidth, int shapeHeight, int bwidth, int bheight, double xSpeed, double ySpeed, Color color) {
        super(xPos, yPos, shapeWidth, shapeHeight, bwidth, bheight, xSpeed, ySpeed);
        this.color = color;
    }
    
    /**
     * Draws Gordo on screen.
     * @param Graphics2D g
     */
    public void draw(Graphics2D g) {
        Graphics2D g2 = (Graphics2D) g;
        for (int i = 0; i < data.length; i++) { //Cycles through the array
            if (i == 0) //Sets the color of certain rectangles in the array
                g2.setColor(color);
            else if (i == 17)
                g2.setColor(Color.CYAN);
            else if (i == 27)
                g2.setColor(Color.WHITE);
            g2.fill(data[i]);
        }
    }

    /**
     * Constructs Gordo out of Rectangles. All of the frames needed to animate Gordo's motions are
     * stored in the array, and segments of each are loaded depending on what frame is necessary.
     * 
     * By storing data in the array and procedurally generating the rest, other methods can modify 
     * the image in real time.
     */
    public void assemble() {
        data[0] = new Rectangle (3, 3, 10, 10);
        data[1] = new Rectangle (2, 2, 2, 2);
        data[2] = new Rectangle (12, 12, 2, 2);
        data[3] = new Rectangle (14, 0, 2, 2);
        data[4] = new Rectangle (0, 14, 2, 2);
        data[5] = new Rectangle (1, 6, 2, 3);
        data[6] = new Rectangle (13, 7, 2, 3);
        data[7] = new Rectangle (7, 1, 3, 2);
        data[8] = new Rectangle (6, 13, 3, 2);
        data[9] = new Rectangle (12, 1, 2, 4);
        data[10] = new Rectangle (1, 12, 3, 3);
        data[11] = new Rectangle (11, 2, 4, 2);
        data[12] = new Rectangle (2, 11, 3, 3);
        data[13] = new Rectangle (8, 0, 1, 1);
        data[14] = new Rectangle (7, 15, 1, 1);
        data[15] = new Rectangle (0, 7, 1, 1);
        data[16] = new Rectangle (15, 8, 1, 1); //EOB
        data[17] = new Rectangle (8, 1, 1, 1);
        data[18] = new Rectangle (4, 4, 1, 1);
        data[19] = new Rectangle (1, 7, 1, 1);
        data[20] = new Rectangle (12, 2, 1, 1);
        data[21] = new Rectangle (13, 3, 1, 1);
        data[22] = new Rectangle (14, 8, 1, 1);
        data[23] = new Rectangle (11, 11, 1, 1);
        data[24] = new Rectangle (7, 14, 1, 1);
        data[25] = new Rectangle (2, 12, 1, 1);
        data[26] = new Rectangle (3, 13, 1, 1); //EOC
        data[27] = new Rectangle (4, 7, 2, 2);
        data[28] = new Rectangle (7, 7, 2, 2);
        data[29] = new Rectangle (10, 7, 2, 2);
        data[30] = new Rectangle (5, 6, 2, 1);
        data[31] = new Rectangle (9, 6, 2, 1);
        data[32] = new Rectangle (5, 9, 2, 1);
        data[33] = new Rectangle (9, 9, 2, 1);
        data[34] = new Rectangle (3, 3, 1, 1);
        data[35] = new Rectangle (12, 12, 1, 1);
        data[36] = new Rectangle (2, 7, 1, 1);
        data[37] = new Rectangle (13, 8, 1, 1);
        data[38] = new Rectangle (7, 13, 1, 1);
        data[39] = new Rectangle (14, 1, 1, 1);
        data[40] = new Rectangle (1, 14, 1, 1);
        data[41] = new Rectangle (8, 2, 1, 1);
        data[42] = new Rectangle (2, 13, 1, 1);
        data[43] = new Rectangle (13, 2, 1, 1);
        data[44] = new Rectangle (3, 11, 1, 2);
        data[45] = new Rectangle (4, 12, 1, 1);
        data[46] = new Rectangle (12, 3, 1, 2);
        data[47] = new Rectangle (11, 3, 1, 1);
    }

    /**
     * Moves the creature
     */
    public void move() {
        assemble(); //Assemble the rectangles to be drawn
        if (trigger)
            flipH(data, (int)getBoundingBoxWidth()); //Flip the image horizontally
        if (((getXPos() <= 0) && deltaH == -1) || ((getXPos() + getBoundingBoxWidth() >= Vivarium.getFrameWidth()) && deltaH == 1))
            deltaH *= -1;  //Prevents the image from going off screen by reversing direction
        if (((getYPos() + getBoundingBoxHeight() >= Vivarium.getFrameHeight()) && deltaV == 1) || ((getYPos() <= 0) && deltaV == -1))
            deltaV *= -1;  //Prevents the image from going off screen by reversing direction
        moveTo(getXPos() + (deltaH * getXSpeed()), getYPos() + (deltaV * getYSpeed())); //Move the object to the new location
        for (int i = 0; i < data.length; i++)
            data[i].setLocation((int)(data[i].getX() + getXPos()), (int)(data[i].getY() + getYPos())); //Set the visible part of the object to the Bounding Box's position
        trigger = !trigger; //Used to alternate between the two frames that Gordo has
    }

    /**
     * Reports the name of the object involved in a collision.
     * 
     * @return the name of the Class, as a String
     */
    public String getType() {
        return "Gordo";
    }
    
    /**
     * Determines the appropriate response to a collision, and returns the status code.
     * @param Moveable2DShape other, the other shape to check in a collision.
     * @return int collision code. 0 = harmless, 3 = removes object, 4 = death for Kirby.
     */
    public int collisionResponse(Moveable2DShape other) {
        if (!other.getType().equals("Gordo") && !other.getType().equals("Block"))
            other.collisionResponse(this); //Find out what the other creature plans to do in response to the collision
        else {
            deltaH *= -1; //Change the direction on impact
            deltaV *= -1; //Change the direction on impact
        }
        return 0; //Gordo is invincible, and never dies. Gordo however is unstoppable, and kills everything in its path.
    }
}