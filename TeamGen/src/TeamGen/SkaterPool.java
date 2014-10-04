package TeamGen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by stefan on 10/4/14.
 */
public class SkaterPool implements Iterable<Skater>{
    List<Skater> pool = new ArrayList<Skater>();
    public void fill(SkaterSource skaterSource) throws Exception {
        pool.addAll(skaterSource.readAllSkaters());
        Collections.sort(pool);
    }

    public int size() {
        return pool.size();
    }

    public Skater get(int i) {
        return pool.get(i);
    }

    public void removeTeam(Team team) {
        for (Skater skater : team)
            pool.remove(skater);
    }

    public List<Team> getPossibleTeams() throws Exception {
        List<Team> possibleTeams = new ArrayList<Team>();
        for (int s1 = 0; s1 < size(); s1++)
            for (int s2 = s1 + 1; s2 < size(); s2++)
                for (int s3 = s2 + 1; s3 < size(); s3++)
                    try {
                        Team team = new Team();
                        team.addSkater(get(s1));
                        team.addSkater(get(s2));
                        team.addSkater(get(s3));
                        possibleTeams.add(team);
                    }
                    catch (InvalidSkaterException ignored) {}
        return possibleTeams;
    }

    @Override
    public Iterator<Skater> iterator() {
        return pool.iterator();
    }

    public void clear() {
        pool.clear();
    }
}
