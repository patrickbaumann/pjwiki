/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pjwiki;

import pjwiki.syntax.WikiSyntaxParserLists;
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
public class WikiSyntaxParserListsTest {

    public WikiSyntaxParserListsTest() {
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
     * Test of execute method, of class WikiSyntaxParserLists.
     */
    @Test
    public void testExecuteBasicUlList() {
        System.out.println("execute");
        String text = 
                "** bold stuff **" + "\r\n" +
                "  * list1" + "\r\n" +
                "    * list2" + "\r\n" +
                "    * list3" + "\r\n" +
                "  * list4" + "\r\n" +
                "      * list 5" + "\r\n" +
                "[[blah]]" + "\r\n";
        WikiSyntaxParserLists instance = new WikiSyntaxParserLists();
        String expResult = 
                "** bold stuff **" + "\r\n" +
                "<ul>" + "\r\n" +
                "  <li>list1</li>" + "\r\n" +
                "  <ul>" + "\r\n" +
                "    <li>list2</li>" + "\r\n" +
                "    <li>list3</li>" + "\r\n" +
                "  </ul>" + "\r\n" +
                "  <li>list4</li>" + "\r\n" +
                "  <ul>" + "\r\n" +
                "    <ul>" + "\r\n" +
                "      <li>list 5</li>" + "\r\n" +
                "    </ul>" + "\r\n" +
                "  </ul>" + "\r\n" +
                "</ul>" + "\r\n" +
                "[[blah]]" + "\r\n";
        String result = instance.execute(text);
        assertEquals(expResult, result);
    }
    public void testExecuteBasicOlList() {
        System.out.println("execute");
        String text =
                "** bold stuff **" + "\r\n" +
                "  - list1" + "\r\n" +
                "    - list2" + "\r\n" +
                "    - list3" + "\r\n" +
                "  - list4" + "\r\n" +
                "      - list 5" + "\r\n" +
                "[[blah]]" + "\r\n";
        WikiSyntaxParserLists instance = new WikiSyntaxParserLists();
        String expResult =
                "** bold stuff **" + "\r\n" +
                "<ol>" + "\r\n" +
                "  <li>list1</li>" + "\r\n" +
                "  <ol>" + "\r\n" +
                "    <li>list2</li>" + "\r\n" +
                "    <li>list3</li>" + "\r\n" +
                "  </ol>" + "\r\n" +
                "  <li>list4</li>" + "\r\n" +
                "  <ol>" + "\r\n" +
                "    <ol>" + "\r\n" +
                "      <li>list 5</li>" + "\r\n" +
                "    </ol>" + "\r\n" +
                "  </ol>" + "\r\n" +
                "</ol>" + "\r\n" +
                "[[blah]]" + "\r\n";
        String result = instance.execute(text);
        assertEquals(expResult, result);
    }

    public void testExecuteEmbeddedDiffLists() {
        System.out.println("execute");
        String text =
                "** bold stuff **" + "\r\n" +
                "  * list1" + "\r\n" +
                "    - list2" + "\r\n" +
                "    - list3" + "\r\n" +
                "  * list4" + "\r\n" +
                "      * list 5" + "\r\n" +
                "[[blah]]" + "\r\n";
        WikiSyntaxParserLists instance = new WikiSyntaxParserLists();
        String expResult =
                "** bold stuff **" + "\r\n" +
                "<ul>" + "\r\n" +
                "  <li>list1</li>" + "\r\n" +
                "</ul>" + "\r\n" +
                "<ol>" + "\r\n" +
                "  <ol>" + "\r\n" +
                "    <li>list2</li>" + "\r\n" +
                "    <li>list3</li>" + "\r\n" +
                "  </ol>" + "\r\n" +
                "</ol>" + "\r\n" +
                "<ul>" + "\r\n" +
                "  <li>list4</li>" + "\r\n" +
                "  <ul>" + "\r\n" +
                "    <ul>" + "\r\n" +
                "      <li>list 5</li>" + "\r\n" +
                "    </ul>" + "\r\n" +
                "  </ul>" + "\r\n" +
                "</ul>" + "\r\n" +
                "[[blah]]" + "\r\n";
        String result = instance.execute(text);
        assertEquals(expResult, result);
    }
}