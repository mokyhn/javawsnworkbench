package wsn.communication;

public class TimeMessage implements IMessage
{
   
    private final TimeMessageHeader header;
    private final TimeMessage message;
      
    public TimeMessage(int id, double importance, TimeMessage m, long handlingDelay, long currentTimeEstimate) 
    {           
        header = new TimeMessageHeader(id, importance, handlingDelay, currentTimeEstimate);
        message = m;
    }
    
    @Override
    public Integer getSender()
    {
        return header.getSender();
    }
    
    public TimeMessage getMessage()
    {
        return message;
    }

    @Override
    public Double getNoteworthyness()
    {
        return header.getImportance();
    }
    
    public TimeMessageHeader getHeader()
    {
        return header;
    }
    
}
