package wsn.time;

public class RelativeClock
{
    final AbsoluteClock clock;    
    final long offset;
    final double driftFactor;
    
    public RelativeClock(AbsoluteClock clock)
    {
        this.clock = clock;
        this.offset = 0;
        this.driftFactor = 1.0;
    }
    
    public RelativeClock(AbsoluteClock clock, long offset)
    {
        this.clock = clock;
        this.offset = offset;
        this.driftFactor = 1.0;
    }
    
    public RelativeClock(AbsoluteClock clock, long offset, double driftFactor)
    {
        this.clock = clock;
        this.offset = offset;
        this.driftFactor = driftFactor;
    }
    
    public long getTime()
    {
        return offset + (long) (clock.getTime() * driftFactor);
    }
    
    public long getActualOffset()
    {                
        return offset;
    }
}
