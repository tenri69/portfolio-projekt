package fractal;

public class Side {
    public Point p1;
    public Point p2;

    public Side(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Point getP1() {
        return p1;

    }
    public Point getP2() {
        return p2;

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Side other = (Side) obj;

        return (this.getP1().equals(other.getP1()) && this.getP2().equals(other.getP2())) ||
                (this.getP1().equals(other.getP2()) && this.getP2().equals(other.getP1()));
    }

    @Override
    public int hashCode() {
        return p1.hashCode() + p2.hashCode();
    }
}
