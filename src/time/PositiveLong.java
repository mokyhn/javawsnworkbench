package time;

public class PositiveLong 
{
    private Long value;
    
    public PositiveLong(long l) {
        if (l >= 0)
        {
            this.value = l;
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }
    
    public void increment()
    {
        if (value < Long.MAX_VALUE)
        {
            value++;
        }
        else
        {
            throw new ArithmeticException("Overflow");
        }
    }
    
    public void add(long x) 
    {
       if (x < 0) 
       {
            throw new IllegalArgumentException();
       } 
       else
       {
            long tmp = x + value;

            if (tmp < 0)
            {
                throw new ArithmeticException("Overflow");

            } 
            else
            {
                this.value = tmp;
            }
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
