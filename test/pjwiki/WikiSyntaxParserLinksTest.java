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
        System.out.println("execute");
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

    public void testInternalLinks() {
        System.out.println("execute");
        String text =
                "test [[something]] stuff" +
                "[[somethign2:test thigns|Yahoo!]] things";
        WikiSyntaxParserLinks instance = new WikiSyntaxParserLinks();
        String expResult =
                "test <a class='intexist' href='#' wiki='something'>something</a> stuff" +
                "<a class='intmissing' href='#' wiki='something2:test thigns'>Yahoo!</a> things";
        String result = instance.execute(text);
        assertEquals(expResult, result);
    }


    //TODO: self page anchor links
    //TODO: external anchor links
}