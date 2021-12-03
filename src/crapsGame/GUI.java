package crapsGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used as View Craps Class.
 * @autor Alan Valderrama
 * @version v.1.0.0 date:02/12/2021
 */
public class GUI extends JFrame
{
    public static final String BEGINNING_MESSAGE="Welcome to Craps \n"
            +"Push the button 'Launch' to start the game."
            +"\nIf you starting shot is 7 or 11 you win with Natural."
            +"\nIf you starting shot is 2, 3 or 12 you lose with Craps."
            +"\nIf you starting shot is any other value, you establish the Point."
            +"\nIf you stay on Point, you can roll again."
            +"\nBut now you'll win if you new number is equal to Point value."
            +"\nWithout previously your number has been 7.";

    private int flag;
    private Header headerProject;
    private JLabel dice1, dice2;
    private JButton launch;
    private JPanel dicePanel, resultsPanel;
    private ImageIcon diceImage;
    private JTextArea outMessage, diceResults;
    private JSeparator separator;
    private Listener listener;
    private ModelCraps modelCraps;

    /**
     * Constructor of GUI class
     */
    public GUI()
    {
        initGUI();

        //Default JFrame configuration
        this.setTitle("Craps Game");
        //this.setSize(200,100);
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI()
    {
        //Set up JFrame Container's Layout
        //Create Listener Object or Control Object
        listener=new Listener();
        modelCraps=new ModelCraps();

        //Set up JComponents
        headerProject = new Header("Craps Game Table", Color.BLACK);

        this.add(headerProject,BorderLayout.NORTH);
        
        diceImage=new ImageIcon(getClass().getResource("/resources/dado.jpg"));
        dice1=new JLabel(diceImage);
        dice2=new JLabel(diceImage);

        launch=new JButton("Launch");
        launch.addActionListener(listener);
        
        dicePanel=new JPanel();
        dicePanel.setPreferredSize(new Dimension(200, 80));
        dicePanel.setBorder(BorderFactory.createTitledBorder("Your dice."));
        dicePanel.add(dice1);
        dicePanel.add(dice2);
        dicePanel.add(launch);

        this.add(dicePanel, BorderLayout.CENTER);

        outMessage=new JTextArea(7,31);
        outMessage.setText(BEGINNING_MESSAGE);
        //outMessage.setBorder(BorderFactory.createTitledBorder("What should you do?"));
        JScrollPane scroll=new JScrollPane(outMessage);

        resultsPanel=new JPanel();
        resultsPanel.setBorder(BorderFactory.createTitledBorder("What should you do?"));
        resultsPanel.add(scroll);
        resultsPanel.setPreferredSize(new Dimension(370, 180));


        this.add(resultsPanel,BorderLayout.EAST);

        diceResults=new JTextArea(4,31);
        separator=new JSeparator();
        separator.setPreferredSize(new Dimension(320, 7));
        separator.setBackground(Color.GREEN);
    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is executed by console.
     */
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            GUI miProjectGUI = new GUI();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Listener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            flag=0;

            modelCraps.calculateShot();
            int[] faces=modelCraps.getFaces();
            diceImage=new ImageIcon(getClass().getResource("/resources/"+faces[0]+".png"));
            dice1.setIcon(diceImage);
            diceImage=new ImageIcon(getClass().getResource("/resources/"+faces[1]+".png"));
            dice2.setIcon(diceImage);

            modelCraps.determinateGame();

            if(flag==0)
            {
                resultsPanel.removeAll();
                resultsPanel.setBorder(BorderFactory.createTitledBorder("Results"));
                resultsPanel.add(diceResults);
                resultsPanel.add(separator);
                resultsPanel.add(outMessage);
                flag=1;
            }

            diceResults.setText(modelCraps.getStateToString()[0]);
            outMessage.setRows(4);
            outMessage.setText(modelCraps.getStateToString()[1]);

            revalidate();
            repaint();
        }
    }
}
