package tests;

import TeamGen.*;
import junit.framework.TestCase;

/**
 * Created by Stefan Haan on 9/28/14.
 */
public class SimpleTeamGenTests extends TestCase {
    public void testTeamGeneration() throws Exception {
        SkaterSource skaterSource = new CSVSkaterSource("res/skaters.csv");
        TeamGenerator simpleGenerator = new SimpleTeamGenerator(skaterSource, 5);
        Draw generatedTeams = simpleGenerator.generateTeams();

        assertEquals(10, generatedTeams.teamCount());
        for (Team team : generatedTeams)
            assertEquals(true, team.isFull());
    }

}

