package wsn.math;

public class Random
{

    private static final Long a = 1664525L;
    private static final Long c = 1013904223L;

    private long current;

    public Random()
    {
        current = 1L;
    }

    public Random(long seed)
    {
        current = seed;
    }

    public Double nextDouble()
    {
        Long t1 = getNextLong();
        Long t2 = getNextLong();

        if (t1 > t2)
        {
            if (t1 == 0)
            {
                return nextDouble();
            } else
            {
                return (t2 + 0d) / t1;
            }
        } else
        {
            if (t2 == 0)
            {
                return nextDouble();
            } else
            {
                return (t1 + 0d) / t2;
            }
        }
    }

    public Long getNextLong()
    {
        current = (a * current + c) % (2L << 32);
        if (current < 0)
        {
            current = -current;
        }
        return current;
    }

    /**
     * 
     * @param range
     * @return an integer in interval 0..range-1
     */
    public int getInt(int range)
    {
        return (int) ((nextDouble()-0.00001) * range);
    }
}
