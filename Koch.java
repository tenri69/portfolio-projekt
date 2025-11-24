package koch;

import fractal.Fractal;
import fractal.Side;
import fractal.Point;

import java.util.ArrayList;
import java.util.List;

public class Koch extends Fractal {
    private final Point startPoint;
    private final int length;

    /**
     * Creates an object that handles Koch's fractal.
     *
     * @param length the length of the triangle side
     */
    public Koch(int length, Point startPoint) {
        super();
        this.length = length;
        this.startPoint = startPoint;
    }

    /**
     * Returns the title.
     *
     * @return the title
     */
    @Override
    public String getTitle() {
        return "Kochs triangel";
    }

    /**
     * Skapar en sida i snöflingan utifrån senaste sidan,
     * en vinkel och en sidlängd.
     * Har fraktalen en Consumer som tar delresultat,
     * postas den även till den.
     *
     * @param length längden som linjen ska ha
     * @param alpha  vinkeln som linjen ska ha
     */
    private void addStep(double length, int alpha) {
        Point current;

        if (sidesToDraw.isEmpty()) {
            current = this.startPoint;
        } else {
            Side last = sidesToDraw.get(sidesToDraw.size() - 1);
            current = last.p2;
        }
        double x = current.getX() + length * Math.cos(alpha * Math.PI / 180);
        double y = current.getY() - length * Math.sin(alpha * Math.PI / 180);
        this.addSide(new Side(current, new Point(x, y)));
    }

    /**
     * Metoden som får fraktalen att beräknas
     *
     * @return returnerar sidorna som fraktalen består av
     */
    public List<Side> getSidesToDraw() {
        sidesToDraw = new ArrayList<>();
        fractalLine(order, length, 0);
        fractalLine(order, length, 120);
        fractalLine(order, length, 240);
        return this.sidesToDraw;
    }

    /**
     * Recursive method: Draws a recursive line of the triangle.
     * Use addStep to draw things
     */

    public void draw(int order, double length) {
        fractalLine(order, length, 0);
        fractalLine(order, length, 120);
        fractalLine(order, length, 240);
    }


    private void fractalLine(int order, double length, int alpha) {
        if (order == 0) {
            addStep(length, alpha);
        } else {
            fractalLine(order-1, length/3, alpha);
            fractalLine(order-1, length/3, alpha-60);
            fractalLine(order-1, length/3, alpha+60);
            fractalLine(order-1, length/3, alpha);
        }
    }
}
