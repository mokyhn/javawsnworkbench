package wsn.main;

public interface IInbox<T>
{

    boolean add(T t);
    boolean hasMessages();
    T take();
    
}
