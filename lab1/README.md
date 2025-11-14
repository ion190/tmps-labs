# Car Manufacturing

**Goal:**  
Demonstrate several **creational design patterns** in a small Java project that simulates a simple car manufacturing system.

---

## Patterns Used

- **Singleton** – `FactoryManager`  
  Only one instance manages all factories and the prototype registry.

- **Factory Method** – `CarFactory`  
  Creates complete `Car` objects using part factories.

- **Abstract Factory** – `PartsFactory`  
  Produces families of related parts (`Engine`, `Wheels`, `Chassis`, `Paint`).

- **Builder** – `CarBuilder`  
  Builds complex cars step-by-step (`SportsCarBuilder`, `SUVBuilder`, `CarDirector`).


---

## Project Structure

| Package | Purpose |
|----------|----------|
| `client` | Contains the `Main` demo class |
| `domain.models` | Core model classes like `Car`, `Engine`, `Wheel`, `Paint` |
| `domain.factory` | Factories (Factory Method + Abstract Factory) |
| `domain.builder` | Builder pattern implementation |
| `domain.singleton` | Singleton `FactoryManager` |

---

## How It Works

1. **Singleton Manager**  
   `FactoryManager` creates and holds one instance of:
   - `GasCarFactory`
   - `ElectricCarFactory`
   - `CarPrototypeRegistry`

2. **Factory + Abstract Factory**  
   - Each car type uses a specific `PartsFactory` (gas or electric).  
   - The factory method `createCar()` builds a car using compatible parts.

3. **Builder**  
   - `CarDirector` and builders (`SportsCarBuilder`, `SUVBuilder`) create cars step-by-step.
   - Useful for cars that need special configurations.

---

## Output

```
[Factory] Created gas car:
Car{id=a27fae39, model='simple sedan', type=SEDAN, engine=Engine{fuel=GASOLINE, hp=126}, wheels=[Wheel{17in,all-season}, Wheel{17in,all-season}, Wheel{17in,all-season}, Wheel{17in,all-season}], paint=Paint{silver}}

[Factory] Created electric car:
Car{id=918de86c, model='electric suv', type=ELECTRIC, engine=Engine{fuel=ELECTRIC, hp=220}, wheels=[Wheel{18in,winter}, Wheel{18in,winter}, Wheel{18in,winter}, Wheel{18in,winter}], paint=Paint{Pearl White}}

[Combined] Electric SUV built with Factory parts + Builder:
Car{id=0a1340fe, model='tesla-SUV', type=SUV, engine=Engine{fuel=ELECTRIC, hp=220}, wheels=[Wheel{18in,winter}, Wheel{18in,winter}, Wheel{18in,winter}, Wheel{18in,winter}], paint=Paint{white}}
```

## Demo Flow (Main.java)

- Create cars using **factories** (`GasCarFactory`, `ElectricCarFactory`).
- Build a sports car using the **builder pattern**.
- Combine patterns (e.g., use factory parts with a builder).

---

