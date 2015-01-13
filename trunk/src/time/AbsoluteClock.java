package time;

public class AbsoluteClock
{
    private final PositiveLong time;
    
    public AbsoluteClock()
    {
        time = new PositiveLong(0);
    }

    public AbsoluteClock(long t)
    {
        time = new PositiveLong(t);
    }
    
    public void tick()
    {
        time.increment();
    }
    
    public long getTime()
    {
        return time.value();
    }
    
}
