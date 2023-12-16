package edu.project4.Transformations;

import edu.project4.ColorUtils.Color;
import edu.project4.ColorUtils.FractalColor;
import edu.project4.Figure.Point;

public class SpiralTransformation implements Transformation {
    private final Color color;

    public SpiralTransformation(FractalColor fractalColor) {
        this.color = fractalColor.toColor();
    }

    @Override
    public Point transform(Point point) {
        double x = point.x();
        double y = point.y();
        double r = Math.sqrt(x * x + y * y);
        double theta = Math.atan2(y, x);

        if (r == 0) {
            return new Point(0, 0);
        }

        double sinTheta = Math.sin(theta);
        double cosTheta = Math.cos(theta);
        double sinR = Math.sin(r);
        double cosR = Math.cos(r);

        double newX = (1 / r) * (cosTheta + sinR);
        double newY = (1 / r) * (sinTheta - cosR);

        return new Point(newX, newY);
    }

    @Override
    public Color getColor() {
        return color;
    }
}
