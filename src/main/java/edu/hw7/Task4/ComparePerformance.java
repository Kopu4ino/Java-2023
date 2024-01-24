package edu.hw7.Task4;

@SuppressWarnings({
    "UncommentedMain",
    "MagicNumber",
    "MultipleVariableDeclarations",
    "RegexpSinglelineJava",
    "MultipleStringLiterals"})
public class ComparePerformance {
    private ComparePerformance() {
    }

    public static void main(String[] args) throws InterruptedException {
        int[] totalPointsOptions = {10_000_000, 100_000_000, 1_000_000_000};
        for (int totalPoints : totalPointsOptions) {
            long startTime, endTime;

            // Однопоточная версия
            startTime = System.nanoTime();
            double piSingle = CalcPiSingleThread.calculatePi(totalPoints);
            endTime = System.nanoTime();
            System.out.println("Однопоточная версия (" + totalPoints + " точек): "
                + (endTime - startTime) / 1e9 + " секунд. Pi = " + piSingle);

            // Многопоточная версия
            startTime = System.nanoTime();
            double piMulti = CalcPiMultiThread.calculatePiMultiThread(totalPoints);
            endTime = System.nanoTime();
            System.out.println("Многопоточная версия (" + totalPoints + " точек): "
                + (endTime - startTime) / 1e9 + " секунд. Pi = " + piMulti + "\n");
        }
    }
}
