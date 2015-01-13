package wsn.communication;

import Topology.ITopology;
import agent.IAgent;
import java.util.Collection;

public class Broadcast
{
    private final IAgent broadCaster;
    private final ITopology topology;
    
    public Broadcast(IAgent broadCaster, ITopology topology)
    {
        this.topology = topology;
        this.broadCaster = broadCaster;
    }
    
    public void send(IMessage m)
    {
         Collection<IAgent> neighbors = topology.getNeighbors(broadCaster);
         for (IAgent a : neighbors)
         {
             if (a != broadCaster)
             {
                 a.receive(m);
             }
         }
    }
}
