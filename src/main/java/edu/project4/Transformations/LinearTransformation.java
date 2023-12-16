package edu.project4.Transformations;

import edu.project4.ColorUtils.Color;
import edu.project4.ColorUtils.FractalColor;
import edu.project4.Figure.Point;
import edu.project4.Linear.LinearTransformationCoefficients;

@SuppressWarnings("MultipleVariableDeclarations")
public class LinearTransformation implements Transformation {
    private final double a, b, c, d, e, f;
    private final Color color;

    public LinearTransformation(LinearTransformationCoefficients coeffs, FractalColor fractalColor) {
        this.a = coeffs.a();
        this.b = coeffs.b();
        this.c = coeffs.c();
        this.d = coeffs.d();
        this.e = coeffs.e();
        this.f = coeffs.f();
        this.color = fractalColor.toColor();
    }

    @Override
    public Point transform(Point point) {
        double x = point.x();
        double y = point.y();
        double newX = a * x + b * y + e;
        double newY = c * x + d * y + f;
        return new Point(newX, newY);
    }

    @Override
    public Color getColor() {
        return color;
    }
}
