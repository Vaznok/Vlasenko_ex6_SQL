package tasks.anagramm;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static tasks.anagramm.AnagrammProcessor.makeAnagramm;

public class AnagrammProcessorTest {
	@Test (expected = IllegalArgumentException.class)
	public void test_makeAnagramm_argument_null() {
		makeAnagramm(null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_makeAnagramm_argument_string_is_empty() {
		makeAnagramm(new String());
	}
	
	@Test
	public void test_makeAnagram_arguments_correct_result() {
		assertThat(makeAnagramm("asd!f zx##c"), is("fds!a cx##z"));
		assertThat(makeAnagramm("fds!a cx##z"), is("asd!f zx##c"));
		assertThat(makeAnagramm("abcd efgh"), is("dcba hgfe"));
		assertThat(makeAnagramm("a1bcd, efg!h"), is("d1cba, hgf!e"));
		assertThat(makeAnagramm("12!@&*%$:;"), is("12!@&*%$:;"));
	}

	@Test
	public void test_makeAnagram_arguments_incorrect_result() {
		assertThat(makeAnagramm("asd!f zx##c"), not("cxz!f ds##a"));
		assertThat(makeAnagramm("asd!f zx##cz"), not("cxz!fds##a"));
		assertThat(makeAnagramm("1abcd efgh2"), not("2dcba hgfe1"));
		assertThat(makeAnagramm("12!@&*%$:;"), not(";:$%*&@!21"));
	}
}
