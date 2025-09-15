class EmployeeBean {
    private String employeeId;
    private String firstName;
    private String lastName;
    private double salary;
    private String department;
    private boolean active;

    public EmployeeBean() {} // no-arg constructor

    public EmployeeBean(String employeeId, String firstName, String lastName, double salary, String department, boolean active) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.department = department;
        this.active = active;
    }

    // Getters
    public String getEmployeeId() { return employeeId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public double getSalary() { return salary; }
    public String getDepartment() { return department; }
    public boolean isActive() { return active; }

    // Setters
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setSalary(double salary) { if (salary > 0) this.salary = salary; }
    public void setDepartment(String department) { this.department = department; }
    public void setActive(boolean active) { this.active = active; }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return employeeId + ": " + getFullName() + " (" + department + "), Salary: " + salary;
    }
}

public class EmployeeBeanDemo {
    public static void main(String[] args) {
        EmployeeBean emp = new EmployeeBean("E01", "Ravi", "Kumar", 45000, "IT", true);
        System.out.println(emp);
        emp.setSalary(50000);
        System.out.println("Updated Salary: " + emp.getSalary());
    }
}