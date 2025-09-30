// Base class
class Course {
    String title, instructor, enrollmentDate;
    
    Course(String title, String instructor, String enrollmentDate) {
        this.title = title;
        this.instructor = instructor;
        this.enrollmentDate = enrollmentDate;
    }
    
    // Method to override
    void showProgress() {
        System.out.println("Generic course progress tracking...");
    }
}

// Subclass 1: Video Course
class VideoCourse extends Course {
    int completionPercent;
    int watchTime; // in hours
    
    VideoCourse(String title, String instructor, String date, int completionPercent, int watchTime) {
        super(title, instructor, date);
        this.completionPercent = completionPercent;
        this.watchTime = watchTime;
    }
    
    @Override
    void showProgress() {
        System.out.println("Video Course: " + title);
        System.out.println("Completion: " + completionPercent + "% | Watch Time: " + watchTime + " hrs\n");
    }
}

// Subclass 2: Interactive Course
class InteractiveCourse extends Course {
    int quizScore;
    int projectsCompleted;
    
    InteractiveCourse(String title, String instructor, String date, int quizScore, int projectsCompleted) {
        super(title, instructor, date);
        this.quizScore = quizScore;
        this.projectsCompleted = projectsCompleted;
    }
    
    @Override
    void showProgress() {
        System.out.println("Interactive Course: " + title);
        System.out.println("Quiz Score: " + quizScore + "% | Projects Completed: " + projectsCompleted + "\n");
    }
}

// Subclass 3: Reading Course
class ReadingCourse extends Course {
    int pagesRead;
    int notesTaken;
    
    ReadingCourse(String title, String instructor, String date, int pagesRead, int notesTaken) {
        super(title, instructor, date);
        this.pagesRead = pagesRead;
        this.notesTaken = notesTaken;
    }
    
    @Override
    void showProgress() {
        System.out.println("Reading Course: " + title);
        System.out.println("Pages Read: " + pagesRead + " | Notes Taken: " + notesTaken + "\n");
    }
}

// Subclass 4: Certification Course
class CertificationCourse extends Course {
    int examAttempts;
    boolean certified;
    
    CertificationCourse(String title, String instructor, String date, int examAttempts, boolean certified) {
        super(title, instructor, date);
        this.examAttempts = examAttempts;
        this.certified = certified;
    }
    
    @Override
    void showProgress() {
        System.out.println("Certification Course: " + title);
        System.out.println("Exam Attempts: " + examAttempts + " | Certified: " + certified + "\n");
    }
}

// Main driver
public class CourseTest {
    public static void main(String[] args) {
        Course c1 = new VideoCourse("Java Basics", "Alice", "2025-01-10", 70, 12);
        Course c2 = new InteractiveCourse("Python Projects", "Bob", "2025-02-05", 85, 3);
        Course c3 = new ReadingCourse("Data Structures", "Charlie", "2025-02-20", 120, 10);
        Course c4 = new CertificationCourse("AWS Cloud", "Diana", "2025-03-01", 2, true);
        
        // Polymorphism in action: same method, different behavior
        c1.showProgress();
        c2.showProgress();
        c3.showProgress();
        c4.showProgress();
    }
}