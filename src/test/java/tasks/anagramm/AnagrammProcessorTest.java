package tasks.anagramm;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static tasks.anagramm.AnagrammProcessor.makeAnagramm;

public class AnagrammProcessorTest {

    @Test (expected = IllegalArgumentException.class)
    public void makeAnagramm_ArgumentNull_IllegalArgumentExceptionThrown() {
        makeAnagramm(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void makeAnagramm_ArgumentStringIsEmpty_IllegalArgumentExceptionThrown() {
        makeAnagramm(new String());
    }

    @Test
    public void makeAnagramm_ArgumentOnlyLetters_CorrectAnagramm() {
        assertThat(makeAnagramm("abcd efgh"), is("dcba hgfe"));
    }

    @Test
    public void makeAnagramm_ArgumentOnlyDigits_CorrectAnagramm() {
        assertThat(makeAnagramm("142 367 432"), is("142 367 432"));
    }

    @Test
    public void makeAnagramm_ArgumentOnlySpecialSymbols_CorrectAnagramm() {
        assertThat(makeAnagramm("@*(:,/##-_?!.><="), is("@*(:,/##-_?!.><="));
    }

    @Test
    public void makeAnagramm_ArgumentLettersAndDigits_CorrectAnagramm() {
        assertThat(makeAnagramm("ab1cd e23fgh4"), is("dc1ba h23gfe4"));
    }

    @Test
    public void makeAnagramm_ArgumentLettersAndSpecialSymbols_CorrectAnagramm() {
        assertThat(makeAnagramm("asd!f zx##c"), is("fds!a cx##z"));
    }

    @Test
    public void makeAnagramm_ArgumentDigitsAndSpecialSymbols_CorrectAnagramm() {
        assertThat(makeAnagramm("09/!2 3#4#! 6 *"), is("09/!2 3#4#! 6 *"));
    }

    @Test
    public void makeAnagramm_ArgumentDigitsAndSpecialSymbolsAndLetters_CorrectAnagramm() {
        assertThat(makeAnagramm("0!f2zbhy 3#4asdf#! a6 b*"), is("0!y2hbzf 3#4fdsa#! a6 b*"));
    }
}
