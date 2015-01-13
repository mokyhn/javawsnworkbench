package wsn;

import agent.IAgent;
import java.util.Collection;
import time.AbsoluteClock;
import wsn.Math.Random;

public interface IModel
{

    AbsoluteClock getAbsoluteClock();

    Collection<IAgent> getAgents();

    Random getRandom();

    
}
