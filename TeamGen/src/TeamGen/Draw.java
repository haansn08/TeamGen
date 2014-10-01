package TeamGen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Stefan Haan on 9/28/14.
 */
public class Draw implements Iterable<Team> {
    List<Team> teams = new ArrayList<Team>();
    public void addTeam(Team team) {
        teams.add(team);
    }
    public int teamCount() {
        return teams.size();
    }
    public void clear() {
        teams.clear();
    }
    @Override
    public Iterator<Team> iterator() {
        return teams.iterator();
    }


    public double teamAverage() {
        double sum = 0.0;
        for (Team team : this)
            sum += team.totalTime();
        return sum / (double)teamCount();
    }

    public double deviation() {
        double teamAverage = teamAverage();
        double variance = 0.0;
        for (Team team : this)
            variance += Math.pow(team.totalTime() - teamAverage, 2);
        variance /= (double)(teamCount() - 1);
        return Math.sqrt(variance);
    }
}
