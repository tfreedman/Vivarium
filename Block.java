import java.awt.*;

/**
 * The Block Class, used for creating Block objects. A Block object is an 
 * invisible platform in which objects can interact with.
 * 
 * @author Tyler Freedman 
 * @version April 15, 2011
 */
public class Block extends Creature
{
    /**
     * Constructs a block of a given size, at a given location
     *
     * @param int xPos, int yPos - initial x and y location, int shapeWidth, shapeHeight - width and height of the Block object.
     */
    public Block(int xPos, int yPos, int shapeWidth, int shapeHeight) {
        super(xPos, yPos, shapeWidth, shapeHeight, shapeWidth, shapeHeight, 0, 0); //Blocks can't move
    }

    /**
     * Draws the object onto the Vivarium. By default, blocks are invisible, but they
     * can be made visible by adding g2.setColor(Color.ORANGE); g2.fill(data);, and
     * creating Rectangle data = new Rectangle (xPos, yPos, shapeWidth, shapeHeight);
     * 
     * @param Graphics2D g
     */
    public void draw(Graphics2D g) {
        Graphics2D g2 = (Graphics2D) g;
    }

    /**
     * Overrides Moveable2DShape's default move method with one that prevents a
     * block object from moving. Does nothing, but removing it will break the program.
     */
    public void move() { 
    } 

    /**
     * Reports the name of the object involved in a collision.
     * 
     * @return the name of the Class, as a String
     */
    public String getType() {
        return "Block";
    }

    /**
     * Goes to the other object's collisionResponse method, in the event of a collision,
     * since a block is completely harmless and does nothing.
     * 
     * @return collision damage. 0 = harmless, 3 = removes object, 4 = death for Kirby.
     */
    public int collisionResponse(Moveable2DShape other) {
        if (!other.getType().equals("Block"))
            other.collisionResponse(this);
        return 0;
    }
}