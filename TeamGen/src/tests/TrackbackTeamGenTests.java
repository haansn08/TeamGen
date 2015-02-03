package tests;

import TeamGen.*;
import junit.framework.TestCase;

/**
 * Created by Stefan Haan on 2/3/15.
 */
public class TrackbackTeamGenTests extends TestCase {
    public void testCompleteness() throws Exception {
        SkaterSource skaterSource = new CSVSkaterSource("res/skaters.csv");
        TeamGenerator trackbackGen = new TrackbackTeamGenerator(skaterSource);
        Draw draw = trackbackGen.generateTeams();
        assertEquals(skaterSource.readAllSkaters().size() / 3, draw.teamCount());
    }
}
