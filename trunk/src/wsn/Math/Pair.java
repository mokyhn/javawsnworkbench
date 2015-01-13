package wsn.Math;

public class Pair<S,T>
{
    private final S s;
    private final T t;
    
    public Pair(S s, T t) 
    {
        this.s = s;
        this.t = t;
    }
  
    public S getFst()
    {
        return s;
    }

    public T getSnd()
    {
        return t;
    }
    
}
