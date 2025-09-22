class BasicMath {
    // Overloaded methods
    public int calculate(int a, int b) {
        System.out.println("BasicMath: Adding two integers");
        return a + b;
    }

    public double calculate(double a, double b) {
        System.out.println("BasicMath: Adding two doubles");
        return a + b;
    }

    public int calculate(int a, int b, int c) {
        System.out.println("BasicMath: Adding three integers");
        return a + b + c;
    }
}

class AdvancedMath extends BasicMath {
    // More overloads
    public double calculate(int a, double b) {
        System.out.println("AdvancedMath: Integer + Double");
        return a + b;
    }

    public double calculate(double a, int b) {
        System.out.println("AdvancedMath: Double + Integer");
        return a + b;
    }

    public int calculate(int... numbers) { // varargs
        System.out.println("AdvancedMath: Adding multiple integers (varargs)");
        int sum = 0;
        for (int n : numbers) sum += n;
        return sum;
    }
}

public class HW5_Test {
    public static void main(String[] args) {
        BasicMath bm = new BasicMath();
        System.out.println("Result: " + bm.calculate(5, 10));
        System.out.println("Result: " + bm.calculate(2.5, 7.5));
        System.out.println("Result: " + bm.calculate(1, 2, 3));

        AdvancedMath am = new AdvancedMath();
        System.out.println("Result: " + am.calculate(5, 10.5));
        System.out.println("Result: " + am.calculate(3.5, 2));
        System.out.println("Result: " + am.calculate(1, 2, 3, 4, 5, 6));
    }
}