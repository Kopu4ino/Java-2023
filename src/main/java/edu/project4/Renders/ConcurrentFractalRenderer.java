package edu.project4.Renders;

import edu.project4.Figure.Pixel;
import edu.project4.Figure.Point;
import edu.project4.Figure.Rect;
import edu.project4.Image.FractalImage;
import edu.project4.Transformations.Transformation;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import static java.lang.Math.log10;
import static java.lang.Math.pow;

public class ConcurrentFractalRenderer {
    private static final double GAMMA = 0.8;
    private static final int SKIP_ITER_CNT = 20;
    private static final long SEED = System.currentTimeMillis();

    public FractalImage render(
        int width,
        int height,
        Rect world,
        List<Transformation> transformations,
        int samples,
        int iterPerSample,
        int numberOfThreads
    ) {
        FractalImage canvas = new FractalImage(width, height);
        AtomicReference<Pixel>[][] pixels = new AtomicReference[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                pixels[x][y] = new AtomicReference<>(new Pixel(0, 0, 0, 0));
            }
        }

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        for (int thread = 0; thread < numberOfThreads; thread++) {
            executorService.submit(() -> {
                Random random = new Random(SEED + Thread.currentThread().getId());
                for (int i = 0; i < samples / numberOfThreads; i++) {
                    Point point = new Point(
                        world.x() + random.nextDouble() * world.width(),
                        world.y() + random.nextDouble() * world.height()
                    );

                    for (int j = -SKIP_ITER_CNT; j < iterPerSample; j++) {
                        Transformation transformation = transformations.get(random.nextInt(transformations.size()));
                        point = transformation.transform(point);

                        if (j >= 0 && world.contains(point)) {
                            int canvasX = (int) ((point.x() - world.x()) / world.width() * width);
                            int canvasY = (int) ((point.y() - world.y()) / world.height() * height);

                            if (canvas.contains(canvasX, canvasY)) {
                                pixels[canvasX][canvasY].updateAndGet(pixel -> pixel.update(transformation.getColor()));
                            }
                        }
                    }
                }
            });
        }

        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(1, TimeUnit.HOURS)) {
                throw new RuntimeException("Rendering did not finish within 1 hour");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                canvas.setPixel(x, y, pixels[x][y].get());
            }
        }

        applyGammaCorrection(canvas, GAMMA);

        return canvas;
    }

    private void applyGammaCorrection(FractalImage canvas, double gamma) {
        double maxLog = 0.0;

        for (int x = 0; x < canvas.getWidth(); x++) {
            for (int y = 0; y < canvas.getHeight(); y++) {
                Pixel pixel = canvas.getPixel(x, y);
                if (pixel.hitCount() > 0) {
                    double logBrightness = log10(pixel.hitCount());
                    maxLog = Math.max(maxLog, logBrightness);
                }
            }
        }

        for (int x = 0; x < canvas.getWidth(); x++) {
            for (int y = 0; y < canvas.getHeight(); y++) {
                Pixel pixel = canvas.getPixel(x, y);
                if (pixel.hitCount() > 0) {
                    double normalizedBrightness = log10(pixel.hitCount()) / maxLog;
                    int correctedRed = (int) (pixel.r() * pow(normalizedBrightness, 1.0 / gamma));
                    int correctedGreen = (int) (pixel.g() * pow(normalizedBrightness, 1.0 / gamma));
                    int correctedBlue = (int) (pixel.b() * pow(normalizedBrightness, 1.0 / gamma));

                    canvas.setPixel(x, y, new Pixel(correctedRed, correctedGreen, correctedBlue, pixel.hitCount()));
                }
            }
        }
    }
}
