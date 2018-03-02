package com.company.shop.dao;

import com.company.shop.drinks.Alcohol;
import com.company.shop.drinks.Drink;
import com.company.shop.drinks.Soft;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GoodsDAOImpl implements GoodsDAO {

    private File file;
    private static GoodsDAOImpl productDAO;

    private GoodsDAOImpl(File file) {
        this.file = file;
    }

    @Override
    public List<Drink> readAllDrinks() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<String> drinks = new ArrayList<>();
        String s;

//Читаем построчно и добовляем строки в List

        while ((s = reader.readLine()) != null) {
            drinks.add(s);
        }
        reader.close();
        return parseDrinks(drinks);
    }

//Парсим напитки в обьекты и возвращаем List обьектов

    private List<Drink> parseDrinks(List<String> drinks) {
        Iterator<String> drinksIterator = drinks.iterator();
        List<Drink> drinkList = new ArrayList<>(drinks.size());

       //Использую итератор.
        while (drinksIterator.hasNext()) {
        //Разбил строку по запятым.
            String[] components = drinksIterator.next().split(",");
            if (components[components.length - 2].endsWith("%")) {
                drinkList.add(builAlcoholicDring(components));
            } else {
                drinkList.add(buildSoftDrink(components));
            }
        }
        return drinkList;
    }

    private Alcohol builAlcoholicDring(String[] components) {
        Alcohol drink = new Alcohol();
        drink.setName(components[0].replace("\"", "").trim());
        drink.setPurchasePrice(new Double(components[1].trim()));
        drink.setClassification(components[2].replace("\"", "").trim());
        drink.setVolume(new Double(components[3].trim()));
        drink.setGrade(new Double(components[4].trim().replace("%", "")));
        drink.setQuantity(new Integer(components[5].trim()));
        System.out.println(drink);
        return drink;
    }

    private Soft buildSoftDrink(String[] components) {
        Soft drink = new Soft();
        drink.setName(components[0].replace("\"", "").trim());
        drink.setPurchasePrice(new Double(components[1].trim()));
        drink.setGroup(components[2].replace("\"", "").trim());
        drink.setVolume(new Double(components[3].trim()));
        int i = 4;
        List<String> composition = new ArrayList<>();
        while (!components[i].endsWith("\"")) {
            composition.add(components[i].replace("\"", "").trim());
            i++;
        }
        composition.add(components[i].replace("\"", "").trim());
        drink.setComposition(composition);
        drink.setQuantity(new Integer(components[i + 1].trim()));
        System.out.println(drink);
        return drink;
    }

    @Override
    public void writeDrink(Drink drink) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(drink + "/n");
        writer.close();
    }

    @Override
    public void rewriteAllDrinks(List<Drink> drinks) throws IOException {
        this.file.delete();
        this.file.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        Iterator<Drink> iterator = drinks.iterator();
        while (iterator.hasNext()) {
            writer.write(iterator.next().toString() + "\n");
        }
        writer.close();
    }

    public synchronized static GoodsDAO getInctance(File f) {
        if (productDAO == null) {
            productDAO = new GoodsDAOImpl(f);
        }
        return productDAO;
    }

}
