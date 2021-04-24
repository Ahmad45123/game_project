package exceptions;

public class MaxRecruitedException extends BuildingException {
    public MaxRecruitedException() {
        super();
    }

    MaxRecruitedException(String s) {
        super(s);
    }
}
