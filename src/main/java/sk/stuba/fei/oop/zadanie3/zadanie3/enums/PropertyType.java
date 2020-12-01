package sk.stuba.fei.oop.zadanie3.zadanie3.enums;

public enum PropertyType {
    HOUSE("House"),
    FAMILYHOUSEBRICK("Family House - brick"),
    FAMILYHOUSEWOOD("Family House - wood");

    private final String properity;

    PropertyType(String properity) {
        this.properity = properity;
    }

    public String getProperity() {
        return properity;
    }
}
