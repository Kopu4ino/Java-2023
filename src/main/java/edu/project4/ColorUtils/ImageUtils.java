package edu.project4.ColorUtils;

import edu.project4.Figure.Pixel;
import edu.project4.Image.FractalImage;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageUtils {
    private ImageUtils() {
    }

    public static void saveFractalImage(FractalImage fractalImage, String path) throws IOException {
        int width = fractalImage.getWidth();
        int height = fractalImage.getHeight();

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Pixel pixel = fractalImage.getPixel(x, y);

                int colorValue = getRGB(pixel.r(), pixel.g(), pixel.b());
                bufferedImage.setRGB(x, y, colorValue);
            }
        }

        File outputFile = new File(path);
        ImageIO.write(bufferedImage, "png", outputFile);
    }

    @SuppressWarnings("MagicNumber")
    private static int getRGB(int r, int g, int b) {
        return (r << 16) | (g << 8) | b;
    }
}
