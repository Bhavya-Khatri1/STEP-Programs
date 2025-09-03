class Employee {
    private String empId;
    private String empName;
    private String department;
    private double baseSalary;
    private String empType;
    private static int totalEmployees = 0;
    private static int empCounter = 1;
    public Employee(String empName, String department, double baseSalary) {
        this.empId = generateEmpId();
        this.empName = empName;
        this.department = department;
        this.baseSalary = baseSalary;
        this.empType = "Full-Time";
        totalEmployees++;
    }
    public Employee(String empName, double hourlyRate, int hoursWorked) {
        this.empId = generateEmpId();
        this.empName = empName;
        this.department = "Part-Time";
        this.baseSalary = hourlyRate * hoursWorked;
        this.empType = "Part-Time";
        totalEmployees++;
    }
    public Employee(String empName, double contractAmount) {
        this.empId = generateEmpId();
        this.empName = empName;
        this.department = "Contract";
        this.baseSalary = contractAmount;
        this.empType = "Contract";
        totalEmployees++;
    }
    public double calculateSalary(double bonus) {
        if (empType.equals("Full-Time")) {
            return baseSalary + bonus;
        }
        return baseSalary;
    }
    public double calculateSalary(int hoursWorked, double hourlyRate) {
        if (empType.equals("Part-Time")) {
            return hoursWorked * hourlyRate;
        }
        return baseSalary;
    }
    public double calculateSalary() {
        return baseSalary;
    }
    public double calculateTax(double salary, double rate) {
        return salary * rate;
    }
    public double calculateTax(double salary) {
        return salary * 0.10;
    }
    public void generatePaySlip(double salary, double tax) {
        System.out.println("======= Pay Slip =======");
        System.out.println("Employee ID   : " + empId);
        System.out.println("Name          : " + empName);
        System.out.println("Department    : " + department);
        System.out.println("Type          : " + empType);
        System.out.println("Salary        : " + salary);
        System.out.println("Tax Deducted  : " + tax);
        System.out.println("Net Salary    : " + (salary - tax));
        System.out.println("========================\n");
    }
    public void displayEmployeeInfo() {
        System.out.println("Employee ID: " + empId);
        System.out.println("Name       : " + empName);
        System.out.println("Type       : " + empType);
        System.out.println("Base Salary: " + baseSalary);
        System.out.println("---------------------------");
    }
    public static int getTotalEmployees() {
        return totalEmployees;
    }
    private static String generateEmpId() {
        return "EMP" + String.format("%03d", empCounter++);
    }
}
public class EmployeePayrollDemo {
    public static void main(String[] args) {
        Employee e1 = new Employee("Alice", "IT", 40000);
        Employee e2 = new Employee("Bob", 500, 40);
        Employee e3 = new Employee("Charlie", 60000);
        
        double salary1 = e1.calculateSalary(5000);
        double tax1 = e1.calculateTax(salary1, 0.15);
        e1.generatePaySlip(salary1, tax1);
        double salary2 = e2.calculateSalary(40, 500);
        double tax2 = e2.calculateTax(salary2);
        e2.generatePaySlip(salary2, tax2);
        double salary3 = e3.calculateSalary();
        double tax3 = e3.calculateTax(salary3, 0.05);
        e3.generatePaySlip(salary3, tax3);
        e1.displayEmployeeInfo();
        e2.displayEmployeeInfo();
        e3.displayEmployeeInfo();
        System.out.println("Total Employees: " + Employee.getTotalEmployees());
    }
}