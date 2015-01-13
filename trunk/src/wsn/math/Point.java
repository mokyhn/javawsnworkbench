package wsn.math;

public class Point
{

    private final double x;
    private final double y;

    public Point(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    
    public Point(Point p) 
    {
        this.x = p.getX();
        this.y = p.getY();
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public double distance(Point p)
    {
        return distance(this, p);
    }

    public static double distance(Point p1, Point p2)
    {
        return Vector.getVector(p1, p2).norm();
    }

    public Vector asVector()
    {
        return new Vector(getX(), getY());
    }

    @Override
    public String toString()
    {
        return "p(" + getX() + ", " + getY() + ")";
    }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof Point)
        {
            Point tmp = (Point) o;
            return tmp.getX() == this.getX() && tmp.getY() == this.getY();
        } else
        {
            return false;
        }
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.getX()) ^ (Double.doubleToLongBits(this.getX()) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.getY()) ^ (Double.doubleToLongBits(this.getY()) >>> 32));
        return hash;
    }

}
