package tasks.floatingDivision;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ViewDivisionTest {
    private final ViewDivision view =
            new ViewDivision(0, 39, "0", null, null, null);

    @Test(expected = IllegalArgumentException.class)
    public void drawLineForHead_ArgumentInsertionLessThanZero_IllegalArgumentExceptionThrown() {
        view.drawLineForHead(-1, "string", 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void drawLineForHead_ArgumentNumLengthLessThanIndexInsertionAndStringLengthTogether_IllegalArgumentExceptionThrown() {
        view.drawLineForHead(5, "string", 10);
    }

    @Test
    public void drawLineForHead_ArgumentIndexInsertion4_CorrectResult() {
        String result = view.drawLineForHead(4, "string", 10);
        assertEquals(result, "    string");
    }

    @Test
    public void drawLineForHead_ArgumentIndexInsertion0_CorrectResult() {
        String result = view.drawLineForHead(0, "string", 10);
        assertEquals(result, "string    ");
    }

    @Test
    public void drawLineForHead_ArgumentIndexInsertion2_CorrectResult() {
        String result = view.drawLineForHead(2, "string", 10);
        assertEquals(result, "  string  ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void drawLineForBody_ArgumentInsertionLessThanZero_IllegalArgumentExceptionThrown() {
        view.drawLineForBody(-1, "string");
    }

    @Test
    public void drawLineForBody_ArgumentIndexInsertion0_CorrectResult() {
        String result = view.drawLineForBody(0, "string");
        assertEquals(result, "string");
    }

    @Test
    public void drawLineForBody_ArgumentIndexInsertion2_CorrectResult() {
        String result = view.drawLineForBody(2, "string");
        assertEquals(result, "  string");
    }
}
