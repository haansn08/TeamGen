package tests;
import TeamGen.SkaterPool;
import TeamGen.Team;
import junit.framework.TestCase;

/**
 * Created by stefan on 10/4/14.
 */
public class SkaterPoolTests extends TestCase {
    public void testFillPool() throws Exception {
        SkaterPool testPool = new SkaterPool();
        testPool.fill(Skaters.getSkaterSource());
        assertEquals(Skaters.skaterCount, testPool.size());
        assertEquals(Skaters.jeremy, testPool.get(0));
        assertEquals(Skaters.vani, testPool.get(1));
        assertEquals(Skaters.roxi, testPool.get(5));
    }
    public void testRemoveTeam() throws Exception{
        SkaterPool testPool = new SkaterPool();
        testPool.fill(Skaters.getSkaterSource());
        assertEquals(Skaters.skaterCount, testPool.size());
        Team teamA = new Team();
        teamA.addSkater(Skaters.jeremy);
        teamA.addSkater(Skaters.robert);
        teamA.addSkater(Skaters.roxi);
        testPool.removeTeam(teamA);
        assertEquals(Skaters.skaterCount - 3, testPool.size());
        assertEquals(Skaters.vani, testPool.get(0));
        assertEquals(Skaters.stefan, testPool.get(2));
    }
    public void testClearPool() throws Exception {
        SkaterPool testPool = new SkaterPool();
        testPool.fill(Skaters.getSkaterSource());
        testPool.clear();
        assertEquals(0, testPool.size());
    }
}
