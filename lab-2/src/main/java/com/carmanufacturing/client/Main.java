package com.carmanufacturing.client;

import com.carmanufacturing.domain.models.Car;
import com.carmanufacturing.domain.models.Engine;
import com.carmanufacturing.domain.models.Wheel;
import com.carmanufacturing.structural.adapter.ExternalInventory;
import com.carmanufacturing.structural.adapter.ExternalInventoryAdapter;
import com.carmanufacturing.structural.adapter.PartsInventory;
import com.carmanufacturing.structural.composite.CarOrderItem;
import com.carmanufacturing.structural.composite.OrderGroup;
import com.carmanufacturing.structural.composite.OrderComponent;
import com.carmanufacturing.structural.facade.ManufacturingFacade;

import java.util.List;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        ManufacturingFacade facade = new ManufacturingFacade();

        // Facade: create a standard gas car
        Car sedan = facade.createStandardGasCar("car1");
        System.out.println("[Facade] Created car: " + sedan);

        // Facade: build a sports car using electric parts (example of combining patterns)
        Car sport = facade.createSportsFromElectricParts("car-e");
        System.out.println("[Facade] Built sports car from electric parts: " + sport);

        // Adapter: use an external inventory to fetch an engine and wheels
        ExternalInventory externalInventory = new ExternalInventory();
        PartsInventory adapter = new ExternalInventoryAdapter(externalInventory);
        Engine extEngine = adapter.fetchEngine("electric");
        List<Wheel> extWheels = adapter.fetchWheels("18,performance");
        System.out.println("[Adapter] External engine: " + extEngine);
        System.out.println("[Adapter] External wheels: " + extWheels);

        // COMPOSITE

        // Create basic order items
        CarOrderItem item1 = new CarOrderItem(sedan, 22000);
        CarOrderItem item2 = new CarOrderItem(sport, 45000);

        // Top-level order group
        OrderGroup mainOrder = new OrderGroup("MainOrder-1001");

        // Add single items
        mainOrder.add(item1);
        mainOrder.add(item2);

        // Create a bundle (sub-group) that contains a compact car and a standard sport clone
        OrderGroup holidayBundle = new OrderGroup("HolidayBundle");
        Car compact = facade.createStandardGasCar("Compact");
        holidayBundle.add(new CarOrderItem(compact, 18000));

        // Because prototype was removed, create a fresh configured car for the bundle
        Car bundleSport = facade.createSportsFromElectricParts("Sport-Bundle");
        holidayBundle.add(new CarOrderItem(bundleSport, 30000));

        // Add nested subgroup inside the holiday bundle (e.g., accessories group)
        OrderGroup accessories = new OrderGroup("Accessories");
        accessories.add(new CarOrderItem(facade.createStandardGasCar("Accessory-Stub-1"), 199));
        accessories.add(new CarOrderItem(facade.createStandardGasCar("Accessory-Stub-2"), 299));
        holidayBundle.add(accessories);

        // Add bundle to main order
        mainOrder.add(holidayBundle);

        // Create a corporate group that contains multiple orders
        OrderGroup corporate = new OrderGroup("Corporate-Account-42");
        corporate.add(new CarOrderItem(facade.createStandardGasCar("Corporate-Sedan"), 25000));
        corporate.add(new CarOrderItem(facade.createStandardGasCar("Corporate-SUV"), 32000));

        // Add corporate as a subgroup of main order
        mainOrder.add(corporate);

        // Print the full structure and totals
        System.out.println("\n[Composite] Full order description: " + mainOrder.getDescription());
        System.out.println("[Composite] Full order total price: " + mainOrder.getPrice());

        // Demonstrate removal: remove one accessory from holiday bundle
        System.out.println("\n[Composite] Removing one accessory from HolidayBundle...");
        OrderComponent toRemove = null;
        for (OrderComponent oc : holidayBundle.getChildren()) {
            if (oc.getDescription() != null && oc.getDescription().contains("Accessory-Stub-1")) {
                toRemove = oc;
                break;
            }
        }
        if (toRemove != null) {
            holidayBundle.remove(toRemove);
        }

        System.out.println("[Composite] Updated order description: " + mainOrder.getDescription());
        System.out.println("[Composite] Updated order total price: " + mainOrder.getPrice());

        // Traverse and print each component (pre-order traversal)
        System.out.println("\n[Composite] Traversing order components:");
        traverseAndPrint(mainOrder);
    }

    // helper traversal (pre-order)
    private static void traverseAndPrint(OrderComponent root) {
        Stack<OrderComponent> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            OrderComponent current = stack.pop();
            System.out.println(" - " + current.getDescription() + " (" + current.getPrice() + ")");
            if (current instanceof OrderGroup) {
                OrderGroup og = (OrderGroup) current;
                List<OrderComponent> children = og.getChildren();
                // push children in reverse to preserve original order
                for (int i = children.size() - 1; i >= 0; i--) stack.push(children.get(i));
            }
        }
    }
}
