package com.itmo.diplom.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestParamTimeValidation {
    private final Pattern pattern;
    private Matcher matcher;

    private static final String TIME12HOURS_PATTERN = "(0[0123456789]|1[012]):[0-5][0-9]";

    public RequestParamTimeValidation(){
        pattern = Pattern.compile(TIME12HOURS_PATTERN);
    }

    public boolean validate(final String time){
        matcher = pattern.matcher(time);
        return matcher.matches();
    }
}
