import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class GameWindow extends JPanel implements ActionListener {
    Timer timer;

    //global variables for dim of projectile, counter for object enlargement, and a final score value
    int delayTimer = 10;
    int w = 0;
    int h = 0;
    int counterGrow = 0;
    int finalScore = 0;

    //booleans for resizing consistently and ending the game
    boolean resize = false;
    boolean endGame = false;
    boolean hasFired = false;

    //declaration of a single player and a single enemy
    Player player;
    Enemy enemy;

    //JLabel declarations along with the declaration for the intended font
    Font titleFont = new Font("Courier New", Font.BOLD, 60);
    JLabel instruction = new JLabel("Press SPACE to begin", SwingConstants.CENTER);
    JLabel controls = new JLabel("Use WASD to avoid homework!", SwingConstants.CENTER);
    JLabel finalScoreDisplay = new JLabel("<html>YOU FAILED<br>Score: " + finalScore + "</html>" );

    //Image for pixelated giant enemy
    ImageIcon finalBoss = new ImageIcon("src//entityModels//enemyIcon.png");
    Image image = finalBoss.getImage();

    //constructor for game window
    public GameWindow(){
        initializeGameWindow();
    }//end GameWindow constructor

    public void initializeGameWindow(){
        //sets up instructions, controls, and final score display
        instruction.setForeground(Color.RED);
        instruction.setFont(titleFont);

        controls.setForeground(Color.RED);
        controls.setFont(titleFont);

        finalScoreDisplay.setForeground(Color.RED);
        finalScoreDisplay.setFont(titleFont);
        finalScoreDisplay.setVisible(false);

        //make focusable for drawing, sets background to black, and sets double buffered to enabled for help with motion
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);

        //adds all UI elements
        add(instruction);
        add(controls);
        add(finalScoreDisplay);
        addKeyListener(new TAdapter());

        //create new enemy and player
        player = new Player();
        enemy = new Enemy();

        //timer declaration, delay use, and start
        timer = new Timer(delayTimer, this);
        timer.start();
    }//end initialize game window

    public void paintComponent(Graphics g){
        //paints and moves forward with player and enemy movement
        playerStep();
        enemyStep();

        //super.paintComponent prevents the trailing effect of moving images
        super.paintComponent(g);
        draw(g);
        Toolkit.getDefaultToolkit().sync();
    }//end paintCompare

    public void draw(Graphics g){
        //draw giant enemy, player and enemyProjectile
        Graphics g2d = (Graphics2D) g;
        g2d.drawImage(image, 10, 10, 450, 450, null, this);
        g2d.drawImage(player.getImage(), player.getX(), player.getY(), this);

        //sets color of projectile to red and handles resizing algorithm
        g2d.setColor(Color.RED);
        hasFired = enemy.getHasFired();
        if(endGame != true && hasFired != false){
            if(w == 80){
                resize = true;
            }//end if
            if(resize == true) {
                if(counterGrow % 20 == 0){
                    w--;
                    h--;
                }//end if
                g2d.fillOval(enemy.getX(), enemy.getY(), w, h);
                if (w == 0){
                    resize = false;
                }//end if
            }//end if
            else{
                if(counterGrow % 20 == 0){
                    w++;
                    h++;
                }//end if
                g2d.fillOval(enemy.getX(), enemy.getY(), w, h);
            }//end else
            counterGrow++;
        }//end if
    }//end draw

    @Override
    public void actionPerformed(ActionEvent action){
        //processes step on input detection
        playerStep();
        enemyStep();

        //call to check collision method (see for more info)
        checkCollisions();
    }//end actionPerformed

    private void playerStep() {
        //processes player movement and repainting to new location via X, Y coordination
        player.move();
        repaint(player.getX()-1, player.getY()-1, player.getWidth()+1, player.getHeight()+1);
    }//end playerStep

    private void enemyStep(){
        //processes enemy projectile movement and repainting to new location via X, Y coordination
        enemy.move();
        repaint(enemy.getX()-1, enemy.getY()-1, enemy.getWidth()+1, enemy.getHeight()+1);
    }//end enemyStep

    public void checkCollisions(){
        //creates rectangles based on the boundaries returned from the player and enemy object
        Rectangle playerHitBox = player.getBounds();
        Rectangle enemyHitBox = enemy.getBounds();

        //removes player model on hit
        if (playerHitBox.intersects(enemyHitBox)){
            player.gameOver();
            gameOverScreen();
        }//end if
    }//end checkCollisions

    public void gameOverScreen(){
        //grabs final score once method is called
        if(endGame != true){
            finalScore = enemy.getScore();
        }//end if
        endGame = true;

        //displays final score upon game over
        instruction.setVisible(false);
        controls.setVisible(false);
        finalScoreDisplay.setVisible(true);
        finalScoreDisplay.setText("<html>YOU FAILED<br>Score: " + finalScore + "</html>" );
    }//end game over screen

    private class TAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent event){
            //detects input and disables startup messages upon detection
            enemy.keyPressed(event);
            if(enemy.getHasFired() == true){
                player.keyPressed(event);
                instruction.setVisible(false);
                controls.setVisible(false);
            }//end if
        }//end keyPressed

        @Override
        public void keyReleased (KeyEvent event){
            player.keyReleased(event);
        }//end keyReleased
    }//end TAdapter
}//end GameWindow class
