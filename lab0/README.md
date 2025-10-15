# Shape Area Calculator (SOLID Principles in Node.js)

## Project Description
This project is a **console-based Shape Area Calculator**, built with **Node.js** to demonstrate **Object-Oriented Programming (OOP)** and the application of **three SOLID principles**:

- **S – Single Responsibility**
- **O – Open/Closed**
- **L – Liskov Substitution**

It allows a user to dynamically choose a geometric shape (e.g., circle, rectangle, triangle, or square) from the console, input the required dimensions, and automatically calculate its area.

The project is intentionally kept small and simple so that each OOP and SOLID concept can be clearly seen and understood.

---

## ⚙️ How It Works
When the program starts:
1. It displays a list of available shapes.  
2. The user types the name of a shape (for example: `circle` or `rectangle`).  
3. The program asks for the necessary dimensions (like radius, width, height, etc.).  
4. Once the user enters the values, it calculates and displays the area.  
5. The user can choose to calculate another area or exit the program.

All interaction happens through the **console**, using Node.js’s built-in `readline` module.

---

## 🧩 OOP & SOLID Principles Applied

### **1. S – Single Responsibility Principle**
Each class or component has a **single, clear purpose**:
- `Circle`, `Rectangle`, `Triangle`, `Square` → calculate area for that shape only.  
- `ShapeFactory` → creates and manages available shape types.  
- `AreaCalculator` → computes the area of any given shape.  
- `InputHandler` → handles user input/output (console interaction).

This separation makes the code easier to maintain and modify.

---

### **2. O – Open/Closed Principle**
The system is **open for extension but closed for modification**.

If you want to add a new shape (e.g., `Parallelogram`), you can simply:
- Create a new class that implements an `area()` method.
- Register it in `ShapeFactory`.

No existing code needs to be changed — just extended.

---

### **3. L – Liskov Substitution Principle**
Every shape class (e.g., `Circle`, `Rectangle`) can be **used interchangeably** as a `Shape`.  
The `AreaCalculator` depends only on the abstract behavior (`area()`), not on any specific shape type.

That means all shapes are substitutable for one another without breaking the program.

---

## 🧠 Technologies Used
- **Node.js** (JavaScript runtime)
- **readline/promises** (for interactive console input)
