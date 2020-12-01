package sk.stuba.fei.oop.zadanie3.zadanie3.userdata;

import lombok.Data;
import org.springframework.stereotype.Controller;
import sk.stuba.fei.oop.zadanie3.zadanie3.insurence.Contract;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@Data
public class User  {

    private UUID userID = UUID.randomUUID();
    private String id;

    @NotEmpty
    private String forname;

    @NotEmpty
    private String lastname;


    @NotEmpty
    private String birthiD;

    @Email
    private String email;

    @NotNull
    @Valid
    private Address permanentResidence;

    private Address addressForCorrespondence;
    private List<Contract> usercontract=new ArrayList<Contract>();




    public User() {
        setId(userID.toString());
    }













    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id==null || id.trim().isEmpty()){
            throw new IllegalArgumentException("Someting went wrong with creating a uniq ID PLS try again");
        }
        this.id = id;
    }

    public String getForname() {
        return forname;
    }

    public void setForname(String forname) {
        if (forname==null || forname.trim().isEmpty()){
            throw new IllegalArgumentException("Forname is required ");
        }
        this.forname = forname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        if (lastname==null || lastname.trim().isEmpty()){
            throw new IllegalArgumentException("Lastname is required ");
        }
        this.lastname = lastname;
    }

    public String getBirthiD() {
        return birthiD;
    }

    public   void setBirthiD(String birthiD) {
        if (birthiD==null || birthiD.trim().isEmpty()){
            throw new IllegalArgumentException("BirthID is required ");
        }
        this.birthiD = birthiD;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email==null || email.trim().isEmpty()){
            throw new IllegalArgumentException("Email is required ");
        }
        this.email = email;
    }

    public Address getPermanentResidence() {
        return permanentResidence;
    }

    public void setPermanentResidence(Address permanentResidence) {

        this.permanentResidence = permanentResidence;
    }

    public Address getAddressForCorrespondence() {
        return addressForCorrespondence;
    }

    public void setAddressForCorrespondence(Address addressForCorrespondence) {
        this.addressForCorrespondence = addressForCorrespondence;
    }
    public List<Contract> getUsercontract() {
        return usercontract;
    }


    public void setUsercontract(List<Contract> usercontract) {
        this.usercontract = usercontract;
    }

    public void setUsercontract(Contract usercontract) {
        this.usercontract.add(usercontract);
    }
    public void setUsercontract(String id,Contract usercontract) {
       for (int j=0; j< this.usercontract.size();j++){
           if (this.usercontract.get(j).getId().equals(id)){
               this.usercontract.set(j,usercontract);
           }
       }
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", forname='" + forname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birthiD='" + birthiD + '\'' +
                ", email='" + email + '\'' +
                ", permanentResidence=" + permanentResidence +
                ", addressForCorrespondence=" + addressForCorrespondence +
                ",  number of usercontract=" + usercontract.size() +
                '}';
    }
}
