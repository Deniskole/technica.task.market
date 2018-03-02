package com.company.shop;


import com.company.shop.dao.DAOFactory;
import com.company.shop.drinks.Drink;
import com.company.shop.drinks.DrinkInfo;

import java.io.File;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.*;

public class Shop {

    private List<Drink> drinks;
    private LocalDateTime date;
    private Map<String, DrinkInfo> drinkSoldsInfo;


    public static void main(String[] args) throws IOException {
        Shop shop = new Shop();
        shop.beginWork();
    }

    public Shop() throws IOException {
        this.drinks = DAOFactory.getProductDao(new File(System.getProperty("user.dir") + "\\data.csv")).readAllDrinks();
        this.date = LocalDateTime.of(2017, 9, 22, 8, 0, 0, 0);
        drinkSoldsInfo = new HashMap<String, DrinkInfo>();
    }

    public void beginWork() {
        System.out.println("Начало работы");
        for (int i = 0; i < 30; i++) {
            System.out.println("\n\n\nНачало " + (i + 1) + " дня");
            emulateDay();
            System.out.println("Конец " + (i + 1) + " дня");
        }

        makeReport();

        try {
            DAOFactory.getProductDao(new File(System.getProperty("user.dir") + "\\drink.csv")).rewriteAllDrinks(this.drinks);
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл!");
        }
    }

    private void emulateDay() {
        for (int i = 8; i < 21; i++) {
            System.out.println("\n-----------------------" + " Время: " + (i + 1) + ":00 ----------------------");
            emulateHour();
        }
        this.date.plusHours(11);
        rebuyProducts();
    }

    private void emulateHour() {
        int buysCount = new Random().nextInt(11);
        System.out.println("Купили " + buysCount + " товар(ов)");
        for (int i = 0; i < buysCount; i++) {
            System.out.print("Покупка #" + (i + 1) + " ");
            emulateBuy();
        }

        this.date.plusHours(1);
    }

    private void emulateBuy() {
        Drink drink = this.drinks.get(new Random().nextInt(drinks.size()));
        int quantity = new Random().nextInt(10) + 1;

        if (drink.getQuantity() >= quantity) {
            drink.setQuantity(drink.getQuantity() - quantity);
            double price = calculatePrice(drink, quantity);

            DrinkInfo info = this.drinkSoldsInfo.get(drink.getName());
            if (info == null) {
                info = new DrinkInfo(drink.getName(), quantity, 0, price, drink.getPurchasePrice());
            } else {
                info.setSoldQuantity(info.getSoldQuantity() + quantity);
                info.setPriceSum(info.getPriceSum() + price);
            }

            this.drinkSoldsInfo.put(drink.getName(), info);
            System.out.println("\'" + drink.getName() + "\' купили кол-во " + quantity + " стоимость " + String.format("%.2f", price));
        } else {
            System.out.println("\'" + drink.getName() + "\' нет в наличии " + quantity);
        }
    }

    private double calculatePrice(Drink drink, int quantity) {
        double initialPrice = drink.getPurchasePrice();
        if (date.getDayOfWeek() == DayOfWeek.SUNDAY || date.getDayOfWeek() == DayOfWeek.SATURDAY)
            return (initialPrice * quantity) * 1.15;
        else if (date.getHour() >= 18 && date.getHour() <= 20)
            return (initialPrice * quantity) * 1.08;
        else if (quantity > 2)
            return (initialPrice * (quantity - 2)) * 1.07 + ((initialPrice * 2) * 1.10);
        else
            return (initialPrice * quantity) * 1.10;
    }


    private void rebuyProducts() {
        line();
        System.out.println("Закупка товара");
        Iterator<Drink> drinkIterator = this.drinks.iterator();
        while (drinkIterator.hasNext()) {
            Drink d = drinkIterator.next();
            if (d.getQuantity() < 10) {
                d.setQuantity(d.getQuantity() + 150);
                DrinkInfo info = this.drinkSoldsInfo.get(d.getName());
                if (info == null) {
                    info = new DrinkInfo(d.getName(), 0, 150, 0.0, d.getPurchasePrice());
                } else {
                    info.setRebuyQuantity(info.getRebuyQuantity() + 150);
                }
                this.drinkSoldsInfo.put(d.getName(), info);
                System.out.println("Товар \"" + d.getName() + " закуплено");
            }
        }
        line();
    }

    private void makeReport() {
        line();
        Iterator<DrinkInfo> iter = this.drinkSoldsInfo.values().iterator();
        Double soldsSum = 0.0, drinkCost = 0.0, rebuySum = 0.0;
        while (iter.hasNext()) {
            DrinkInfo info = iter.next();
            System.out.println("Информация о товаре \'" + info.getName() + "\' было.");
            System.out.println("\t\tПродано: " + info.getSoldQuantity() + "\t\t\t\t\tЗакуплено: " + info.getRebuyQuantity());
            System.out.println("\t\tПри закупочной цене + " + info.getPurchasePrice() + "\t\tПрибиль от продаж: " + String.format("%.2f", info.getPriceSum()));
            soldsSum += info.getPriceSum();
            drinkCost += info.getSoldQuantity() * info.getPurchasePrice();
            rebuySum += info.getRebuyQuantity() * info.getPurchasePrice();
        }
        System.out.println();
        System.out.println("Прибыль магазина: " + String.format("%.2f", soldsSum) + " - " + String.format("%.2f", drinkCost) +
                " = " + String.format("%.2f", (soldsSum - drinkCost)));
        System.out.println("Завтраты на закупку: " + String.format("%.2f", rebuySum));
        line();
    }


    public void line() {
        System.out.println("------------------------------------------------------------");
    }


}
