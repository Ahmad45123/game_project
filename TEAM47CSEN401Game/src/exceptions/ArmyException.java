package exceptions;

abstract public class ArmyException extends EmpireException {
    ArmyException() {
        super();
    }

    ArmyException(String s) {
        super(s);
    }
}
