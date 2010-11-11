/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pjwiki;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Patrick
 */
public class WikiLinkTest {

    public WikiLinkTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of toString method, of class WikiLink.
     */
    @Test
    public void testToString() {
        WikiWord w1 = new WikiWord("testing1");
        WikiWord w2 = new WikiWord("testing2");
        WikiLink instance = new WikiLink(w1, w2);
        String expResult = w1.toString()+"->"+w2.toString();
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of cachedLinkAdd method, of class WikiLink.
     */
    @Test
    public void testCachedLinkAddAndRemove() {
        WikiLink wl = new WikiLink(new WikiWord("testing1"), new WikiWord("testing2"));
        assertFalse(wl.cacheExist());
        wl.cacheAdd();
        assertTrue(wl.cacheExist());
    }
}