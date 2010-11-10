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
public class WikiSyntaxParserNoWikiTest {

    public WikiSyntaxParserNoWikiTest() {
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
     * Test of prepare method, of class WikiSyntaxParserNoWiki.
     */
    @Test
    public void testBothMultiLine() {
        String text =
                "tseting <nowiki> test</nowiki> testing <nowiki>testi\r\n" +
                "ng!!!</nowiki> test stuff\r\n";
        WikiSyntaxParserNoWiki instance = new WikiSyntaxParserNoWiki();
        String expResult =
                "tseting <nowiki /> testing <nowiki /> test stuff\r\n";
        String result = instance.prepare(text);
        assertEquals(expResult, result);

        expResult =
                "tseting <nowiki> test</nowiki> testing <nowiki>testi\r\n" +
                "ng!!!</nowiki> test stuff\r\n";
        result = instance.execute(text);
        assertEquals(expResult, result);
    }
}