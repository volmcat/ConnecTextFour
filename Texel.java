
/**
 * This class creates new texel objects and can retrieve information from them.
 * 
 * @author (Brice Vollmer) 
 * @version (12/12/2015)
 */
public class Texel
{
    private int x, y;
    private char ch;

    /*Constructor for objects of class Texel
     * @param 3 integers that represents the column, row and character of the object
     * @return a newly formed texel object
     */
    public Texel(int inX, int inY, char inCh)
    {
        x = inX;
        y = inY;
        ch = inCh;
    }

    /*Get the xValue (column) of the texel object
     * @param texel object
     * @return xvalue as an integer
     */
    public int getX() {return x; }

    /*get the y-value of the texel object
     * @param texel object
     * @return yvalue as in integer
     */
    public int getY() {return y; }

    /*get the character of the texel object
     * @param texel object
     * @return character as a char
     */
    public char getCh() {return ch;}
}
