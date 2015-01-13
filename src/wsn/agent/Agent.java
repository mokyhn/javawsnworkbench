package wsn.agent;

import java.util.LinkedList;
import wsn.time.RelativeClock;
import wsn.main.IInbox;
import wsn.math.Numbers;
import wsn.math.Random;
import wsn.main.TimeReceivedMessage;
import wsn.communication.Broadcast;
import wsn.communication.IMessage;
import wsn.communication.TimeMessage;
import wsn.communication.TimeMessageHeader;

public class Agent extends AbstractAgent implements IAgent, ITimeAgent
{    
    
    
    private final Random rnd; 
    private final RelativeClock rel_clock;
    IInbox<TimeReceivedMessage> receivedMessages;
    private Broadcast broadcast;
       
    private long localClockAdjustment = 0L;
    
    private long importance_counter = 0L;
    private final long timeintervalBetweenImportance = 2000;
    private long timePreviousImportance = 0;
    
    public Agent(int id, Random rnd, RelativeClock rel_clock)
    {
        super(id);
        this.rnd = rnd;
        this.rel_clock = rel_clock;
    }

    public void setBroadcaster(Broadcast broadcast)
    {
        this.broadcast = broadcast;
    }
  
    public void setInboxBuffer(IInbox<TimeReceivedMessage> receiveBuffer)
    {
        this.receivedMessages = receiveBuffer;
    }

    
    @Override
    public void handleMessageReception()
    {
        if (receivedMessages.hasMessages())
        {
            TimeReceivedMessage rm = receivedMessages.take();
            long tmp = extractSuggestedTimeModification(rm);
            localClockAdjustment += tmp;
        }
    }

    @Override
    public void handleMessagesSending()
    {
        //TODO: WORK on rules for publication / forwarding...
        if (receivedMessages.hasMessages())
        {        
            TimeReceivedMessage rm = receivedMessages.take();
            
            if (rnd.getInt(100) <= 5) // Forward message in some cases
            {
                if (rm.getMessage() instanceof TimeMessage) 
                {
                     
                    TimeMessage tm = (TimeMessage) rm.getMessage();
                    broadcast.send(new TimeMessage(
                                               this.getID(), 
                                               importance_counter, 
                                               tm, 
                                               rel_clock.getTime()-rm.getArrivalTime(), 
                                               getTimeEstimate()));
                }
            }

        }
        else
        {     
            if (rnd.getInt(100) <= 5) {
            broadcast.send(new TimeMessage(this.getID(), 
                                           importance_counter, 
                                           null, 
                                           0L, 
                                           getTimeEstimate()));
            }
           
        
        }
    }
    
    
    @Override
    public Long getTimeEstimate()
    {
        return rel_clock.getTime() + localClockAdjustment;
    }
    
    private void updateImportance()
    {
        importance_counter++;        
                 
        if (rel_clock.getTime() > timeintervalBetweenImportance + timePreviousImportance)
        {                                                             
            importance_counter = 0;
            timePreviousImportance = rel_clock.getTime();
        } 
   
    }
    
    
    @Override
    public boolean receive(IMessage m)
    {
        updateImportance();
        if (beenHereBefore(_id, m))
        {
            return false;
        } 
        else
        {
            return receivedMessages.add(new TimeReceivedMessage(m, rel_clock.getTime()));  // Store message and log reception time      
        }
    }
    
    private boolean beenHereBefore(int id, IMessage m)
    {              
        while (m  instanceof TimeMessage)
        {
            TimeMessage fm = (TimeMessage) m;                        
            TimeMessageHeader tmh = fm.getHeader();
            if (tmh.getSender() == id)
            {
                return true;
            }
            else
            {
                m = fm.getMessage();
            }
        }

        return false;   
    }
     
   
     
  
    private long extractSuggestedTimeModification(TimeReceivedMessage rm)
    {      
        LinkedList<TimeMessageHeader> headers = new LinkedList<>();       
              
        Long myArrivaltime = rm.getArrivalTime();
        Long timeNow = rel_clock.getTime();
        Long handlingDelay = timeNow - myArrivaltime;

        assert(handlingDelay >= 0);
        
        LinkedList<Long> suggestedTime = new LinkedList<>();
        
        IMessage m = rm.getMessage();
        //Double accumulatedImportance = 0d;
        
        while (m  instanceof TimeMessage)
        {
            TimeMessage fm = (TimeMessage) m;                        
            suggestedTime.addLast((fm.getHeader().getTimeEstimate() + handlingDelay));
            headers.addLast(fm.getHeader());
            //accumulatedImportance += fm.getHeader().getImportance();
            handlingDelay += fm.getHeader().getHandlingDelay();
            m = fm.getMessage();
        }

   
        
        long best = Long.MAX_VALUE;

        for (Long l : suggestedTime)
        {
            long candidate = Math.max(l, getTimeEstimate());
            if (candidate < best)
            {
                best  = candidate;
            }
        }
        long matchingValue = Numbers.difference(getTimeEstimate(), best);
        
        return matchingValue;
     
    }

    @Override
    public String toString()
    {
        return "Agent " + getID() + " adjusted time is " + getTimeEstimate() + " " + receivedMessages.toString() + " of importance " + importance_counter;
        //return getTimeEstimate() + "";
        //return getTimeEstimate() + " , " + rel_clock.getTime();

    }

   
    
}