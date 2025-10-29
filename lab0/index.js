// Abstract "Shape" (conceptual)
class Shape {
  area() {
    throw new Error("area() must be implemented by subclasses");
  }
}

// Concrete shapes (each single responsibility)
class Circle extends Shape {
  constructor(radius) {
    super();
    this.radius = Number(radius);
  }
  area() {
    return Math.PI * this.radius * this.radius;
  }
}

class Rectangle extends Shape {
  constructor(width, height) {
    super();
    this.width = Number(width);
    this.height = Number(height);
  }
  area() {
    return this.width * this.height;
  }
}

class Triangle extends Shape {
  constructor(base, height) {
    super();
    this.base = Number(base);
    this.height = Number(height);
  }
  area() {
    return (this.base * this.height) / 2;
  }
}

class Square extends Shape {
  constructor(side) {
    super();
    this.side = Number(side);
  }
  area() {
    return this.side * this.side;
  }
}

// ShapeFactory: create shapes from name + params
// (Open/Closed: register new shapes without changing callers)
class ShapeFactory {
  constructor() {
    this.registry = new Map();
    this.register("circle", { s: Circle, params: ["radius"] });
    this.register("rectangle", { s: Rectangle, params: ["width", "height"] });
    this.register("triangle", { s: Triangle, params: ["base", "height"] });
    this.register("square", { s: Square, params: ["side"] });
  }

  register(name, { s, params }) {
    this.registry.set(name.toLowerCase(), { s, params });
  }

  exists(name) {
    return this.registry.has(name.toLowerCase());
  }

  getParamNames(name) {
    const rec = this.registry.get(name.toLowerCase());
    return rec ? rec.params : null;
  }

  create(name, paramValues) {
    const rec = this.registry.get(name.toLowerCase());
    if (!rec) throw new Error(`Unknown shape: ${name}`);
    return new rec.s(...paramValues);
  }

  listShapes() {
    return Array.from(this.registry.keys());
  }
}

// AreaCalculator: depends on a Shape abstraction (Liskov)
class AreaCalculator {
  // accepts any object that implements area()
  computeArea(shapeInstance) {
    return shapeInstance.area();
  }
}

// InputHandler: responsible only for user IO (Single Responsibility)
const readline = require("readline/promises");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

class InputHandler {
  constructor(shapeFactory, calculator) {
    this.shapeFactory = shapeFactory;
    this.calculator = calculator;
  }

  async run() {
    let again = true;

    while (again) {
      console.log("\nAvailable shapes:", this.shapeFactory.listShapes().join(", "));
      const shapeName = (await rl.question("Enter shape name (or 'exit' to quit): ")).trim().toLowerCase();
      if (!shapeName || shapeName === "exit" || shapeName === "quit") break;

      if (!this.shapeFactory.exists(shapeName)) {
        console.log(`Unknown shape "${shapeName}". Try again.`);
        continue;
      }

      const paramNames = this.shapeFactory.getParamNames(shapeName);
      const paramValues = [];

      let invalidInput = false;
      for (const p of paramNames) {
        const answer = (await rl.question(`Enter ${p}: `)).trim();
        const num = Number(answer);
        if (Number.isNaN(num) || !isFinite(num) || num <= 0) {
          console.log(`Invalid value for ${p}. Must be a positive number.`);
          invalidInput = true;
          break;
        }
        paramValues.push(num);
      }
      if (invalidInput) continue;

      try {
        const shapeInstance = this.shapeFactory.create(shapeName, paramValues);
        const area = this.calculator.computeArea(shapeInstance);
        console.log(`Area of ${shapeName} = ${area}`);
      } catch (err) {
        console.log("Error computing area:", err.message);
      }

      const cont = (await rl.question("Calculate another? (y/n): ")).trim().toLowerCase();
      again = cont === "y" || cont === "yes";
    }

    console.log("Goodbye!");
    await rl.close();
  }
}

async function main() {
  const factory = new ShapeFactory();
  const calculator = new AreaCalculator();

  const inputHandler = new InputHandler(factory, calculator);
  await inputHandler.run();
}

main().catch((e) => {
  console.error("Fatal error:", e);
  rl.close();
});
