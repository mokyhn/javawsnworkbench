package wsn.communication;

public class TimeMessageHeader
{
    private final int sender;
    private final double importance;
    private final long handlingDelay;
    private final long currentTimeEstimate;
    
    public TimeMessageHeader(int sender, double importance, long handlingDelay, long currentTimeEstimate) 
    {
        if (handlingDelay < 0) 
        {
            throw new IllegalArgumentException("Handling time cannot be negative");
        }               
                
        this.sender = sender;     
        this.importance = importance;
        this.handlingDelay = handlingDelay;
        this.currentTimeEstimate = currentTimeEstimate;
    }
        
    public Integer getSender()
    {
        return sender;
    }

    
    public Double getImportance()
    {
        return importance;
    }  
       
    public long getTimeEstimate()
    {
        return currentTimeEstimate;
    }
    
    public long getHandlingDelay() 
    {
        return handlingDelay;
    }    
}
