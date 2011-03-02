/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pjwiki;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class WikiWordFileTest {

    static File temppath ;

    public WikiWordFileTest() {
        try {
            temppath = new File(File.createTempFile("test", "test").getParentFile() + File.separator + "wikitest");
        } catch (IOException ex) {
            Logger.getLogger(WikiWordFileTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        temppath.mkdirs();
        WikiWordPageFile.setDataRoot(temppath);

        WikiWordPageFile wwf = new WikiWordPageFile("testing");
        wwf.save("TESTING");
        System.out.println(WikiWordPageFile.getDataRoot());
    }

    @After
    public void tearDown() {
        try{
            String[] files = temppath.list();
            for(String file: files)
            {
                (new File(file)).deleteOnExit();
            }
        }catch(Exception e){}
    }

    /**
     * Test of validate method, of class WikiWordFile.
     */
    @Test
    public void testValidate() throws Exception {
        WikiWordPageFile instance;
        //WikiWordFile.setDataRoot(null);

        WikiWordPageFile.setDataRoot(new File("."));
        try{
            instance = new WikiWordPageFile((WikiWord)null);
            fail("Should have thrown exception for being a bad word");
        }catch(Exception e)
        {
            assertTrue(e.getMessage().contains("WikiWord"));
        }
        try{
            instance = new WikiWordPageFile("Index");
        }catch(Exception e)
        {
            fail("Completely valid word should not fail!");
        }
    }

    @Test
    public void testSaveAndLoad() throws Exception {
        System.out.println("saveWikiMarkup");
        String contents = "thisisatestoftheemergency\r\n\r\ntest\r\ntesttest\r\n";
        WikiWordPageFile instance = new WikiWordPageFile("imawesome");
        instance.save(contents);
        String loaded = instance.load();
        assertEquals(contents, loaded);
    }

    /**
     * Test of tryLockFor method, of class WikiWordFile.
     */
    @Test
    public void testLockAndModifiable() throws Exception {
        WikiWordPageFile instance = new WikiWordPageFile("testing");
        
        assertTrue("Anyone can edit an unlocked file", instance.isModifiableFor("Patrick"));
        assertTrue("Anyone can edit an unlocked file", instance.isModifiableFor("Jonathon"));
        assertTrue("Patrick should be able to lock an unlocked file", instance.tryLockFor("Patrick"));
        assertTrue("Patrick should be able to modify his lock", instance.isModifiableFor("Patrick"));
        assertFalse("Jonathan should not be able to modify Patrick's lock", instance.isModifiableFor("Jonathon"));
        assertFalse("Jonathan should not be able to lock Patrick's lock", instance.tryLockFor("Jonathon"));
        assertFalse("Jonathan should not be able to unlock Patrick's lock", instance.unlockFor("Jonathon"));
        assertTrue("Patrick should be able to unlock his locked file", instance.unlockFor("Patrick"));
        assertTrue("Anyone can edit an unlocked file", instance.isModifiableFor("Patrick"));
        assertTrue("Anyone can edit an unlocked file", instance.isModifiableFor("Jonathon"));
    }

    /**
     * Test of setDataRoot method, of class WikiWordFile.
     */
    @Test
    public void testSetDataRoot() throws Exception {
        System.out.println("setDataRoot");
        File dataRoot = new File(".");
        WikiWordPageFile.setDataRoot(dataRoot);
        WikiWordPageFile a = new WikiWordPageFile("test");
        assertNotNull("should not be null", WikiWordPageFile.getDataRoot());
    }

    @Test
    public void testGetVersions() throws Exception {
        // TODO: Add versioning
        fail("TODO");
    }

    @Test
    public void testGetVersionContent() throws Exception {
        // TODO: Add versioning and retriving of specific version content
        fail("TODO");
    }

    @Test
    public void testTimeLockTimeout() throws Exception {
        // TODO: Storing of date and time at which the lock will expire
        fail("TODO");
    }

}