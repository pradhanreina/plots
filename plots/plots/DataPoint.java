package plots;
/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author reinapradhan
 *  @version Jun 15, 2021
 */
public class DataPoint
{
    //~ Fields ................................................................
    int x;
    int y;

    //~ Constructors ..........................................................
    public DataPoint(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    //~Public  Methods ........................................................
    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

}
