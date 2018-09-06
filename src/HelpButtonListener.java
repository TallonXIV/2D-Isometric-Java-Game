import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpButtonListener implements ActionListener {
    Menu menu;
    public HelpButtonListener(Menu menu){
        this.menu = menu;
    }//end constructor

    @Override
    public void actionPerformed(ActionEvent action) {
        //initializes help screen in menu
        menu.initHelp();
    }//end actionPerformed method
}//end class
