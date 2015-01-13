package wsn;

import Topology.MetricTopology;
import agent.Agent;
import agent.IAgent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import time.AbsoluteClock;
import time.RelativeClock;
import wsn.Math.Point;
import wsn.Math.Random;
import wsn.communication.Broadcast;

public class Model implements IModel {
    // Model specific parameters
    private final static Double AREA_RADIUS = 500.0; //meters
    private final static Double COMMUNICATION_RANGE_RADIUS  = 150.0; //meters, midrange  
    private final static int N = 40; // number of agents
    private final static long RANDOM_SEED = 34110; 
    
    private final static int MAX_INBOX_SIZE = 20;

    
    private final AbsoluteClock abs_clock;
    private final Random random;    
    

    private final List<IAgent> agents;
    private final MetricTopology topology;
    
    public Model() {
        agents = new ArrayList();
        random = new Random(RANDOM_SEED);
        abs_clock = new AbsoluteClock();
        topology = new MetricTopology(COMMUNICATION_RANGE_RADIUS);
        initializeAgents();
    }
       
    @Override
    public Collection<IAgent> getAgents()
    {
        return agents;
    }
    
    @Override
    public Random getRandom()
    {
        return random;
    }

    @Override
    public AbsoluteClock getAbsoluteClock() 
    {
        return abs_clock;
    }
   
    public final void initializeAgents() {
        
        
        for (int id = 0; id < N; id++) {
            Point p = new Point(  AREA_RADIUS * getRandom().nextDouble(),
                                AREA_RADIUS * getRandom().nextDouble()); 
            Agent a = new Agent(id, random, new RelativeClock(abs_clock, random.getInt(10000)));
            Broadcast broadcast = new Broadcast(a, topology);
            a.setBroadcaster(broadcast);
            a.setInboxBuffer(new LimitedMessageBuffer(MAX_INBOX_SIZE));
            topology.addAgent(a, p);
            agents.add(a);            
        }    
    }
    
        
    @Override
    public String toString() {
        String s = "";
        s += "Number of agents: " + N;
        s += "\n" + "Area radius: " + AREA_RADIUS;
        s += "\n" + "Random seed: " + RANDOM_SEED;
        s += "\n" + "Agents: ";
        for (IAgent a : getAgents()) {
            s += "\n" + a.toString() + "\n";
        }
        return s;
    }

    public MetricTopology getTopology()
    {
        return topology;
    }

  

    
}
