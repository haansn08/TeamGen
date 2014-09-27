package TeamGen;

/**
 * Created by Stefan Haan on 9/27/14.
 */
public class InvalidSkaterException extends Exception{
    public static final int INVALID_NATION = 1;
    public static final int INVALID_GENDER = 2;

    private int reason;
    public InvalidSkaterException(int reason){
        this.reason = reason;
    }

    public int getReason() {
        return reason;
    }
}
