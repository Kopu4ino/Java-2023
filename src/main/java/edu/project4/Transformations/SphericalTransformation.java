package edu.project4.Transformations;

import edu.project4.ColorUtils.Color;
import edu.project4.ColorUtils.FractalColor;
import edu.project4.Figure.Point;

public class SphericalTransformation implements Transformation {
    private final Color color;

    public SphericalTransformation(FractalColor fractalColor) {
        this.color = fractalColor.toColor();
    }

    @Override
    public Point transform(Point point) {
        double rSquared = point.x() * point.x() + point.y() * point.y();
        double invRSquared = rSquared > 0 ? 1.0 / rSquared : 0;
        double newX = point.x() * invRSquared;
        double newY = point.y() * invRSquared;
        return new Point(newX, newY);
    }

    @Override
    public Color getColor() {
        return color;
    }
}
