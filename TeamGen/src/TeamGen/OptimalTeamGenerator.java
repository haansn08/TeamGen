package TeamGen;

import java.util.List;

/**
 * Created by Stefan Haan on 9/30/14.
 */
public class OptimalTeamGenerator extends TeamGenerator { //The name is a joke
    private SkaterPool skaterPool = new SkaterPool();
    private double optimalTeamAverage;

    public OptimalTeamGenerator(SkaterSource skaterSource) throws Exception {
        skaterPool.fill(skaterSource);
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
        List<Team> possibleTeams;
        while (true) {
            possibleTeams = skaterPool.getPossibleTeams();
            if (possibleTeams.size() == 0)
                break;
            Team bestTeam = findBestTeam(possibleTeams);

            result.addTeam(bestTeam);
            skaterPool.removeTeam(bestTeam);

        }
        return result;
    }

    private Team findBestTeam(List<Team> possibleTeams) {
        double bestTeamAverageDiff = Double.MAX_VALUE;
        Team bestTeam = null;
        for (Team team : possibleTeams) {
            double teamAverageDiff = Math.abs(team.totalTime() - optimalTeamAverage);
            if (teamAverageDiff < bestTeamAverageDiff){
                bestTeamAverageDiff = teamAverageDiff;
                bestTeam = team;
            }
        }
        return bestTeam;
    }
}
