package com.company.shop.dao;

import com.company.shop.drinks.Drink;

import java.io.IOException;
import java.util.List;

public interface GoodsDAO {

    List<Drink> readAllDrinks() throws IOException;

    void writeDrink(Drink drink) throws IOException;

    void rewriteAllDrinks(List<Drink> drinks) throws IOException;

}
