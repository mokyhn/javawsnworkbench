package wsn.math;

import java.util.ArrayList;
import java.util.List;

public class PointGenerator
{
    public static List<Point> generatePoints(int n, long seed, double area_radius, double individual_distance) 
    {
        List<Point> result = new ArrayList<>();
        
        Random rnd = new Random(seed);
        
        while (result.size() < n)
        {
            if (result.isEmpty()) 
            {
                result.add(new Point(rnd.nextDouble() * area_radius, rnd.nextDouble() * area_radius));
            }
            else
            {
                int m = result.size();
                Point p = result.get(rnd.getInt(m));                
                Vector v = new Vector(rnd.nextDouble() * individual_distance, rnd.nextDouble() * individual_distance);                
                result.add(p.add(v));
                
            }
        }
        
        return result;
    }
}
