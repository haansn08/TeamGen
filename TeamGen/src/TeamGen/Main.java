package TeamGen;

import java.util.List;

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
        TeamGenerator generator = new TrackbackTeamGenerator(skaterSource);
        Draw draw = generator.generateTeams();

        printTeams(draw);
        printIgnoredSkaters(draw, skaterSource);
        System.out.println();
        System.out.println("------- Statistics ---------------------");
        printStatistics(draw);
    }

    private static void printTeams(Draw draw) {
        int i = 1;
        for (Team team : draw) {
            System.out.print("Team " + (i++) + ":\t");
            for (Skater skater : team)
                printSkater(skater);
            System.out.print("\tTotal:" + round(team.totalTime(), 2));
            System.out.println();
        }
    }

    private static void printIgnoredSkaters(Draw draw, SkaterSource skaterSource) throws Exception {
        List<Skater> allSkaters = skaterSource.readAllSkaters();
        for (Team team : draw)
            for (Skater skater : team)
                allSkaters.remove(skater);
        if (allSkaters.isEmpty())
            return;

        System.out.println("------- Ignored Skaters ----------------");
        for (Skater skater : allSkaters)
            printSkater(skater);
    }

    private static void printSkater(Skater skater) {
        System.out.print(skater.getName() + " (" + skater.getTimeSeconds() + ")\t");
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
