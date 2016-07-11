package com.in6k.StreamAndLambdas;


import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Year;

import static com.in6k.StreamAndLambdas.MyTestClass.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class GeneralTests {

    @Test
    public void isNumberOfDaysIsCorrect() throws Exception {
        Year year = Year.parse("2016");
        assertEquals(getDays(Year.parse("2016")).size(),year.length()-1);
    }




}
