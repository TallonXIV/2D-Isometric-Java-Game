import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartButtonListener implements ActionListener {
    Menu menu;
    public StartButtonListener(Menu menu){
        this.menu = menu;
    }//end constructor

    @Override
    public void actionPerformed(ActionEvent action) {
        //calls init game method... It's rough trust me...
        menu.initGame();
    }//end actionPerformed method
}//end classs
