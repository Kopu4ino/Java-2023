package edu.project4.Transformations;

import edu.project4.ColorUtils.Color;
import edu.project4.ColorUtils.FractalColor;
import edu.project4.Figure.Point;
import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.exp;
import static java.lang.Math.sin;

public class ExponentialTransformation implements Transformation {
    private final Color color;

    public ExponentialTransformation(FractalColor fractalColor) {
        this.color = fractalColor.toColor();
    }

    @Override
    public Point transform(Point point) {
        double x = point.x();
        double y = point.y();
        double expValue = exp(x - 1);

        double newX = expValue * cos(PI * y);
        double newY = expValue * sin(PI * y);
        return new Point(newX, newY);
    }

    @Override
    public Color getColor() {
        return color;
    }
}
