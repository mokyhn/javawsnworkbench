package wsn.math;

public class Triple<S,T,U>
{
    private final S s;
    private final T t;
    private final U u;
    
    public Triple(S s, T t, U u) 
    {
        this.s = s;
        this.t = t;
        this.u = u;
    }
  
    public S getFst()
    {
        return s;
    }

    public T getSnd()
    {
        return t;
    }
    
    public U getTrd()
    {
        return u;
    }
}
