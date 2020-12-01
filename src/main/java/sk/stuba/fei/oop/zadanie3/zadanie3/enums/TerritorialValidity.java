package sk.stuba.fei.oop.zadanie3.zadanie3.enums;

public enum TerritorialValidity {
    SLOVAKIA("Slovakia"),
    WORLD("World"),
    WORLDPLUSSLOVAKIA("World + Slovakia");

    private final String place;

    TerritorialValidity(String place) {
        this.place = place;
    }

    public String getPlace() {
        return place;
    }
}
