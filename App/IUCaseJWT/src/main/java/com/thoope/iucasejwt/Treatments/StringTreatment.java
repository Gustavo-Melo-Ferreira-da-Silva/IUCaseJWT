package com.thoope.iucasejwt.Treatments;

import java.util.regex.Pattern;

public class StringTreatment {

    public static boolean HasNumber(String s){
        Pattern pattern = Pattern.compile(".*\\d+.*");
        return pattern.matcher(s).matches();
    }

    public static boolean isNumber(String strNum){
        try{
            Integer.parseInt(strNum);
            return true;
        } catch (NumberFormatException ex){
            return false;
        }
    }
}
