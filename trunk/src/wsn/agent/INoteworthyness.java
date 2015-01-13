package wsn.agent;

public interface INoteworthyness
{
    /**
     * 
     * @return how much importance an agent has. An example would be connectivity 
     * - a highly connected agent could be considered more noteworthy than others.
     * It's estimations on the world could gain higher weight.
     */
    double getNoteWorthyness();
}
