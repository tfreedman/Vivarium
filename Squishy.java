import java.awt.*;

/**
 * The Squishy Class, used for creating Squishy objects. Squishy is the name of the squid
 * character in Kirby's Dream Land, so the class is named after it.
 * 
 * @author Tyler Freedman 
 * @version April 15, 2011
 */
public class Squishy extends Creature
{
    int counter = 0; //Used to generate jerky motion
    int deltaH = 1; //Default horizontal direction
    int deltaV = -1; //Default vertical direction
    Rectangle data[] = new Rectangle[91];
    Color[] colors = {Color.BLUE, Color.CYAN, Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.YELLOW};
    int colorPosition = 0; //What index in the array to access
    Color color;

    /**
     * Constructs a Squishy object
     * @param int X Position, YPosition, shape Width, shape Height, Bounding Box Width, Bounding Box Height. Double X Speed, Y Speed.
     */
    public Squishy(int xPos, int yPos, int shapeWidth, int shapeHeight, int bwidth, int bheight, double xSpeed, double ySpeed) {
        super(xPos, yPos, shapeWidth, shapeHeight, bwidth, bheight, xSpeed, ySpeed);
    }

    /**
     * Draws Squishy on screen.
     * @param Graphics2D g
     */
    public void draw(Graphics2D g) {
        Graphics2D g2 = (Graphics2D) g;
        if (counter <= 3) { //Used to generate the jerky motion.
            for (int i = 0; i < 57; i++) {
                if (i == 0) //Sets the color of certain rectangles in the array
                    g2.setColor(Color.BLACK);
                else if (i == 11)
                    g2.setColor(Color.WHITE);
                else if (i == 34)
                    g2.setColor(colors[colorPosition%7]);
                g2.fill(data[i]);
            }
            counter++;
        }
        if (counter >= 4) { //The other part of the jerky motion
            for (int i = 57; i < 91; i++) {
                if (i == 57)
                    g2.setColor(Color.BLACK);
                else if (i == 67)
                    g2.setColor(Color.WHITE);
                else if (i == 78)
                    g2.setColor(colors[colorPosition%7]);
                g2.fill(data[i]);
            }
            counter++;
            if (counter == 10) //Repeat the cycle
                counter = 0;
        }
    }

