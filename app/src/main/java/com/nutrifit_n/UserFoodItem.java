package com.nutrifit_n;

public class UserFoodItem {
    public String foodName, mealType;
    public float servingSize;
    public double energy, protein, fat, carbohydrate;
    public double sugar, fiber, calcium, iron, sodium;
    public double vitaminA, vitaminC;


    public UserFoodItem() {}

    public UserFoodItem(String foodName, float servingSize) {
        this.foodName = foodName;
        this.servingSize = servingSize;
    }
}