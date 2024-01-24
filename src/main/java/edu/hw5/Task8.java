package edu.hw5;

public class Task8 {
    private Task8() {
    }

    public final static String ODD_LENGTH = "^(00|11|01|10)*(0|1)$";
    public final static String EVEN_CNT_ZEROS = "^1*(01*01*01*)*$";
    public final static String EVERY_ODD_CHAR_IS_ONE = "^(1[01])*1?$";
    public final static String ZERO_OR_ONE = "0(00|01|10|11)*|1(0|1)(00|01|10|11)*";
}
