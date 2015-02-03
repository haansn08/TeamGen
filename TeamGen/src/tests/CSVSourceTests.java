package tests;

import TeamGen.CSVSkaterSource;
import TeamGen.Skater;
import TeamGen.SkaterSource;
import junit.framework.TestCase;

import java.util.List;

/**
 * Created by Stefan Haan on 9/27/14.
 */
public class CSVSourceTests extends TestCase {
    public void testLoadCSVFile() throws Exception{
        SkaterSource csvSource = new CSVSkaterSource("res/skaters.csv");
        List<Skater> skaters = csvSource.readAllSkaters();
        assertEquals("Armin", skaters.get(21).getName());
        assertEquals(36.50, skaters.get(27).getTimeSeconds());
        assertEquals(Skater.FEMALE, skaters.get(8).getGender());
        assertEquals("AUT", skaters.get(1).getNation());
    }
}
