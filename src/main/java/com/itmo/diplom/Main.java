
package com.itmo.diplom;

import com.itmo.diplom.util.RequestParamTimeValidation;
import com.itmo.diplom.util.StopWatch;

import java.time.LocalTime;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        String time1 = "10:29";
        String time2 = "13:29";
        String time3 = "00:29";
        String time4 = "04:63";
        RequestParamTimeValidation validation = new RequestParamTimeValidation();
        System.out.println(validation.validate(time1));
        System.out.println(validation.validate(time2));
        System.out.println(validation.validate(time3));
        System.out.println(validation.validate(time4));
    }
}

