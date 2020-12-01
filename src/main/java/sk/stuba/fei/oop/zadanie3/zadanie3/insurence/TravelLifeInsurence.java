package sk.stuba.fei.oop.zadanie3.zadanie3.insurence;

import sk.stuba.fei.oop.zadanie3.zadanie3.enums.PurposeOfTheTrip;
import sk.stuba.fei.oop.zadanie3.zadanie3.userdata.User;

import java.time.LocalDateTime;

public class TravelLifeInsurence extends Contract{
    private User insured;
    private boolean withinTheEU;
    private PurposeOfTheTrip triptype;

    public TravelLifeInsurence(LocalDateTime datum, User insurer, LocalDateTime startdate, LocalDateTime enddate, double sum, double installment, User insured, boolean withinTheEU, PurposeOfTheTrip triptype) {
        super( datum, insurer, startdate, enddate, sum, installment);
        setInsured(insured);
        setWithinTheEU(withinTheEU);
        setTriptype(triptype);
    }

    public TravelLifeInsurence() {
        super();
    }




    public User getInsured() {
        return insured;
    }

    public void setInsured(User insured) {
        this.insured = insured;
    }

    public boolean isWithinTheEU() {
        return withinTheEU;
    }

    public void setWithinTheEU(boolean withinTheEU) {

        this.withinTheEU = withinTheEU;
    }

    public PurposeOfTheTrip getTriptype() {
        return triptype;
    }

    public void setTriptype(PurposeOfTheTrip triptype) {
        this.triptype = triptype;
    }


    @Override
    public String toString() {
        return "TravelLifeInsurence{" +
                super.toString()+
                "insured=" + insured +
                ", withinTheEU=" + withinTheEU +
                ", triptype=" + triptype +
                '}';
    }
}
