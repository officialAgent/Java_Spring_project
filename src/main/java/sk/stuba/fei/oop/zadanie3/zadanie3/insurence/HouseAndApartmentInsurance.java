package sk.stuba.fei.oop.zadanie3.zadanie3.insurence;


import sk.stuba.fei.oop.zadanie3.zadanie3.enums.PropertyType;
import sk.stuba.fei.oop.zadanie3.zadanie3.userdata.Address;
import sk.stuba.fei.oop.zadanie3.zadanie3.userdata.User;

import java.time.LocalDateTime;

public class HouseAndApartmentInsurance extends NonLifeInsurance {
    private Boolean garage_insurance;

    public HouseAndApartmentInsurance(LocalDateTime datum, User insurer, LocalDateTime startdate, LocalDateTime enddate, double sum, double installment, PropertyType propertyType, double value, Address address, Boolean garage_insurance) {
        super(datum, insurer, startdate, enddate, sum, installment, propertyType, value, address);
        setGarage_insurance(garage_insurance);
    }

    public HouseAndApartmentInsurance() {
        super();

    }



    public Boolean getGarage_insurance() {
        return garage_insurance;
    }

    public void setGarage_insurance(Boolean garage_insurance) {
        this.garage_insurance = garage_insurance;
    }

    @Override
    public String toString() {
        return "HouseAndApartmentInsurance{" +
                super.toString()+
                "garage_insurance=" + garage_insurance +
                '}';
    }
}
