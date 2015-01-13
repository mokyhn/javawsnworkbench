package wsn.agent;

import wsn.communication.IMessage;

public interface IAgent
{
    int getID();
    
    boolean receive(IMessage m);
    
    void handleMessageReception();
    void handleMessagesSending();

}
