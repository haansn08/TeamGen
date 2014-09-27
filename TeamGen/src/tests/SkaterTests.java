package tests;

/**
 * Created by Stefan Haan on 9/27/14.
 */
import TeamGen.Skater;
import junit.framework.TestCase;

public class SkaterTests extends TestCase{
    public void testConstructor(){
        Skater skater = new Skater(39.21);
        assertEquals(39.21, skater.getTimeSeconds(), 0.0001);
    }
}