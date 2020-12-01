package sk.stuba.fei.oop.zadanie3.zadanie3.api;

import org.jetbrains.annotations.NotNull;
import sk.stuba.fei.oop.zadanie3.zadanie3.insurence.AccidentIsurance;
import sk.stuba.fei.oop.zadanie3.zadanie3.insurence.TravelLifeInsurence;
import sk.stuba.fei.oop.zadanie3.zadanie3.userdata.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Userservice extends Api<User> {

    private Map<String, User> usersmap;

    public Userservice() {
        this.usersmap = new HashMap<>();
    }


    @Override
    public User idvalidation(  String id){
        if (usersmap.containsKey(id)){
            return usersmap.get(id);
        }
        throw new IllegalArgumentException("ID not found in the database or it is already in database ");
    }
    @Override
    public User idvalidationadd(@NotNull User user){
        Object[] keys = usersmap.keySet().toArray();
        if (usersmap.containsKey(user.getId())){
            System.out.println("id hiba");
            throw new IllegalArgumentException("ID is already in database ");


        }
        for (int i=0;i<usersmap.size();i++){
            if( usersmap.get(keys[i]).getBirthiD().equals(user.getBirthiD())){
                System.out.println("szuletesi hiba");
                throw new IllegalArgumentException("The birthID is in the database you can not creat user ");
            }
        }

        return user;
    }

    @Override
    public User validation(@NotNull User user, String id){
        if (idvalidation(id).getBirthiD().equals(user.getBirthiD())){
            return idvalidation(id);
        }

        throw new IllegalArgumentException("ID not found in the database or you set a wrong birthID, in validation ");
    }

    @Override
    public void add(User user ) {
        User valid=idvalidationadd(user);
        if (user.getAddressForCorrespondence().getTownship().trim().isEmpty() || user.getAddressForCorrespondence().getNumber().trim().isEmpty() || user.getAddressForCorrespondence().getStreet().trim().isEmpty() || user.getAddressForCorrespondence().getZipcode().trim().isEmpty()){
           user.setAddressForCorrespondence(user.getPermanentResidence());
        }
        this.usersmap.put(valid.getId(), valid);
    }

    @Override
    public void edit( User user,String id) {
        User editable = validation(user, id);
        usersmap.replace(id, editable, user);
        user.setId(editable.getId());
        user.setUsercontract(editable.getUsercontract());

        for (Map.Entry<String,User> entry : usersmap.entrySet()){

        for (int i = 0; i <entry.getValue().getUsercontract().size(); i++) {

            if (entry.getValue().getUsercontract().get(i).getInsurer().getId().equals(user.getId())) {
                user.getUsercontract().get(i).setInsurer(user);
            }
            if (entry.getValue().getUsercontract().get(i) instanceof TravelLifeInsurence && ((TravelLifeInsurence) entry.getValue().getUsercontract().get(i)).getInsured().getId().equals(user.getId())) {

                ((TravelLifeInsurence) entry.getValue().getUsercontract().get(i)).setInsured(user);
            }
            if (entry.getValue().getUsercontract().get(i) instanceof AccidentIsurance &&  ((AccidentIsurance) entry.getValue().getUsercontract().get(i)).getInsured().getId().equals(user.getId())) {
                ((AccidentIsurance) entry.getValue().getUsercontract().get(i)).setInsured(user);
            }
        }


        }
    }


    public List<User> findbyid(String id){
        List<User> user=new ArrayList<>();
        user.add(usersmap.get(id));
        return user;
    }



    @Override
    public Map<String, User> returnAll(){
        return  usersmap;
    }



}
