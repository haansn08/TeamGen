package TeamGen;

/**
 * Created by Stefan Haan on 9/27/14.
 */
public class Skater {
    public static final int FEMALE = 1;
    public static final int MALE = 2;

    private double time;
    private int gender;
    private String nation;
    public Skater(double timeSeconds, int gender, String nation) {
        time = timeSeconds;
        this.gender = gender;
        this.nation = nation.toUpperCase();
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
}
