package sk.stuba.fei.oop.zadanie3.zadanie3.insurence;

import sk.stuba.fei.oop.zadanie3.zadanie3.enums.PropertyType;
import sk.stuba.fei.oop.zadanie3.zadanie3.userdata.Address;
import sk.stuba.fei.oop.zadanie3.zadanie3.userdata.User;

import java.time.LocalDateTime;

public class HouseholdInsurence extends NonLifeInsurance {
    private double valueHoushold;

    public HouseholdInsurence(LocalDateTime datum, User insurer, LocalDateTime startdate, LocalDateTime enddate, double sum, double installment, PropertyType propertyType, double value, Address address, double valueHoushold) {
        super( datum, insurer, startdate, enddate, sum, installment, propertyType, value, address);
        setValueHoushold(valueHoushold);
    }


    public HouseholdInsurence() {
        super();

    }




    public double getValueHoushold() {
        return valueHoushold;
    }

    public void setValueHoushold(double valueHoushold) {
        if(valueHoushold==0){
            throw new IllegalArgumentException("Someting wrong with the value ");
        }
        this.valueHoushold = valueHoushold;
    }

    @Override
    public String toString() {
        return "HouseholdInsurence{" +
                "valueHoushold=" + valueHoushold +
                '}';
    }
}

