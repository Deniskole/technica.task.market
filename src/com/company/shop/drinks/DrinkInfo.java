package com.company.shop.drinks;

import java.util.Objects;

public class DrinkInfo {

    private String name;
    private Integer soldQuantity;
    private Integer rebuyQuantity;
    private Double priceSum;
    private Double purchasePrice;

    public DrinkInfo(String drinkName, Integer soldQuantity, Integer rebuyQuantity, Double priceSum, Double drinkPurchasePrice) {
        this.name = drinkName;
        this.soldQuantity = soldQuantity;
        this.rebuyQuantity = rebuyQuantity;
        this.priceSum = priceSum;
        this.purchasePrice = drinkPurchasePrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(Integer soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public Integer getRebuyQuantity() {
        return rebuyQuantity;
    }

    public void setRebuyQuantity(Integer rebuyQuantity) {
        this.rebuyQuantity = rebuyQuantity;
    }

    public Double getPriceSum() {
        return priceSum;
    }

    public void setPriceSum(Double priceSum) {
        this.priceSum = priceSum;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DrinkInfo info = (DrinkInfo) o;
        return Objects.equals(name, info.name) &&
                Objects.equals(soldQuantity, info.soldQuantity) &&
                Objects.equals(rebuyQuantity, info.rebuyQuantity) &&
                Objects.equals(priceSum, info.priceSum) &&
                Objects.equals(purchasePrice, info.purchasePrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, soldQuantity, rebuyQuantity, priceSum, purchasePrice);
    }
}
