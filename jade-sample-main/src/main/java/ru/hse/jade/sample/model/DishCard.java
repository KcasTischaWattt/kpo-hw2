package ru.hse.jade.sample.model;

import java.util.ArrayList;
import java.util.List;

public class DishCard {
    private int cardId;
    private String dishName;
    private String descr;
    private double time;
    private List<Operation> operations = new ArrayList<>();

    public List<Operation> getOperations() {
        return operations;
    }

    public int getCardId() {
        return cardId;
    }


    public String getDescr() {
        return descr;
    }


    public String getDishName() {
        return dishName;
    }


    public double getTime() {
        return time;
    }
}
