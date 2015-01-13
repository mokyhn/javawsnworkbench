package wsn.main;

import wsn.agent.IAgent;
import java.util.ArrayList;
import java.util.List;
import wsn.math.MeanAndDeviation;

public class Simulation
{
    private final Model model;
    
    
    // Statistics
    ArrayList<Double> deviation = new ArrayList<>();

    
    
    public Simulation(Model m)
    {
        model = m;
    }
    
    public void step()
    {        
        for (IAgent a : model.getAgents()) 
        {
            model.getAbsoluteClock().tick();
            // Agent run
            a.handleMessageReception();
            a.handleMessagesSending();
        }
        collectStatistics();
    }
    
    private void collectStatistics()
    {
        Double[] dummy = {};
        deviation.add((new MeanAndDeviation(model.getTimeEstimates().toArray(dummy))).deviation());
    }
    
    public void step(int n)
    {
        for (int i = 0; i < n; i++)
        {
            step();
        }
    }
    
    public List<Double> getDeviations()
    {
        return deviation;
    }
}
