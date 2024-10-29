package esiea.utils;

import static org.junit.Assert.*;
import org.junit.Test;
import utils.StringUtils;

public class StringUtilsTest {
    
    @Test
    public void testEstEntier() {
        assertTrue(StringUtils.estEntier("123"));
        assertFalse(StringUtils.estEntier("abc"));
        assertFalse(StringUtils.estEntier("12.3"));
        assertFalse(StringUtils.estEntier(null));
        assertFalse(StringUtils.estEntier(""));
    }

    @Test
    public void testNbOccurrence() {
        assertEquals(3, StringUtils.nbOccurrence("hello world!", 'l'));
        assertEquals(0, StringUtils.nbOccurrence("hello world!", 'z'));
        assertEquals(1, StringUtils.nbOccurrence("a", 'a'));
        assertEquals(0, StringUtils.nbOccurrence("", 'a'));
    }
}
