package com.carmanufacturing.structural.composite;

import java.util.ArrayList;
import java.util.List;

// Composite node that can contain other OrderComponents,items or groups
public class OrderGroup implements OrderComponent {
    private final String name;
    private final List<OrderComponent> children = new ArrayList<>();

    public OrderGroup(String name) {
        this.name = name;
    }

    public void add(OrderComponent c) {
        children.add(c);
    }

    public void remove(OrderComponent c) {
        children.remove(c);
    }

    public List<OrderComponent> getChildren() {
        return new ArrayList<>(children);
    }

    @Override
    public double getPrice() {
        double total = 0;
        for (OrderComponent c : children) total += c.getPrice();
        return total;
    }

    @Override
    public String getDescription() {
        StringBuilder sb = new StringBuilder(name + ": [");
        for (OrderComponent c : children) {
            sb.append(c.getDescription()).append(", ");
        }
        if (!children.isEmpty()) sb.setLength(sb.length() - 2); // remove last comma
        sb.append("]");
        return sb.toString();
    }
}
