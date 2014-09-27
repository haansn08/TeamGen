package TeamGen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stefan Haan on 9/27/14.
 */
public class CSVSkaterSource extends SkaterSource {
    BufferedReader fileReader;
    List<Skater> skaters;
    public CSVSkaterSource(String filename) throws Exception{
        fileReader = new BufferedReader(
                new FileReader(filename)
        );
    }

    @Override
    public List<Skater> readAllSkaters() {
        if (skaters != null)
            return skaters;
        skaters = new ArrayList<Skater>();
        try {
            readFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return skaters;
    }

    private void readFile() throws Exception {
        while (fileReader.ready()){
            skaters.add(
                    parseCSV(fileReader.readLine())
            );
        }
    }

    private Skater parseCSV(String line) {
        String[] columns = line.split(";");
        String name = columns[0];
        double time = Double.parseDouble(columns[1]);
        String nation = columns[3];
        int gender;
        if (columns[2].equalsIgnoreCase("FEMALE"))
            gender = Skater.FEMALE;
        else
            gender = Skater.MALE;
        return new Skater(name, time, gender, nation);
    }
}
