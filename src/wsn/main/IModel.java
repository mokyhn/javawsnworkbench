package wsn.main;

import wsn.agent.IAgent;
import java.util.Collection;
import wsn.time.AbsoluteClock;
import wsn.math.Random;

public interface IModel
{

    AbsoluteClock getAbsoluteClock();

    Collection<IAgent> getAgents();

    Random getRandom();

    
}
