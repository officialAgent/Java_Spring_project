package sk.stuba.fei.oop.zadanie3.zadanie3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import sk.stuba.fei.oop.zadanie3.zadanie3.api.Api;
import sk.stuba.fei.oop.zadanie3.zadanie3.api.Contractservice;
import sk.stuba.fei.oop.zadanie3.zadanie3.enums.PropertyType;
import sk.stuba.fei.oop.zadanie3.zadanie3.enums.PurposeOfTheTrip;
import sk.stuba.fei.oop.zadanie3.zadanie3.enums.TerritorialValidity;
import sk.stuba.fei.oop.zadanie3.zadanie3.insurence.*;
import sk.stuba.fei.oop.zadanie3.zadanie3.userdata.User;
import sk.stuba.fei.oop.zadanie3.zadanie3.api.Userservice;

import javax.validation.Valid;

@Component
@Slf4j
@Controller
@SpringBootApplication
public class Zadanie3Application {
    Api<User> users = new Userservice();
    Api<Contract> cotracts = new Contractservice();


    public static void main(String[] args) {
        SpringApplication.run(Zadanie3Application.class, args);


    }



    @GetMapping("/")
    public String findAll(Model model){

        model.addAttribute("user",users.returnAll() );
        return "index";
    }

    @GetMapping("/add")
    public String addUser(Model model){
        model.addAttribute("user", new User());

        return "user/adduser";

    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute @Valid User user,BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("user",user);
            return "user/adduser";
        }
        try {
            users.add(user);
        }
        catch (IllegalArgumentException e){
            return "user/adduser";
        }
        model.addAttribute("user",user);
        return "user/submitted";
    }

    @GetMapping("/user/id/info/{id}")
    public String byId(@PathVariable String id, Model model) {
      model.addAttribute("user",users.findbyid(id).get(0));
        return "user/one";
    }

    @GetMapping("/user/id/{id}")
    public String editbyid(@PathVariable String id, Model model) {
        model.addAttribute("user",users.findbyid(id).get(0));
        return "user/edituser";
    }
    @PostMapping("/edit/{id}")
    public String edituser(@PathVariable String id,@ModelAttribute @Valid User user,BindingResult bindingResult ,Model model){
        if (bindingResult.hasErrors()) {
            return "user/edituser";
        }
        try {
            users.edit(user,id);
        }
        catch (IllegalArgumentException e){
           bindingResult.rejectValue("birthiD","user","birthiD can not be changed");
            return "user/edituser";
        }
        model.addAttribute("user",user);
        return "user/submitted";

    }


    /*


    User part up




    Contract part down


     */
    @GetMapping("/add/contract/travel/{id}")
    public String addTravel(@PathVariable String id,Model model){
        model.addAttribute("user",users.findbyid(id).get(0));
        model.addAttribute("travelLifeInsurence",new TravelLifeInsurence());
        model.addAttribute("triptype", PurposeOfTheTrip.values());
        model.addAttribute("insured",users.returnAll());
        //return "/user/id/info/"+ id;
        return "contract/add/travelcontract";
    }


    @PostMapping("/submitcontract/{id}")
    public String submit(@PathVariable String id,@ModelAttribute @Valid TravelLifeInsurence travelLifeInsurence,BindingResult bindingResult,Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("travelLifeInsurence", travelLifeInsurence);
            model.addAttribute("user",users.findbyid(id).get(0));
            model.addAttribute("triptype", PurposeOfTheTrip.values());
            model.addAttribute("insured",users.returnAll());
            return "contract/add/travelcontract";
        }

        travelLifeInsurence.setInsured(users.findbyid(travelLifeInsurence.getInsured().getId()).get(0));
        travelLifeInsurence.setInsurer(users.findbyid(id).get(0));
        travelLifeInsurence.setDatum(java.time.LocalDateTime.now());
        cotracts.add(travelLifeInsurence);
        return "contract/submittedcontract";
    }



    @GetMapping("/add/contract/accidnet/{id}")
    public String addacident(@PathVariable String id,Model model){
        model.addAttribute("user",users.findbyid(id).get(0));
        model.addAttribute("accidentIsurance",new AccidentIsurance());
        model.addAttribute("triptype", TerritorialValidity.values());
        model.addAttribute("insured",users.returnAll());
        //return "/user/id/info/"+ id;
        return "contract/add/accidnetcontract";
    }

    @PostMapping("/submitcontract/acidental/{id}")
    public String submitacc(@PathVariable String id, @ModelAttribute @Valid AccidentIsurance accidentIsurance, BindingResult bindingResult,Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("user",users.findbyid(id).get(0));
            model.addAttribute("accidentIsurance",accidentIsurance);
            model.addAttribute("triptype", TerritorialValidity.values());
            model.addAttribute("insured",users.returnAll());
            return "contract/add/accidnetcontract";
        }
        accidentIsurance.setInsured(users.findbyid(accidentIsurance.getInsured().getId()).get(0));
        accidentIsurance.setInsurer(users.findbyid(id).get(0));
        accidentIsurance.setDatum(java.time.LocalDateTime.now());
        cotracts.add(accidentIsurance);
        return "contract/submittedcontract";
    }




    @GetMapping("/add/contract/household/{id}")
    public String addhosehold(@PathVariable String id,Model model){
        model.addAttribute("user",users.findbyid(id).get(0));
        model.addAttribute("householdInsurence",new HouseholdInsurence());
        model.addAttribute("triptype", PropertyType.values());
        return "contract/add/householdcontract";
    }

    @PostMapping("/submitcontract/household/{id}")
    public String submithouse(@PathVariable String id,@ModelAttribute @Valid HouseholdInsurence householdInsurence,BindingResult bindingResult,Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("user",users.findbyid(id).get(0));
            model.addAttribute("householdInsurence",householdInsurence);
            model.addAttribute("triptype", PropertyType.values());
            return "contract/add/householdcontract";}
        householdInsurence.setInsurer(users.findbyid(id).get(0));
        householdInsurence.setDatum(java.time.LocalDateTime.now());
        cotracts.add(householdInsurence);
        return "contract/submittedcontract";
    }




    @GetMapping("/add/contract/householdapp/{id}")
    public String addhoseholdadnapp(@PathVariable String id,Model model){
        model.addAttribute("user",users.findbyid(id).get(0));
        model.addAttribute("houseAndApartmentInsurance",new HouseAndApartmentInsurance());
        model.addAttribute("triptype", PropertyType.values());
        return "contract/add/householdappcontract";
    }

    @PostMapping("/submitcontract/householdapp/{id}")
    public String submithouseandapp(@PathVariable String id,@ModelAttribute HouseAndApartmentInsurance houseAndApartmentInsurance,BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("user",users.findbyid(id).get(0));
            model.addAttribute("houseAndApartmentInsurance",houseAndApartmentInsurance);
            model.addAttribute("triptype", PropertyType.values());
            return "contract/add/householdappcontract";
        }
        houseAndApartmentInsurance.setInsurer(users.findbyid(id).get(0));
        houseAndApartmentInsurance.setDatum(java.time.LocalDateTime.now());
        cotracts.add(houseAndApartmentInsurance);
        return "contract/submittedcontract";
    }




    @GetMapping("/contract/id/info/{id}")
    public String contractinfo(@PathVariable String id, Model model){
        Contract contract=cotracts.findbyid(id).get(0);

        if (contract instanceof TravelLifeInsurence){
            model.addAttribute("contract", contract);
            return "contract/info/travelinfo"; }
        else if (contract instanceof HouseAndApartmentInsurance){
            model.addAttribute("contract",contract);
            return "contract/info/houseandapinfo"; }
        else if (contract instanceof HouseholdInsurence){
            model.addAttribute("contract",contract);
            return "contract/info/householdinfo"; }
        else if (contract instanceof AccidentIsurance){
            model.addAttribute("contract",contract);
            return "contract/info/accidnetinfo"; }
        else { throw new IllegalArgumentException("Get mapping error ");}

    }

    @GetMapping("/contract/id/edit/{id}")
    public String contractedit(@PathVariable String id, Model model){
        Contract contract=cotracts.findbyid(id).get(0);
        model.addAttribute("user",users.findbyid(contract.getInsurer().getId()).get(0));
        if (contract instanceof TravelLifeInsurence){
            model.addAttribute("travelLifeInsurence", contract);
            model.addAttribute("triptype", PurposeOfTheTrip.values());
            model.addAttribute("insured",users.returnAll());
            return "contract/editcontract/travelcontract"; }
        else if (contract instanceof HouseAndApartmentInsurance){
            model.addAttribute("houseAndApartmentInsurance",contract);
            model.addAttribute("triptype", PropertyType.values());
            return "contract/editcontract/householdappcontract"; }
        else if (contract instanceof HouseholdInsurence){
            model.addAttribute("householdInsurence",contract);
            model.addAttribute("triptype", PropertyType.values());
            return "contract/editcontract/householdcontract"; }
        else if (contract instanceof AccidentIsurance){
            model.addAttribute("accidentIsurance",contract);
            model.addAttribute("triptype", TerritorialValidity.values());
            model.addAttribute("insured",users.returnAll());
            return "contract/editcontract/accidnetcontract"; }
        else { throw new IllegalArgumentException("Get mapping error ");}

    }
    @PostMapping("/submitcontract/acidentaledited/{id}/{user}")
    public String submitaccedited(@PathVariable String user,@PathVariable String id,@ModelAttribute  @Valid AccidentIsurance accidentIsurance,BindingResult bindingResult,Model model){
        accidentIsurance.setInsurer(users.findbyid(user).get(0));
        accidentIsurance.setInsured(users.findbyid(accidentIsurance.getInsured().getId()).get(0));
        if (bindingResult.hasErrors()) {
            model.addAttribute("user",users.findbyid(user).get(0));
            accidentIsurance.setId(id);
            model.addAttribute("accidentIsurance",accidentIsurance);
            model.addAttribute("triptype", TerritorialValidity.values());
            model.addAttribute("insured",users.returnAll());
            return "contract/editcontract/accidnetcontract";
        }


        cotracts.edit(accidentIsurance,id);
        return "contract/submittedcontract";
    }

    @PostMapping("/submitcontract/traveledited/{id}/{user}")
    public String submittraveledited(@PathVariable String user,@PathVariable String id,@ModelAttribute @Valid TravelLifeInsurence travelLifeInsurence,BindingResult bindingResult, Model model){
        travelLifeInsurence.setInsurer(users.findbyid(user).get(0));
        travelLifeInsurence.setInsured(users.findbyid(travelLifeInsurence.getInsured().getId()).get(0));
        if (bindingResult.hasErrors()){
            travelLifeInsurence.setId(id);
            model.addAttribute("travelLifeInsurence", travelLifeInsurence);
            model.addAttribute("user",users.findbyid(travelLifeInsurence.getInsurer().getId()).get(0));
            model.addAttribute("triptype", PurposeOfTheTrip.values());
            model.addAttribute("insured",users.returnAll());
            return "contract/editcontract/travelcontract";
        }


        cotracts.edit(travelLifeInsurence,id);
        return "contract/submittedcontract";
    }

    @PostMapping("/submitcontract/householdedited/{id}/{user}")
    public String submitaccedited(@PathVariable String user,@PathVariable String id,@ModelAttribute @Valid HouseholdInsurence householdInsurence,BindingResult bindingResult,Model model) {
        householdInsurence.setInsurer(users.findbyid(user).get(0));
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", users.findbyid(householdInsurence.getInsurer().getId()).get(0));
            householdInsurence.setId(id);
            model.addAttribute("householdInsurence", householdInsurence);
            model.addAttribute("triptype", PropertyType.values());
            model.addAttribute("insured", users.returnAll());
            return "contract/editcontract//householdcontract";
        }


        cotracts.edit(householdInsurence,id);
        return "contract/submittedcontract";
    }

    @PostMapping("/submitcontract/householdapp/{id}/{user}")
    public String submitaccedited(@PathVariable String user,@PathVariable String id,@ModelAttribute @Valid HouseAndApartmentInsurance houseAndApartmentInsurance,BindingResult bindingResult, Model model){
        houseAndApartmentInsurance.setInsurer(users.findbyid(user).get(0));
        if (bindingResult.hasErrors()) {
            model.addAttribute("user",users.findbyid(houseAndApartmentInsurance.getInsurer().getId()).get(0));
            houseAndApartmentInsurance.setId(id);
            model.addAttribute("houseAndApartmentInsurance",houseAndApartmentInsurance);
            model.addAttribute("triptype", PropertyType.values());
            return "contract/editcontract/householdappcontract";
        }

        cotracts.edit(houseAndApartmentInsurance,id);

        return "contract/submittedcontract";
    }



}

