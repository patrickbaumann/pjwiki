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
public class WikiWordTest {

    public WikiWordTest() {
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
     * Test of isValid method, of class WikiWord.
     */
    @Test
    public void testBasicIsValid() {
        // valid paths
        System.out.println("singleWord");
        assertEquals(true, (new WikiWord("test")).isValid());
        System.out.println("multi word");
        assertEquals(true, (new WikiWord("testing Test")).isValid());
        System.out.println("multi-word");
        assertEquals(true, (new WikiWord("testing-Test2")).isValid());
        System.out.println("multi_word");
        assertEquals(true, (new WikiWord("te231sting_123T123est")).isValid());
        System.out.println("implicitPath");
        assertEquals(true, (new WikiWord("test:test")).isValid());
        System.out.println("explicitPath");
        assertEquals(true, (new WikiWord(":test:test")).isValid());
        System.out.println("pathWithIndexSignifier");
        assertEquals(true, (new WikiWord("test:test:")).isValid());

        //invalid paths
        System.out.println("space on left");
        assertEquals(false, (new WikiWord(" test")).isValid());
        System.out.println("space on right");
        assertEquals(false, (new WikiWord("test ")).isValid());
        System.out.println("invalid char");
        assertEquals(false, (new WikiWord("te(st")).isValid());
        System.out.println("invalid char");
        assertEquals(false, (new WikiWord("te=st")).isValid());
        System.out.println("start with number");
        assertEquals(false, (new WikiWord("123test")).isValid());
        System.out.println("space between words");
        assertEquals(false, (new WikiWord(":123test :test")).isValid());
    }

    /**
     * Test of toString method, of class WikiWord.
     */
    @Test
    public void testBasicToString() {
        System.out.println("basic word");
        assertEquals(":singleWord", (new WikiWord("singleWord")).toString());
        System.out.println("rootpath");
        assertEquals(":singleWord", (new WikiWord(":singleWord")).toString());
        System.out.println("implicit multi word");
        assertEquals(":foo:bar:test:stuff", (new WikiWord("foo:bar:test:stuff")).toString());
        System.out.println("explicit multi word");
        assertEquals(":foo:bar", (new WikiWord(":foo:bar")).toString());
        System.out.println("index included");
        assertEquals(":foo:bar:index", (new WikiWord(":foo:bar:")).toString());
    }

    @Test
    public void testRelativeConstructor() {
        WikiWord anchor = new WikiWord(":test:path");
        WikiWord relative;

        System.out.println("relative word");
        relative = new WikiWord("path2", anchor);
        assertEquals(true, relative.isValid());
        assertEquals(":test:path2", relative.toString());

        System.out.println("child word");
        relative = new WikiWord("path:testing", anchor);
        assertEquals(true, relative.isValid());
        assertEquals(":test:path:testing", relative.toString());

        System.out.println("relative word");
        relative = new WikiWord(".:path2", anchor);
        assertEquals(true, relative.isValid());
        assertEquals(":test:path2", relative.toString());

        System.out.println("relative word");
        relative = new WikiWord("..:path2", anchor);
        assertEquals(true, relative.isValid());
        assertEquals(":path2", relative.toString());

        System.out.println("relative word");
        relative = new WikiWord("..:path2:", anchor);
        assertEquals(true, relative.isValid());
        assertEquals(":path2:"+WikiWord.INDEX_TEXT, relative.toString());

        System.out.println("explicit root");
        relative = new WikiWord("path2:", anchor);
        assertEquals(true, relative.isValid());
        assertEquals(":test:path2:"+WikiWord.INDEX_TEXT, relative.toString());


    }
}