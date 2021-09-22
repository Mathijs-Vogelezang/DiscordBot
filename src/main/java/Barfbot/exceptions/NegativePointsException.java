package Barfbot.exceptions;

public class NegativePointsException extends InvalidPointsException{
    public NegativePointsException () {
        super("Could not decrement score, otherwise points will be negative");
    }

    public NegativePointsException(String reason) {
        super(reason);
    }
}
