package com.company.shop.drinks;

import java.util.Objects;

public class Alcohol extends Drink{
    private String classification;
    private Double grade;

    public Alcohol() {
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Alcohol that = (Alcohol) o;
        return Objects.equals(classification, that.classification) &&
                Objects.equals(grade, that.grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), classification, grade);
    }

    @Override
    public String toString() {
        return "\"" + getName() + "\", " + getPurchasePrice() + ", " + "\"" + getClassification() + "\", " + getVolume() + ", " + getGrade() + "%, " + getQuantity();
    }
}
