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

    ArrayList<DataPoint> data;

    //~ Constructors ..........................................................
    public Histogram(ArrayList<DataPoint> data, String title,
        String dependant, String independant)
    {
        this.data = data;
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

        //place data into bins
        int[] bins = new int[7];
        for (int r = 0; r < data.size(); r++)
        {
            int resid = data.get(r).getX();
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
        int tempMax = maxBin + numYlabels;

        if (maxBin < numYlabels)
        {
            numYlabels = maxBin;
            tempMax = maxBin;

        }

        System.out.println("num Y labes: " + numYlabels);

        int yIntervalValue = tempMax / numYlabels;
        int yIntervalVisual = yAxisHeight / numYlabels;

        // Drawing Y - Axis "Ticks"
        int yValueWidth = (int)(0.05 * getWidth());
        for (int y = 0; y <= numYlabels; y++)
        {
            int yValue = (numYlabels - y) * yIntervalValue;
            int yPosition = axisOriginY + y * yIntervalVisual;
            // value label
            g.drawString(yValue + "", yValueWidth, yPosition);
            // "tick"
            g.drawLine(axisOriginX - 5, yPosition, axisOriginX + 5, yPosition);
        }

        //Drawing Bars
        int yIntervalBar = yAxisHeight / tempMax;

        for(int bin = 0; bin < bins.length; bin++)
        {
            int barWidth = xIntervalVisual;
            int barHeight = bins[bin]*yIntervalBar;

            int barX = axisOriginX + bin * xIntervalVisual;
            int barY = yAxisMax - bins[bin] * yIntervalBar;

            g.drawRect(barX, barY, barWidth, barHeight);
        }








    }

    /**
     * Adds a data point to the data set
     * @param point
     */
    public void addData(DataPoint point)
    {
        data.add(point);
    }

    /**
     * Returns greatest x value
     * @return max
     */
    public int getMax()
    {
        int max = data.get(0).getX();

        for(int i = 0; i < data.size(); i++)
        {
            if (data.get(i).getX() > max)
            {
                max = data.get(i).getX();
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
        ArrayList<DataPoint> sampleData = new ArrayList<DataPoint>();
        Histogram hist = new Histogram(sampleData, "title", "idependant", "independant");

        hist.addData(new DataPoint(1));
        hist.addData(new DataPoint(2));
        hist.addData(new DataPoint(2));
        hist.addData(new DataPoint(2));
        hist.addData(new DataPoint(2));
        hist.addData(new DataPoint(2));
        hist.addData(new DataPoint(3));
        hist.addData(new DataPoint(4));
        hist.addData(new DataPoint(5));
        hist.addData(new DataPoint(6));
        hist.addData(new DataPoint(7));
        hist.addData(new DataPoint(8));
        hist.addData(new DataPoint(9));
        hist.addData(new DataPoint(50));


        JFrame frame = new JFrame("Histogram");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




        frame.add(hist);
        frame.setSize(475,475);
        frame.setVisible(true);


    }

}
