package agent;

public abstract class AbstractAgent 
{
    protected final int _id;
    
    public AbstractAgent(int id)
    {
        _id = id;
    }
    
    
    public int getID()
    {
        return _id;
    }
    
}
