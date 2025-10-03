// Abstract class Shape
abstract class Shape {
    protected double area;
    protected double perimeter;

    public abstract void calculateArea();
    public abstract void calculatePerimeter();
}

// Interface Drawable
interface Drawable {
    void draw();
}

// Circle class extending Shape and implementing Drawable
class Circle extends Shape implements Drawable {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public void calculateArea() {
        area = Math.PI * radius * radius;
    }

    @Override
    public void calculatePerimeter() {
        perimeter = 2 * Math.PI * radius;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a Circle with radius: " + radius);
    }

    public void display() {
        System.out.println("Area: " + area + ", Perimeter: " + perimeter);
    }
}

// Main class
public class LabProblem2 {
    public static void main(String[] args) {
        Circle circle = new Circle(5);
        circle.calculateArea();
        circle.calculatePerimeter();
        circle.draw();
        circle.display();
    }
}