package wsn.topology;

import wsn.agent.IAgent;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GraphTopology implements ITopology
{
    private final Map<IAgent, Set<IAgent>> neighbors =  new HashMap<>();
    
    
    public GraphTopology() 
    {
    }
    
    public void setTopology(List<IAgent> agents, int[][] graph) 
    {
        for (int e = 0; e < graph.length; e++) // edges
        {
            int[] pair = graph[e];
            int v = pair[0];
            int w = pair[1];
            assert(v >= 0 && v < agents.size());
            assert(w >= 0 && w < agents.size());

            addEdge(agents.get(v), agents.get(w));
            addEdge(agents.get(w), agents.get(v));                
        }
    }

    private void addEdge(IAgent v, IAgent w)
    {
        if (neighbors.containsKey(v)) 
        {
            neighbors.get(v).add(w);
        }
        else
        {
            Set<IAgent> newNeighborList =  new HashSet<>();
            newNeighborList.add(w);
            neighbors.put(v, newNeighborList);       
        }
    }

    @Override
    public Collection<IAgent> getNeighbors(IAgent a)
    {
        return neighbors.get(a);
    }

    @Override
    public Collection<IAgent> getIsolatedAgents()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
