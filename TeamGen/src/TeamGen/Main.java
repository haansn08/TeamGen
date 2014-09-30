package TeamGen;

/**
 * Created by Stefan Haan on 9/28/14.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length < 1){
            System.err.println("Must specify CSV skater file");
            return;
        }

        SkaterSource skaterSource = new CSVSkaterSource(args[0]);
        TeamGenerator generator = new OptimalTeamGenerator(skaterSource);
        Draw generatedTeams = generator.generateTeams();

        printTeams(generatedTeams);
        System.out.println();
        System.out.println("------- Statistics ---------------------");
        printStatistics(generatedTeams);
    }

    private static void printTeams(Draw draw) {
        int i = 1;
        for (Team team : draw) {
            System.out.print("Team " + (i++) + ":\t");
            for (Skater skater : team)
                System.out.print(skater.getName() + " (" + skater.getTimeSeconds() + ")\t");
            System.out.print("\tTotal:" + round(team.totalTime(), 2));
            System.out.println();
        }
    }

    private static void printStatistics(Draw teams) {
        double averageTeamTime = teams.teamAverage();
        System.out.println("Team average:\t" + round(averageTeamTime, 2));

        double averageSkaterTime = averageTeamTime / (double)(Team.TEAM_SIZE);
        System.out.println("Skater average:\t" + round(averageSkaterTime, 2));

        double teamDeviation = teams.deviation();
        System.out.println("Team deviation:\t" + round(teamDeviation, 3));
    }

    private static double round (double number, int digits){
        double power = Math.pow(10.0, digits);
        return Math.round(number * power) / power;
    }
}
