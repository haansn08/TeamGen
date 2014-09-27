package tests;

import TeamGen.InvalidSkaterException;
import TeamGen.Skater;
import TeamGen.Team;
import junit.framework.TestCase;

/**
 * Created by Stefan Haan on 9/27/14.
 */
public class TeamTests extends TestCase {
    private Skater jeremy = new Skater(34.03, Skater.MALE, "CAN");
    private Skater robert = new Skater(38.87, Skater.MALE, "AUT");
    private Skater vani = new Skater(38.35, Skater.FEMALE, "AUT");
    private Skater stefan = new Skater(39.12, Skater.MALE, "AUT");
    private Skater martin = new Skater(38.47, Skater.MALE, "SUI");
    private Skater roxi = new Skater(40.45, Skater.FEMALE, "GER");

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
        try{
            team.addSkater(stefan);
            fail();
        }
        catch (IndexOutOfBoundsException ignored){}
    }

    public void testValidTeamNation() throws Exception {
        Team teamA = new Team();
        teamA.addSkater(jeremy); //CAN
        teamA.addSkater(robert); //AUT
        try {
            teamA.addSkater(vani);   //AUT
            fail();
        }
        catch (InvalidSkaterException e){
            assertEquals(InvalidSkaterException.INVALID_NATION, e.getReason());
        }

        Team teamB = new Team();
        teamB.addSkater(jeremy); //CAN
        teamB.addSkater(vani);   //AUT
        teamB.addSkater(martin); //SUI
    }

    public void testValidTeamGender() throws Exception{
        Team teamA = new Team();
        teamA.addSkater(jeremy);
        teamA.addSkater(robert);
        try{
            teamA.addSkater(martin);
            fail();
        }
        catch (InvalidSkaterException e){
            assertEquals(InvalidSkaterException.INVALID_GENDER, e.getReason());
        }

        Team teamB = new Team();
        teamB.addSkater(jeremy);
        teamB.addSkater(robert);
        teamB.addSkater(roxi);
    }
}
