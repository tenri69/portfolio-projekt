package mountain;

import fractal.Fractal;
import fractal.Point;
import fractal.Side;

import java.util.*;

public class Mountain extends Fractal {
    private int order = 0;
    private Point p1;
    private Point p2;
    private Point p3;
    private double dev;
    private Map<Side, Point> midpoints = new HashMap<Side, Point>();

    public Mountain(Point p1, Point p2, Point p3, double dev) {
        super();
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.dev = dev;

    }

    /**
     * Returns the title.
     *
     * @return the title
     */
    @Override
    public String getTitle() {
        return "Mountain";
    }
    public int getOrder() { return order; }
    public void setOrder(int Order) { order=Order; }

      /**
     * Metoden som får fraktalen att beräknas
     *
     * @return returnerar sidorna som fraktalen består av
     */
      public List<Side> getSidesToDraw() {
          midpoints.clear();
          sidesToDraw = new ArrayList<>();
          divideTriangle(order, p1, p2, p3, dev);
          return sidesToDraw;
      }

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

    private void divideTriangle(int order, Point a, Point b, Point c, double dev) {
        if (order == 0) {
            addSide(new Side(a, b));
            addSide(new Side(b, c));
            addSide(new Side(c, a));
        } else  {
            Point abMid = getMidPoint(a, b, dev);
            Point bcMid = getMidPoint(b, c, dev);
            Point caMid = getMidPoint(c, a, dev);
            double newDev = dev / 2.0;

            divideTriangle(order - 1, a,      abMid, caMid, newDev);
            divideTriangle(order - 1, abMid,  b,     bcMid, newDev);
            divideTriangle(order - 1, caMid,  bcMid, c,     newDev);
            divideTriangle(order - 1, abMid,  bcMid, caMid, newDev);
        }
    }


    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}




