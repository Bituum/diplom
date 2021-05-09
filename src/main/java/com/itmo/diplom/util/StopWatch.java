package com.itmo.diplom.util;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class StopWatch {
    public LocalTime getElapsedTime(long startedTime) {
            LocalTime localTime = LocalTime.now();
            long seconds = Duration.between(localTime.withSecond(0).withMinute(0).withHour(0), localTime).getSeconds();
            return LocalTime.ofSecondOfDay(seconds - startedTime);
        }
}

