# Car Manufacturing (Behavioral extension)

**Goal:**  
Demonstrate several **behavioral design patterns** (Strategy, Chain of Responsibility, Observer) by extending the Car Manufacturing project.

---

## Patterns Used

- **Strategy** – `PricingStrategy`, `StandardPricingStrategy`, `SportPricingStrategy`  
  Encapsulates different pricing algorithms for cars and allows switching between them at runtime.

- **Chain of Responsibility** – `DiscountHandler`, `SalesRepresentativeHandler`, `ManagerHandler`, `DirectorHandler`  
  Models a discount approval workflow where each role can approve or forward a discount request.

- **Observer** – `OrderSubject`, `SimpleOrderNotifier`, `OrderObserver`, `EmailOrderObserver`, `AccountingOrderObserver`  
  Implements a notification mechanism for order-related events (order created, discount approved/rejected).

---

## Project Structure

| Package | Purpose |
|----------|----------|
| `client` | `Main` demo class showing behavioral patterns |
| `behavioral.strategy` | Pricing strategy interfaces and implementations |
| `behavioral.chain` | Discount approval chain handlers and request object |
| `behavioral.observer` | Order notification subject and observers |
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

3. **Observer (order notifications)**
   - `OrderSubject` defines methods to add/remove observers and notify them about events.
   - `SimpleOrderNotifier` stores a list of `OrderObserver`s and calls them when:
      - a new order is created,
      - a discount is approved,
      - a discount is rejected.
   - `OrderObserver` has a single method:  
     `onOrderEvent(String eventType, String orderDescription, double orderTotal, double discountPercent);`
   - Concrete observers:
      - `EmailOrderObserver` – simulates sending an email notification.
      - `AccountingOrderObserver` – simulates informing the accounting system.
   - The main flow:
      - After building the order (using Strategy to price each item), the subject notifies observers with `"ORDER_CREATED"`.
      - After each discount request passes through the Chain of Responsibility, the result triggers:
         - `"DISCOUNT_APPROVED"` with the approved percent, or
         - `"DISCOUNT_REJECTED"` if nobody in the chain can approve it.

---

## Output (sample)

```text
Strategy: pricing
[Strategy] Sedan 'toyota-prius' price (standard): 22520.0
[Strategy] Sport 'bugati' price (sport strategy): 50599.99999999999
[Strategy] Order total (sum of strategy-based prices): 95640.0
[Observer-Email] New order created: MainOrder-Behavioral: [toyota-prius, bugati, Corporate-SUV], total=95640.0
[Observer-Accounting] Order created: MainOrder-Behavioral: [toyota-prius, bugati, Corporate-SUV], total=95640.0

Chain of Responsibility: discount approval

[Chain] Requesting 3.0% discount for order total 95640.0
[Chain] Sales Representative approved 3.0% discount.
[Chain] Discount APPROVED by Sales Representative
[Chain] Final price after discount: 92770.8
[Observer-Email] Discount 3.0% approved for MainOrder-Behavioral: [toyota-prius, bugati, Corporate-SUV] (total=95640.0)
[Observer-Accounting] Discount 3.0% approved by chain for MainOrder-Behavioral: [toyota-prius, bugati, Corporate-SUV]

[Chain] Requesting 10.0% discount for order total 95640.0
[Chain] Sales Representative cannot approve 10.0%, passing up...
[Chain] Manager approved 10.0% discount.
[Chain] Discount APPROVED by Manager
[Chain] Final price after discount: 86076.0
[Observer-Email] Discount 10.0% approved for MainOrder-Behavioral: [toyota-prius, bugati, Corporate-SUV] (total=95640.0)
[Observer-Accounting] Discount 10.0% approved by chain for MainOrder-Behavioral: [toyota-prius, bugati, Corporate-SUV]

[Chain] Requesting 25.0% discount for order total 95640.0
[Chain] Sales Representative cannot approve 25.0%, passing up...
[Chain] Manager cannot approve 25.0%, passing up...
[Chain] Director approved 25.0% discount.
[Chain] Discount APPROVED by Director
[Chain] Final price after discount: 71730.0
[Observer-Email] Discount 25.0% approved for MainOrder-Behavioral: [toyota-prius, bugati, Corporate-SUV] (total=95640.0)
[Observer-Accounting] Discount 25.0% approved by chain for MainOrder-Behavioral: [toyota-prius, bugati, Corporate-SUV]

[Chain] Requesting 40.0% discount for order total 95640.0
[Chain] Sales Representative cannot approve 40.0%, passing up...
[Chain] Manager cannot approve 40.0%, passing up...
[Chain] Director cannot approve 40.0% discount (too high).
[Chain] Discount NOT approved. Final price remains: 95640.0
[Observer-Email] Discount 40.0% REJECTED for MainOrder-Behavioral: [toyota-prius, bugati, Corporate-SUV] (total=95640.0)
[Observer-Accounting] Discount 40.0% rejected for MainOrder-Behavioral: [toyota-prius, bugati, Corporate-SUV]
```