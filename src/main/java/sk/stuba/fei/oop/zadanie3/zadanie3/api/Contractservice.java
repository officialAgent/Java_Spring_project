package sk.stuba.fei.oop.zadanie3.zadanie3.api;

import org.jetbrains.annotations.NotNull;
import sk.stuba.fei.oop.zadanie3.zadanie3.insurence.*;

import java.util.*;

public class Contractservice extends Api<Contract> {
    private Map<String, Contract> contractmap;


    public Contractservice() {
        this.contractmap =  this.contractmap = new HashMap<>();
    }
    @Override
    public Contract idvalidation(String id){
        if (contractmap.containsKey(id)){
            return contractmap.get(id) ;
        }
        throw new IllegalArgumentException("ID not found in the database or it is already in database ");
    }


    @Override
    public Contract idvalidationadd(@NotNull Contract contract){
        if (contractmap.containsKey(contract.getId())){
            throw new IllegalArgumentException("ID not found in the database or it is already in database ");
        }
        return contract;
    }


    @Override
    public Contract validation(@NotNull Contract contract, String id ){
        if (idvalidation(id) !=null ){
            return idvalidation(id);
        }
        throw new IllegalArgumentException("ID not found in the database or you set a wrong contract date ");
    }


    @Override
    public void add( @NotNull Contract contract){
        Contract valid=idvalidationadd(contract);
        contractmap.put(valid.getId(),valid);
        contract.getInsurer().setUsercontract(contract);


    }

    @Override
    public void edit(@NotNull Contract contract,String id){
        if(contract instanceof AccidentIsurance &&  contractmap.get(id) instanceof AccidentIsurance){updatecontract(contract,id);}
       else if(contract instanceof HouseholdInsurence && contractmap.get(id) instanceof HouseholdInsurence){updatecontract(contract,id);}
       else if(contract instanceof HouseAndApartmentInsurance && contractmap.get(id) instanceof HouseAndApartmentInsurance){updatecontract(contract,id);}
       else if(contract instanceof TravelLifeInsurence && contractmap.get(id) instanceof TravelLifeInsurence){updatecontract(contract,id);}
        else { throw new IllegalArgumentException("U want to edit a wrong contract type, the problem will be with the ID or with the contract type ");}
    }


    public List<Contract> findbyid (String id){
        /*
        Object[] keys = contractmap.keySet().toArray();
        List<Contract> usercontracts=new ArrayList<>();
        for (int i=0;i<contractmap.size();i++){
         if(  contractmap.get(keys[i]).getInsurer().getId().equals(id)){
             Contract contract=contractmap.get(contractmap.get(keys[i]).getId());
             usercontracts.add(contract);
         }
         return usercontracts;
        }*/
        List<Contract> usercontracts=new ArrayList<>();
        Contract auto=contractmap.get(id);
        usercontracts.add(auto);
        return usercontracts;
    }
    public Map<String, Contract> returnAll(){

        return contractmap;
    }

    private Contract findc(String id){
      return  contractmap.get(id);
    }


    private void updatecontract(Contract contract,String id){
        Contract editabel=validation(contract,id);
        contract.setDatum(editabel.getDatum());
        contractmap.replace(editabel.getId(),editabel,contract);
        contract.getInsurer().setUsercontract(contract.getId(),contract);
    }
}
