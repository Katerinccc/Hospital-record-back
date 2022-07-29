package com.sofka.record.utility;

public class Util {
    public static boolean validateLength(String text, int min, int max){
        return text.length() >= min && text.length() <= max;
    }
}
