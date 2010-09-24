/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pjwiki;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Patrick
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({pjwiki.WikiSyntaxParserFormattingTest.class,pjwiki.WikiWordTest.class,pjwiki.resources.ResourcesSuite.class})
public class PjwikiSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

}