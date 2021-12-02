package crapsGame;

import java.util.Random;

/**
 *  Class Dice generate a Random between 1 and 6
 * @author  Alan Valderrama
 * @version v.1.0.0 date 01/12/2021
 */
public class Dice
{
    /**
     * Attribute
     */
    private int visibleFace;

    /**
     * Method that generate a random value to visibleFace
     * @return number between (1,6)
     */
    public int getVisibleFace()
    {
        Random randomNumber=new Random();
        visibleFace= randomNumber.nextInt(6)+1;

        return visibleFace;
    }
}