    /**
     * Constructs Squishy out of Rectangles. All of the frames needed to animate Squishy's motions are
     * stored in the array, and segments of each are loaded depending on what frame is necessary.
     * 
     * By storing data in the array and procedurally generating the rest, other methods can modify 
     * the image in real time.
     */
    public void assemble() {
        data[0] = new Rectangle(4, 1, 8, 14);
        data[1] = new Rectangle(3, 2, 10, 13);
        data[2] = new Rectangle(1, 7, 14, 7);
        data[3] = new Rectangle(2, 14, 12, 1);
        data[4] = new Rectangle(6, 0, 4, 1);
        data[5] = new Rectangle(2, 3, 12, 3);
        data[6] = new Rectangle(0, 8, 16, 2);
        data[7] = new Rectangle(0, 11, 16, 2);
        data[8] = new Rectangle(4, 15, 2, 1);
        data[9] = new Rectangle(7, 15, 2, 1);
        data[10] = new Rectangle(10, 15, 2, 1); //EOB
        data[11] = new Rectangle(7, 1, 2, 10);
        data[12] = new Rectangle(5, 2, 6, 3);
        data[13] = new Rectangle(4, 9, 8, 1);
        data[14] = new Rectangle(5, 6, 1, 3);
        data[15] = new Rectangle(10, 6, 1, 3);
        data[16] = new Rectangle(6, 5, 4, 2);
        data[17] = new Rectangle(4, 3, 8, 2);
        data[18] = new Rectangle(2, 11, 2, 2); 
        data[19] = new Rectangle(12, 11, 2, 2);
        data[20] = new Rectangle(14, 8, 1, 2);
        data[21] = new Rectangle(1, 8, 1, 2);
        data[22] = new Rectangle(5, 12, 1, 3);     
        data[23] = new Rectangle(10, 12, 1, 3);     
        data[24] = new Rectangle(7, 14, 2, 1);
        data[25] = new Rectangle(6, 10, 4, 1);
        data[26] = new Rectangle(2, 8, 1, 1);
        data[27] = new Rectangle(4, 8, 1, 1);
        data[28] = new Rectangle(11, 8, 1, 1);
        data[29] = new Rectangle(13, 8, 1, 1);
        data[30] = new Rectangle(2, 10, 1, 1);
        data[31] = new Rectangle(13, 10, 1, 1);
        data[32] = new Rectangle(1, 11, 1, 1);
        data[33] = new Rectangle(14, 11, 1, 1); //EOW
        data[34] = new Rectangle(3, 3, 1, 3);
        data[35] = new Rectangle(12, 3, 1, 3); 
        data[36] = new Rectangle(7, 13, 2, 1);
        data[37] = new Rectangle(4, 10, 2, 1);
        data[38] = new Rectangle(10, 10, 2, 1);
        data[39] = new Rectangle(11, 13, 1, 2);     
        data[40] = new Rectangle(4, 13, 1, 2);     
        data[41] = new Rectangle(4, 2, 1, 1);
        data[42] = new Rectangle(11, 2, 1, 1);
        data[43] = new Rectangle(6, 1, 1, 1);
        data[44] = new Rectangle(9, 1, 1, 1);
        data[45] = new Rectangle(5, 5, 1, 1);
        data[46] = new Rectangle(10, 5, 1, 1);
        data[47] = new Rectangle(4, 7, 1, 1);
        data[48] = new Rectangle(11, 7, 1, 1);
        data[49] = new Rectangle(2, 9, 1, 1);
        data[50] = new Rectangle(13, 9, 1, 1);
        data[51] = new Rectangle(1, 12, 1, 1);
        data[52] = new Rectangle(6, 12, 1, 1);
        data[53] = new Rectangle(9, 12, 1, 1);
        data[54] = new Rectangle(14, 12, 1, 1);
        data[55] = new Rectangle(2, 13, 1, 1);
        data[56] = new Rectangle(13, 13, 1, 1); //EOC
        data[57] = new Rectangle(4, 7, 8, 8);
        data[58] = new Rectangle(2, 8, 12, 7);
        data[59] = new Rectangle(1, 9, 14, 2);
        data[60] = new Rectangle(1, 12, 14, 3);
        data[61] = new Rectangle(0, 13, 16, 2);
        data[62] = new Rectangle(1, 15, 2, 1);
        data[63] = new Rectangle(4, 15, 2, 1);
        data[64] = new Rectangle(7, 15, 2, 1);
        data[65] = new Rectangle(10, 15, 2, 1);
        data[66] = new Rectangle(13, 15, 2, 1); //EOB
        data[67] = new Rectangle(5, 8, 6, 3);
        data[68] = new Rectangle(4, 9, 8, 2);
        data[69] = new Rectangle(3, 10, 10, 1);
        data[70] = new Rectangle(5, 12, 6, 1);
        data[71] = new Rectangle(1, 13, 2, 1);
        data[72] = new Rectangle(13, 13, 2, 1);
        data[73] = new Rectangle(7, 14, 2, 1);
        data[74] = new Rectangle(2, 14, 1, 1);
        data[75] = new Rectangle(5, 14, 1, 1);
        data[76] = new Rectangle(10, 14, 1, 1);
        data[77] = new Rectangle(13, 14, 1, 1); //EOW
        data[78] = new Rectangle(6, 11, 4, 1);
        data[79] = new Rectangle(4, 12, 1, 1);
        data[80] = new Rectangle(11, 12, 1, 1);
        data[81] = new Rectangle(4, 8, 1, 1);
        data[82] = new Rectangle(11, 8, 1, 1);
        data[83] = new Rectangle(2, 9, 2, 1);
        data[84] = new Rectangle(12, 9, 2, 1);
        data[85] = new Rectangle(2, 10, 1, 1);
        data[86] = new Rectangle(13, 10, 1, 1);
        data[87] = new Rectangle(1, 14, 1, 1);
        data[88] = new Rectangle(4, 14, 1, 1);
        data[89] = new Rectangle(11, 14, 1, 1);
        data[90] = new Rectangle(14, 14, 1, 1);
    }

