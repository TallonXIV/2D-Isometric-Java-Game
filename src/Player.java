import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Player {
    //Globals for vertical (y) and horizontal (y) displacement
    private int dx;
    private int dy;

    //Initial position of player
    private int x = 700;
    private int y = 400;

    //Global image and  integers
    private int w;
    private int h;
    private Image image;

    //Constructor
    public Player() {
        loadImage();
    }//end Player constructor

    //loads the image for the playerModel icon. In this case, my face
    private void loadImage() {
        ImageIcon playerModel = new ImageIcon("src//entityModels//playerIcon.jpg");
        image = playerModel.getImage();
        w = image.getWidth(null);
        h = image.getHeight(null);
    }//end loadImage

    //This method handles movement as well as panel wrapping
    public void move() {
        //Gives illusion of window wrapping by setting player to another location upon going too far left or right
        if(x == 1500) {
            x = -100;
        }else if (x == -100) {
            x = 1500;
        }
        x += dx;


        //Gives illusion of window wrapping by setting player to opposite end if they go too far north or south
        if(y == 1000) {
            y = -100;
        }else if (y == -100) {
            y = 1000;
        }
        y += dy;
    }//end move

    public Rectangle getBounds(){
        //this is for collision detection
        return new Rectangle(x, y, w, h);
    }
    //Getters
    public int getX() { return x; }//end getX
    public int getY() { return y; }//end getY

    public int getWidth() { return w; }//end getWidth
    public int getHeight() { return h; }//end getHeight

    public Image getImage() { return image; }//end getImage

    //Sets up key-press detection for WASD keys OR arrow keys
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        //if W, A, S, or D is pressed, ensure a displacement value in the appropriate direction of 1
        if (key == KeyEvent.VK_A) {
            dx = -1;
        }//end if
        if (key == KeyEvent.VK_D) {
            dx = 1;
        }//end if
        if (key == KeyEvent.VK_W) {
            dy = -1;
        }//end if
        if (key == KeyEvent.VK_S) {
            dy = 1;
        }//end if
    }//end keyPressed

    //detects the releasing of pressed keys
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        //if W, A, S, or D is pressed and then released, stop moving by setting displacement to 0
        if (key == KeyEvent.VK_A) {
            dx = 0;
        }//end if
        if (key == KeyEvent.VK_D) {
            dx = 0;
        }//end if
        if (key == KeyEvent.VK_S) {
            dy = 0;
        }//end if
        if (key == KeyEvent.VK_W) {
            dy = 0;
        }//end if
    }//end keyReleased

    //acts as a game over by removing player model from view
    public void gameOver(){
        image = null;
    }//end gameOver
}//end Player class