package TeamGen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stefan Haan on 9/27/14.
 */
public class Team {
    public static final int TEAM_SIZE = 3;
    private List<Skater> members = new ArrayList<Skater>(TEAM_SIZE);

    public void addSkater(Skater skater) throws Exception{
        if (isFull())
            throw new IndexOutOfBoundsException("Too many skaters in team");
        if (!isValidNation(skater.getNation()))
            throw new InvalidSkaterException(InvalidSkaterException.INVALID_NATION);
        if (!isValidGender(skater.getGender()))
            throw new InvalidSkaterException(InvalidSkaterException.INVALID_GENDER);
        members.add(skater);
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
}
