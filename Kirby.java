import java.awt.*;

/**
 * The Kirby Class, used for creating Kirby objects.
 * 
 * @author Tyler Freedman 
 * @version April 15, 2011
 */
public class Kirby extends Creature
{
    boolean trigger = true; //Determines which frame of Kirby to draw.
    boolean flip = false; //Should Kirby be flipped horizontally?
    int deltaH = 1; //Default horizontal direction
    int deltaV = -1;//Default vertical direction
    Rectangle data[] = new Rectangle[88];
    int counter = 0; //Used to control flickering when Kirby collides with an enemy
    String lastEncounter = ""; //Used to prevent Kirby from being stuck inside a block
    Color color;

    /**
     * Constructs a Kirby object
     * @param int X Position, YPosition, shape Width, shape Height, Bounding Box Width, Bounding Box Height. Double X Speed, Y Speed. Color color.
     */
    public Kirby(int xPos, int yPos, int shapeWidth, int shapeHeight, int bwidth, int bheight, double xSpeed, double ySpeed, Color color) {
        super(xPos, yPos, shapeWidth, shapeHeight, bwidth, bheight, xSpeed, ySpeed);
        this.color = color;
    }

    /**
     * Draws Kirby on screen.
     * @param Graphics2D g
     */
    public void draw(Graphics2D g) {
        Graphics2D g2 = (Graphics2D) g;
        if (counter % 2 == 0 || counter == 0) { //Used to drop a few frames when Kirby has just been damaged.
            if (!trigger) //First frame of Kirby
                for (int i = 0; i < 69; i++) {
                    if (i == 0)//Sets the color of certain rectangles in the array
                        g2.setColor(color);
                    else if (i == 5)
                        g2.setColor(Color.BLACK);
                    else if (i == 44)
                        g2.setColor(Color.PINK);
                    g2.fill(data[i]);
            }

            else if(trigger) //Second frame of Kirby
                for (int i = 0; i < data.length; i++) {
                    if (i == 0 || i == 86)
                        g2.setColor(color);
                    else if (i == 5 || i == 69)
                        g2.setColor(Color.BLACK);
                    else if (i == 44 || i == 80)
                        g2.setColor(Color.PINK);
                    g2.fill(data[i]);
            }
        }
        if (counter > 0) //Used to drop certain frames when Kirby takes damage.
            counter--;
        trigger = !trigger; //Change the next frame to the opposite of this one
    }

    /**
     * Moves the creature
     */
    public void move() {
        assemble(); //Assemble the rectangles to be drawn
        if (((getXPos() <= 0) && deltaH == -1) || ((getXPos() + getBoundingBoxWidth() >= Vivarium.getFrameWidth()) && deltaH == 1)) {
            deltaH *= -1; //Prevents the image from going off screen by reversing direction
            flip = !flip; //Flips Kirby's sprite
            lastEncounter = ""; //Prevents Kirby from getting stuck in an object
        }
        if (((getYPos() + getBoundingBoxHeight() >= Vivarium.getFrameHeight()) && deltaV == 1) || ((getYPos() <= 0) && deltaV == -1)) {
            deltaV *= -1; //Prevents the image from going off screen by reversing direction
            lastEncounter = ""; //Prevents Kirby from getting stuck in an object
        }
        if (trigger) { //Overrides parts of the array with data from one frame, by applying the changes to it
            data[11] = data[14] = data[17] = data[47] = data[48] = new Rectangle(0,0,0,0);
            data[0] = new Rectangle(4, 3, 15, 12);
            moveTo(getXPos() + (deltaH * getXSpeed()), getYPos() + (deltaV * getYSpeed()) + 1); //Moves Kirby, and sets him to bounce
        }
        else {            
            data[47] = new Rectangle(2, 2, 1, 1); //Overrides parts of the array with data from one frame, by applying the changes to it
            data[48] = new Rectangle(2, 5, 1, 1);
            data[17] = new Rectangle(1, 2, 1, 4);
            data[14] = new Rectangle(2, 1, 1, 1);
            data[11] = new Rectangle(3, 0, 4, 1);
            data[0] = new Rectangle(2, 1, 15, 17); 
            moveTo(getXPos() + (deltaH * getXSpeed()), getYPos() + (deltaV * getYSpeed()) - 1); //Moves Kirby, and sets him to bounce
        }
        if (flip) //Causes Kirby's image to be flipped.
            flipH(data, (int)getBoundingBoxWidth());
        for (int i = 0; i < data.length; i++) //Updates the rectangles' position
            data[i].setLocation((int)(data[i].getX() + getXPos()), (int)(data[i].getY() + getYPos()));
    }

