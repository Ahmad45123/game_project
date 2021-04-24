package exceptions;

public class FriendlyCityException extends ArmyException {
    public FriendlyCityException() {
        super();
    }

    FriendlyCityException(String s) {
        super(s);
    }
}
