import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent action) {
        //exit window, kill program. A little harsh, but it gets the job done
        System.exit(0);
    }//end actionPerformed
}//end ExitButtonListener class
