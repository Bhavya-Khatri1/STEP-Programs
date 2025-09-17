package university;

import java.util.ArrayList;
import java.util.List;

final class AcademicRecord {
    private final String studentId;
    private final List<String> completedCourses;

    public AcademicRecord(String studentId, List<String> completedCourses) {
        this.studentId = studentId;
        this.completedCourses = new ArrayList<>(completedCourses);
    }

    public boolean hasCompleted(String course) {
        return completedCourses.contains(course);
    }
}

class Student {
    private String name;
    private AcademicRecord record;

    public Student(String name, AcademicRecord record) {
        this.name = name;
        this.record = record;
    }

    public String getName() { return name; }
    public AcademicRecord getRecord() { return record; }
}

class Course {
    private String courseCode;
    private String prerequisite;

    public Course(String courseCode, String prerequisite) {
        this.courseCode = courseCode;
        this.prerequisite = prerequisite;
    }

    public String getCourseCode() { return courseCode; }
    public String getPrerequisite() { return prerequisite; }
}

class Professor {
    private String name;

    public Professor(String name) { this.name = name; }
}

class Classroom {
    private String roomNumber;

    public Classroom(String roomNumber) { this.roomNumber = roomNumber; }
}

class RegistrationSystem {
    public boolean register(Student student, Course course) {
        if (course.getPrerequisite() == null || student.getRecord().hasCompleted(course.getPrerequisite())) {
            System.out.println(student.getName() + " registered for " + course.getCourseCode());
            return true;
        } else {
            System.out.println("Prerequisite not met!");
            return false;
        }
    }

    public static void main(String[] args) {
        List<String> completed = new ArrayList<>();
        completed.add("CS101");
        AcademicRecord record = new AcademicRecord("S1", completed);
        Student s = new Student("Bob", record);

        Course c1 = new Course("CS201", "CS101");
        Course c2 = new Course("CS301", "CS201");

        RegistrationSystem system = new RegistrationSystem();
        system.register(s, c1);
        system.register(s, c2);
    }
}
