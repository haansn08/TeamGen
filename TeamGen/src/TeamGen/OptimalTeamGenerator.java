package TeamGen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stefan Haan on 9/30/14.
 */
public class OptimalTeamGenerator extends TeamGenerator { //The name is a joke
    private List<Skater> skaterPool = new ArrayList<Skater>();
    private double optimalTeamAverage;

    public OptimalTeamGenerator(SkaterSource skaterSource){
        skaterPool.addAll(skaterSource.readAllSkaters());
        calculateTeamAverage();
    }

    private void calculateTeamAverage() {
        for (Skater skater : skaterPool)
            optimalTeamAverage += skater.getTimeSeconds();
        optimalTeamAverage /= (double)skaterPool.size();
        optimalTeamAverage *= 3.0;
    }

    @Override
    public Draw generateTeams() throws Exception {
        Draw result = new Draw();
        List<Team> possibleTeams = getPossibleTeams();
        while (possibleTeams.size() > 0){
            //find best team
            double bestTeamAverageDiff = Double.MAX_VALUE;
            Team bestTeam = null;
            for (Team team : possibleTeams) {
                double teamAverageDiff = Math.abs(team.totalTime() - optimalTeamAverage);
                if (teamAverageDiff < bestTeamAverageDiff){
                    bestTeamAverageDiff = teamAverageDiff;
                    bestTeam = team;
                }
            }
            result.addTeam(bestTeam);
            //remove used skaters
            for (Skater skater : bestTeam)
                skaterPool.remove(skater);
            possibleTeams = getPossibleTeams();
        }
        return result;
    }

    private List<Team> getPossibleTeams() throws Exception {
        List<Team> possibleTeams = new ArrayList<Team>();
        for (int s1 = 0; s1 < skaterPool.size(); s1++) {
            for (int s2 = s1 + 1; s2 < skaterPool.size(); s2++) {
                for (int s3 = s2 + 1; s3 < skaterPool.size(); s3++) {
                    try{
                        Team team = new Team();
                        team.addSkater(skaterPool.get(s1));
                        team.addSkater(skaterPool.get(s2));
                        team.addSkater(skaterPool.get(s3));
                        possibleTeams.add(team);
                    } catch (InvalidSkaterException ignored) {}
                }
            }
        }
        return possibleTeams;
    }
}
