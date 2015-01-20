package wsn.math;

public class Vector
{

    private final double x;
    private final double y;

    public Vector(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
  
    public Vector(Vector v)
    {
        this.x = v.getX();
        this.y = v.getY();
    }

    public Vector(Point p1, Point p2)
    {
        this.x = p2.getX() - p1.getX();
        this.y = p2.getY() - p1.getY();
    }
    
    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public double norm()
    {
        return Math.sqrt(getX() * getX() + getY() * getY());
    }

    public double angle()
    {
        if (getX() > 0)
        {
            return Math.acos(getY() / getX());
        } else
        {
            if (getX() < 0 && getY() >= 0)
            {
                return Math.acos(getY() / getX()) + Math.PI;
            } else
            {
                if (getX() < 0 && getY() < 0)
                {
                    return Math.acos(getY() / getX()) - Math.PI;
                } else
                {
                    if (getX() == 0 && getY() > 0)
                    {
                        return Math.PI / 2;
                    } else
                    {
                        if (getX() == 0 && getY() < 0)
                        {
                            return -Math.PI / 2;
                        } else
                        {
                            return Double.NaN;
                        }
                    }
                }
            }
        }
    }

    public Vector add(Vector v)
    {
        return new Vector(getX() + v.getX(), getY() + v.getY());
    }
   
    public Vector multiply(Double s)
    {
        return new Vector(getX()*s, getY()*s);
    }
    
    public Vector normalize()
    {
        double norm = norm();
        return new Vector(x / norm, y / norm);
    }
    
    @Override
    public String toString()
    {
        return "v(" + getX() + ", " + getY() + ")";
    }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof Vector)
        {
            Vector tmp = (Vector) o;
            return tmp.getX() == this.getX() && tmp.getY() == this.getY();
        } else
        {
            return false;
        }
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.getX()) ^ (Double.doubleToLongBits(this.getX()) >>> 32));
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.getY()) ^ (Double.doubleToLongBits(this.getY()) >>> 32));
        return hash;
    }
}
