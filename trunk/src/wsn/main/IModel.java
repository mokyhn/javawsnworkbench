package wsn.main;

import java.util.Collection;
import java.util.List;
import wsn.agent.ITimeAgent;
import wsn.time.AbsoluteClock;
import wsn.topology.MetricTopology;

public interface IModel
{

    AbsoluteClock getAbsoluteClock();

    Collection<ITimeAgent> getAgents();
    MetricTopology getTopology();
    
    
    List<Double> getTimeEstimates();

    
}
