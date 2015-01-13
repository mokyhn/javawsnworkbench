package wsn.topology;

import java.util.Collection;
import wsn.agent.IAgent;

public interface ITopology
{
    Collection<IAgent> getNeighbors(IAgent a);    
    
}
