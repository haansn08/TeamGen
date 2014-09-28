package tests;

/**
 * Created by Stefan Haan on 9/27/14.
 */
import TeamGen.Skater;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SkaterTests extends TestCase{
    public void testConstructor(){
        Skater skater = new Skater(39.21, Skater.MALE, "aut");
        assertEquals(39.21, skater.getTimeSeconds(), 0.0001);
        assertEquals(Skater.MALE, skater.getGender());
        assertEquals("AUT", skater.getNation());
    }
    public void testSorting(){
        List<Skater> skaters = new ArrayList<Skater>();
        skaters.add(new Skater(34.51, Skater.MALE, "NOR"));   //1
        skaters.add(new Skater(37.73, Skater.FEMALE, "USA")); //3
        skaters.add(new Skater(39.28, Skater.FEMALE, "CZE")); //4
        skaters.add(new Skater(35.86, Skater.MALE, "CAN"));   //2
        skaters.add(new Skater(41.48, Skater.FEMALE, "USA")); //5
        Collections.sort(skaters);

        assertEquals(34.51, skaters.get(0).getTimeSeconds());
        assertEquals(39.28, skaters.get(3).getTimeSeconds());
        assertEquals(35.86, skaters.get(1).getTimeSeconds());
    }
}