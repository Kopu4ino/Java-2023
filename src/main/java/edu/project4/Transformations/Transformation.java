package edu.project4.Transformations;

import edu.project4.ColorUtils.Color;
import edu.project4.Figure.Point;

public interface Transformation {
    Point transform(Point point);

    Color getColor();
}
