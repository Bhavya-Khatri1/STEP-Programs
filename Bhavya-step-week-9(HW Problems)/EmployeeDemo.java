class Employee {
    int id;
    String name;
    double salary;

    // Constructor
    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    // Override toString() for readable object details
    @Override
    public String toString() {
        return "Employee [ID: " + id + ", Name: " + name + ", Salary: ₹" + salary + "]";
    }
}

public class EmployeeDemo {
    public static void main(String[] args) {
        Employee e1 = new Employee(101, "Bhavya", 55000);
        Employee e2 = new Employee(102, "Rohan", 62000);
        Employee e3 = new Employee(103, "Sneha", 70000);

        // Display employee details and their class names
        System.out.println(e1);
        System.out.println("Class Name: " + e1.getClass().getName());

        System.out.println(e2);
        System.out.println("Class Name: " + e2.getClass().getName());

        System.out.println(e3);
        System.out.println("Class Name: " + e3.getClass().getName());
    }
}