package wsn.main;

import java.util.Collection;
import wsn.agent.ITimeAgent;
import wsn.math.Random;
import wsn.time.AbsoluteClock;
import wsn.topology.MetricTopology;

public interface IModel
{

    AbsoluteClock getAbsoluteClock();

    Collection<ITimeAgent> getAgents();
    MetricTopology getTopology();
    
    Random getRandom();

    
}
