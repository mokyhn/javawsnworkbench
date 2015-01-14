package wsn.main;

import wsn.topology.MetricTopology;
import wsn.agent.Agent;
import wsn.agent.IAgent;
import wsn.agent.ITimeAgent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import wsn.time.AbsoluteClock;
import wsn.time.RelativeClock;
import static wsn.main.Main.model;
import wsn.math.Point;
import wsn.math.Random;
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
    

    private final List<ITimeAgent> agents;
    private final MetricTopology topology;
    
    public Model() {
        agents = new ArrayList();
        random = new Random(RANDOM_SEED);
        abs_clock = new AbsoluteClock();
        topology = new MetricTopology(COMMUNICATION_RANGE_RADIUS);
        initializeAgents();
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
    public Collection<ITimeAgent> getAgents()
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

    @Override
    public MetricTopology getTopology()
    {
        return topology;
    }

    
    public List<Double> getTimeEstimates()
    {
        ArrayList<Double> times = new ArrayList<>();
        for (IAgent a : model.getAgents())
        {
            times.add(0d + ((ITimeAgent) a).getTimeEstimate());
        }
        return times;
    }
  
    
}
