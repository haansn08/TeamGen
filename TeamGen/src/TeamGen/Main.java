package TeamGen;

/**
 * Created by Stefan Haan on 9/28/14.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length < 1){
            System.err.print("Must specify CSV skater file");
            return;
        }
        int maxRuns = 1;
        if (args.length > 1)
            maxRuns = Integer.parseInt(args[1]);

        SkaterSource skaterSource = new CSVSkaterSource(args[0]);
        TeamGenerator generator = new OptimalTeamGenerator(skaterSource);
        Draw generatedTeams = generateTeams(generator, maxRuns);

        printTeams(generatedTeams);
        System.out.println();
        System.out.println("------- Statistics ---------------------");
        printStatistics(generatedTeams);
    }

    private static Draw generateTeams(TeamGenerator generator, int maxRuns) throws Exception {
        double bestDeviation = Double.MAX_VALUE;
        Draw bestGeneration = null;
        for (int i = 0; i < maxRuns; i++) {
            Draw generatedTeams = generator.generateTeams();
            if (generatedTeams.deviation() < bestDeviation){
                bestDeviation = generatedTeams.deviation();
                bestGeneration = generatedTeams;
            }
        }
        return bestGeneration;
    }

    private static void printTeams(Draw draw) {
        int i = 1;
        for (Team team : draw) {
            System.out.print("Team " + i + ":\t");
            for (Skater skater : team)
                System.out.print(skater.getName() + " (" + skater.getTimeSeconds() + ")\t");
            System.out.print("\tTotal:" + Math.round(team.totalTime() * 100.0) / 100.0);
            System.out.println();
            i++;
        }
    }

    private static void printStatistics(Draw teams) {
        double averageTeamTime = teams.teamAverage();
        System.out.println("Team average:\t" + Math.round(averageTeamTime * 100.0) / 100.0);

        double averageSkaterTime = averageTeamTime / (double)(Team.TEAM_SIZE);
        System.out.println("Skater average:\t" + Math.round(averageSkaterTime * 100.0) / 100.0);

        double teamDeviation = teams.deviation();
        System.out.println("Team deviation:\t" + Math.round(teamDeviation * 1000.0) / 1000.0);
    }
}
