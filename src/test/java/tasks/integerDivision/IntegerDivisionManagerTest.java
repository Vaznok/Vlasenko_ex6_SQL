package tasks.integerDivision;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static tasks.integerDivision.IntegerDivisionManager.*;

public class IntegerDivisionManagerTest {
    @Test
    public void makeDivision_ArgumentNumberHigherThanDivisor_IllegalArgumentExceptionThrown() {
        makeDivision(2398, 2399);
    }
    
    @Test
    public void makeDivision_ArgumentNumberZero_CorrectResult() {
        makeDivision(0, 23);
        assertThat(getPartialNums(), is(Arrays.asList()));
        assertThat(getNearestDivisorNums(), is(Arrays.asList()));
        assertThat(getNumRemains(), is(Arrays.asList()));
    }

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
    public void makeDivision_ArgumentsCorrectDivisorOneDigit_CorrectResult() {
        makeDivision(78459, 4);
        assertThat(getPartialNums(), is(Arrays.asList(7, 38, 24, 5, 19)));
        assertThat(getNearestDivisorNums(), is(Arrays.asList(4, 36, 24, 4, 16)));
        assertThat(getNumRemains(), is(Arrays.asList(3, 2, 0, 1, 3)));
    }

    @Test
    public void makeDivision_ArgumentsCorrectDivisorThreeDigits_CorrectResult() {
        makeDivision(4567843, 456);
        assertThat(getPartialNums(), is(Arrays.asList(456, 784, 3283, 91)));
        assertThat(getNearestDivisorNums(), is(Arrays.asList(456, 456, 3192)));
        assertThat(getNumRemains(), is(Arrays.asList(0, 328, 91)));
    }

    @Test
    public void makeDivision_ArgumentsCorrectDivisorHasSameDigitsAsNumber_CorrectResult() {
        makeDivision(4567, 4354);
        assertThat(getPartialNums(), is(Arrays.asList(4567)));
        assertThat(getNearestDivisorNums(), is(Arrays.asList(4354)));
        assertThat(getNumRemains(), is(Arrays.asList(213)));
    }
}