    /**
     * Moves the creature
     */
    public void move() {
        assemble(); //Assemble the rectangles to be drawn
        if (((getXPos() <= 0) && deltaH == -1) || ((getXPos() + getBoundingBoxWidth() >= Vivarium.getFrameWidth()) && deltaH == 1)) {
            deltaH *= -1; //Prevents the image from going off screen by reversing direction
            colorPosition++; //Increase the color displayed
        }
        if (((getYPos() + getBoundingBoxHeight() >= Vivarium.getFrameHeight()) && deltaV == 1) || ((getYPos() <= 0) && deltaV == -1)) {
            deltaV *= -1; //Prevents the image from going off screen by reversing direction
            colorPosition++; //Increase the color displayed.
        }
        if (counter <= 3) //Which jerky frame to generate
            moveTo(getXPos() + (deltaH * getXSpeed()), getYPos() +  1 + getYSpeed() + (deltaV * getYSpeed()));
        else
            moveTo(getXPos() + (deltaH * getXSpeed()), getYPos() - 1 - getYSpeed() + (deltaV * getYSpeed()));
        for (int i = 0; i < data.length; i++) //Updates the rectangles' position
            data[i].setLocation((int)(data[i].getX() + getXPos()), (int)(data[i].getY() + getYPos()));
    }

    /**
     * Reports the name of the object involved in a collision.
     * 
     * @return the name of the Class, as a String
     */
    public String getType() {
        return "Squishy";
    }

    /**
     * Determines the appropriate response to a collision, and returns the status code.
     * @param Moveable2DShape other, the other shape to check in a collision.
     * @return int collision code. 0 = harmless, 3 = removes object, 4 = death for Kirby.
     */
    public int collisionResponse(Moveable2DShape other) {
        Rectangle collider = this.getBoundingBox().intersection(other.getBoundingBox());
        boolean hMotion = false; //Algorithm for determining which axis a collision occurs on. See readme
        boolean vMotion = false;
        int cWidth = (int)collider.getWidth(); //Get the union between the two objects - width
        int cHeight = (int)collider.getHeight(); //Get the union between the two objects - height
        if ((cWidth * cHeight) == 1) //If the overlap is small, change both directions
            vMotion = hMotion = true;
        if (cWidth > 1 && cHeight == 1) //If the overlap in one direction is 1px, determine the axis
            vMotion = true;
        else
            hMotion = true;
        if (other.getType().equals("WaddleDee")) {
            colorPosition++; //Increase the color
            deltaH *= -1; //Change Squishy's direction
            deltaV *= -1; //Change Squishy's direction
            other.collisionResponse(this); //Figure out what he's doing in the collision
        }
        else if ((other.getType().equals("Block") || other.getType().equals("Squishy")) && (other.getBoundingBoxWidth() * other.getBoundingBoxHeight()) != 1) {
            if (vMotion)
                deltaH *= -1;//Change Squishy's Vertical Direction
            if (hMotion)
                deltaV *= -1; //Change Squishy's horizontal direction
            colorPosition++; //Increase the color.
        }
        if (colorPosition == colors.length) //If the color is the end of the array, start at 0
            colorPosition = 0;
        else if (other.getType().equals("Gordo") || other.getType().equals("Kirby"))
            return 3; //Commit suicide if touched by Gordo or Kirby.
        return 0;
    }
}