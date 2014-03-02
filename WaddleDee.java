import java.awt.*;

/**
 * The WaddleDee Class, used for creating WaddleDee objects. Waddle Dee is the name
 * of the red thing that walks on the platforms. Named after the character in Kirby's Dream Land.
 * 
 * @author Tyler Freedman 
 * @version April 15, 2011
 */
public class WaddleDee extends Creature
{
    boolean trigger; //Alternates between frames of WaddleDee
    Rectangle data[] = new Rectangle[82];
    int deltaH = 1; //Default horizontal direction
    int deltaV = -1; //Default vertical direction
    boolean flip = true; //Should WaddleDee be flipped horizontally?
    Color color;

    /**
     * Constructs a WaddleDee object
     * @param int X Position, YPosition, shape Width, shape Height, Bounding Box Width, Bounding Box Height. Double X Speed, Y Speed. Color color.
     */
    public WaddleDee(int xPos, int yPos, int shapeWidth, int shapeHeight, int bwidth, int bheight, double xSpeed, double ySpeed, Color color) {
        super(xPos, yPos, shapeWidth, shapeHeight, bwidth, bheight, xSpeed, ySpeed);
        this.color = color;
    }

    /**
     * Draws WaddleDee on screen.
     * @param Graphics2D g
     */
    public void draw(Graphics2D g) {
        Graphics2D g2 = (Graphics2D) g;
        if (!trigger) //First frame of WaddleDee
            for (int i = 0; i < 46; i++) {
                if (i == 0) //Sets the color of certain rectangles in the array
                    g2.setColor(color);
                else if (i == 4)
                    g2.setColor(Color.WHITE);
                else if (i == 16)
                    g2.setColor(Color.BLACK);
                g2.fill(data[i]);
        }

        else if(trigger) //Second frame of WaddleDee
            for (int i = 46; i < data.length; i++) {
                if (i == 46)
                    g2.setColor(Color.BLACK);
                else if (i == 56)
                    g2.setColor(Color.WHITE);
                else if (i == 66)
                    g2.setColor(color);
                g2.fill(data[i]);
        }
        trigger = !trigger; //Change the next frame to the opposite of this one
    }

    /**
     * Constructs Waddle Dee out of Rectangles. All of the frames needed to animate Waddle Dee's motions are
     * stored in the array, and segments of each are loaded depending on what frame is necessary.
     * 
     * By storing data in the array and procedurally generating the rest, other methods can modify 
     * the image in real time.
     */
    public void assemble() {
        data[0] = new Rectangle(3, 1, 9, 13); 
        data[1] = new Rectangle(12, 3, 3, 9);
        data[2] = new Rectangle(1, 3, 2, 9);
        data[3] = new Rectangle(12, 12, 1, 1); //EOP
        data[4] = new Rectangle(8, 2, 2, 1);
        data[5] = new Rectangle(9, 3, 1, 1);
        data[6] = new Rectangle(2, 4, 5, 5);
        data[7] = new Rectangle(3, 3, 3, 1);
        data[8] = new Rectangle(1, 6, 1, 3);
        data[9] = new Rectangle(2, 9, 4, 1);
        data[10] = new Rectangle(2, 14, 3, 1);
        data[11] = new Rectangle(11, 14, 3, 1);
        data[12] = new Rectangle(14, 12, 1, 2);
        data[13] = new Rectangle(13, 13, 1, 1);
        data[14] = new Rectangle(1, 12, 1, 2);
        data[15] = new Rectangle(2, 13, 1, 1); //EOW
        data[16] = new Rectangle(2, 15, 8, 1);
        data[17] = new Rectangle(5, 14, 6, 1);
        data[18] = new Rectangle(11, 15, 3, 1);
        data[19] = new Rectangle(15, 4, 1, 5);
        data[20] = new Rectangle(5, 0, 5, 1);
        data[21] = new Rectangle(7, 4, 1, 5);
        data[22] = new Rectangle(1, 10, 5, 1);
        data[23] = new Rectangle(11, 3, 4, 1);
        data[24] = new Rectangle(2, 2, 4, 1);
        data[25] = new Rectangle(0, 6, 1, 4);
        data[26] = new Rectangle(3, 4, 1, 3);
        data[27] = new Rectangle(14, 8, 1, 4);
        data[28] = new Rectangle(1, 3, 1, 3);
        data[29] = new Rectangle(12, 9, 2, 1);
        data[30] = new Rectangle(10, 1, 2, 1);
        data[31] = new Rectangle(3, 1, 2, 1);
        data[32] = new Rectangle(15, 12, 1, 2);
        data[33] = new Rectangle(11, 13, 2, 1);
        data[34] = new Rectangle(0, 12, 1, 2);
        data[35] = new Rectangle(3, 13, 2, 1);
        data[36] = new Rectangle(13, 12, 1, 1);
        data[37] = new Rectangle(10, 4, 1, 1);
        data[38] = new Rectangle(1, 11, 1, 1);
        data[39] = new Rectangle(2, 12, 1, 1);
        data[40] = new Rectangle(1, 14, 1, 1);
        data[41] = new Rectangle(14, 14, 1, 1);
        data[42] = new Rectangle(14, 4, 1, 1);
        data[43] = new Rectangle(12, 2, 1, 1);
        data[44] = new Rectangle(6, 3, 1, 1);
        data[45] = new Rectangle(6, 9, 1, 1); //EOB
        data[46] = new Rectangle(2, 3, 11, 11);
        data[47] = new Rectangle(1, 4, 13, 9);
        data[48] = new Rectangle(5, 1, 6, 2);
        data[49] = new Rectangle(14, 5, 1, 7);
        data[50] = new Rectangle(0, 6, 16, 3);
        data[51] = new Rectangle(3, 2, 9, 13);
        data[52] = new Rectangle(0, 9, 1, 1);
        data[53] = new Rectangle(5, 15, 5, 1);
        data[54] = new Rectangle(12, 2, 1, 1);
        data[55] = new Rectangle(13, 3, 1, 1); //EOB
        data[56] = new Rectangle(5, 14, 5, 1);
        data[57] = new Rectangle(8, 2, 2, 1);
        data[58] = new Rectangle(9, 3, 1, 1);
        data[59] = new Rectangle(1, 8, 6, 2);
        data[60] = new Rectangle(5, 5, 2, 3);
        data[61] = new Rectangle(2, 4, 4, 1);
        data[62] = new Rectangle(1, 6, 1, 2);
        data[63] = new Rectangle(7, 6, 1, 3);
        data[64] = new Rectangle(2, 10, 3, 1);
        data[65] = new Rectangle(3, 5, 1, 3); //EOW
        data[66] = new Rectangle(9, 6, 6, 3);
        data[67] = new Rectangle(11, 5, 3, 5);
        data[68] = new Rectangle(8, 4, 5, 2);
        data[69] = new Rectangle(7, 11, 6, 2);
        data[70] = new Rectangle(2, 12, 6, 1);
        data[71] = new Rectangle(8, 10, 3, 1);
        data[72] = new Rectangle(10, 13, 2, 1);
        data[73] = new Rectangle(3, 13, 2, 1);
        data[74] = new Rectangle(5, 10, 2, 1);
        data[75] = new Rectangle(5, 2, 3, 1);
        data[76] = new Rectangle(10, 3, 3, 1);
        data[77] = new Rectangle(7, 3, 2, 1);
        data[78] = new Rectangle(10, 2, 1, 1);
        data[79] = new Rectangle(9, 9, 1, 1);
        data[80] = new Rectangle(6, 4, 1, 1);
        data[81] = new Rectangle(7, 9, 1, 1);
    }

