package tests;

import TeamGen.Team;
import junit.framework.TestCase;

import static tests.Skaters.*;

/**
 * Created by Stefan Haan on 9/27/14.
 */
public class TeamTests extends TestCase {

    public void testAddSkater() throws Exception {
        Team team = new Team();
        team.addSkater(jeremy);
        team.addSkater(vani);
        assertEquals(false, team.isFull());
        team.addSkater(martin);
        assertEquals(3, team.getMemberCount());
        assertEquals(true, team.isFull());
    }

    public void testTeamOverflow() throws Exception {
        Team team = new Team();
        team.addSkater(jeremy);
        team.addSkater(robert);
        team.addSkater(roxi);
        if (team.addSkater(stefan))
            fail();
    }

    public void testValidTeamNation() throws Exception {
        Team teamA = new Team();
        teamA.addSkater(jeremy); //CAN
        teamA.addSkater(robert); //AUT
        if (teamA.addSkater(vani))   //AUT
            fail();
        Team teamB = new Team();
        teamB.addSkater(jeremy); //CAN
        teamB.addSkater(vani);   //AUT
        teamB.addSkater(martin); //SUI
    }

    public void testValidTeamGender() throws Exception {
        Team teamA = new Team();
        teamA.addSkater(jeremy);
        teamA.addSkater(robert);
        if (teamA.addSkater(martin))
            fail();
        Team teamB = new Team();
        teamB.addSkater(jeremy);
        teamB.addSkater(robert);
        teamB.addSkater(roxi);
    }
}
