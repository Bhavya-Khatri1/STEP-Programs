// Parent class
class LibraryUser {
    String name;

    LibraryUser(String name) {
        this.name = name;
    }

    // Common operations
    void enterLibrary() {
        System.out.println(name + " entered the library.");
    }

    void displayInfo() {
        System.out.println("User: " + name);
    }
}

// Student class
class Student extends LibraryUser {
    Student(String name) {
        super(name);
    }

    void borrowBook() {
        System.out.println(name + " borrowed a book.");
    }

    void accessComputer() {
        System.out.println(name + " is accessing a computer.");
    }
}

// Faculty class
class Faculty extends LibraryUser {
    Faculty(String name) {
        super(name);
    }

    void reserveBook() {
        System.out.println(name + " reserved a book.");
    }

    void accessResearchDatabase() {
        System.out.println(name + " is accessing the research database.");
    }
}

// Guest class
class Guest extends LibraryUser {
    Guest(String name) {
        super(name);
    }

    void browseBooks() {
        System.out.println(name + " is browsing books.");
    }
}

// Class to test 
public class LibraryUserTest {
    public static void main(String[] args) {
        // Upcasting: storing specialized users as general LibraryUser
        LibraryUser[] users = {
            new Student("Alice"),
            new Faculty("Dr. Bob"),
            new Guest("Charlie")
        };

        // Common operations
        for (LibraryUser user : users) {
            user.enterLibrary();
            user.displayInfo();
        }

        // Specialized operations require casting
        ((Student) users[0]).borrowBook();
        ((Faculty) users[1]).accessResearchDatabase();
        ((Guest) users[2]).browseBooks();
    }
}