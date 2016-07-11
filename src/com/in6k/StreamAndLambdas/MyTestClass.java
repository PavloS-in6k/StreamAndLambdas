package com.in6k.StreamAndLambdas;

import sun.nio.cs.Surrogate;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MyTestClass {
    public static List<LocalDate> getDays(Year year) {
        List <LocalDate> dates = new ArrayList<LocalDate>();
        Stream.iterate()
        dates.stream().filter(value -> value.getYear())
        return dates;
    }
}
