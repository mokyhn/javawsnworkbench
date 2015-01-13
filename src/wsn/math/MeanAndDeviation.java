package wsn.math;

public class MeanAndDeviation
{

    private double sum = 0;
    private double squaredSum = 0;
    private double measurements = 0;
    private final double mean;
    private final double variance;
    private final double deviation;
    
    public MeanAndDeviation(Double[] l)
    {
        for (double x : l) 
        {
            sum += x;
            squaredSum += x*x;
            measurements++;
        }
        mean = sum / measurements;
        variance = (squaredSum / measurements) - mean*mean;
        deviation = Math.sqrt(variance);
    }
          
    public double mean()
    {
        return mean;
    }
    
    public double deviation()
    {
        return deviation;
    }
    
    public double sum()
    {
        return sum;
    }
    
    public double squaredSum()
    {
        return squaredSum;
    }
    
    public static void main(String[] args) 
    {
        MeanAndDeviation s = new MeanAndDeviation(new Double[] {1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0,10.0});
        System.out.println(s.mean());
        System.out.println(s.deviation());
    }
}
