import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReturnButtonListener implements ActionListener{
    Menu menu;
    public ReturnButtonListener(Menu menu) {
        this.menu = menu;
    }//end constructor

    public void actionPerformed(ActionEvent action) {
        //go back to main menu
        menu.backToMenu();
    }//end actionPerformed method
}//end class
