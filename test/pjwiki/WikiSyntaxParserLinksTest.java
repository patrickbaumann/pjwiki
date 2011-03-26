/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pjwiki;

import pjwiki.syntax.WikiSyntaxParserLinks;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aq615c
 */
public class WikiSyntaxParserLinksTest {

    public WikiSyntaxParserLinksTest() {
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
     * Test of execute method, of class WikiSyntaxParserLinks.
     */
    @Test
    public void testExternalLinks() {
        String text = 
                "test [[http://www.google.com]] stuff" +
                "[[http://www.yahoo.com|Yahoo!]] things";
        WikiSyntaxParserLinks instance = new WikiSyntaxParserLinks();
        String expResult =
                "test <a class='external' href='http://www.google.com' target='_blank'>http://www.google.com</a> stuff" +
                "<a class='external' href='http://www.yahoo.com' target='_blank'>Yahoo!</a> things";
        String result = instance.execute(text);
        assertEquals(expResult, result);
    }

    /**
     * Test windows links.
     */
    @Test
    public void testWindowsLinks() {
        String text =
                "test [[C:\\Temp\\test.txt|Text File]] stuff" +
                "[[\\\\networkshare\\location of thing\\and\\stuff]] things";
        WikiSyntaxParserLinks instance = new WikiSyntaxParserLinks();
        String expResult =
                "test <a class='file' href='file:///C:/Temp/test.txt' target='_blank'>Text File</a> stuff" +
                "<a class='file' href='file://networkshare/location of thing/and/stuff' target='_blank'>\\\\networkshare\\location of thing\\and\\stuff</a> things";
        String result = instance.execute(text);
        assertEquals(expResult, result);
    }

    /**
     * Test of execute method, of class WikiSyntaxParserLinks.
     */
    @Test
    public void testInternalLinks() {
        String text =
                "test [[something]] stuff" +
                "[[somethign2:test thigns|Yahoo!]] things";
        WikiWord.current = new WikiWord(":");
        (new WikiWord("something")).cacheAdd();

        WikiSyntaxParserLinks instance = new WikiSyntaxParserLinks();
        String expResult =
                "test <a class='intexist' href='#' wiki='something'>something</a> stuff" +
                "<a class='intmissing' href='#' wiki='somethign2:test thigns'>Yahoo!</a> things";
        String result = instance.execute(text);
        assertEquals(expResult, result);
    }




    //TODO: self page anchor links
    //TODO: external anchor links
}