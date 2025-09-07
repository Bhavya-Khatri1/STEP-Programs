// Q4_StudentGradeManagementSystem

class Subject {
    String subjectCode;
    String subjectName;
    int credits;
    String instructor;

    public Subject(String code, String name, int credits, String ins) {
        this.subjectCode = code;
        this.subjectName = name;
        this.credits = credits;
        this.instructor = ins;
    }
}

class Student {
    String studentId;
    String studentName;
    String className;
    String[] subjects;
    double[] marks;   // one mark per subject
    double gpa;

    static int totalStudents = 0;
    static String schoolName = "Campus High";
    static String[] gradingScale = {"A", "B", "C", "D", "F"};
    static double passPercentage = 40.0;

    public Student(String id, String name, String cls, String[] subjects) {
        this.studentId = id;
        this.studentName = name;
        this.className = cls;
        this.subjects = subjects;
        this.marks = new double[subjects.length];
        this.gpa = 0.0;
        totalStudents++;
    }

    public void addMarks(String subject, double mark) {
        for (int i = 0; i < subjects.length; i++) {
            if (subjects[i].equals(subject)) {
                marks[i] = mark;
                return;
            }
        }
    }

    public void calculateGPA() {
        double sum = 0;
        for (double m : marks) {
            sum += m;
        }
        double avg = sum / marks.length;
        gpa = avg / 10.0;  // simple scale: 0-100 → 0-10
    }

    public void generateReportCard() {
        System.out.println("\nReport Card - " + studentName + " (" + studentId + ")");
        for (int i = 0; i < subjects.length; i++) {
            System.out.println(subjects[i] + ": " + marks[i]);
        }
        System.out.println("GPA: " + gpa);
        System.out.println("Promotion Eligible: " + checkPromotionEligibility());
    }

    public boolean checkPromotionEligibility() {
        for (double m : marks) {
            if (m < passPercentage) return false;
        }
        return true;
    }

    // Static methods
    public static void setGradingScale(String[] scale) {
        gradingScale = scale;
    }

    public static double calculateClassAverage(Student[] students) {
        double sum = 0;
        int count = 0;
        for (Student s : students) {
            if (s != null) {
                for (double m : s.marks) {
                    sum += m;
                    count++;
                }
            }
        }
        return (count == 0) ? 0 : (sum / count);
    }
}

public class Q4_StudentGradeManagementSystem {
    public static void main(String[] args) {
        String[] subs = {"Math", "Physics", "Chemistry"};

        Student s1 = new Student("S1", "John", "10A", subs);
        Student s2 = new Student("S2", "Emma", "10A", subs);

        // Add marks
        s1.addMarks("Math", 85);
        s1.addMarks("Physics", 78);
        s1.addMarks("Chemistry", 92);
        s1.calculateGPA();

        s2.addMarks("Math", 55);
        s2.addMarks("Physics", 40);
        s2.addMarks("Chemistry", 60);
        s2.calculateGPA();

        // Generate report cards
        s1.generateReportCard();
        s2.generateReportCard();

        // Class average
        Student[] class10A = {s1, s2};
        double classAvg = Student.calculateClassAverage(class10A);
        System.out.println("\nClass Average: " + classAvg);
    }
}