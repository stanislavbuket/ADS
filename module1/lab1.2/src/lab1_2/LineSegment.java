package lab1_2;

public record LineSegment(double x1, double y1, double x2, double y2) {

    public double getLength() {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public double getAngle() {
        double angle = Math.toDegrees(Math.atan2(y2 - y1, x2 - x1));
        return angle < 0 ? angle + 360 : angle;
    }

    @Override
    public String toString() {
        return String.format("LineSegment[(%.2f, %.2f) -> (%.2f, %.2f), length=%.2f, angle=%.2f]", 
            x1, y1, x2, y2, getLength(), getAngle());
    }
}