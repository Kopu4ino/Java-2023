package edu.project4.Image;

import edu.project4.Figure.Pixel;

public class FractalImage {
    private final int width;
    private final int height;
    public final Pixel[][] pixels;

    public FractalImage(int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = new Pixel[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                pixels[x][y] = new Pixel(0, 0, 0, 0);
            }
        }
    }

    public Pixel getPixel(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return pixels[x][y];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void setPixel(int x, int y, Pixel pixel) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            pixels[x][y] = pixel;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public boolean contains(int x, int y) {
        return x >= 0 && x < this.width && y >= 0 && y < this.height;
    }
}
