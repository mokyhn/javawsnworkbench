package wsn.topology;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import wsn.agent.IAgent;
import wsn.math.Point;

public class MetricTopology implements ITopology
{
    private final Map<IAgent, Point> absolutePlacement;    
    private final Map<IAgent, Set<IAgent>> neighbors;
    
    private final double radius;
    
    public MetricTopology(double r)
    {
        if (r > 0) 
        {
            absolutePlacement = new HashMap<>();
            neighbors = new HashMap<>();        
            radius = r;
        }
        else
        {
            throw new IllegalArgumentException("Radius cannot be 0 or negative");
        }
    }
    
    public void addAgent(IAgent new_agent, Point new_point)
    {
        if (absolutePlacement.containsKey(new_agent))
        {
            throw new IllegalArgumentException("No need to register the same agent twice..."); 
        }
        else
        {
            absolutePlacement.put(new_agent, new_point); // Add placement of agent
            Set<IAgent> newNeighborList =  new HashSet<>();
            neighbors.put(new_agent, newNeighborList); // Add an empty neighbor list
            
            for (Map.Entry<IAgent, Point> e : absolutePlacement.entrySet())
            {
                IAgent existing_agent = e.getKey();
                Point  existing_point = e.getValue();
            
                if (existing_agent != new_agent)
                {
                    if (new_point.distance(existing_point) < radius) 
                    {
                        newNeighborList.add(existing_agent);
                        neighbors.get(existing_agent).add(new_agent);
                    }

                }
            
            }
        
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
        ArrayList<IAgent> result = new ArrayList<>();
        
        for (Map.Entry<IAgent, Point> e : absolutePlacement.entrySet())
        {
            IAgent a = e.getKey();
            
            if (getNeighbors(a).isEmpty()) {
                result.add(a);
            }
            
        }
        
        return result;
    }
    
    public Collection<IAgent> getNeighbors(int a)
    {
        IAgent theOne = null;
        for (Map.Entry<IAgent, Point> e : absolutePlacement.entrySet()) {
            if (e.getKey().getID() == a) 
            {
                theOne = e.getKey();
                break;
            }
        }
        
        if (theOne == null)
        {
            return null;
        }
        
        return neighbors.get(theOne);
    }
    
    public Point getAbsolutePlacement(IAgent a)
    {
        return absolutePlacement.get(a);
    }

    public double actualXRadius()
    {
        Double xMin = Double.POSITIVE_INFINITY;
        Double xMax = Double.NEGATIVE_INFINITY;
        for (Map.Entry<IAgent, Point> e : absolutePlacement.entrySet())
        {
            Point p = e.getValue();
            xMin = Math.min(xMin, p.getX());
            xMax = Math.max(xMax, p.getX());
        }
        return Math.abs(xMax - xMin);
    }

    public double actualYRadius()
    {
        Double yMin = Double.POSITIVE_INFINITY;
        Double yMax = Double.NEGATIVE_INFINITY;
        for (Map.Entry<IAgent, Point> e : absolutePlacement.entrySet())
        {
            Point p = e.getValue();
            yMin = Math.min(yMin, p.getY());
            yMax = Math.max(yMax, p.getY());
        }
        return Math.abs(yMax - yMin);
    }

    

}
