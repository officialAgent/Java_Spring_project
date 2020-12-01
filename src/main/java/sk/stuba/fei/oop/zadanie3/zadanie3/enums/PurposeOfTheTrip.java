package sk.stuba.fei.oop.zadanie3.zadanie3.enums;

public enum PurposeOfTheTrip {
    WORK("Work"),
    RECREATION("Recreation"),
    SPORTS("Sports"),
    SCHOOLHOLIDAY("Schoolholiday");


    private final String purpose;

    PurposeOfTheTrip(String purpose) {
        this.purpose = purpose;
    }

    public String getPurpose() {
        return purpose;
    }
}
