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

    public class ScatterPlots2 extends JPanel
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
        public ScatterPlots2(ArrayList<DataPoint> data, String title,
            String dependant, String independant)
        {
            this.data = data;
            this.dependant = dependant;
            this.independant = independant;
            this.title = title;
        }

        //~Public  Methods ........................................................

        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            /*Graphics2D g2d = (Graphics2D)g;
            AffineTransform orig = g2d.getTransform();

            AffineTransform at = new AffineTransform();
            at.setToRotation(Math.toRadians(270),  50, (getHeight() - 30)/2);
            g2d.setTransform(at);
            //g2.drawString("This is a vertical text", 10, 10);

           // g2d.rotate(Math.toRadians(270.0));
            g2d.drawString(dependant, 50,200);
            //(getHeight() - 30)/2
            g2d.setTransform(orig);*/

            g.drawLine(
                (int)(getWidth()* 0.1),
                (int)(getHeight() * 0.1),
                (int)( getWidth()* 0.1),
                (int)(getHeight() * 0.90)
                );

            g.drawLine(
                (int)(getWidth()* 0.1),
                (int)(getHeight() * 0.90),
                (int)(getWidth()* 0.90),
                (int)(getHeight() * 0.90)
                );

            g.drawString(title, getWidth()/2 - title.length()/2, 25);

           xscale = (int) (getWidth() * 0.8)/(getXmax() );
           int count = -1;
           int threshold = getXmax()/5;
           // x <= getWidth() - 50
           for (int x = (int)(getWidth()*0.1) ; count < getXmax(); x++)
            {
                if ((x - (int)(getWidth()*0.1)) % xscale == 0 )
                {

                    count ++;

                    if (count % threshold == 0)
                    {
                        g.drawString(count + "", x - 5, (int)(getHeight() * 0.95));
                        g.drawLine(x, (int)(getHeight()*0.89), x, (int)(getHeight() * 0.91) );
                        //count++;

                    }
                }


            }

           yscale =  (int)(getHeight() * 0.8 )/(getYmax() );
           int countY = 0;
           int thresholdY = getYmax()/5;
           //y >= 50
           for (int y =(int)( getHeight() * 0.9); countY < getYmax() ; y--)
            {
                if (y  % yscale == 0 && y != (int)( getHeight() * 0.9))
                {

                    countY ++;
                    if (countY % thresholdY == 0)
                    {
                        g.drawString(countY + "", (int)(getWidth() * 0.03), y );
                        g.drawLine((int)(getWidth() * 0.08), y, (int)(getWidth() * 0.12), y );

                    }
                }


            }

           for (DataPoint data : data)
           {
               int xPos = xscale * data.getX() +  (int)(getWidth()* 0.1);
               int yPos =  (int)(  getHeight()*0.9 - yscale * data.getY());

               g.fillOval(xPos - (int)(getWidth() * 0.03) , yPos - (int)(getHeight()*0.03) ,
                   (int)(getWidth()*0.015), (int)(getHeight()*0.015));
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

            ScatterPlots2 sp = new ScatterPlots2(sampleData, "Scatterplot - Test", "dependant - variable",
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
            sp.addPoint(new DataPoint(50,7));
            sp.addPoint(new DataPoint(100,7));
           // sp.addPoint(new DataPoint(200,7));
           // sp.addPoint(new DataPoint(200,180));


            frame.add(sp);
            frame.setSize(500,500);
            frame.setVisible(true);


        }
    }



