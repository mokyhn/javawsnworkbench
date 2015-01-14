package wsn.math;

import java.util.Arrays;
import java.util.List;

public class LinearRegression
{   
    double beta_0, beta_1;
    double r;

    
    public LinearRegression(List<Double> x, List<Double> y)
    {        
        double xmean;
        double ymean;

        assert(x.size() == y.size());
        assert(x.size() > 0);
        
        MeanAndDeviation mx = new MeanAndDeviation(x);
        MeanAndDeviation my = new MeanAndDeviation(y);
               
        int m = x.size();
        
        double sum = 0;
        for (int i = 0; i < m; i++)
        {
            sum += x.get(i)*y.get(i);
        }
        
        
        beta_1 = ( m * sum - mx.sum() * my.sum() ) / ( m*mx.squaredSum() - mx.sum()*mx.sum()) ;      
        xmean = mx.mean();
        ymean = my.mean();
        beta_0 = ymean - beta_1 * xmean;
        
        // Correlation coefficient
        r = ( m * sum - mx.sum() * my.sum() ) 
                /
                ( 
                    Math.sqrt(m*mx.squaredSum() - mx.sum()*mx.sum()) 
                    * 
                    Math.sqrt(m*my.squaredSum() - my.sum()*my.sum())
                );
        
        
    }
    
    public double a() 
    {
        return beta_0;
    }
    
    public double b() 
    {
        return beta_1;
    }
    
    
    public double r()
    {
        return r;
    }
    
    public String toString()
    {
        return "y = " + beta_0 + " + " + beta_1 + " *x,   r = " + r;
        
    }
    
    public static void main(String[] args) 
    {
        LinearRegression lr = new LinearRegression(
                Arrays.asList(1.0, 2.0, 3.0), 
                Arrays.asList(2.0, 3.0, 4.0));
        System.out.println(lr.toString());

        LinearRegression lr2 = new LinearRegression(
                Arrays.asList(1.0, 4.0, 9.0, 10.0, 11.0), 
                Arrays.asList(2.0, 77.0, 92.0, 123.0, 124.0));
        System.out.println(lr2.toString());
        
    }
    
}
