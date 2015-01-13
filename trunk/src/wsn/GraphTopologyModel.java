package wsn;

import Topology.GraphTopology;
import Topology.ITopology;
import agent.Agent;
import agent.IAgent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import time.AbsoluteClock;
import time.RelativeClock;
import wsn.Math.Random;
import wsn.communication.Broadcast;

public class GraphTopologyModel implements IModel {
    // Model specific parameters
    private final static int N = 4; // number of agents
    private final static long RANDOM_SEED = 34110; 
    
    private final static int MAX_INBOX_SIZE = 20;

    
    private final AbsoluteClock abs_clock;
    private final Random random;    
    

    private final List<IAgent> agents;
    private final ITopology topology;

    int[][] lineGraph = {
        { 0, 1 },
        { 1, 2 },
        { 2, 3 }
    };
    
    public GraphTopologyModel() {
        agents = new ArrayList();
        random = new Random(RANDOM_SEED);
        abs_clock = new AbsoluteClock();
        topology = new GraphTopology();

        initializeAgents();

    }

    public final void initializeAgents() {          
        for (int id = 0; id < N; id++) {
            Agent a = new Agent(id, random, new RelativeClock(abs_clock, random.getInt(10000)));
            Broadcast broadcast = new Broadcast(a, topology);
            a.setBroadcaster(broadcast);
            a.setInboxBuffer(new LimitedMessageBuffer(MAX_INBOX_SIZE));
            agents.add(a);            
        } 
        ((GraphTopology) topology).setTopology(agents, lineGraph);

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
        s += "\n" + "Random seed: " + RANDOM_SEED;
        s += "\n" + "Agents: ";
        for (IAgent a : agents) {
            s += "\n" + a.toString() + "\n";
        }
        return s;
    }

    @Override
    public Collection<IAgent> getAgents()
    {
        return agents;
    }


    
}
