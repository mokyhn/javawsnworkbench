package wsn;

import java.util.LinkedList;

public class LimitedMessageBuffer<T> implements IInbox<T>
{
    private final LinkedList<T> receivedMessages;
    private final int max_size;
    
    public LimitedMessageBuffer(int max_size)
    {
        this.max_size = max_size;
        receivedMessages = new LinkedList<>();
    }
    
    @Override
    public T take() 
    {
        return receivedMessages.removeLast();
    }
  
    public T peak() 
    {
        return receivedMessages.peekLast();
    }
    
    @Override
    public boolean hasMessages()
    {
        return !receivedMessages.isEmpty();
    }
    
    @Override
    public boolean add(T t)
    {        
        if (receivedMessages.size() <  max_size) 
        {
            receivedMessages.addFirst(t);  // Store message and log reception time      
            return true;
        }
        return false;
    }
          
    @Override
    public String toString()
    {
        return "Inboxbuffer: " + receivedMessages.size();
    }
    
}
