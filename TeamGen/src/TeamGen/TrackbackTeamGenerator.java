package TeamGen;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/**
 * Created by Stefan Haan on 2/3/15.
 */
public class TrackbackTeamGenerator extends TeamGenerator {
    SkaterPool skaterPool = new SkaterPool();
    Stack<Team> currentDraw = new Stack<Team>();
    int maxTeamCount;

    public TrackbackTeamGenerator(SkaterSource skaterSource) throws Exception {
        skaterPool.fill(skaterSource);
        maxTeamCount = skaterPool.size() / 3;
    }


    @Override
    public Draw generateTeams() throws Exception {
        double teamAverage = skaterPool.calculateTeamAverage();
        if (!chooseTeam(teamAverage))
            throw new Exception("Could not generate draw with every skater. Try a different generation algorithm");
        return getFinalDraw();
    }

    public boolean chooseTeam(double teamAverage) throws Exception {
        if (currentDraw.size() == maxTeamCount)
            return true;
        List<Team> choices = skaterPool.getPossibleTeams();
        Collections.sort(choices, new TeamComparator(teamAverage));
        for (Team choice : choices) {
            currentDraw.push(choice);
            skaterPool.removeTeam(choice);
            if (chooseTeam(teamAverage))
                return true;
            skaterPool.addTeam(currentDraw.pop());
        }
        return false;
    }

    class TeamComparator implements Comparator<Team> {
        double averageTeamTime;
        public TeamComparator (double averageTeamTime){
            this.averageTeamTime = averageTeamTime;
        }
        @Override
        public int compare(Team t1, Team t2) {
            double deviationT1 = Math.abs(t1.totalTime() - averageTeamTime);
            double deviationT2 = Math.abs(t2.totalTime() - averageTeamTime);
            double diff = deviationT1 - deviationT2;
            if (diff < 0.0)
                return -1;
            else if (diff > 0.0)
                return  1;
            return 0;
        }
    }

    private Draw getFinalDraw() {
        Draw finalDraw = new Draw();
        for (Team team : currentDraw) {
            finalDraw.addTeam(team);
        }
        return finalDraw;
    }
}
