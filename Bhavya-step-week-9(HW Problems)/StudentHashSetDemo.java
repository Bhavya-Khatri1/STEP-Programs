import java.util.*;

class Student {
    int rollNo;
    String name;

    // Constructor
    Student(int rollNo, String name) {
        this.rollNo = rollNo;
        this.name = name;
    }

    // Override equals() based on rollNo
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Student s = (Student) obj;
        return this.rollNo == s.rollNo;
    }

    // Override hashCode() consistent with equals()
    @Override
    public int hashCode() {
        return Objects.hash(rollNo);
    }

    @Override
    public String toString() {
        return "Student[RollNo: " + rollNo + ", Name: " + name + "]";
    }
}

public class StudentHashSetDemo {
    public static void main(String[] args) {
        HashSet<Student> students = new HashSet<>();

        Student s1 = new Student(1, "Alice");
        Student s2 = new Student(2, "Bob");
        Student s3 = new Student(1, "Alice Duplicate");

        students.add(s1);
        students.add(s2);
        students.add(s3); // Duplicate based on rollNo

        System.out.println("Students in HashSet:");
        for (Student s : students)
            System.out.println(s);
    }
}