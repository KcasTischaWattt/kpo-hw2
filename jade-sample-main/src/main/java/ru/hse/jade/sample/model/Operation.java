package ru.hse.jade.sample.model;

import java.util.HashMap;
import java.util.Map;

public class Operation {
    private int operType;
    private int equipType;
    private double time;
    private Map<Integer, Double> products = new HashMap<>();

    public int getEquipType() {
        return equipType;
    }

    public double getTime() {
        return time;
    }

    public int getOperType() {
        return operType;
    }

    public Map<Integer, Double> getProducts() {
        return products;
    }
}
