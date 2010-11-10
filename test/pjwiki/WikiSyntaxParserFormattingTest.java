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
public class WikiSyntaxParserFormattingTest {

    public WikiSyntaxParserFormattingTest() {
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
     * Test of execute method, of class WikiSyntaxParserFormatting.
     */
    @Test
    public void testBold() {
        String text = "This **is** a test.";
        WikiSyntaxParserFormatting instance = new WikiSyntaxParserFormatting();
        String expResult = 
                "This <b>is</b> a test.";
        String result = instance.execute(text);
        assertEquals(expResult, result);
    }

    @Test
    public void testItalics() {
        String text = "This //is// a test.";
        WikiSyntaxParserFormatting instance = new WikiSyntaxParserFormatting();
        String expResult =
                "This <i>is</i> a test.";
        String result = instance.execute(text);
        assertEquals(expResult, result);
    }

    @Test
    public void testUnderline() {
        String text = "This __is__ a test.";
        WikiSyntaxParserFormatting instance = new WikiSyntaxParserFormatting();
        String expResult =
                "This <u>is</u> a test.";
        String result = instance.execute(text);
        assertEquals(expResult, result);
    }

    @Test
    public void testStrike() {
        String text = "This --is-- a test.";
        WikiSyntaxParserFormatting instance = new WikiSyntaxParserFormatting();
        String expResult =
                "This <strike>is</strike> a test.";
        String result = instance.execute(text);
        assertEquals(expResult, result);
    }

    @Test
    public void testManualLineBreak() {
        String text =
                "I want a new line\\\\ and stuff\r\n" +
                "This should end up as three lines\r\n";
        WikiSyntaxParserFormatting instance = new WikiSyntaxParserFormatting();
        String expResult =
                "I want a new line<br />and stuff\r\n" +
                "This should end up as three lines\r\n";
        String result = instance.execute(text);
        assertEquals(expResult, result);
    }

    @Test
    public void testMonospaced() {
        String text =
                "I want ''a new line and'' ''stuff\r\n" +
                "This should end up'' as three lines\r\n";
        WikiSyntaxParserFormatting instance = new WikiSyntaxParserFormatting();
        String expResult =
                "I want <pre>a new line and</pre> <pre>stuff\r\n" +
                "This should end up</pre> as three lines\r\n";
        String result = instance.execute(text);
        assertEquals(expResult, result);
    }

    @Test
    public void testAll() {
        String text = "This --is-- a **test** and //stuff// and __things__.";
        WikiSyntaxParserFormatting instance = new WikiSyntaxParserFormatting();
        String expResult =
                "This <strike>is</strike> a <b>test</b> and <i>stuff</i> and <u>things</u>.";
        String result = instance.execute(text);
        assertEquals(expResult, result);
    }

    @Test
    public void testMix() {
        String text = "This **is a //test of** mixing//.";
        WikiSyntaxParserFormatting instance = new WikiSyntaxParserFormatting();
        String expResult =
                "This <b>is a <i>test of</b> mixing</i>.";
        String result = instance.execute(text);
        assertEquals(expResult, result);
    }


}