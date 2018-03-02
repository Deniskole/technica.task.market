package com.company.shop.drinks;

import java.util.Objects;

public abstract class Drink {

    private String name;
    private Double purchasePrice;
    private Double volume;
    private Integer quantity;

    public Drink() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drink drink = (Drink) o;
        return Objects.equals(name, drink.name) &&
                Objects.equals(purchasePrice, drink.purchasePrice) &&
                Objects.equals(volume, drink.volume) &&
                Objects.equals(quantity, drink.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, purchasePrice, volume, quantity);
    }
}

