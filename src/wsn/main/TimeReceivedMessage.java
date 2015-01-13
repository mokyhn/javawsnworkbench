package wsn.main;

import wsn.communication.IMessage;

public class TimeReceivedMessage
{
    private final IMessage message;
    private final Long arrivalTime;
    
    public TimeReceivedMessage(IMessage m, long time)
    {
        this.message = m;
        this.arrivalTime = time;
    }

    public IMessage getMessage()
    {
        return message;
    }

    public Long getArrivalTime()
    {
        return arrivalTime;
    }
}
