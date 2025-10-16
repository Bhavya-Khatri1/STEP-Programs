class University {
    String uniName;

    University(String uniName) {
        this.uniName = uniName;
    }

    // Member Inner Class
    class Department {
        String deptName;

        Department(String deptName) {
            this.deptName = deptName;
        }

        void display() {
            // Access outer class member directly
            System.out.println("Department: " + deptName + ", University: " + uniName);
        }
    }

    // Static Nested Class
    static class ExamCell {
        static void conductExam() {
            System.out.println("ExamCell: Conducting exams for all departments");
        }
    }
}

public class UniversityDemo {
    public static void main(String[] args) {
        // Member inner class
        University uni = new University("SRM University");
        University.Department csDept = uni.new Department("CSE");
        csDept.display();

        // Static nested class
        University.ExamCell.conductExam();
    }
}