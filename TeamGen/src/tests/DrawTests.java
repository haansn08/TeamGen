package tests;

import TeamGen.Draw;
import TeamGen.Skater;
import TeamGen.Team;
import junit.framework.TestCase;

import static tests.Skaters.*;

/**
 * Created by Stefan Haan on 9/28/14.
 */
public class DrawTests extends TestCase {
    public void testTeamList() throws Exception {
        Draw draw = new Draw();
        draw.addTeam(new Team());
        draw.addTeam(new Team());
        assertEquals(2, draw.teamCount());
        draw.clear();
        assertEquals(0, draw.teamCount());

        Team teamA = new Team();
        teamA.addSkater(new Skater(40.0, Skater.FEMALE, "SUI"));
        draw.addTeam(teamA);
        for(Team team : draw)
            for (Skater skater : team)
                assertEquals(40.0, skater.getTimeSeconds());
    }
    public void testStatistics() throws Exception {
        Team teamA = new Team();
        teamA.addSkater(jeremy);
        teamA.addSkater(robert);
        teamA.addSkater(roxi);

        Team teamB = new Team();
        teamB.addSkater(martin);
        teamB.addSkater(vani);
        teamB.addSkater(roxi);

        Draw draw = new Draw();
        draw.addTeam(teamA);
        draw.addTeam(teamB);

        double teamAverage = (teamA.totalTime() + teamB.totalTime()) / 2.0;
        assertEquals(teamAverage, draw.teamAverage());
        assertEquals(2.77186, draw.deviation(), 0.0001);
    }
}
