import javax.swing.*;
import java.awt.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.event.*;

/**
 * The Viewer program for the Vivarium Project.
 * 
 * @author Tyler Freedman 
 * @version April 15, 2011
 */
public class VivariumViewer 
{   
    static final int FRAME_WIDTH = 900, FRAME_HEIGHT = 128; //Frame height and width
    static java.applet.AudioClip clip; //Background Music
    static final int DELAY = 66; // Milliseconds between timer ticks - 66 == 15fps
    static Vivarium vivarium = new Vivarium(); //Creates the Vivarium
    public static void main(String[] args) throws InterruptedException, Exception {
        JFrame frame = new JFrame(); //New Frame
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setResizable(false);
        frame.setTitle("Vivarium");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try { //Tries to set the background. Should never fail, unless the project itself is broken.
            frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("background.png")))));
        } catch (IOException e) {} //Nothing we can do at this point to fix it, if it does fail.

        frame.pack();
        frame.setVisible(true);
        clip = java.applet.Applet.newAudioClip (new URL("file:Float.wav"));
        clip.loop (); //Begins playing the background music, as a loop.
        vivarium.setBounds (0, 0, FRAME_WIDTH, FRAME_HEIGHT); 
        frame.add(vivarium); //Displays it

        class FrameGenerator implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                VivariumViewer.vivarium.repaint();      //Draws the frame
            }
        }
        ActionListener listener = new FrameGenerator();
        final Timer t = new Timer(DELAY, listener); // Redraws the frame each 66ms
        t.start(); //Starts the timer
    }

    /**
     * Plays a sound effect for when Kirby has taken damage.
     */
    public static void damage() throws Exception {
        java.applet.AudioClip damage = java.applet.Applet.newAudioClip (new URL("file:Contact.wav"));
        damage.play ();
    }

    /**
     * Plays a sound effect for when Kirby dies. Also stops the background music.
     */
    public static void death() throws Exception {
        java.applet.AudioClip death = java.applet.Applet.newAudioClip (new URL("file:Death.wav"));
        clip.stop();
        death.play();
    }
}
