package wsn.time;

public class PositiveLong 
{
    private Long value;
    
    public PositiveLong(long l) {
        assert(l >= 0);
        if (l >= 0)
        {
            this.value = l;
        }
    }
    
    public void increment()
    {
        assert(value < Long.MAX_VALUE);
        value++;
    }
    
    public void add(long x)
    {
        assert (x >= 0);

        long tmp = x + value;

        if (tmp < 0)
        {
            throw new ArithmeticException("Overflow");

        } else
        {
            this.value = tmp;
        }
    }
    
    public long value() 
    {
        return value;
    }
    
    @Override
    public String toString() 
    {
        return Long.toString(value);
    }
}