    /**
     * Moves the creature
     */
    public void move() {
        assemble(); //Assemble the rectangles to be drawn
        if (((getXPos() <= 0) && deltaH == -1) || ((getXPos() + getBoundingBoxWidth() >= Vivarium.getFrameWidth()) && deltaH == 1)) {
            deltaH *= -1;  //Prevents the image from going off screen by reversing direction
            flip = !flip; //Flips Waddle Dee's sprite
        }
        if (((getYPos() + getBoundingBoxHeight() >= Vivarium.getFrameHeight()) && deltaV == 1) || ((getYPos() <= 0) && deltaV == -1))
            deltaV *= -1; //Prevents the image from going off screen by reversing direction
        moveTo(getXPos() + (deltaH * getXSpeed()), getYPos() + (deltaV * getYSpeed())); //Moves WaddleDee
        if (flip) //Displays WaddleDee flipped
            flipH(data, (int)getBoundingBoxWidth());
        for (int i = 0; i < data.length; i++) //Updates the rectangles' position
            data[i].setLocation((int)(data[i].getX() + getXPos()), (int)(data[i].getY() + getYPos()));
    }

    /**
     * Reports the name of the object involved in a collision.
     * 
     * @return the name of the Class, as a String
     */
    public String getType() {
        return "WaddleDee";
    }

    /**
     * Forces Waddle Dee to be rendered backwards, and changes his direction.
     */
    public void flip() {
        flip = !flip;
        deltaH *= -1;
    }

    /**
     * Determines the appropriate response to a collision, and returns the status code.
     * @param Moveable2DShape other, the other shape to check in a collision.
     * @return int collision code. 0 = harmless, 3 = removes object, 4 = death for Kirby.
     */
    public int collisionResponse(Moveable2DShape other) {
        if (other.getType().equals("WaddleDee")) {
            this.flip(); //Makes the two colliding Waddle Dees bounce off each other
            other.flip();
        }
        else if (other.getType().equals("Kirby") || other.getType().equals("Gordo"))
            return 3; //Destroys itself
        else if (other.getType().equals("Squishy") || other.getType().equals("Block")) {
            deltaH *= -1; //Change Waddle Dee's Horizontal Direction
            deltaV *= -1; //Change Waddle Dee's Vertical Direction
            flip = !flip; //Changes the direction of the sprite
        }
        return 0;
    }
}
