package TeamGen;

import java.util.List;

/**
 * Created by Stefan Haan on 9/28/14.
 */
public class Main {
    public static void main(String[] args){
        if (args.length != 1){
            System.err.print("Must specify CSV skater file");
            return;
        }
        SkaterSource skaterSource;
        try {
            skaterSource = loadSkaterSource(args[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        TeamGenerator generator = new SimpleTeamGenerator(skaterSource);
        List<Team> generatedTeams = generator.generateTeams();
        printTeams(generatedTeams);
        System.out.println();
        System.out.println("------- Statistics ---------------------");
        printStatistics(generatedTeams);
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
        double totalTime = 0.0;
        for (Team team : teams)
            totalTime += team.totalTime();
        double averageTeamTime = totalTime / (double)(teams.size());
        System.out.println("Team average:\t" + Math.round(averageTeamTime * 100.0) / 100.0);

        double averageSkaterTime = averageTeamTime / (double)(Team.TEAM_SIZE);
        System.out.println("Skater average:\t" + Math.round(averageSkaterTime * 100.0) / 100.0);

        double teamVariance = 0.0;
        for (Team team : teams)
            teamVariance += Math.pow(team.totalTime() - averageTeamTime, 2.0);
        System.out.println("Team variance:\t" + Math.round(teamVariance * 1000.0) / 1000.0);

        double teamDeviation = Math.sqrt(teamVariance);
        System.out.println("Team deviation:\t" + Math.round(teamDeviation * 1000.0) / 1000.0);
    }
}
