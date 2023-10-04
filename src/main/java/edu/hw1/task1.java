package edu.hw1;

public class task1 {

    public static int get_sec_duration(String str_duration){
        String regex = "^[0-9]+:[0-5][0-9]$";

        if (!str_duration.matches(regex)){
            return -1;
        }

        String[] arr_dur = str_duration.split(":");
        Integer minutes = Integer.parseInt(arr_dur[0]);
        Integer seconds = Integer.parseInt(arr_dur[1]);

        return 60 * minutes + seconds;
    }
}
