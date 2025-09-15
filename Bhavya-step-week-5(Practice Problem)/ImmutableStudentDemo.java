import java.time.LocalDate;
import java.util.*;

final class ImmutableStudent {
    private final String studentId;
    private final String name;
    private final LocalDate birthDate;
    private final List<String> courses;

    public ImmutableStudent(String studentId, String name, LocalDate birthDate, List<String> courses) {
        this.studentId = studentId;
        this.name = name;
        this.birthDate = birthDate;
        this.courses = new ArrayList<>(courses);
    }

    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public LocalDate getBirthDate() { return birthDate; }
    public List<String> getCourses() { return new ArrayList<>(courses); } // return copy

    public int getAge() {
        return LocalDate.now().getYear() - birthDate.getYear();
    }

    // Modification by returning new object
    public ImmutableStudent withNewCourse(String course) {
        List<String> newCourses = new ArrayList<>(this.courses);
        newCourses.add(course);
        return new ImmutableStudent(this.studentId, this.name, this.birthDate, newCourses);
    }

    @Override
    public String toString() {
        return studentId + " " + name + " Courses: " + courses;
    }
}

public class ImmutableStudentDemo {
    public static void main(String[] args) {
        List<String> courses = new ArrayList<>();
        courses.add("Math");

        ImmutableStudent s1 = new ImmutableStudent("S01", "Anita", LocalDate.of(2002, 5, 10), courses);
        System.out.println(s1);

        ImmutableStudent s2 = s1.withNewCourse("Science");
        System.out.println("Original: " + s1);
        System.out.println("Modified: " + s2);
    }
}