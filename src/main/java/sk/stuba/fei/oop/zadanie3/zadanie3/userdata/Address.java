package sk.stuba.fei.oop.zadanie3.zadanie3.userdata;

import lombok.Data;

import javax.validation.constraints.NotEmpty;



@Data
public class Address {


    @NotEmpty
    private String township;


    @NotEmpty
    private String zipcode;


    @NotEmpty
    private String street;


    @NotEmpty
    private String number;


    public Address(String township, String zipcode, String street, String number) {
       setTownship(township);
       setNumber(number);
       setStreet(street);
       setZipcode(zipcode);
    }

    public Address() {

    }

    public String getTownship() {
        return township;
    }

    public void setTownship(String township) {

        this.township = township;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {

        this.zipcode = zipcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {

        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {

        this.number = number;
    }





    @Override
    public String toString() {
        return "Address{" +
                "Township='" + township + '\'' +
                ", Zipcode='" + zipcode + '\'' +
                ", Street='" + street + '\'' +
                ", Number='" + number + '\'' +
                '}';
    }
}