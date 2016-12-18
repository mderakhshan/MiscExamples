package codility;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by Mir on 08/11/2016.
 */
public class PrimeTest {

    @Test // (expected = Exception.class)
    public void whenInvalidArgument_ShouldReturnEmptyArray () {
        int[] expected = new int[0];
        int[] result;
        result = Prime.primeNumbers(5, 4);
        assertThat(result, is(expected));
        assertThat (result, is(notNullValue()));
        assertThat (result, allOf(is(notNullValue()), is(expected)));

        String S = "hello";
        String S1 = new String("hello");
        assertThat (S, containsString("ell"));
        assertThat (S, allOf (startsWith("h"), endsWith("o")));
        assertThat (S, is (S1));
        assertThat (S, is(equalTo(S1)));
        assertThat (S, equalTo(S1));
    }
}