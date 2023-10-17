package edu.hw1;

public class Task7 {

    private Task7() {
    }

    public static int rotateRight(Integer num, Integer shift) {
        if (shift < 0) {
            return rotateLeft(num, Math.abs(shift));
        }

        Integer bitsCnt = getBitsCnt(num);
        Byte maskOfBitsCnt = (byte) ((1 << bitsCnt) - 1); // Единичная масска длины bitsCnt

        Integer correctShift = shift % bitsCnt;

        Integer rightPart = num >> correctShift;
        Integer leftPart = num << bitsCnt - correctShift;

        return rightPart | leftPart & maskOfBitsCnt;
    }

    public static int rotateLeft(Integer num, Integer shift) {
        if (shift < 0) {
            return rotateRight(num, Math.abs(shift));
        }

        Integer bitsCnt = getBitsCnt(num);
        Byte maskOfBitsCnt = (byte) ((1 << bitsCnt) - 1); // Единичная маска длины bitsCnt

        Integer correctShift = shift % bitsCnt;

        Integer leftPart = num << correctShift;
        Integer rightPart = num >> bitsCnt - correctShift;

        return leftPart & maskOfBitsCnt | rightPart;
    }

    private static int getBitsCnt(Integer num) {
        return Integer.toBinaryString(num).length();
    }
}
