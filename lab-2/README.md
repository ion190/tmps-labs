# Car Manufacturing (Structural extension)

**Goal:**  
Demonstrate several **structural design patterns** (Facade, Adapter, Composite) by extending the Car Manufacturing project.

---

## Patterns Used

- **Facade** – `ManufacturingFacade`  
  Simplifies client interaction with factories and builders (hides internal complexity).

- **Adapter** – `ExternalInventoryAdapter`  
  Adapts a simulated external inventory API (`ExternalInventory`) to our internal `PartsInventory` interface.

- **Composite** – `OrderGroup`, `CarOrderItem`  
  Represent complex orders that may contain individual items and nested groups/bundles.

---

## Project Structure

| Package | Purpose |
|----------|----------|
| `client` | `Main` demo class showing structural patterns |
| `structural.adapter` | Adapter for external inventory |
| `structural.facade` | Facade providing simplified manufacturing API |
| `structural.composite` | Composite order classes (`OrderGroup`, `CarOrderItem`) |
| `domain.models` | Existing core models (`Car`, `Engine`, `Wheel`, `Paint`) |
| `domain.factory` | Existing factories (used by the facade) |

---

## How It Works

1. **Facade**  
   `ManufacturingFacade` exposes simplified methods:
    - `createStandardGasCar(String modelName)`
    - `createSportsFromElectricParts(String modelName)`  
      It hides factory + builder details from the client.

2. **Adapter**  
   `ExternalInventoryAdapter` wraps `ExternalInventory` (third-party API) and exposes a uniform `PartsInventory` interface for the system.

3. **Composite**  
   Orders are built from `OrderComponent` elements. `OrderGroup` can contain `CarOrderItem`s or other `OrderGroup`s, allowing nested bundles and aggregated pricing.

---

## Output (sample)

```
[Facade] Created car: Car{id=7732e63c, model='car1', type=SEDAN, engine=Engine{fuel=GASOLINE, hp=126}, wheels=[Wheel{17in,all-season}, Wheel{17in,all-season}, Wheel{17in,all-season}, Wheel{17in,all-season}], paint=Paint{silver}}
[Facade] Built sports car from electric parts: Car{id=2582e2c8, model='car-e', type=SPORTS, engine=Engine{fuel=ELECTRIC, hp=550}, wheels=[Wheel{18in,winter}, Wheel{18in,winter}, Wheel{18in,winter}, Wheel{18in,winter}], paint=Paint{Red}}
[Adapter] External engine: Engine{fuel=ELECTRIC, hp=200}
[Adapter] External wheels: [Wheel{18in,performance}, Wheel{18in,performance}, Wheel{18in,performance}, Wheel{18in,performance}]

[Composite] Full order description: MainOrder-1001: [car1, car-e, HolidayBundle: [Compact, Sport-Bundle, Accessories: [Accessory-Stub-1, Accessory-Stub-2]], Corporate-Account-42: [Corporate-Sedan, Corporate-SUV]]
[Composite] Full order total price: 172498.0

[Composite] Removing one accessory from HolidayBundle...
[Composite] Updated order description: MainOrder-1001: [car1, car-e, HolidayBundle: [Compact, Sport-Bundle], Corporate-Account-42: [Corporate-Sedan, Corporate-SUV]]
[Composite] Updated order total price: 172000.0

[Composite] Traversing order components:
 - MainOrder-1001: [car1, car-e, HolidayBundle: [Compact, Sport-Bundle], Corporate-Account-42: [Corporate-Sedan, Corporate-SUV]] (172000.0)
 - car1 (22000.0)
 - car-e (45000.0)
 - HolidayBundle: [Compact, Sport-Bundle] (48000.0)
 - Compact (18000.0)
 - Sport-Bundle (30000.0)
 - Corporate-Account-42: [Corporate-Sedan, Corporate-SUV] (57000.0)
 - Corporate-Sedan (25000.0)
 - Corporate-SUV (32000.0)
```