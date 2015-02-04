package TeamGen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Stefan Haan on 9/27/14.
 */
public class Team implements Iterable<Skater>{
    public static final int TEAM_SIZE = 3;
    private List<Skater> members = new ArrayList<Skater>(TEAM_SIZE);

    public boolean addSkater(Skater skater) throws Exception{
        if (isFull() || !isValidNation(skater.getNation()) || !isValidGender(skater.getGender()))
            return false;
        members.add(skater);
        return true;
    }

    private boolean isValidGender(int gender) {
        if (members.size() < TEAM_SIZE - 1)
            return true;
        for (Skater member : members)
            if (member.getGender() != gender)
                return true;
        return false;
    }

    private boolean isValidNation(String nation) {
        for (Skater member : members)
            if (member.getNation().equalsIgnoreCase(nation))
                return false;
        return true;
    }

    public boolean isFull() {
        return !(members.size() < TEAM_SIZE);
    }

    public int getMemberCount() {
        return members.size();
    }

    @Override
    public Iterator<Skater> iterator() {
        return members.iterator();
    }

    public double totalTime(){
        double sum = 0.0;
        for (Skater skater : this)
            sum += skater.getTimeSeconds();
        return  sum;
    }
}
