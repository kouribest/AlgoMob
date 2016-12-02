package Project;

/**
 * Created by skouriba on 30/11/16.
 */
import jbotsim.Node;
import java.awt.geom.Point2D;

public abstract class VectorNode extends Node {
    public static final double DEVIATION = 20.0;
    public Point2D vector = new Point2D.Double(0, 0);
    private Point2D nextPoint;
    private double speed;

    public void travelTo(Point2D requestedTarget) {
        Point2D projection = add(getLocation(), vector);
        Point2D requestedChange = sub(requestedTarget, projection);
        if (len(requestedChange) < DEVIATION) {
            nextPoint = requestedTarget;
        }else{
            Point2D allowedChange = mul(requestedChange, DEVIATION / len(requestedChange));
            nextPoint = add(projection, allowedChange);
        }
        setDirection(nextPoint);
        vector = sub(nextPoint, getLocation());
        speed = len(vector)/10.0; // move split into 10 rounds (smooth rendering)
    }

    @Override
    public void onClock() {
        if (nextPoint != null){
            if (distance(nextPoint) > speed) {
                move(speed);
            }else{
                setLocation(nextPoint);
                nextPoint = null;
                onPointReached(getLocation());
            }
        }
    }

    /* Called when the next point is reached (to be overridden) */
    public abstract void onPointReached(Point2D point);

    /* Vector operations */
    private static double len(Point2D p) {
        return (new Point2D.Double(0,0)).distance(p);
    }
    private static Point2D mul(Point2D p, double a) {
        return (new Point2D.Double(p.getX()*a, p.getY()*a));
    }
    private static Point2D sub(Point2D p1, Point2D p2) {
        return (new Point2D.Double(p1.getX()-p2.getX(), p1.getY()-p2.getY()));
    }
    private static Point2D add(Point2D p1, Point2D p2) {
        return (new Point2D.Double(p1.getX()+p2.getX(), p1.getY()+p2.getY()));
    }
}
