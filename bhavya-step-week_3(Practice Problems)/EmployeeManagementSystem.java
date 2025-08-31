import java.util.*;
class Employee {
    private static String companyName;
    private static int totalEmployees = 0;

    private String empId;
    private String name;
    private String department;
    private double salary;
    public Employee() {
        this.empId = "";
        this.name = "";
        this.department = "";
        this.salary = 0.0;
        totalEmployees++;
    }
    public Employee(String empId, String name, String department, double salary) {
        this.empId = empId;
        this.name = name;
        this.department = department;
        this.salary = salary;
        totalEmployees++;
    }
    public static void setCompanyName(String name) {
        companyName = name;
    }
    public static int getTotalEmployees() {
        return totalEmployees;
    }
    public static String getCompanyName() {
        return companyName;
    }
    public double calculateAnnualSalary() {
        return salary * 12;
    }
    public void updateSalary(double newSalary) {
        this.salary = newSalary;
    }
    public void displayEmployee() {
        System.out.println("EmpID: " + empId);
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);
        System.out.println("Salary: " + salary);
    }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
    public String getName() { return name; }
}
class Department {
    private String deptName;
    private Employee[] employees;
    private int employeeCount;
    public Department(String deptName, int size) {
        this.deptName = deptName;
        this.employees = new Employee[size];
        this.employeeCount = 0;
    }
    public void addEmployee(Employee e) {
        if (employeeCount < employees.length) {
            employees[employeeCount] = e;
            employeeCount++;
            System.out.println("Employee added to " + deptName + " department.");
        } else {
            System.out.println("Department is full!");
        }
    }
    public void displayEmployees() {
        if (employeeCount == 0) {
            System.out.println("No employees in " + deptName + " department.");
        } else {
            for (int i = 0; i < employeeCount; i++) {
                employees[i].displayEmployee();
                System.out.println("Annual Salary: " + employees[i].calculateAnnualSalary());
                System.out.println("-------------------");
            }
        }
    }
    public Employee findHighestPaid() {
        if (employeeCount == 0) return null;
        Employee highest = employees[0];
        for (int i = 1; i < employeeCount; i++) {
            if (employees[i].getSalary() > highest.getSalary()) {
                highest = employees[i];
            }
        }
        return highest;
    }
    public double calculateTotalPayroll() {
        double total = 0;
        for (int i = 0; i < employeeCount; i++) {
            total += employees[i].getSalary();
        }
        return total;
    }
    public String getDeptName() { return deptName; }
    public Employee[] getEmployees() { return employees; }
    public int getEmployeeCount() { return employeeCount; }
}
public class EmployeeManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Company Name: ");
        Employee.setCompanyName(sc.nextLine());
        Department d1 = new Department("IT", 10);
        Department d2 = new Department("HR", 10);
        int choice;
        do {
            System.out.println("\n=== EMPLOYEE MANAGEMENT SYSTEM ===");
            System.out.println("1. Add Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Search Employee");
            System.out.println("4. Department Statistics");
            System.out.println("5. Company Statistics");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter EmpID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Department (IT/HR): ");
                    String dept = sc.nextLine();
                    System.out.print("Enter Salary: ");
                    double sal = sc.nextDouble();
                    sc.nextLine();

                    Employee e = new Employee(id, name, dept, sal);
                    if (dept.equalsIgnoreCase("IT")) {
                        d1.addEmployee(e);
                    } else if (dept.equalsIgnoreCase("HR")) {
                        d2.addEmployee(e);
                    } else {
                        System.out.println("Invalid department!");
                    }
                    break;

                case 2:
                    System.out.println("\n--- IT Department ---");
                    d1.displayEmployees();
                    System.out.println("\n--- HR Department ---");
                    d2.displayEmployees();
                    break;

                case 3:
                    System.out.print("Enter Employee Name to search: ");
                    String searchName = sc.nextLine();
                    boolean found = false;
                    for (Employee emp : d1.getEmployees()) {
                        if (emp != null && emp.getName().equalsIgnoreCase(searchName)) {
                            emp.displayEmployee();
                            found = true;
                        }
                    }
                    for (Employee emp : d2.getEmployees()) {
                        if (emp != null && emp.getName().equalsIgnoreCase(searchName)) {
                            emp.displayEmployee();
                            found = true;
                        }
                    }
                    if (!found) System.out.println("Employee not found.");
                    break;

                case 4:
                    System.out.println("IT Payroll: " + d1.calculateTotalPayroll());
                    System.out.println("HR Payroll: " + d2.calculateTotalPayroll());
                    Employee highIT = d1.findHighestPaid();
                    Employee highHR = d2.findHighestPaid();
                    if (highIT != null) System.out.println("Highest Paid in IT: " + highIT.getName() + " - " + highIT.getSalary());
                    if (highHR != null) System.out.println("Highest Paid in HR: " + highHR.getName() + " - " + highHR.getSalary());
                    break;

                case 5:
                    double companyPayroll = d1.calculateTotalPayroll() + d2.calculateTotalPayroll();
                    System.out.println("Company Name: " + Employee.getCompanyName());
                    System.out.println("Total Employees: " + Employee.getTotalEmployees());
                    System.out.println("Total Payroll: " + companyPayroll);
                    break;

                case 6:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 6);
        sc.close();
    }
}