package sk.stuba.fei.oop.zadanie3.zadanie3.insurence;


import sk.stuba.fei.oop.zadanie3.zadanie3.enums.TerritorialValidity;
import sk.stuba.fei.oop.zadanie3.zadanie3.userdata.User;

import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

public class AccidentIsurance extends Contract {
    private User insured;

    @PositiveOrZero
    private double permanent_consequences_of_the_accident;

    @PositiveOrZero
    private double deathDueOInjury;

    @PositiveOrZero
    private double dailyCompensationForHospitalization;

    private TerritorialValidity territorialValidity;


    public AccidentIsurance(LocalDateTime datum, User insurer, LocalDateTime startdate, LocalDateTime enddate, double sum, double installment, User insured, double permanent_consequences_of_the_accident, double deathDueOInjury, double dailyCompensationForHospitalization, TerritorialValidity territorialValidity) {
        super(datum, insurer, startdate, enddate, sum, installment);
        setDailyCompensationForHospitalization(dailyCompensationForHospitalization);
        setDeathDueOInjury(deathDueOInjury);
        setInsured(insured);
        setTerritorialValidity(territorialValidity);
        setPermanent_consequences_of_the_accident(permanent_consequences_of_the_accident);

    }

    public AccidentIsurance() {
        super();

    }






    public User getInsured() {
        return insured;
    }


    public void  setInsured(User insured) {

       this.insured=insured;
    }

    public double getPermanent_consequences_of_the_accident() {
        return permanent_consequences_of_the_accident;
    }

    public void setPermanent_consequences_of_the_accident(double permanent_consequences_of_the_accident) {
        if(permanent_consequences_of_the_accident ==0){
            throw new IllegalArgumentException("Someting wrong with the value ");
        }
        this.permanent_consequences_of_the_accident = permanent_consequences_of_the_accident;
    }

    public double getDeathDueOInjury() {
        return deathDueOInjury;
    }

    public void setDeathDueOInjury(double deathDueOInjury) {
        if(deathDueOInjury ==0){
            throw new IllegalArgumentException("Someting wrong with the value ");
        }
        this.deathDueOInjury = deathDueOInjury;
    }

    public double getDailyCompensationForHospitalization() {
        return dailyCompensationForHospitalization;
    }

    public void setDailyCompensationForHospitalization(double dailyCompensationForHospitalization) {
        if(dailyCompensationForHospitalization ==0){
            throw new IllegalArgumentException("Someting wrong with the value ");
        }
        this.dailyCompensationForHospitalization = dailyCompensationForHospitalization;
    }

    public TerritorialValidity getTerritorialValidity() {
        return territorialValidity;
    }

    public void setTerritorialValidity(TerritorialValidity territorialValidity) {
        this.territorialValidity = territorialValidity;
    }

    @Override
    public String toString() {
        return "AccidentIsurance{" +
                super.toString()+
                "insured=" + insured +
                ", permanent_consequences_of_the_accident=" + permanent_consequences_of_the_accident +
                ", Death_due_o_injury=" + deathDueOInjury +
                ", Daily_compensation_for_hospitalization=" + dailyCompensationForHospitalization +
                ", territorialValidity=" + territorialValidity +
                '}';
    }
}
