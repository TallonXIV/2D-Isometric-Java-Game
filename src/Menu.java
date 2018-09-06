import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.Color;

public class Menu extends JPanel{
    //Declares main window frame
    JFrame gameFrame = new JFrame("Evasion Launcher");

    //declares the panels for menu, help screen, and the game
    JPanel menuPanel = new JPanel();
    JPanel helpPanel = new JPanel();

    //declares buttons used
    JButton startButton = new JButton("Start");
    JButton helpButton = new JButton ("Info");
    JButton exitButton = new JButton ("Exit");
    JButton returnFromHelp = new JButton ("Return");

    //declares text displayed
    JTextField text = new JTextField(20);
    JTextArea helpInfo = new JTextArea("This is a horror game inspired by being a Computer Science major. "
                                     + "The further you go, the harder it gets... Can you survive?");

    //fonts
    Font titleFont = new Font("Courier New", Font.BOLD, 60);
    Font buttonFont = new Font("Courier New", Font.BOLD, 45);

    public Menu() {
        initWindow();
        initMenu();
    }//end view constructor

    public void initWindow(){
        //sets up the initial window and locks the size
        ImageIcon windowIcon = new ImageIcon("src//entityModels//enemyIcon.png");
        gameFrame.setIconImage(windowIcon.getImage());
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setLayout(new BorderLayout());
        gameFrame.setBackground(Color.BLACK);
        gameFrame.setSize(600, 600);
        gameFrame.setResizable(false);
    }//end initWindow

    public void initMenu(){
        //makes help panel initially invisible
        helpPanel.setVisible(false);
        menuPanel.setBorder(new EmptyBorder(50, 10, 10, 10));

        //sets up start button with action listener
        startButton.setPreferredSize(new Dimension(400, 100));
        startButton.setFont(buttonFont);
        startButton.setBackground(Color.RED);
        startButton.setForeground(Color.BLACK);
        startButton.addActionListener(new StartButtonListener(this));

        //sets up help button with action listener
        helpButton.setPreferredSize(new Dimension(400, 100));
        helpButton.setFont(buttonFont);
        helpButton.setBackground(Color.RED);
        helpButton.setForeground(Color.BLACK);
        helpButton.addActionListener(new HelpButtonListener(this));

        //sets up exit button with action listener
        exitButton.setPreferredSize(new Dimension(400, 100));
        exitButton.setFont(buttonFont);
        exitButton.setBackground(Color.RED);
        exitButton.setForeground(Color.BLACK);
        exitButton.addActionListener(new ExitButtonListener());

        //Sets Header title and makes it not editable
        text.setEditable(false);
        text.setText("    Evasion!");
        text.setFont(titleFont);
        text.setBackground(Color.BLACK);
        text.setForeground(Color.RED);
        text.setBorder(BorderFactory.createEmptyBorder());

        //add elements to the menuPanel and make visible
        menuPanel.add(startButton);
        menuPanel.add(helpButton);
        menuPanel.add(exitButton);
        menuPanel.setVisible(true);
        menuPanel.setBackground(Color.BLACK);

        //add elements to the window frame and set visible
        gameFrame.add(text, BorderLayout.NORTH);
        gameFrame.add(menuPanel);
        gameFrame.setVisible(true);
    }//end initMenu

    public void initHelp(){
        //disables menuPanel and replaces it with the help panel
        menuPanel.setVisible(false);
        helpPanel.setVisible(true);
        helpPanel.setBackground(Color.BLACK);

        //Tweaks to the text, called helpInfo
        helpInfo.setOpaque(false);
        helpInfo.setFont(buttonFont);
        helpInfo.setForeground(Color.RED);
        helpInfo.setLineWrap(true);
        helpInfo.setWrapStyleWord(true);
        helpInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        helpInfo.setEditable(false);
        helpInfo.setSize(550, 550);

        //tweaks to the return button found on the help panel
        returnFromHelp.setPreferredSize(new Dimension(200, 75));
        returnFromHelp.setFont(buttonFont);
        returnFromHelp.setBackground(Color.RED);
        returnFromHelp.setForeground(Color.BLACK);
        returnFromHelp.addActionListener(new ReturnButtonListener(this));

        //adds elements to the helpPanel
        helpPanel.add(returnFromHelp);
        helpPanel.add(helpInfo);

        //adds help Panel to main window
        gameFrame.add(helpPanel);
    }//end initHelp

    public void initGame() {
        //when the game starts, dispose of all other windows
        gameFrame.dispose();
        GameLauncher gl = new GameLauncher();
        //this is AWFUL practice, but I had to conjoin my menus and my game by calling the main method of the game
        gl.main(new String[0]);
    }//end initLayout

    public void backToMenu(){
        //prevents duplicate gameWindows on launch by simply swapping visibility
        menuPanel.setVisible(true);
        helpPanel.setVisible(false);
    }//end backToMenu
}//end class
