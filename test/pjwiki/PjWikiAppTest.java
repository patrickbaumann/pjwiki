/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pjwiki;

import java.awt.Window;
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
public class PjWikiAppTest {

    public PjWikiAppTest() {
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
     * Test of startup method, of class PjWikiApp.
     */
    @Test
    public void testStartup() {
        System.out.println("startup");
        PjWikiApp instance = new PjWikiApp();
        instance.startup();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of configureWindow method, of class PjWikiApp.
     */
    @Test
    public void testConfigureWindow() {
        System.out.println("configureWindow");
        Window root = null;
        PjWikiApp instance = new PjWikiApp();
        instance.configureWindow(root);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getApplication method, of class PjWikiApp.
     */
    @Test
    public void testGetApplication() {
        System.out.println("getApplication");
        PjWikiApp expResult = null;
        PjWikiApp result = PjWikiApp.getApplication();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class PjWikiApp.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        PjWikiApp.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}