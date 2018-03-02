package com.company.shop.drinks;

import java.util.List;
import java.util.Objects;

public class Soft extends Drink {
    private String group;
    private List<String> composition;

    public Soft() {
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public List<String> getComposition() {
        return composition;
    }

    public void setComposition(List<String> composition) {
        this.composition = composition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Soft softDrink = (Soft) o;
        return Objects.equals(group, softDrink.group) &&
                Objects.equals(composition, softDrink.composition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), group, composition);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("\"");
        for (int i = 0; i < composition.size() - 1; i++) {
            builder.append(composition.get(i) + ", ");
        }
        builder.append(composition.get(composition.size() - 1));
        builder.append("\"");
        return "\"" + getName() + "\", " + getPurchasePrice() + ", \"" + getGroup() + "\", " + getVolume() + ", " + builder.toString() + ", " + getQuantity();
    }


}
