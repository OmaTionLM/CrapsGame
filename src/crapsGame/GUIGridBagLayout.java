package crapsGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIGridBagLayout extends JFrame
{
    public static final String BEGINNING_MESSAGE="Welcome to Craps \n"
            +"Push the button 'Launch' to start the game."
            +"\nIf you starting shot is 7 or 11 you win with Natural."
            +"\nIf you starting shot is 2, 3 or 12 you lose with Craps."
            +"\nIf you starting shot is any other value, you establish the Point."
            +"\nIf you stay on Point, you can roll again."
            +"\nBut now you'll win if you new number is equal to Point value."
            +"\nWithout previously your number has been 7.";

    private Header headerProject;
    private JLabel dice1, dice2;
    private JButton launch, help, exit;
    private JPanel dicePanel;
    private ImageIcon diceImage;
    private JTextArea outMessage, diceResults;
    private Listener listener;
    private ModelCraps modelCraps;
    private int cambioTefa;

    public GUIGridBagLayout()
    {
        initGUI();
        //Default JFrame configuration
        this.setTitle("Craps Game");
        this.setUndecorated(true);
        this.setBackground(new Color(255,255,255,255));
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initGUI()
    {
        //Set up JFrame Container's Layout
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints=new GridBagConstraints();

        //Create Listener Object or Control Object
        listener=new Listener();
        modelCraps=new ModelCraps();

        //Set up JComponents
        headerProject = new Header("Craps Game Table", Color.BLACK);
        constraints.gridx=0;
        constraints.gridy=0;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.HORIZONTAL;
        this.add(headerProject, constraints);

        help=new JButton(" ? ");
        help.addActionListener(listener);
        constraints.gridx=0;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_START;
        this.add(help, constraints);

        exit=new JButton("Exit");
        exit.addActionListener(listener);
        constraints.gridx=1;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_END;
        this.add(exit, constraints);

        diceImage=new ImageIcon(getClass().getResource("/resources/dado.jpg"));
        dice1=new JLabel(diceImage);
        dice2=new JLabel(diceImage);

        dicePanel=new JPanel();
        dicePanel.setPreferredSize(new Dimension(300, 300));
        dicePanel.setBorder(BorderFactory.createTitledBorder("Your dice."));
        dicePanel.add(dice1);
        dicePanel.add(dice2);
        constraints.gridx=0;
        constraints.gridy=2;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;
        add(dicePanel, constraints);

        diceResults=new JTextArea(4,31);
        diceResults.setBorder(BorderFactory.createTitledBorder("Results"));
        diceResults.setText("You must roll the dice.");
        diceResults.setEditable(false);
        constraints.gridx=1;
        constraints.gridy=2;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;
        add(diceResults, constraints);

        launch=new JButton("Launch");
        launch.addActionListener(listener);
        constraints.gridx=0;
        constraints.gridy=3;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;
        add(launch, constraints);

        outMessage=new JTextArea(4,31);
        outMessage.setText("Use the help button (?) to see the game rules.");
        outMessage.setBorder(BorderFactory.createTitledBorder("Messages."));
        outMessage.setEditable(false);
        constraints.gridx=0;
        constraints.gridy=4;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;
        add(outMessage, constraints);
    }

    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            GUIGridBagLayout miProjectGUI = new GUIGridBagLayout();
        });
    }

    private class Listener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource()==launch)
            {
                modelCraps.calculateShot();
                int[] faces=modelCraps.getFaces();
                diceImage=new ImageIcon(getClass().getResource("/resources/"+faces[0]+".png"));
                dice1.setIcon(diceImage);
                diceImage=new ImageIcon(getClass().getResource("/resources/"+faces[1]+".png"));
                dice2.setIcon(diceImage);

                modelCraps.determinateGame();

                diceResults.setText(modelCraps.getStateToString()[0]);
                outMessage.setText(modelCraps.getStateToString()[1]);
            }
            else
            {
                if(e.getSource()==help)
                {
                    JOptionPane.showMessageDialog(null, BEGINNING_MESSAGE);
                }
                else
                {
                    System.exit(0);
                }
            }
        }
    }
}
