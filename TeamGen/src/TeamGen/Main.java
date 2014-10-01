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
        for (int maxRuns = 1; maxRuns <= 1000; maxRuns++) {
            TeamGenerator generator = new SimpleTeamGenerator(skaterSource, maxRuns);
            Draw generatedTeams = generator.generateTeams();
            System.out.println(maxRuns + " " + generatedTeams.deviation());
        }
    }

}
