package edu.project4;

import edu.project4.ColorUtils.FractalColor;
import edu.project4.ColorUtils.ImageUtils;
import edu.project4.Figure.Rect;
import edu.project4.Image.FractalImage;
import edu.project4.Linear.LinearTransformationCoefficients;
import edu.project4.Renders.ConcurrentFractalRenderer;
import edu.project4.Renders.FractalRenderer;
import edu.project4.Transformations.DiscTransformation;
import edu.project4.Transformations.ExponentialTransformation;
import edu.project4.Transformations.HeartTransformation;
import edu.project4.Transformations.LinearTransformation;
import edu.project4.Transformations.PolarTransformation;
import edu.project4.Transformations.SphericalTransformation;
import edu.project4.Transformations.SpiralTransformation;
import edu.project4.Transformations.Transformation;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class BenchmarkTest {
    @Test
    void benchmark() {
        // Arrange
        int width = 1920;
        int height = 1080;
        Rect world = new Rect(-1.25, -1.25, 2, 2);
        int numberOfThreads = Runtime.getRuntime().availableProcessors();
        int samples = 1000000;
        int iterPerSample = 20;
        long seed = System.currentTimeMillis();

        List<Transformation> transformations = new ArrayList<>();

        transformations.add(new LinearTransformation(
            LinearTransformationCoefficients.generateCoefficients(),
            FractalColor.WHITE
        ));
        transformations.add(new SphericalTransformation(FractalColor.AZURE));
        transformations.add(new PolarTransformation(FractalColor.WHITE));
        transformations.add(new ExponentialTransformation(FractalColor.RED));
        transformations.add(new SpiralTransformation(FractalColor.AZURE));
        transformations.add(new DiscTransformation(FractalColor.SKY_BLUE));
        transformations.add(new HeartTransformation(FractalColor.SKY_BLUE));
        transformations.add(new DiscTransformation(FractalColor.SKY_BLUE));

        // Act
        // Рендеринг однопоточным рендерером
        FractalRenderer singleThreadRenderer = new FractalRenderer();
        long startSingle = System.nanoTime();
        FractalImage imageSingle =
            singleThreadRenderer.render(width, height, world, transformations, samples, iterPerSample, seed);
        long endSingle = System.nanoTime();

        // Рендеринг многопоточным рендерером
        ConcurrentFractalRenderer multiThreadRenderer = new ConcurrentFractalRenderer();
        long startMulti = System.nanoTime();
        FractalImage imageMulti = multiThreadRenderer.render(
            width,
            height,
            world,
            transformations,
            samples,
            iterPerSample,
            numberOfThreads
        );

        long endMulti = System.nanoTime();

        // Сохранение результатов
        String singlePath = "fractalSingle.png";
        String multiPath = "fractalMulti.png";

        try {
            ImageUtils.saveFractalImage(imageSingle, singlePath);
            ImageUtils.saveFractalImage(imageMulti, multiPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Assert
        // В данном случае assert не используется, так как тест направлен на измерение производительности.
        // Вместо этого выводятся результаты времени выполнения.
        System.out.println("Single-threaded rendering took: " + (endSingle - startSingle) / 1e9 + " sec.");
        System.out.println("Multi-threaded rendering took: " + (endMulti - startMulti) / 1e9 + " sec.");
    }

}
