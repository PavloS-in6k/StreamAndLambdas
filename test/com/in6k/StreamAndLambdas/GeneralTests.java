package com.in6k.StreamAndLambdas;


import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.*;
import java.util.regex.Matcher;
import java.util.stream.Stream;

import static com.in6k.StreamAndLambdas.MyTestClass.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class GeneralTests {

    Year year;

    @Before
    public void setup() {
        year = Year.parse("2016");
    }

    @Test
    public void isNumberOfDaysIsCorrect() throws Exception {
        assertEquals(year.length(), getUnsortedStreamDays(year).count());
    }

    @Test
    public void isFistDayInStreamIsFirstDayOfYear() throws Exception {
        assertEquals(LocalDate.parse("2016-01-01"), getUnsortedStreamDays(year).toArray()[0]);
    }

    @Test
    public void isWrongDatesFiltered() throws Exception {
        Stream stream = getWeekends(Stream.of(
                LocalDate.parse("2016-01-01"),
                LocalDate.parse("2016-01-04"),
                LocalDate.parse("2016-01-05"),
                LocalDate.parse("2016-01-06"),
                LocalDate.parse("2016-01-07"),
                LocalDate.parse("2016-01-08")
        ));
        assertThat(stream.toArray().length, is(0));
    }

    @Test
    public void isFirstSortedDatesDayIsWeekend() throws Exception {
        Stream stream = getWeekends(Stream.of(
                LocalDate.parse("2016-01-01"),
                LocalDate.parse("2016-01-02"),
                LocalDate.parse("2016-01-03"),
                LocalDate.parse("2016-01-04")
        ));
        ArrayList<Object> dates = new ArrayList<>();
        dates.add(LocalDate.parse("2016-01-02"));
        dates.add(LocalDate.parse("2016-01-03"));
        assertThat(stream.toArray(), equalTo(dates.toArray()));
    }

    @Test
    public void isLastDatesIsWeekends() throws Exception {
        Stream stream = getWeekends(Stream.of(
                LocalDate.parse("2016-01-29"),
                LocalDate.parse("2016-01-30"),
                LocalDate.parse("2016-01-31")
        ));
        ArrayList<Object> dates = new ArrayList<>();
        dates.add(LocalDate.parse("2016-01-30"));
        dates.add(LocalDate.parse("2016-01-31"));
        assertThat(stream.toArray(), equalTo(dates.toArray()));
    }

    @Test
    public void isWrongDatesIgnored() throws Exception {
        Stream stream = getWeekends(Stream.of(
                LocalDate.parse("2016-01-15"),
                LocalDate.parse("2016-01-16"),
                LocalDate.parse("2016-01-17"),
                LocalDate.parse("2016-01-18")
        ));
        ArrayList<Object> dates = new ArrayList<>();
        assertThat(stream.toArray(), equalTo(dates.toArray()));
    }

    @Test
    public void isMapReturnedRight() throws Exception {
        Stream stream = getWeekends(Stream.of(
                LocalDate.parse("2016-01-29"),
                LocalDate.parse("2016-01-30"),
                LocalDate.parse("2016-01-31")
        ));
        Map<Month, LocalDate> dates = new HashMap<>();

        dates.put(Month.JANUARY, LocalDate.parse("2016-01-30"));
        dates.put(Month.JANUARY, LocalDate.parse("2016-01-31"));



        assertThat(getMapOfDaysFromStream(stream), equalTo(dates));
    }


}
