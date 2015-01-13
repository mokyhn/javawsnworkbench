
package wsn.main;

import wsn.agent.IAgent;


public class Main {

   
    static Model model;
    
    
    public static void main(String[] args) {
        model = new Model();
        System.out.println(model.toString());
        PlotModel.plot(model);
        Simulation s = new Simulation(model);
               
        for (int i = 0; i < 50; i++) {
            //PlotModel.dumpModelHTML5(model1, "plot"+i+".html");
            System.out.println("\n\nIteration " + i + "-----------------------------------");
            for (IAgent a : model.getAgents())
            {               
                System.out.println(a.toString());
            }
            s.step();
        }
        
        int i = 0;
        for (Double d : s.getDeviations())
        {
            System.out.println(i +"," +d);
            i++;
        }
    }
      
    
}
