package sk.stuba.fei.oop.zadanie3.zadanie3.insurence;

import lombok.Data;
import org.springframework.stereotype.Controller;
import sk.stuba.fei.oop.zadanie3.zadanie3.userdata.User;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.UUID;

@Controller
@Data
public abstract class Contract {

    private UUID contractID = UUID.randomUUID();
    private String id;
    private LocalDateTime datum;
    private User insurer;

    @FutureOrPresent
    @NotNull
    private LocalDateTime startdate;


    @NotNull
    @Future
    private LocalDateTime enddate;

    @Positive
    private double sum;

    @Positive
    private double installment;



    public Contract(LocalDateTime datum, User insurer, LocalDateTime startdate,LocalDateTime enddate, double sum, double installment) {
        setId(contractID.toString());
        setDatum(datum);
        setInsurer(insurer);
        setStartdate(startdate);
        setEnddate(enddate);
        setSum(sum);
        setInstallment(installment);
    }

    public Contract() {
        setId(contractID.toString());
    }





    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id==null || id.trim().isEmpty()){
            throw new IllegalArgumentException("Contract is ID can not be set try it again ");
        }
        this.id = id;
    }


    public LocalDateTime getDatum() {
        return datum;
    }

   public void setDatum(LocalDateTime datum) {
        if (datum==null ){
            throw new IllegalArgumentException("Contract Date  is required ");
        }
        this.datum = datum;
    }

    public User getInsurer() {
        return insurer;
    }

    public void setInsurer(User insurer) {
        if (insurer==null ){
            throw new IllegalArgumentException("Contract Insurer can not be set ");
        }
            this.insurer=insurer;
    }

    public LocalDateTime getStartdate() {
        return startdate;
    }

    public void setStartdate(LocalDateTime startdate) {
        if (startdate==null ){
            throw new IllegalArgumentException("Contract Start Date  is required ");
        }
        this.startdate = startdate;
    }

    public LocalDateTime getEnddate() {
        return enddate;
    }

    public void setEnddate(LocalDateTime enddate) {
        if (enddate==null ){
            throw new IllegalArgumentException("Contract End Date  is required ");
        }
        this.enddate = enddate;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        if(sum ==0){
            throw new IllegalArgumentException("Someting wrong with the value ");
        }
        this.sum = sum;
    }

    public double getInstallment() {
        return installment;
    }

    public void setInstallment(double installment) {
        if(installment ==0){
            throw new IllegalArgumentException("Someting wrong with the value ");
        }
        this.installment = installment;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id='" + id + '\'' +
                ", datum=" + datum +
                ", insurer=" + insurer +
                ", startdate=" + startdate +
                ", enddate=" + enddate +
                ", sum=" + sum +
                ", installment=" + installment +
                '}';
    }
}
