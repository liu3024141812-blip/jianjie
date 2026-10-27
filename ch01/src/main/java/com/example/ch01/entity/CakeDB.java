package com.example.ch01.entity;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class CakeDB {
    private static Map<String,Cake> cake=new LinkedHashMap<>();
    static {
        cake.put("1",new Cake("1","A蛋糕"));
        cake.put("2",new Cake("2","B蛋糕"));
        cake.put("3",new Cake("3","C蛋糕"));
        cake.put("4",new Cake("4","D蛋糕"));
        cake.put("5",new Cake("5","E蛋糕"));
    }

    public static Collection<Cake> getAll(){
        return  cake.values();
    }
    public static Cake getCake(String id){
        return cake.get(id);
    }
}
