package tests;

import TeamGen.Skater;
import TeamGen.SkaterSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stefan Haan on 9/28/14.
 */
public class Skaters {
    public static Skater jeremy = new Skater(34.03, Skater.MALE, "CAN");
    public static Skater vani = new Skater(38.35, Skater.FEMALE, "AUT");
    public static Skater martin = new Skater(38.47, Skater.MALE, "SUI");
    public static Skater robert = new Skater(38.87, Skater.MALE, "AUT");
    public static Skater stefan = new Skater(39.21, Skater.MALE, "AUT");
    public static Skater roxi = new Skater(40.45, Skater.FEMALE, "GER");
    public static final int skaterCount = 6;
    private static class TestSource implements SkaterSource {
        List<Skater> skaters = new ArrayList<Skater>(skaterCount);
        public TestSource(){
            skaters.add(jeremy);
            skaters.add(robert);
            skaters.add(vani);
            skaters.add(stefan);
            skaters.add(martin);
            skaters.add(roxi);
        }
        @Override
        public List<Skater> readAllSkaters() throws Exception {
            return skaters;
        }
    }
    private static SkaterSource skaterSource = new TestSource();
    public static SkaterSource getSkaterSource(){
        return skaterSource;
    }

}
