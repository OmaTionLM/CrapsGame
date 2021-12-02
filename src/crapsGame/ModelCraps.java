package crapsGame;

/**
 * ModelCraps apply craps rules.
 * state=1 Natural winner.
 * state=2 Craps looser.
 * state=3 Establish point.
 * state=4 Winner point.
 * state=5 Looser point.
 * @author Alan Valderrama
 * @version V.1.0.0 date 02/12/2021
 */
public class ModelCraps
{
    /**
     * Attributes
     * Dice: They are the Dice of the game.
     * Shot: returned number.
     * point: Established points.
     * flag: accountant.
     * stateToString: Message to user.
     * faces: values of the Dice.
     */
    private Dice dice1, dice2;
    private int shot, point, state, flag;
    private String stateToString;
    private int[] faces;

    /**
     * Class Constructor.
     */
    public ModelCraps()
    {
        dice1=new Dice();
        dice2=new Dice();
        faces=new int[2];
        flag=0;
    }

    /**
     * Establish the shot value according to each Dice.
     */
    public void calculateShot()
    {
        faces[0]=dice1.getVisibleFace();
        faces[1]=dice2.getVisibleFace();
        shot= faces[0] + faces[1];
    }

    /**
     * Establish game state according to state attribute value.
     *
     * state=1 Natural winner.
     * state=2 Craps looser.
     * state=3 Establish point.
     */
    public void determinateGame()
    {
        if(flag==0)
        {
            if(shot==7 || shot==11)
            {
                state=1;
            }
            else
            {
                if(shot==3 || shot==2 || shot==12)
                {
                    state=2;
                }
                else
                {
                    state=3;
                    point=shot;
                    flag=1;
                }
            }
        }
        else
        {
            //roundPoint
            roundPoint();
        }
    }

    /**
     * Establish game state according to state attribute value.
     * state=4 Winner point.
     * state=5 Looser point.
     */
    private void roundPoint()
    {
        if(shot==point)
        {
            state=4;
            flag=0;
        }
        if(shot==7)
        {
            state = 5;
            flag = 0;
        }
    }

    /**
     * @return shot value.
     */
    public int getShot()
    {
        return shot;
    }

    /**
     * @return point value.
     */
    public int getPoint()
    {
        return point;
    }

    /**
     * Establish message game state according to state attribute value.
     * @return Message for the View class.
     */
    public String getStateToString()
    {
        switch (state)
        {
            case 1: stateToString="Your number is natural, you win!";
            break;

            case 2: stateToString="Your number is craps, you lose!";
            break;

            case 3: stateToString="Your number is point: "+point+ " roll again!" + "\n but if your next number is 7 before "+point+" you'll lose!";
            break;

            case 4: stateToString="Your number is "+point+" again, you win!";
            break;

            case 5: stateToString="Your number is 7 before "+point+", you lose!";
            break;
        }
        return stateToString;
    }

    /**
     * @return faces value.
     */
    public int[] getFaces()
    {
        return faces;
    }
}
