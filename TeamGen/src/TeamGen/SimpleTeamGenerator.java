package TeamGen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Stefan Haan on 9/28/14.
 */
public class SimpleTeamGenerator extends TeamGenerator {
    private List<Skater> skaterPool;
    private Random rand = new Random(System.currentTimeMillis());

    public SimpleTeamGenerator(SkaterSource skaterSource) {
        skaterPool = skaterSource.readAllSkaters();
        Collections.sort(skaterPool);
    }

    @Override
    public List<Team> generateTeams() {
        List<Team> result = new ArrayList<Team>();
        int maxTeamCount = (skaterPool.size() - skaterPool.size()%Team.TEAM_SIZE) / Team.TEAM_SIZE;
        //Possibly won't terminate with some skater lists (!)
        while (result.size() < maxTeamCount){
            result.add(generateTeam());
        }
        return result;
    }

    private Team generateTeam() {
        Team newTeam = new Team();
        for (int i = 0; i < Team.TEAM_SIZE; i++) {
            try{
                int skaterIndex = drawSkater(i);
                newTeam.addSkater(skaterPool.get(skaterIndex));
                skaterPool.remove(skaterIndex);
            } catch (InvalidSkaterException e) {
                i--; //draw this skater again
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return newTeam;
    }

    private int drawSkater(int quarter) {
        int quaterSize = skaterPool.size() / Team.TEAM_SIZE;
        return rand.nextInt(quaterSize) + quarter*quaterSize;
    }
}
