package tasks.floatingDivision;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static tasks.floatingDivision.FloatingDivisionManager.*;
import static tasks.floatingDivision.FloatingDivisionManager.makeDivision;

public class FloatingDivisionManagerTest {

    @Test(expected = IllegalArgumentException.class)
    public void makeDivision_ArgumentDivisorZero_IllegalArgumentExceptionThrown() {
        makeDivision(542, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void makeDivision_ArgumentNumberLessThanZero_IllegalArgumentExceptionThrown() {
        makeDivision(-2, 23);
    }

    @Test(expected = IllegalArgumentException.class)
    public void makeDivision_ArgumentDivisorLessThanZero_IllegalArgumentExceptionThrown() {
        makeDivision(542, -56);
    }

    @Test(expected = IllegalArgumentException.class)
    public void makeDivision_ArgumentsNumberAndDivisorLessThanZero_IllegalArgumentExceptionThrown() {
        makeDivision(-2, -23);
    }
    @Test(expected = IllegalArgumentException.class)
    public void makeDivision_ArgumentsNumberAndDivisorEqualsZero_IllegalArgumentExceptionThrown() {
        makeDivision(0, 0);
    }

    @Test
    public void makeDivision_ArgumentNumberZero_CorrectResult() {
        makeDivision(0, 23);
        assertThat(getPartialNums(), is(Arrays.asList()));
        assertThat(getNearestDivisorNums(), is(Arrays.asList()));
        assertThat(getNumRemains(), is(Arrays.asList()));
        assertThat(getResult(), is("0"));
    }

    @Test
    public void makeDivision_ArgumentsCorrectDivisorOneDigit_CorrectResult() {
        makeDivision(78459, 4);
        assertThat(getPartialNums(), is(Arrays.asList(7, 38, 24, 5, 19, 30, 20)));
        assertThat(getNearestDivisorNums(), is(Arrays.asList(4, 36, 24, 4, 16, 28, 20)));
        assertThat(getNumRemains(), is(Arrays.asList(3, 2, 0, 1, 3, 2, 0)));
        assertThat(getResult(), is("19614.75"));
    }

    @Test
    public void makeDivision_ArgumentsCorrectDivisorOneDigitPeriod10_CorrectResult() {
        makeDivision(78459, 4, 10);
        assertThat(getPartialNums(), is(Arrays.asList(7, 38, 24, 5, 19, 30, 20)));
        assertThat(getNearestDivisorNums(), is(Arrays.asList(4, 36, 24, 4, 16, 28, 20)));
        assertThat(getNumRemains(), is(Arrays.asList(3, 2, 0, 1, 3, 2, 0)));
        assertThat(getResult(), is("19614.75"));
    }
    @Test
    public void makeDivision_ArgumentNumberHigherThanDivisor_CorrectResult() {
        makeDivision(1000, 3);
        assertThat(getPartialNums(), is(Arrays.asList(10, 10, 10, 10)));
        assertThat(getNearestDivisorNums(), is(Arrays.asList(9, 9, 9, 9)));
        assertThat(getNumRemains(), is(Arrays.asList(1, 1, 1, 1)));
        assertThat(getResult(), is("333.(3)"));
    }

    @Test
    public void makeDivision_ArgumentNumberHigherThanDivisorPeriod10_CorrectResult() {
        makeDivision(1000, 3, 10);
        assertThat(getPartialNums(), is(Arrays.asList(10, 10, 10, 10)));
        assertThat(getNearestDivisorNums(), is(Arrays.asList(9, 9, 9, 9)));
        assertThat(getNumRemains(), is(Arrays.asList(1, 1, 1, 1)));
        assertThat(getResult(), is("333.(3)"));
    }

    @Test
    public void makeDivision_ArgumentsCorrectDivisorThreeDigits_CorrectResult() {
        makeDivision(7, 12);
        assertThat(getPartialNums(), is(Arrays.asList(70, 100, 40)));
        assertThat(getNearestDivisorNums(), is(Arrays.asList(60, 96, 36)));
        assertThat(getNumRemains(), is(Arrays.asList(10, 4, 4)));
        assertThat(getResult(), is("0.58(3)"));
    }

    @Test
    public void makeDivision_ArgumentsCorrectDivisorThreeDigitsPeriod10_CorrectResult() {
        makeDivision(7, 12, 10);
        assertThat(getPartialNums(), is(Arrays.asList(70, 100, 40)));
        assertThat(getNearestDivisorNums(), is(Arrays.asList(60, 96, 36)));
        assertThat(getNumRemains(), is(Arrays.asList(10, 4, 4)));
        assertThat(getResult(), is("0.58(3)"));
    }

    @Test
    public void makeDivision_ArgumentsCorrectDivisorHasSameDigitsAsNumber_CorrectResult() {
        makeDivision(25, 39);
        assertThat(getPartialNums(), is(Arrays.asList(250, 160, 40, 100, 220)));
        assertThat(getNearestDivisorNums(), is(Arrays.asList(234, 156, 39, 78, 195)));
        assertThat(getNumRemains(), is(Arrays.asList(16, 4, 1, 22, 25)));
        assertThat(getResult(), is("0.64125"));
    }

    @Test
    public void makeDivision_ArgumentsCorrectDivisorHasSameDigitsAsNumberPeriod10_CorrectResult() {
        makeDivision(25, 39, 10);
        assertThat(getPartialNums(), is(Arrays.asList(250, 160, 40, 100, 220, 250, 160, 40)));
        assertThat(getNearestDivisorNums(), is(Arrays.asList(234, 156, 39, 78, 195, 234, 156, 39)));
        assertThat(getNumRemains(), is(Arrays.asList(16, 4, 1, 22, 25, 16, 4, 1)));
        assertThat(getResult(), is("0.64125641"));
    }
}
