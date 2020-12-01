package sk.stuba.fei.oop.zadanie3.zadanie3.insurence;

import org.jetbrains.annotations.NotNull;
import sk.stuba.fei.oop.zadanie3.zadanie3.enums.PropertyType;
import sk.stuba.fei.oop.zadanie3.zadanie3.userdata.Address;
import sk.stuba.fei.oop.zadanie3.zadanie3.userdata.User;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

public abstract class NonLifeInsurance extends Contract {


    private PropertyType propertyType;

    @Positive
    private double value;

    @NotNull
    @Valid
    private Address address;

    public NonLifeInsurance(LocalDateTime datum, User insurer, LocalDateTime startdate, LocalDateTime enddate, double sum, double installment, PropertyType propertyType, double value, Address address) {
        super(datum, insurer, startdate, enddate, sum, installment);
        setPropertyType(propertyType);
        setValue(value);
        setAddress(address);
    }

    public NonLifeInsurance() {
        super();

    }


    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        if(value ==0){
            throw new IllegalArgumentException("Someting wrong with the value ");
        }
        this.value = value;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "NonLifeInsurance{" +
                super.toString()+
                "propertyType=" + propertyType +
                ", value=" + value +
                ", address=" +address.toString()+
                '}';
    }
}
