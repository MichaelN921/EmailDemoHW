import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The test class MailTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MailTest
{
    /**
     * Default constructor for test class MailTest
     */
    public MailTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void WriteEmail()
    {
        Mail mail1 = new Mail("Mike", "Julia", "09/21/2021", "Hello");
        assertEquals("From: Mike\nTo: Julia\nDate: 09/21/2021\nHello", mail1.email());
    }
}

