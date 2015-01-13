
package wsn;

import agent.IAgent;
import agent.ITimeAgent;
import java.util.ArrayList;
import wsn.Math.MeanAndDeviation;


public class Main {

   
    static Model model;
    
    
    public static void main(String[] args) {
        model = new Model();
        System.out.println(model.toString());
        PlotModel.plot(model);
        Simulation s = new Simulation(model);
        
        
        ArrayList<Double> deviation = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            //PlotModel.dumpModelHTML5(model1, "plot"+i+".html");
            System.out.println("\n\nIteration " + i + "-----------------------------------");
            ArrayList<Double> times = new ArrayList<>();
            for (IAgent a : model.getAgents())
            {
                
                System.out.println(a.toString());
                times.add(0d + ((ITimeAgent) a).getTimeEstimate());
            }
            Double[] dummy = {};
            deviation.add((new MeanAndDeviation(times.toArray(dummy))).deviation());
            s.step();
        }
        
        int i = 0;
        for (Double d : deviation)
        {
            System.out.println(i +"," +d);
            i++;
        }
    }
      
    
}
