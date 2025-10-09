public class Hospital {
    private String name;
    public Hospital(String name) { this.name = name; }

    public class Department {
        private String deptName;
        public Department(String deptName) { this.deptName = deptName; }
        public void display() {
            System.out.println("Hospital: " + Hospital.this.name + ", Department: " + deptName);
        }
    }

    public Department createDepartment(String name) {
        return new Department(name);
    }
}