    /**
     * Constructs Kirby out of Rectangles. All of the frames needed to animate Kirby's motions are
     * stored in the array, and segments of each are loaded depending on what frame is necessary.
     * 
     * By storing data in the array and procedurally generating the rest, other methods can modify 
     * the image in real time.
     */
    public void assemble() {
        data[0] = new Rectangle(2, 1, 15, 17); 
        data[1] = new Rectangle(18, 1, 4, 16);
        data[2] = new Rectangle(17, 2, 1, 19);
        data[3] = new Rectangle(3, 8, 14, 14);
        data[4] = new Rectangle(18, 17, 2, 2); //EOW
        data[5] = new Rectangle(17, 7, 1, 3);
        data[6] = new Rectangle(19, 7, 1, 3);
        data[7] = new Rectangle(16, 11, 1, 1);
        data[8] = new Rectangle(16, 15, 1, 1);
        data[9] = new Rectangle(17, 12, 1, 3);
        data[10] = new Rectangle(18, 13, 3, 1);
        data[11] = new Rectangle(3, 0, 4, 1);
        data[12] = new Rectangle(9, 0, 6, 1);
        data[13] = new Rectangle(19, 0, 3, 1);
        data[14] = new Rectangle(2, 1, 1, 1);
        data[15] = new Rectangle(22, 1, 1, 5);
        data[16] = new Rectangle(22, 8, 1, 7);
        data[17] = new Rectangle(1, 2, 1, 4);
        data[18] = new Rectangle(2, 10, 1, 7);
        data[19] = new Rectangle(9, 22, 6, 1);
        data[20] = new Rectangle(7, 1, 2, 1);
        data[21] = new Rectangle(2, 6, 1, 2);
        data[22] = new Rectangle(3, 8, 1, 2);
        data[23] = new Rectangle(15, 1, 2, 1);
        data[24] = new Rectangle(0, 19, 1, 4);
        data[25] = new Rectangle(1, 17, 1, 2);
        data[26] = new Rectangle(21, 15, 1, 2);
        data[27] = new Rectangle(15, 21, 2, 1);
        data[28] = new Rectangle(17, 20, 2, 1);
        data[29] = new Rectangle(20, 17, 1, 2);
        data[30] = new Rectangle(1, 23, 2, 1);
        data[31] = new Rectangle(4, 23, 2, 1);
        data[32] = new Rectangle(7, 21, 2, 1);
        data[33] = new Rectangle(3, 21, 1, 2);
        data[34] = new Rectangle(3, 17, 1, 2);
        data[35] = new Rectangle(19, 19, 1, 1);
        data[36] = new Rectangle(6, 22, 1, 1);
        data[37] = new Rectangle(21, 6, 1, 2);
        data[38] = new Rectangle(4, 20, 3, 1);
        data[39] = new Rectangle(4, 19, 1, 1);
        data[40] = new Rectangle(20, 4, 1, 2);
        data[41] = new Rectangle(19, 3, 1, 1);
        data[42] = new Rectangle(18, 1, 1, 2);
        data[43] = new Rectangle(17, 2, 1, 1); //EOB
        data[44] = new Rectangle(21, 1, 1, 1);
        data[45] = new Rectangle(14, 1, 1, 1);
        data[46] = new Rectangle(9, 1, 1, 1);
        data[47] = new Rectangle(2, 2, 1, 1);
        data[48] = new Rectangle(2, 5, 1, 1);
        data[49] = new Rectangle(18, 3, 1, 1);
        data[50] = new Rectangle(19, 4, 1, 1);
        data[51] = new Rectangle(21, 8, 1, 1);
        data[52] = new Rectangle(3, 15, 1, 2);
        data[53] = new Rectangle(20, 12, 1, 1);
        data[54] = new Rectangle(21, 14, 1, 1);
        data[55] = new Rectangle(3, 7, 1, 1);
        data[56] = new Rectangle(4, 8, 1, 1);
        data[57] = new Rectangle(3, 10, 1, 1);
        data[58] = new Rectangle(19, 18, 1, 1);
        data[59] = new Rectangle(18, 19, 1, 1);
        data[60] = new Rectangle(4, 18, 1, 1);
        data[61] = new Rectangle(5, 19, 1, 1);
        data[62] = new Rectangle(14, 21, 1, 1);
        data[63] = new Rectangle(9, 21, 1, 1);
        data[64] = new Rectangle(4, 21, 3, 1);
        data[65] = new Rectangle(4, 22, 2, 1);
        data[66] = new Rectangle(1, 19, 2, 4);
        data[67] = new Rectangle(2, 17, 1, 2);
        data[68] = new Rectangle(3, 19, 1, 2); //EOP
        data[69] = new Rectangle(5, 2, 2, 1);
        data[70] = new Rectangle(4, 3, 1, 1);
        data[71] = new Rectangle(3, 4, 1, 2);
        data[72] = new Rectangle(2, 6, 1, 2);
        data[73] = new Rectangle(1, 8, 1, 6);
        data[74] = new Rectangle(2, 10, 1, 3);
        data[75] = new Rectangle(3, 8, 1, 2);
        data[76] = new Rectangle(3, 15, 2, 1);
        data[77] = new Rectangle(5, 14, 1, 1);
        data[78] = new Rectangle(6, 13, 1, 1);
        data[79] = new Rectangle(7, 11, 1, 2); //EOB
        data[80] = new Rectangle(7, 10, 1, 1);
        data[81] = new Rectangle(2, 13, 1, 1);
        data[82] = new Rectangle(2, 8, 1, 1);
        data[83] = new Rectangle(3, 6, 1, 1);
        data[84] = new Rectangle(4, 4, 1, 1);
        data[85] = new Rectangle(5, 3, 1, 1); //EOP
        data[86] = new Rectangle(7, 2, 10, 1);
        data[87] = new Rectangle(10, 1, 4, 1); //EOW
    }

