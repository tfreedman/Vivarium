import java.awt.Graphics2D ;
import java.awt.Rectangle ;

abstract public class Moveable2DShape extends Shape2D 
{
    public int environmentWidth = 900;
    public int environmentHeight = 128;
    double xSpeed;
    double ySpeed;
    final double DEFAULT_XSPEED = 1.0;
    final double DEFAULT_YSPEED = 1.0;
    public static final int MAXSPEED = 5;

    public Moveable2DShape(int xPos, int yPos, int shapeWidth, int shapeHeight, int bwidth, int bheight, double xSpeed, double ySpeed) {
        super(xPos, yPos, shapeWidth, shapeHeight);
        setBoundaries(bwidth, bheight);
        setSpeed(xSpeed, ySpeed);   
    }

    // Move the shape around the rectangular environment
    // Do not allow the shape to go outside of the environment boundaries
    public void move()
    {
        double x = getXPos();
        double y = getYPos();

        x += xSpeed;
        y += ySpeed;

        /*
        Reflect the object if it goes too far
         */
        int overShoot = (int) (x + getBoundingBoxWidth()/2 - environmentWidth);
        if (x <= 0) {
            x = 0;
            xSpeed = -xSpeed ;
        }
        else if (overShoot > 0) {
            x = environmentWidth - getBoundingBoxWidth()/2;
            xSpeed = - xSpeed ;
        }
        /*
        Reflect the object if it goes too far
         */
        overShoot = (int) (y + getBoundingBoxHeight()/2 - environmentHeight);
        if (y <= 0) {
            y = 0;
            ySpeed = -ySpeed ;
        }
        else if (overShoot > 0) {
            y = environmentHeight - getBoundingBoxHeight()/2;;
            ySpeed = - ySpeed ;
        }

        // move the shape to the new position
        moveTo(x,y);
    }

    public void setSpeed(double xspeed, double yspeed) {
        this.xSpeed = xspeed;
        this.ySpeed = yspeed;
    } 

    public double getXSpeed() {
        return xSpeed;
    }

    public double getYSpeed() {
        return ySpeed;
    }

    void setBoundaries(int width, int height) {
        environmentWidth = width;
        environmentHeight= height;
    }

    // Detect if there is a collision between this Moveable2DShape
    // object and the other Moveable2DShape object. Return true if a collision
    // occurs, false otherwise.
    abstract public boolean collisionDetect(Moveable2DShape other);

    // Reports the name of the class involved in a collision.
    abstract public String getType();

    // Calling flip() triggers the creature to rotate on an axis.
    // The axis depends on the creature.
    abstract public void flip();

    // Respond to the collision of this Moveable2DShape object and
    // the other Moveable2DShape object
    abstract public int  collisionResponse(Moveable2DShape other);
}

