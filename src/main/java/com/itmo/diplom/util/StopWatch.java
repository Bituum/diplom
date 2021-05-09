package com.itmo.diplom.util;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class StopWatch {

    private long startTime = 0;
    private long stopTime = 0;
    private boolean running = false;

 /*   String convertLongToTime(long time){
        *//*Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        return format.format(date);*//*
        *//*Duration duration = Duration.ofSeconds(time);

        return duration.toString();*//*
    }*/

    public void start() {
        this.startTime = System.currentTimeMillis();
        this.running = true;
    }


    public void stop() {
        this.stopTime = System.currentTimeMillis();
        this.running = false;
    }


    //elaspsed time in milliseconds
    public LocalTime getElapsedTime(long startedTime) {
        LocalTime elapsed;
        if (running) {
            return LocalTime.ofSecondOfDay((System.currentTimeMillis() - startedTime) / 1000);
            //return LocalTime.parse(convertLongToTime(elapsed = (TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) - TimeUnit.MILLISECONDS.toSeconds(startTime))));
        } else {
            long a = System.currentTimeMillis();
            System.out.println(a);
            long b = startedTime;
            System.out.println(b * 1000L);
            long c = a - b;
            System.out.println(c);
            System.out.println(c / 1000);
            System.out.println((c / 1000) % 60);
            System.out.println((c / 1000) / 3600);

            LocalTime localTime = LocalTime.now();
            long seconds = Duration.between(localTime.withSecond(0).withMinute(0).withHour(0), localTime).getSeconds();

            //System.out.println((System.currentTimeMillis() - (startedTime))/ 1000);
            return LocalTime.ofSecondOfDay(seconds - startedTime);
        }
    }


/*    //elaspsed time in seconds
    public long getElapsedTimeSecs() {
        long elapsed;
        if (running) {
            elapsed = ((System.currentTimeMillis() - startTime) / 1000 ;
        } else {
            elapsed = ((stopTime - startTime) / 1000 / 60);
        }
        return elapsed;
    }*/
}