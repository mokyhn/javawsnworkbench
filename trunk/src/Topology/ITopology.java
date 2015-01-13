package Topology;

import java.util.Collection;
import agent.IAgent;

public interface ITopology
{
    Collection<IAgent> getNeighbors(IAgent a);    
    
}
