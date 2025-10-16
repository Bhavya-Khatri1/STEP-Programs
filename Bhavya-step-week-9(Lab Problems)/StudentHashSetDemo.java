import java.util.*;

class Student {
    int id;
    String name;

    // Constructor
    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Override equals() to compare by id
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Student s = (Student) obj;
        return id == s.id; // same id means same student
    }

    // Override hashCode() to be consistent with equals()
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // toString for display
    @Override
    public String toString() {
        return "Student[ID: " + id + ", Name: " + name + "]";
    }
}

public class StudentHashSetDemo {
    public static void main(String[] args) {
        HashSet<Student> set = new HashSet<>();

        Student s1 = new Student(101, "Alice");
        Student s2 = new Student(102, "Bob");
        Student s3 = new Student(101, "Alice"); // same id as s1

        set.add(s1);
        set.add(s2);
        set.add(s3); // duplicate based on id

        System.out.println("Students in HashSet:");
        for (Student s : set)
            System.out.println(s);
    }
}