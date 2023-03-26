package ru.hse.jade.sample.model;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<Dish> dishes = new ArrayList<>();
    private List<DishCard> cards = new ArrayList<>();
    public Dish getDishByDishId(int dishId) {
        for(var dish : dishes) {
            if (dishId == dish.getDishId()) {
                return dish;
            }
        }
        return null;
    }
    public DishCard getDishCardByDishId(int dishId) {
        for(var dish : dishes) {
            if (dishId == dish.getDishId()) {
                for (var card : cards) {
                    if (dish.getCardId() == card.getCardId()) {
                        return card;
                    }
                }
            }
        }
        return null;
    }
}
