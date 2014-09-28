package TeamGen;

/**
 * Created by Stefan Haan on 9/27/14.
 */
public class Skater implements Comparable<Skater>{
    public static final int FEMALE = 1;
    public static final int MALE = 2;

    private double time;
    private int gender;
    private String nation;
    private String name;

    public Skater(double timeSeconds, int gender, String nation) {
        time = timeSeconds;
        this.gender = gender;
        this.nation = nation.toUpperCase();
    }
    public Skater(String name, double timeSeconds, int gender, String nation)
    {
        this.name = name;
        this.time = timeSeconds;
        this.gender = gender;
        this.nation = nation;
    }

    public double getTimeSeconds() {
        return time;
    }
    public int getGender() {
        return gender;
    }
    public String getNation() {
        return nation;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Skater skater) {
        return (int)(getTimeSeconds() - skater.getTimeSeconds());
    }
}
