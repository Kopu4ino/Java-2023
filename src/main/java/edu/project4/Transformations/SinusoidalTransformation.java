package edu.project4.Transformations;

import edu.project4.ColorUtils.Color;
import edu.project4.ColorUtils.FractalColor;
import edu.project4.Figure.Point;

public class SinusoidalTransformation implements Transformation {
    private final Color color;

    public SinusoidalTransformation(FractalColor fractalColor) {
        this.color = fractalColor.toColor();
    }

    @Override
    public Point transform(Point point) {
        double newX = Math.sin(point.x());
        double newY = Math.sin(point.y());
        return new Point(newX, newY);
    }

    @Override
    public Color getColor() {
        return color;
    }
}
