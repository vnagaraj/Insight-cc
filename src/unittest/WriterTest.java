package unittest;

import org.junit.AfterClass;
import org.junit.Test;
import stream.Reader;
import stream.Writer;

import static org.junit.Assert.assertTrue;

/**
 * WriterTest class -Junit tests to validate the write to file
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 April 3rd, 2016
 */
public class WriterTest {
    private static String file = null;


    @Test
    public void writerTest()
    {
        file = UnitTestConstants.CURRENT_DIR + UnitTestConstants.UNITTEST_PATH + UnitTestConstants.OUTPUT_FILE;
        double average = 1.6666677;
        new Writer(file).write(average);
        String expAvg = "1.66";
        Reader reader = new Reader(file);
        assertTrue(reader.readLine() .equals(expAvg));
        average = 2.77;
        new Writer(file).write(average);
        assertTrue(reader.readLine().equals(String.valueOf(average)));
    }

    @AfterClass
    public static void tearDown(){
        new Writer(file).delete();
    }


}
