package wsn;

import agent.IAgent;

public class Simulation
{
    private final IModel model;
    
    public Simulation(IModel m)
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
    }
    
    public void step(int n)
    {
        for (int i = 0; i < n; i++)
        {
            step();
        }
    }
}
