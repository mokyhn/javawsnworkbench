
package wsn.main;

import wsn.agent.IAgent;
import wsn.agent.ITimeAgent;
import java.awt.Graphics;
import java.awt.Panel;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javax.swing.JFrame;
import wsn.math.Point;

public class PlotModel {
    final static int xpix = 700;
    final static int ypix = 700;
    
    public static void plot(final IModel model) {
        Double xradius = model.getTopology().actualXRadius();
        Double yradius = model.getTopology().actualYRadius();
        
        final Double xscale = ((xpix - 40d)) / xradius;
        final Double yscale = ((ypix - 40d)) / yradius;
        
        
        JFrame f = new JFrame(); //where I want to plot the graph
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        f.setSize(xpix, ypix);
        f.setLocation(200,200);
        f.setVisible(true);

        f.add(new Panel() {
             private int translatex(Double v) {
                 return (int) (v * xscale);
             }
             private int translatey(Double v) {
                 return ypix - ((int) (v * yscale));
             }
            
             @Override
             public void paint(Graphics grphcs) {
                 for (IAgent a : model.getAgents()) {
                     int x, y;
                     Point p1 = model.getTopology().getAbsolutePlacement(a);
                     x = translatex(p1.getX());
                     y = translatey(p1.getY());
                     grphcs.fillRect(x, y, 5, 5);
                     grphcs.drawString(Integer.toString(a.getID()), x, y-5);
                     
                     for (IAgent n : model.getTopology().getNeighbors(a)) {
                         Point p2 = model.getTopology().getAbsolutePlacement(n);
                         grphcs.drawLine(x, y, 
                                translatex(p2.getX()),
                                translatey(p2.getY())
                                 );
                     }
                 }
             }
        });
    }
    
    private static String circle(int x, int y) {
        return "<circle cx=\"" + x +"\" cy=\""+ y + "\" r=\"2\" stroke=\"black\" stroke-width=\"1\" fill=\"red\" />\n";
    }
    
    private static String doText(int x, int y, String msg, String extras)
    {
        return "<text x=\"" + x +"\" y=\"" + y +"\" fill=\"black\">"+ msg +
                "<title>"+ extras + "</title>" +                
                "</text>\n";        
    }
    
    private static String doLine(int x1, int y1, int x2, int y2) {
        return "<line x1=\""+ x1 + "\" y1=\"" + y1 +"\" x2=\"" + x2 + 
                "\" y2=\""+ y2 + "\" style=\"stroke:rgb(255,0,0);stroke-width:1\" />\n";
    }
    
    private static int translatex(Double v, Double xscale) {
                 return (int) (v * xscale);
             }
     private static int translatey(Double v, Double yscale) {
         return ypix - ((int) (v * yscale));
     }  
    
     
    
    private static String Html5drawing(IModel model) {
        Double xradius = model.getTopology().actualXRadius();
        Double yradius = model.getTopology().actualYRadius();
        
        final Double xscale = ((xpix - 40d)) / xradius;
        final Double yscale = ((ypix - 40d)) / yradius;

        String result = "";
        
         for (IAgent a : model.getAgents()) {
                     int x, y;
                     Point p1 = model.getTopology().getAbsolutePlacement(a);
                     x = translatex(p1.getX(), xscale);
                     y = translatey(p1.getY(), yscale);
                     result += circle(x,y);
                     result += doText(x, y-5, ""+((ITimeAgent) a).getTimeEstimate(), a.toString()); // Integer.toString(a.getID()) + " ," + 

                     
                     for (IAgent n : model.getTopology().getNeighbors(a)) {
                         Point p2 = model.getTopology().getAbsolutePlacement(n);
                         result += doLine(x, y, translatex(p2.getX(), xscale), 
                                 translatey(p2.getY(), yscale) );
                     }
         }  
        return result;
    
    }
    
    public static void dumpModelHTML5(final IModel model, String filename) 
    {
        String opening = "<!DOCTYPE html><html><body>";
        String closing = "</body></html>";

        String svgstart = "<svg height=\"" + xpix + "\" width=\""+ypix+"\">\n";
        String content = Html5drawing(model) + "\n";
        String svgclose = "</svg>\n";        
        
        
        
        String result = opening + svgstart + content + svgclose + closing;
        PrintWriter out;
        try
        {
            out = new PrintWriter(filename);
            out.println(result);
            out.close();

        } catch (FileNotFoundException ex)
        {
            System.out.println("Could not write file " + filename);
        }
        
    }
    
}
