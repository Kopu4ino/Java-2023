package edu.project4.Transformations;

import edu.project4.ColorUtils.Color;
import edu.project4.ColorUtils.FractalColor;
import edu.project4.Figure.Point;

public class DiscTransformation implements Transformation {
    private final Color color;

    public DiscTransformation(FractalColor fractalColor) {
        this.color = fractalColor.toColor();
    }

    @Override
    public Point transform(Point point) {
        double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
        double theta = Math.atan2(point.y(), point.x());
        double phi = Math.PI * r;
        double newX = (theta / Math.PI) * Math.sin(phi);
        double newY = (theta / Math.PI) * Math.cos(phi);
        return new Point(newX, newY);
    }

    @Override
    public Color getColor() {
        return color;
    }
}
