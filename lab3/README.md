# Car Manufacturing (Behavioral extension)

**Goal:**  
Demonstrate several **behavioral design patterns** (Strategy, Chain of Responsibility) by extending the Car Manufacturing project.

---

## Patterns Used

- **Strategy** – `PricingStrategy`, `StandardPricingStrategy`, `SportPricingStrategy`  
  Encapsulates different pricing algorithms for cars and allows switching between them at runtime.

- **Chain of Responsibility** – `DiscountHandler`, `SalesRepresentativeHandler`, `ManagerHandler`, `DirectorHandler`  
  Models a discount approval workflow where each role can approve or forward a discount request.

---

## Project Structure

| Package | Purpose |
|----------|----------|
| `client` | `Main` demo class showing behavioral patterns |
| `behavioral.strategy` | Pricing strategy interfaces and implementations |
| `behavioral.chain` | Discount approval chain handlers and request object |
| `structural.composite` | Order classes (`OrderGroup`, `CarOrderItem`) used as context for behaviors |
| `structural.facade` | Facade providing simplified manufacturing API (used to create cars) |
| `domain.models` | Existing core models (`Car`, `Engine`, `Wheel`, etc.) |

---

## How It Works

1. **Strategy (pricing)**
    - The interface `PricingStrategy` defines one method:  
      `double calculatePrice(Car car);`
    - `StandardPricingStrategy` computes a base price depending on `CarType` and engine horsepower.
    - `SportPricingStrategy` wraps another `PricingStrategy` and adds a performance markup (e.g., +15%).
    - `CarOrderItem` can be constructed either with:
        - a **fixed price**, or
        - a **PricingStrategy** that calculates the price based on the `Car`.
    - This allows the system to change pricing rules without modifying the order or car classes.

2. **Chain of Responsibility (discount approval)**
    - `DiscountRequest` holds the order total, requested discount percent, and approval result.
    - `DiscountHandler` is the abstract base class with a `handle(DiscountRequest request)` method and a link to the **next** handler.
    - Concrete handlers:
        - `SalesRepresentativeHandler` – can approve small discounts (e.g., up to 5%).
        - `ManagerHandler` – can approve medium discounts (e.g., 5–15%).
        - `DirectorHandler` – can approve larger discounts (e.g., 15–30%).
    - The client creates a chain:
      `SalesRep -> Manager -> Director`  
      and sends a `DiscountRequest` through it. Each handler either:
        - approves the discount and stops the chain, or
        - forwards the request to the next handler.

3. **Integration with existing project**
    - Cars are still created via `ManufacturingFacade` using the creational/structural code from previous labs.
    - Behavioral patterns work **on top of** that:
        - Pricing strategies calculate order item prices.
        - A discount approval chain decides whether a discount can be applied to the total order.

---

## Output (sample)

```text
Strategy: pricing
[Strategy] Sedan 'toyota-prius' price (standard): 22520.0
[Strategy] Sport 'bugati' price (sport strategy): 50599.99999999999
[Strategy] Order total (sum of strategy-based prices): 95640.0

Chain of Responsibility: discount approval

[Chain] Requesting 3.0% discount for order total 95640.0
[Chain] Sales Representative approved 3.0% discount.
[Chain] Discount APPROVED by Sales Representative
[Chain] Final price after discount: 92770.8

[Chain] Requesting 10.0% discount for order total 95640.0
[Chain] Sales Representative cannot approve 10.0%, passing up...
[Chain] Manager approved 10.0% discount.
[Chain] Discount APPROVED by Manager
[Chain] Final price after discount: 86076.0

[Chain] Requesting 25.0% discount for order total 95640.0
[Chain] Sales Representative cannot approve 25.0%, passing up...
[Chain] Manager cannot approve 25.0%, passing up...
[Chain] Director approved 25.0% discount.
[Chain] Discount APPROVED by Director
[Chain] Final price after discount: 71730.0

[Chain] Requesting 40.0% discount for order total 95640.0
[Chain] Sales Representative cannot approve 40.0%, passing up...
[Chain] Manager cannot approve 40.0%, passing up...
[Chain] Director cannot approve 40.0% discount (too high).
[Chain] Discount NOT approved. Final price remains: 95640.0
```