package com.in6k.StreamAndLambdas;

import sun.security.pkcs11.wrapper.Functions;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class MyTestClass {
    public static Stream getUnsortedStreamDays(Year year) {
        Stream stream = Stream.iterate(LocalDate.parse("2016-01-01"), value -> value.plusDays(1))
                .limit(year.length());
        return stream;
    }

    public static Stream getWeekends(Stream<LocalDate> stream) {
        return stream
                .filter(value -> isDateValid(value));
    }

    public static Map<Month, List<LocalDate>> getMapOfDaysFromStream(Stream<LocalDate> stream) {
        //return stream.collect(Collectors.groupingBy(value -> value.getMonth(), value -> value));
        return stream
                .collect(toMap(value -> value.getMonth(), value -> value))
                .entrySet()
                .stream().collect(Collectors.toMap());


    }

    private static boolean isDateValid(LocalDate value) {
        return (value.getMonth().equals(Month.FEBRUARY) && value.getDayOfWeek().equals(DayOfWeek.MONDAY)) ?
                (value.getDayOfMonth() < 8 || (value.getDayOfMonth() > (value.getMonth().maxLength() / 7) * 4))
                        && isWeekend(value.getDayOfWeek())
                :
                (value.getDayOfMonth() < 8 || (value.getDayOfMonth() > (value.getMonth().maxLength() / 7) * 5))
                        && isWeekend(value.getDayOfWeek());
    }

    private static boolean isWeekend(DayOfWeek DOW) {
        return (DOW.equals(DayOfWeek.SUNDAY) || DOW.equals(DayOfWeek.SATURDAY)) ? true : false;
    }
}
