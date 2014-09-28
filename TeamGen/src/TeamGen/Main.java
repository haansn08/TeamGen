package TeamGen;

import java.util.List;

/**
 * Created by Stefan Haan on 9/28/14.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length < 1){
            System.err.print("Must specify CSV skater file");
            return;
        }
        for (int maxRuns = 1; maxRuns <= 1000; maxRuns++) {
            SkaterSource skaterSource = loadSkaterSource(args[0]);
            TeamGenerator generator = new SimpleTeamGenerator(skaterSource);
            List<Team> generatedTeams = generateTeams(generator, maxRuns);
            double averageTeamTime = getTotalTime(generatedTeams) / (double) (generatedTeams.size());
            System.out.println(maxRuns + " " + Math.sqrt(getVariance(generatedTeams, averageTeamTime)));
        }
    }

    private static List<Team> generateTeams(TeamGenerator generator, int maxRuns) {
        double bestVariance = Double.MAX_VALUE;
        List<Team> bestGeneration = null;
        for (int i = 0; i < maxRuns; i++) {
            List<Team> generatedTeams = generator.generateTeams();
            double averageTeamTime = getTotalTime(generatedTeams) / (double) (generatedTeams.size());
            double variance = getVariance(generatedTeams, averageTeamTime);
            if (variance < bestVariance){
                bestGeneration = generatedTeams;
                bestVariance = variance;
            }
        }
        return bestGeneration;
    }

    private static SkaterSource loadSkaterSource(String csvFile) throws Exception {
        return new CSVSkaterSource(csvFile);
    }

    private static void printTeams(List<Team> teams) {
        for (int i = 0; i < teams.size(); i++) {
            System.out.print("Team " + (i+1) + ":\t");
            for (Skater skater : teams.get(i))
                System.out.print(skater.getName() + " (" + skater.getTimeSeconds() + ")\t");
            System.out.print("\tTotal:" + Math.round(teams.get(i).totalTime() * 100.0) / 100.0);
            System.out.println();
        }
    }

    private static void printStatistics(List<Team> teams) {
        double totalTime = getTotalTime(teams);
        double averageTeamTime = totalTime / (double)(teams.size());
        System.out.println("Team average:\t" + Math.round(averageTeamTime * 100.0) / 100.0);

        double averageSkaterTime = averageTeamTime / (double)(Team.TEAM_SIZE);
        System.out.println("Skater average:\t" + Math.round(averageSkaterTime * 100.0) / 100.0);

        double teamVariance = getVariance(teams, averageTeamTime);
        System.out.println("Team variance:\t" + Math.round(teamVariance * 1000.0) / 1000.0);

        double teamDeviation = Math.sqrt(teamVariance);
        System.out.println("Team deviation:\t" + Math.round(teamDeviation * 1000.0) / 1000.0);
    }

    private static double getVariance(List<Team> teams, double averageTeamTime) {
        double teamVariance = 0.0;
        for (Team team : teams)
            teamVariance += Math.pow(team.totalTime() - averageTeamTime, 2.0);
        return teamVariance;
    }

    private static double getTotalTime(List<Team> teams) {
        double totalTime = 0.0;
        for (Team team : teams)
            totalTime += team.totalTime();
        return totalTime;
    }
}
