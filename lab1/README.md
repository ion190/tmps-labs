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

- **Prototype** – `CarPrototypeRegistry`  
  Stores and clones predefined car templates.

---

## Project Structure

| Package | Purpose |
|----------|----------|
| `client` | Contains the `Main` demo class |
| `domain.models` | Core model classes like `Car`, `Engine`, `Wheel`, `Paint` |
| `domain.factory` | Factories (Factory Method + Abstract Factory) |
| `domain.builder` | Builder pattern implementation |
| `domain.prototype` | Prototype registry |
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

4. **Prototype**  
   - `CarPrototypeRegistry` keeps predefined car templates like `"SportBase"`.  
   - New cars can be cloned and modified from these prototypes.

---

## Output

```
[Factory] Created gas car:
Car{id=5470219f, model='simple sedan', type=SEDAN, engine=Engine{fuel=GASOLINE, hp=126}, wheels=[Wheel{17in,all-season}, Wheel{17in,all-season}, Wheel{17in,all-season}, Wheel{17in,all-season}], paint=Paint{Metallic Silver}}

[Factory] Created electric car:
Car{id=e16e9415, model='electric suv', type=ELECTRIC, engine=Engine{fuel=ELECTRIC, hp=220}, wheels=[Wheel{18in,winter}, Wheel{18in,winter}, Wheel{18in,winter}, Wheel{18in,winter}], paint=Paint{Pearl White}}

[Builder] Built sports car:
Car{id=6e6a1eb6, model='supra', type=SPORTS, engine=Engine{fuel=GASOLINE, hp=550}, wheels=[Wheel{20in,performance}, Wheel{20in,performance}, Wheel{20in,performance}, Wheel{20in,performance}], paint=Paint{Red}}
[Pool] Engines available after return: 3

[Prototype] Cloned and modified:
Car{id=d869efb9, model='SportBase - Custom Clone', type=SEDAN, engine=Engine{fuel=GASOLINE, hp=420}, wheels=[Wheel{17in,all-season}, Wheel{17in,all-season}, Wheel{17in,all-season}, Wheel{17in,all-season}], paint=Paint{Metallic Silver}}

[Combined] Electric SUV built with Factory parts + Builder:
Car{id=76dcbdab, model='tesla-SUV', type=SUV, engine=Engine{fuel=ELECTRIC, hp=220}, wheels=[Wheel{18in,winter}, Wheel{18in,winter}, Wheel{18in,winter}, Wheel{18in,winter}], paint=Paint{Green}}
```

## Demo Flow (Main.java)

- Create cars using **factories** (`GasCarFactory`, `ElectricCarFactory`).
- Build a sports car using the **builder pattern**.
- Clone and modify a prototype using the **prototype pattern**.
- Combine patterns (e.g., use factory parts with a builder).

---

