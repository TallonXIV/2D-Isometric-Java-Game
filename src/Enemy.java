/*NOTE: I want to give credit where credit is due: Zetcode's Space Invaders code acted very much as a template for the
structure of this class file. I obviously made many changes in regards to my vision for my game, but they did it very
effective and I used some of their method structures in this and my player class.*/

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Enemy {
    //Globals for vertical (y) and horizondal (y) displacement
    private double dx;
    private double dy;

    //Initial position of enemy
    private int x = 200;
    private int y = 350;
    private int bounces = 0;

    //Global image integers
    private int w;
    private int h;

    //A difficulty slider, more or less
    int difficulty = 20;
    int speedMitigator = 0;
    boolean hasFired = false;

    //declaration of image file in case an enemy model is desired
    ImageIcon enemyModel;
    private Image image;

    //Constructor
    public Enemy() {
        loadImage();
    }//end enemy constructor

    //loads the image for the enemyModel icon.
    private void loadImage() {
        //pulls image from file
        enemyModel = new ImageIcon("src//entityModels//enemyIcon.png");
        image = enemyModel.getImage();

        //declaration of width and height
        w = image.getWidth(null);
        h = image.getHeight(null);
    }//end loadImage

    public Rectangle getBounds(){
        //getBounds for collision detection
        Rectangle hb = new Rectangle(x, y, w, h);
        return hb;
    }//end getBounds

    //This method handles movement and bouncing
    public void move() {
        //if they hit the edges, reverse forces exerted
        if(x > 1500 || x < -100 ){
            //add to bounces for score
            bounces++;

            //reverse movement and add to horizontal(x) displacement
            dx = -dx;
            x += dx;
        }//end if
        if(speedMitigator % difficulty == 0){
            x += dx;
        }//end if

        if(y > 900 || y < -50) {
            bounces++;

            //reverse movement and add to the vertical (y) displacement
            dy = -dy;
            y += dy;
        }//end if
        if(speedMitigator % difficulty == 0){
            y += dy;
        }//end if

        //this changes the speed as the game progresses by calculating bounces (15=easy, 1=hard)
        switch (bounces){
            case 2:
                difficulty = 15;
                break;
            case 4:
                difficulty = 10;
                break;
            case 6:
                difficulty = 5;
                break;
            case 8:
                difficulty = 2;
                break;
            case 15:
                difficulty = 1;
        }//end witch

        //increment speedMitigator to give value to prior modulo functions (dictates speed/difficulty)
        speedMitigator++;
    }//end move

    //Getters
    public int getX() { return x; }//end getX
    public int getY() { return y; }//end getY

    public int getWidth() { return w; }//end getWidth
    public int getHeight() { return h; }//end getHeight

    public int getScore(){ return bounces; }//end getScore
    public boolean getHasFired(){ return hasFired; }//end getHasFired

    //Sets up key-press detection for WASD keys OR arrow keys
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        //if space key is pressed, add to vertical and horizontal displacement, giving it its south-east approach
        if (key == KeyEvent.VK_SPACE) {
            dx = 1;
            dy = 1;
            hasFired = true;
        }//end if
    }//end keyPressed
}//end Enemy class