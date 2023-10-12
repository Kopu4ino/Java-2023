package edu.hw1;

public class Task8 {
    private Task8() {
    }

    public static boolean knightBoardCapture(Integer[][] table) {
        boolean result = true;

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                if (table[i][j].equals(1)) {
                    if (checkCanBeats(table, i, j)) {
                        return false;
                    }
                }
            }
        }
        return result;
    }

    private static boolean checkCanBeats(Integer[][] table, int i, int j) {
        final int LINE_NEG2_SHIFT = -2;
        final int LINE_NEG1_SHIFT = -1;
        final int LINE_2_SHIFT = 2;
        final int LINE_1_SHIFT = 1;

        final int COL_NEG2_SHIFT = -2;
        final int COL_NEG1_SHIFT = -1;
        final int COL_2_SHIFT = 2;
        final int COL_1_SHIFT = 1;

        int[][] shifts = new int[][] {
            {LINE_NEG2_SHIFT, COL_NEG1_SHIFT}, {LINE_NEG2_SHIFT, COL_1_SHIFT},
            {LINE_2_SHIFT, COL_NEG1_SHIFT}, {LINE_2_SHIFT, COL_1_SHIFT},
            {LINE_1_SHIFT, COL_2_SHIFT}, {LINE_1_SHIFT, COL_NEG2_SHIFT},
            {LINE_NEG1_SHIFT, COL_2_SHIFT}, {LINE_NEG1_SHIFT, COL_NEG2_SHIFT}};

        Integer n = table.length;
        Integer m = table[0].length;

        for (int[] shift : shifts) {
            Integer newLineIndex = i + shift[0];
            Integer newColIndex = j + shift[1];

            if (validatePosition(newLineIndex, newColIndex, n, m)) {
                if (table[newLineIndex][newColIndex].equals(1)) {
                    return true;
                }
            }

        }
        return false;
    }

    private static boolean validatePosition(Integer lineIndex, Integer colIndex, Integer lineCnt, Integer colCnt) {
        if (lineIndex < 0 || colIndex < 0) {
            return false;
        }

        if (lineIndex >= lineCnt || colIndex >= colCnt) {
            return false;
        }
        return true;
    }

}
