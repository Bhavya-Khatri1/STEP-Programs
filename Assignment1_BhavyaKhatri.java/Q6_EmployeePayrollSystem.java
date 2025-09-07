// Q6_EmployeePayrollSystem

class Employee {
    String empId;
    String empName;
    String department;
    String designation;
    double baseSalary;
    String joinDate;
    boolean[] attendanceRecord; // true = present, false = absent (30 days)

    public Employee(String id, String name, String dept, String desig, double salary, String join) {
        this.empId = id;
        this.empName = name;
        this.department = dept;
        this.designation = desig;
        this.baseSalary = salary;
        this.joinDate = join;
        this.attendanceRecord = new boolean[30]; // fixed 30 days
    }

    // Mark attendance
    public void markAttendance(int day, boolean present) {
        if (day >= 1 && day <= 30) {
            attendanceRecord[day - 1] = present;
        }
    }

    // Calculate salary (based on attendance)
    public double calculateSalary() {
        int presentDays = 0;
        for (boolean b : attendanceRecord) {
            if (b) presentDays++;
        }
        // Salary proportional to days present
        return (baseSalary / 30.0) * presentDays;
    }

    // Bonus: simple rule → 10% of base if present > 25 days
    public double calculateBonus() {
        int presentDays = 0;
        for (boolean b : attendanceRecord) {
            if (b) presentDays++;
        }
        if (presentDays > 25) {
            return baseSalary * 0.10;
        }
        return 0.0;
    }

    public void generatePaySlip() {
        double salary = calculateSalary();
        double bonus = calculateBonus();
        System.out.println("\nPay Slip for " + empName + " (" + empId + ")");
        System.out.println("Base Salary: " + baseSalary);
        System.out.println("Earned Salary: " + salary);
        System.out.println("Bonus: " + bonus);
        System.out.println("Total Pay: " + (salary + bonus));
    }
}

class Department {
    String deptId;
    String deptName;
    Employee[] employees;
    int empCount;
    double budget;

    public Department(String id, String name, int size, double budget) {
        this.deptId = id;
        this.deptName = name;
        this.employees = new Employee[size];
        this.empCount = 0;
        this.budget = budget;
    }

    public void addEmployee(Employee e) {
        if (empCount < employees.length) {
            employees[empCount] = e;
            empCount++;
        }
    }

    public double calculateDepartmentExpense() {
        double sum = 0;
        for (int i = 0; i < empCount; i++) {
            if (employees[i] != null) {
                sum += employees[i].calculateSalary() + employees[i].calculateBonus();
            }
        }
        return sum;
    }
}

class Company {
    static int totalEmployees = 0;
    static String companyName = "Campus Tech";
    static double totalSalaryExpense = 0.0;
    static int workingDaysPerMonth = 30;

    Department[] departments;
    int deptCount;

    public Company(int size) {
        departments = new Department[size];
        deptCount = 0;
    }

    public void addDepartment(Department d) {
        if (deptCount < departments.length) {
            departments[deptCount] = d;
            deptCount++;
        }
    }

    public static void calculateCompanyPayroll(Company c) {
        totalSalaryExpense = 0;
        for (int i = 0; i < c.deptCount; i++) {
            if (c.departments[i] != null) {
                totalSalaryExpense += c.departments[i].calculateDepartmentExpense();
            }
        }
    }
}

public class Q6_EmployeePayrollSystem {
    public static void main(String[] args) {
        // Create company and department
        Company comp = new Company(3);
        Department d1 = new Department("D1", "IT", 5, 100000);
        comp.addDepartment(d1);

        // Create employees
        Employee e1 = new Employee("E1", "John", "IT", "Developer", 30000, "2025-01-01");
        Employee e2 = new Employee("E2", "Frank", "IT", "Tester", 25000, "2025-02-01");

        d1.addEmployee(e1);
        d1.addEmployee(e2);

        // Mark attendance
        for (int i = 1; i <= 28; i++) {
            e1.markAttendance(i, true); // John present almost all days
        }
        for (int i = 1; i <= 20; i++) {
            e2.markAttendance(i, true); // Frank present 20 days
        }

        // Generate payslips
        e1.generatePaySlip();
        e2.generatePaySlip();

        // Company payroll
        Company.calculateCompanyPayroll(comp);
        System.out.println("\nTotal Company Payroll: " + Company.totalSalaryExpense);
    }
}