    /**
     * Reports the name of the object involved in a collision.
     * 
     * @return the name of the Class, as a String
     */
    public String getType() {
        return "Kirby";
    }

    /**
     * Determines the appropriate response to a collision, and returns the status code.
     * @param Moveable2DShape other, the other shape to check in a collision.
     * @return int collision code. 0 = harmless, 3 = removes object, 4 = death for Kirby.
     */
    public int collisionResponse(Moveable2DShape other) {
        int statusCode = 0; //Code to be returned
        Rectangle collider = this.getBoundingBox().intersection(other.getBoundingBox());
        boolean hMotion = false; //Algorithm for determining which axis a collision occurs on. See readme
        boolean vMotion = false;
        int cWidth = (int)collider.getWidth(); //Get the union between the two objects - width
        int cHeight = (int)collider.getHeight();//Get the union between the two objects - height
        if ((cWidth * cHeight) < 4) //If the overlap is small, change both directions
            vMotion = hMotion = true;
        if (cWidth > 1 && cHeight == 1) //If the overlap in one direction is 1px, determine the axis
            vMotion = true;
        else
            hMotion = true;
        if (other.getType().equals("Block") && (other.getBoundingBoxWidth() * other.getBoundingBoxHeight()) != 1 && !lastEncounter.equals(other.toString())) {
            lastEncounter = other.toString(); //Stores the last object Kirby collided with
            if (vMotion) {
                flip = !flip; //Flip Kirby Horizontally
                deltaH *= -1; //Change Kirby's direction
            }
            if (hMotion)
                deltaV *= -1; //Change Kirby's direction
        }
        else if (other.getType().equals("Squishy") || other.getType().equals("WaddleDee"))
            try {
                VivariumViewer.damage(); //Play the sound effect
                statusCode = other.collisionResponse(this); //Get the damage reported by the other object
                counter = 5; //Set Kirby to flicker on the next few frames.
            } catch (Exception e) {} //Likely the sound effect crashing, if it fails, it's unimportant.
        else if (other.getType().equals("Gordo"))
            try {
                VivariumViewer.death();
                return 4; 
            } catch (Exception e) {} //Likely the sound effect crashing, if it fails, it's unimportant.
        else if (other.getType().equals("Kirby")) { //There should only ever be one Kirby. He's unique.
            deltaV *= -1; //Change Kirby's Vertical Direction
            deltaH *= -1; //Change Kirby's horizontal direction
        }
        return statusCode;
    }
}

