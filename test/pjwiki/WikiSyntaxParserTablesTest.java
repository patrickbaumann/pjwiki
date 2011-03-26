/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pjwiki;

import pjwiki.syntax.WikiSyntaxParserTables;
import java.util.regex.Pattern;
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
public class WikiSyntaxParserTablesTest {

    public WikiSyntaxParserTablesTest() {
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
     * Test of execute method, of class WikiSyntaxParserTables.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        String text = 
                "**nottbl**\r\n" +
                "^ h1 ^ h2  ^  h3  ^  h4 ^\n" +
                "| a | **b**   |      |  c  |  \r\n" +
                " |not a table|\r\n";
        WikiSyntaxParserTables instance = new WikiSyntaxParserTables();
        String expResult = 
                "**nottbl**\r\n" +
                "<table>\r\n" +
                "  <tr>\r\n" +
                "    <th>h1</th>\r\n" +
                "    <th align='left'>h2</th>\r\n" +
                "    <th align='center'>h3</th>\r\n" +
                "    <th align='right'>h4</th>\r\n" +
                "  </tr>\r\n" +
                "  <tr>\r\n" +
                "    <td>a</td>\r\n" +
                "    <td align='left'>**b**</td>\r\n" +
                "    <td align='right'></td>\r\n" +
                "    <td align='center'>c</td>\r\n" +
                "  </tr>\r\n" +
                "</table>\r\n"+
                " |not a table|\r\n";
        String result = instance.execute(text);
        assertEquals(expResult, result);
    }


}