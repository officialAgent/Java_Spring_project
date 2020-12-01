package sk.stuba.fei.oop.zadanie3.zadanie3.api;

import java.util.List;
import java.util.Map;


public abstract class Api<T> {

    public abstract void add(T object);

    public abstract Map<String, T> returnAll();

    public abstract List<T>  findbyid(String id);

    public abstract void edit(T object,String id);

    public abstract T validation(T object,String id);

    public  abstract T  idvalidation(String id);

    public  abstract T  idvalidationadd(T obejct);


}
