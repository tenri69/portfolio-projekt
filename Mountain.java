package mountain;

import fractal.Fractal;
import fractal.Point;
import fractal.Side;

import java.util.*;

/**
 * Represents a fractal mountain generated using recursive midpoint displacement.
 * <p>
 * The fractal is defined by an initial triangle (three points) and a deviation value
 * controlling the randomness applied at each recursion level when calculating
 * midpoints. Each recursion subdivides a triangle into four smaller triangles using
 * perturbed midpoints, creating a naturalistic mountain shape.
 * </p>
 *
 * <p>
 * A cache of previously computed midpoints is used to ensure that shared edges
 * between triangles always receive the same midpoint, preventing visual gaps.
 * </p>
 */
public class Mountain extends Fractal {

    /** The recursion depth of the fractal. */
    private int order = 0;

    /** First point of the initial triangle. */
    private Point p1;

    /** Second point of the initial triangle. */
    private Point p2;

    /** Third point of the initial triangle. */
    private Point p3;

    /** Initial deviation used when randomizing midpoint height. */
    private double dev;

    /**
     * A map caching midpoints for each side to avoid recalculating and to ensure
     * consistent midpoint usage between shared triangle edges.
     */
    private Map<Side, Point> midpoints = new HashMap<>();

    /**
     * Creates a new Mountain fractal.
     *
     * @param p1  the first corner point of the initial triangle
     * @param p2  the second corner point of the initial triangle
     * @param p3  the third corner point of the initial triangle
     * @param dev the initial deviation used when randomizing midpoint displacement
     */
    public Mountain(Point p1, Point p2, Point p3, double dev) {
        super();
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.dev = dev;
    }

    /**
     * Returns the displayed title of the fractal.
     *
     * @return the title "Mountain"
     */
    @Override
    public String getTitle() {
        return "Mountain";
    }

    /**
     * Returns the current recursion order of the fractal.
     *
     * @return the recursion depth
     */
    public int getOrder() {
        return order;
    }

    /**
     * Sets the recursion depth for the mountain fractal.
     * Higher values produce more detailed fractals but require more computation.
     *
     * @param order the desired recursion level
     */
    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * Computes and returns all sides that should be drawn for the current
     * recursion order.
     * <p>
     * This method resets the midpoint cache and performs a recursive subdivision
     * starting from the initial triangle.
     * </p>
     *
     * @return a list of sides making up the fractal mountain shape
     */
    public List<Side> getSidesToDraw() {
        midpoints.clear();
        sidesToDraw = new ArrayList<>();
        divideTriangle(order, p1, p2, p3, dev);
        return sidesToDraw;
    }

    /**
     * Computes the midpoint between two points with a randomized vertical displacement.
     * <p>
     * If the midpoint for the given side was previously computed,
     * it is returned from the cache to ensure consistency.
     * </p>
     *
     * @param a   the first endpoint of the side
     * @param b   the second endpoint of the side
     * @param dev the current deviation applied to the midpoint
     * @return a new midpoint with random displacement
     */
    private Point getMidPoint(Point a, Point b, double dev) {
        Side s = new Side(a, b);
        if (midpoints.containsKey(s)) {
            return midpoints.get(s);
        }

        double mx = (a.getX() + b.getX()) / 2;
        double my = (a.getY() + b.getY()) / 2 + RandomUtilities.randFunc(dev);

        Point m = new Point(mx, my);
        midpoints.put(s, m);
        return m;
    }

    /**
     * Recursively subdivides a triangle into four smaller triangles using midpoint displacement.
     *
     * @param order the current recursion level
     * @param a     the first vertex of the triangle
     * @param b     the second vertex of the triangle
     * @param c     the third vertex of the triangle
     * @param dev   the deviation used for midpoint randomization at this level
     */
    private void divideTriangle(int order, Point a, Point b, Point c, double dev) {
        if (order == 0) {
            // At the base case, draw the triangle's edges
            addSide(new Side(a, b));
            addSide(new Side(b, c));
            addSide(new Side(c, a));
        } else {
            // Compute midpoints with noise
            Point abMid = getMidPoint(a, b, dev);
            Point bcMid = getMidPoint(b, c, dev);
            Point caMid = getMidPoint(c, a, dev);

            double newDev = dev / 2.0;

            // Recursively subdivide the triangle into four sub-triangles
            divideTriangle(order - 1, a,     abMid, caMid, newDev);
            divideTriangle(order - 1, abMid, b,     bcMid, newDev);
            divideTriangle(order - 1, caMid, bcMid, c,     newDev);
            divideTriangle(order - 1, abMid, bcMid, caMid, newDev);
        }
    }

    /**
     * Inherits the equality method from the Fractal superclass.
     *
     * @param obj the object to compare
     * @return true if equal according to the superclass implementation
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}





