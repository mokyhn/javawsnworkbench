package wsn.math;

public class LinearRegression
{   
    double beta_0, beta_1;
    double r;

    
    public LinearRegression(Double x[], Double y[])
    {        
        double xmean;
        double ymean;

        assert(x.length == y.length);
        assert(x.length > 0);
        
        MeanAndDeviation mx = new MeanAndDeviation(x);
        MeanAndDeviation my = new MeanAndDeviation(y);
               
        int m = x.length;
        
        double sum = 0;
        for (int i = 0; i < m; i++)
        {
            sum += x[i]*y[i];
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
        LinearRegression lr = new LinearRegression(new Double[]{1.0, 2.0, 3.0}, new Double[]{2.0, 3.0, 4.0});
        System.out.println(lr.toString());

        LinearRegression lr2 = new LinearRegression(new Double[]{1.0, 4.0, 9.0, 10.0, 11.0}, new Double[]{2.0, 77.0, 92.0, 123.0, 124.0});
        System.out.println(lr2.toString());
        
    }
    
}
