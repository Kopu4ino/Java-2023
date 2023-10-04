package edu.hw1;

@SuppressWarnings("MagicNumber")
public class Task1 {

    private Task1(){
    }

    public static int getSecDuration(String strDuration) {
        String regex = "^[0-9]+:[0-5][0-9]$";
        if (!strDuration.matches(regex)) {
            return -1;
        }

        String[] arrDur = strDuration.split(":");
        int minutes = Integer.parseInt(arrDur[0]);
        int seconds = Integer.parseInt(arrDur[1]);

        return 60 * minutes + seconds;
    }
}
