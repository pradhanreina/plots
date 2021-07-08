package plots;
/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author reinapradhan
 *  @version Jun 19, 2021
 */

    /**
     *  Write a one-sentence summary of your class here.
     *  Follow it with additional details about its purpose, what abstraction
     *  it represents, and how to use it.
     *
     *  @author reinapradhan
     *  @version Jun 15, 2021
     */

    import java.awt.*;
    import java.awt.geom.AffineTransform;
    import java.util.*;
    import javax.swing.*;

    public class ScatterPlot extends JPanel
    {
        //~ Fields ................................................................
        //JFrame frame;
        //JPanel panel;

        int xscale;
        int yscale;

        int xmax;
        int ymax;

        String dependant;
        String independant;
        String title;

        ArrayList<DataPoint> data;

        //~ Constructors ..........................................................
        public ScatterPlot(ArrayList<DataPoint> data, String title,
            String dependant, String independant)
        {
            this.data = data;
            this.dependant = dependant;
            this.independant = independant;
            this.title = title;
        }

        // ~Public Methods
        // ........................................................


        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);

            int axisOriginX = (int)(getWidth() * 0.1);
            int axisOriginY = (int)(getHeight() * 0.1);
            int xAxisMax = (int)(getWidth() * 0.9);
            int yAxisMax = (int)(getHeight() * 0.9);
            int xAxisWidth = (int)(getWidth() * 0.8);
            int yAxisHeight = (int)(getHeight() * 0.8);

            // Draw X-axis
            g.drawLine(axisOriginX, yAxisMax, xAxisMax, yAxisMax);
            // Draw Y-axis
            g.drawLine(axisOriginX, axisOriginY, axisOriginX, yAxisMax);
            // Draw Title
            g.drawString(title, getWidth() / 2 - title.length() / 2, 25);



            int numOfXTicks = 5;
            int numOfYTicks = 5;

            int xMax = getXmax() + numOfXTicks;
            int yMax = getYmax() + numOfYTicks;

            int xIncrementVisual = (int)(xAxisWidth / numOfXTicks);
            int yIncrementVisual = (int)(yAxisHeight / numOfYTicks);

            int xIncrementValue = (int)(xMax/ numOfXTicks);
            int yIncrementValue = (int)(yMax / numOfYTicks);

            // Drawing X - Axis "Ticks"
            int xValueHeight = (int)(0.95 * getHeight());
            for (int x = 0; x <= numOfXTicks; x++)
            {
                int xValue = x * xIncrementValue;
                int xPosition = axisOriginX + (x * xIncrementVisual);
                // value label
                g.drawString(xValue + "", xPosition, xValueHeight);
                // "tick"
                g.drawLine(xPosition, yAxisMax - 5, xPosition, yAxisMax + 5);

            }

            // Drawing Y - Axis "Ticks"
            int yValueWidth = (int)(0.05 * getWidth());
            for (int y = 0; y <= numOfYTicks; y++)
            {
                int yValue = (numOfYTicks - y) * yIncrementValue;
                int yPosition = axisOriginY + y * yIncrementVisual;
                // value label
                g.drawString(yValue + "", yValueWidth, yPosition);
                // "tick"
                g.drawLine(axisOriginX - 5, yPosition, axisOriginX + 5, yPosition);
            }

            for (DataPoint data : data)
            {
                int x = data.getX();
                int y = data.getY();

                float xScaled = (float)((float)x / (float)xMax);
                float yScaled = (float)((float)y / (float)yMax);

                int xPosition = (int)(axisOriginX + (xScaled * xAxisWidth));
                int yPosition = (int)(yAxisMax - (yScaled * yAxisHeight));

                g.fillOval(xPosition - 3, yPosition - 3, 6, 6);

            }

        }


        public void addPoint(DataPoint point)
        {
            data.add(point);
        }

        public int getXmax()
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

        public int getYmax()
        {
            int max = data.get(0).getY();

            for(int i = 0; i < data.size(); i++)
            {
                if (data.get(i).getY() > max)
                {
                    max = data.get(i).getY();
                }
            }

            return max;
        }


        public static void main(String[] args)
        {
            ArrayList<DataPoint> sampleData = new ArrayList<DataPoint>();

            JFrame frame = new JFrame("Scatter Plot 2");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            ScatterPlot sp = new ScatterPlot(sampleData, "Scatterplot - Test", "dependant - variable",
                "independant - variable");

            sp.addPoint(new DataPoint(1,2));
            sp.addPoint(new DataPoint(1,3));
            sp.addPoint(new DataPoint(2,4));
            sp.addPoint(new DataPoint(3,5));
            sp.addPoint(new DataPoint(5,2));
            sp.addPoint(new DataPoint(7,10));
            sp.addPoint(new DataPoint(8,4));
            sp.addPoint(new DataPoint(9,1));
            sp.addPoint(new DataPoint(10,7));
            sp.addPoint(new DataPoint(20,7));
            //sp.addPoint(new DataPoint(50,7));
            //sp.addPoint(new DataPoint(100,7));
           // sp.addPoint(new DataPoint(200,7));
           // sp.addPoint(new DataPoint(200,180));


            frame.add(sp);
            frame.setSize(475,475);
            frame.setVisible(true);


        }
    }



