package edu.project4.ColorUtils;

public enum FractalColor {
    RED(255, 0, 0),
//    PURPLE(128, 0, 128),
    WHITE(255, 255, 255),
//    BLACK(0, 0, 0),
//    GOLD(255, 215, 0),
    AZURE(0, 127, 255),
    SKY_BLUE(135, 206, 235);

    private final int red;
    private final int green;
    private final int blue;

    FractalColor(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public Color toColor() {
        return new Color(red, green, blue);
    }
}
