package wsn.math;

public class Numbers
{
    public static long difference(Long a, long b)
    {
        return a >= b ? a - b : b - a;
        
    }
    
      private Long abs(Long l) {
         return l >= 0 ? l : -l;
     }
}
