package edu.project4.Figure;

import edu.project4.ColorUtils.Color;

public record Pixel(int r, int g, int b, int hitCount) {

    public Pixel update(Color color) {
        int red = color.red();
        int green = color.green();
        int blue = color.blue();

        if (this.hitCount == 0) {
            return new Pixel(red, green, blue, 1);
        }
        int updatedRed = (this.r + red) / 2;
        int updatedGreen = (this.g + green) / 2;
        int updatedBlue = (this.b + blue) / 2;
        return new Pixel(updatedRed, updatedGreen, updatedBlue, this.hitCount + 1);
    }
}
