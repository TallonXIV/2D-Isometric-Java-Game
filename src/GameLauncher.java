import java.awt.EventQueue;
import javax.swing.JFrame;

public class GameLauncher extends JFrame{
    public GameLauncher(){
        initialize();
    }//end GameLauncher constructor

    public void initialize(){
        //Initializes new window to house game and its attributes
        add(new GameWindow());
        setSize(1500, 1000);
        setResizable(false);
        setTitle("Evasion!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }//end initialize

    public static void main(String[] args){
        //this allows for the GUI to be completely instantiated before performing. This is a fantastic way of initialization (pulled from Zetcode)
        EventQueue.invokeLater(() ->{
            GameLauncher gl = new GameLauncher();
            gl.setVisible(true);
        });//end event queue
    }//end main
}//end GameLauncher class
