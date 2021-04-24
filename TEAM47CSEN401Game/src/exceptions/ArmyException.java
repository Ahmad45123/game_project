package exceptions;

abstract public class ArmyException extends EmpireException {
    ArmyException() {
        super();
    }

    public ArmyException(String s) {
        super(s);
    }
}
