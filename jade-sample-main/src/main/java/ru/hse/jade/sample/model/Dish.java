package ru.hse.jade.sample.model;

public class Dish {
    private int dishId;
    private int cardId;
    private double price;
    private boolean active;

    public int getDishId() {
        return dishId;
    }
    public int getCardId() {
        return cardId;
    }
    public double getPrice() {
        return price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
