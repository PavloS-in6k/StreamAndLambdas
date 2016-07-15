package com.in6k.StreamAndLambdas;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public static String getMapOfDaysFromStream(Stream<LocalDate> stream) {
        Map<Month, List<LocalDate>> dates = stream
                .collect(Collectors.groupingBy(LocalDate::getMonth));

        dates.values().stream()
                .forEach(value -> value.removeAll(value.subList(2, value.size() - 2)));
        return dates.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(s -> String.format(String.valueOf(s.getKey())) + " " + s.getValue())
                .collect(Collectors.joining("\n"));

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
