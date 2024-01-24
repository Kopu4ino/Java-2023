package edu.project4.Linear;

import java.util.Random;

@SuppressWarnings({"MagicNumber", "MultipleVariableDeclarations"})
public record LinearTransformationCoefficients(double a, double b, double c, double d, double e, double f) {
    public static LinearTransformationCoefficients generateCoefficients() {
        Random random = new Random();
        double a, b, c, d, e, f;
        do {
            a = random.nextDouble() * 2.0 - 1.0;
            b = random.nextDouble() * 2.0 - 1.0;
            c = random.nextDouble() * 2.0 - 1.0;
            d = random.nextDouble() * 2.0 - 1.0;
            e = random.nextDouble() * 5.0 - 3.0;
            f = random.nextDouble() * 3.0 - 2.0;
        } while (!isValid(a, b, c, d, e, f));
        return new LinearTransformationCoefficients(a, b, c, d, e, f);
    }

    private static boolean isValid(double a, double b, double c, double d, double e, double f) {
        double det = a * d - b * c;
        return (a * a + b * b < 1.0)
            && (c * c + d * d < 1.0)
            && (a * a + b * b + c * c + d * d - det * det < 1.0);
    }
}
