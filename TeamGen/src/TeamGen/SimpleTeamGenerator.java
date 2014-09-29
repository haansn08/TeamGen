package TeamGen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Stefan Haan on 9/28/14.
 */
public class SimpleTeamGenerator extends TeamGenerator {
    SkaterSource skaterSource;
    private List<Skater> skaterPool;
    private Random rand = new Random(System.currentTimeMillis());

    public SimpleTeamGenerator(SkaterSource skaterSource) {
        this.skaterSource = skaterSource;
    }

    private void fillSkaterPool() {
        skaterPool = new ArrayList<Skater>();
        skaterPool.addAll(skaterSource.readAllSkaters());
        Collections.sort(skaterPool);
    }

    @Override
    public Draw generateTeams() {
        Draw result = new Draw();
        fillSkaterPool();
        int maxTeamCount = (skaterPool.size() - skaterPool.size()%Team.TEAM_SIZE) / Team.TEAM_SIZE;
        //Possibly won't terminate with some skater lists (!)
        while (result.teamCount() < maxTeamCount){
            try {
                result.addTeam(generateTeam());
            } catch (Exception e) {
                result.clear();
                fillSkaterPool();
            }
        }
        return result;
    }

    private Team generateTeam() throws Exception {
        Team newTeam = new Team();
        int trials = 0;
        for (int i = 0; i < Team.TEAM_SIZE; i++) {
            try{
                int skaterIndex = drawSkater(i);
                newTeam.addSkater(skaterPool.get(skaterIndex));
                skaterPool.remove(skaterIndex);
                trials = 0;
            } catch (InvalidSkaterException e) {
                i--; //draw this skater again
                trials++;
                if (trials > skaterPool.size()) //no special math involved -
                                                //should just be depended on number of skaters available
                    throw new Exception("No more skaters could be selected");
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
