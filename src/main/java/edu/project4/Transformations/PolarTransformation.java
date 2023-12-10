package edu.project4.Transformations;

import edu.project4.ColorUtils.Color;
import edu.project4.ColorUtils.FractalColor;
import edu.project4.Figure.Point;

public class PolarTransformation implements Transformation {
    private final Color color;

    public PolarTransformation(FractalColor fractalColor) {
        this.color = fractalColor.toColor();
    }

    @Override
    public Point transform(Point point) {
        double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
        double theta = Math.atan2(point.y(), point.x());
        double newX = theta / Math.PI;
        double newY = r - 1.0;
        return new Point(newX, newY);
    }

    @Override
    public Color getColor() {
        return color;
    }
}
