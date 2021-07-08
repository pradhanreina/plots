package plots;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author reinapradhan
 *  @version Jul 7, 2021
 */
public class Histogram extends JPanel
{
    //~ Fields ................................................................
    String dependant;
    String independant;
    String title;

    ArrayList<Residual> residuals;

    //~ Constructors ..........................................................
    public Histogram(ArrayList<Residual> data, String title,
        String dependant, String independant)
    {
        residuals = data;
        this.dependant = dependant;
        this.independant = independant;
        this.title = title;
    }

    //~Public  Methods ........................................................


    /**
     * {@inheritDoc}
     * Draws the histogram
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        int axisOriginX = (int)(getWidth()* 0.1);
        int axisOriginY = (int)(getHeight()* 0.1);
        int xAxisMax = (int)(getWidth() * 0.9);
        int yAxisMax = (int)(getHeight() * 0.9);
        int xAxisWidth = (int)(getWidth()*0.8);
        int yAxisHeight = (int)(getHeight()*0.8);

        //Draw X-axis
        g.drawLine(axisOriginX,yAxisMax,xAxisMax,yAxisMax);
        //Draw Y-axis
        g.drawLine(axisOriginX,axisOriginY,axisOriginX,yAxisMax);
        //Draw Title
        g.drawString(title, getWidth()/2 - title.length()/2, 25);


        int numBins = 7;

        int max = getMax() + numBins;

        int xIntervalVisual = xAxisWidth / numBins;
        int xIntervalValue = max / numBins;


        //Drawing X axis "ticks" / bin labels
        int valueHeight = (int)(getHeight() * 0.95);
        for (int x = 0; x <= numBins; x++)
        {
            int value = x * xIntervalValue;
            int location = axisOriginX + x * xIntervalVisual;
            //Write value
            g.drawString(value + "", location, valueHeight);
            //Draw Ticks
            g.drawLine(location, yAxisMax, location, yAxisMax +10 );
        }

        //place residuals into bins
        int[] bins = new int[7];
        for (int r = 0; r < residuals.size(); r++)
        {
            int resid = residuals.get(r).getResid();
            int bin = resid / xIntervalValue;

            bins[bin]++;
        }

        System.out.println("bins");
        for (int a = 0; a < bins.length; a++)
        {
            System.out.println(bins[a]);
        }
        System.out.println("------");

        int maxBin = 0;
        for (int r = 0; r < numBins; r ++)
        {
            if (bins[r] > maxBin)
            {
                maxBin = bins[r];
            }
        }

        System.out.println("max bin: " + maxBin);

        int numYlabels = 5;

        if (maxBin < numYlabels)
        {
            numYlabels = maxBin;
        }

        System.out.println("num Y labes: " + numYlabels);


    }

    /**
     * Adds a data point to the data set
     * @param point
     */
    public void addResidual(Residual resid)
    {
        residuals.add(resid);
    }

    /**
     * Returns greatest x value
     * @return max
     */
    public int getMax()
    {
        int max = residuals.get(0).getResid();

        for(int i = 0; i < residuals.size(); i++)
        {
            if (residuals.get(i).getResid() > max)
            {
                max = residuals.get(i).getResid();
            }
        }


        return max;
    }

    /**
     * Creates a histogram object
     * for testing purposes
     * @param args
     */
    public static void main(String[] args)
    {
        ArrayList<Residual> sampleData = new ArrayList<Residual>();
        Histogram hist = new Histogram(sampleData, "title", "idependant", "independant");

        JFrame frame = new JFrame("Histogram");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        hist.addResidual(new Residual(1));
        hist.addResidual(new Residual(2));
        hist.addResidual(new Residual(3));
        hist.addResidual(new Residual(4));
        hist.addResidual(new Residual(5));
        hist.addResidual(new Residual(6));
        hist.addResidual(new Residual(7));
        hist.addResidual(new Residual(8));
        hist.addResidual(new Residual(9));
        hist.addResidual(new Residual(10));



        frame.add(hist);
        frame.setSize(475,475);
        frame.setVisible(true);


    }

}
