// Q5_LibraryManagementSystem

class Book {
    String bookId;
    String title;
    String author;
    String isbn;
    String category;
    boolean isIssued;
    String issueDate;
    String dueDate;

    public Book(String id, String t, String a, String isbn, String cat) {
        this.bookId = id;
        this.title = t;
        this.author = a;
        this.isbn = isbn;
        this.category = cat;
        this.isIssued = false;
    }
}

class Member {
    String memberId;
    String memberName;
    String memberType;   // Student, Faculty, General
    Book[] booksIssued;
    int issuedCount;
    double totalFines;
    String membershipDate;

    public Member(String id, String name, String type, String date) {
        this.memberId = id;
        this.memberName = name;
        this.memberType = type;
        this.membershipDate = date;
        this.booksIssued = new Book[5];  // can issue max 5 books
        this.issuedCount = 0;
        this.totalFines = 0.0;
    }

    public void addIssuedBook(Book b) {
        if (issuedCount < booksIssued.length) {
            booksIssued[issuedCount] = b;
            issuedCount++;
        }
    }

    public void removeIssuedBook(String bookId) {
        for (int i = 0; i < issuedCount; i++) {
            if (booksIssued[i] != null && booksIssued[i].bookId.equals(bookId)) {
                // shift left
                for (int j = i; j < issuedCount - 1; j++) {
                    booksIssued[j] = booksIssued[j + 1];
                }
                booksIssued[issuedCount - 1] = null;
                issuedCount--;
                return;
            }
        }
    }
}

class Library {
    static int totalBooks = 0;
    static int totalMembers = 0;
    static String libraryName = "Campus Library";
    static double finePerDay = 5.0;
    static int maxBooksAllowed = 3;

    Book[] books;
    Member[] members;
    int bookCount;
    int memberCount;

    public Library(int bookCapacity, int memberCapacity) {
        books = new Book[bookCapacity];
        members = new Member[memberCapacity];
        bookCount = 0;
        memberCount = 0;
    }

    public void addBook(Book b) {
        if (bookCount < books.length) {
            books[bookCount] = b;
            bookCount++;
            totalBooks++;
        }
    }

    public void addMember(Member m) {
        if (memberCount < members.length) {
            members[memberCount] = m;
            memberCount++;
            totalMembers++;
        }
    }

    public boolean issueBook(String bookId, String memberId, String issueDate, String dueDate) {
        Book b = findBook(bookId);
        Member m = findMember(memberId);

        if (b == null || m == null) return false;
        if (b.isIssued) return false;
        if (m.issuedCount >= maxBooksAllowed) return false;

        b.isIssued = true;
        b.issueDate = issueDate;
        b.dueDate = dueDate;
        m.addIssuedBook(b);

        return true;
    }

    public boolean returnBook(String bookId, String memberId, String returnDate) {
        Book b = findBook(bookId);
        Member m = findMember(memberId);

        if (b == null || m == null) return false;
        if (!b.isIssued) return false;

        double fine = calculateFine(b.dueDate, returnDate);
        m.totalFines += fine;

        b.isIssued = false;
        b.issueDate = null;
        b.dueDate = null;
        m.removeIssuedBook(bookId);

        return true;
    }

    public double calculateFine(String dueDate, String returnDate) {
        // Format assumption: YYYY-MM-DD → we'll compare just the day part
        try {
            int dueDay = Integer.parseInt(dueDate.split("-")[2]);
            int retDay = Integer.parseInt(returnDate.split("-")[2]);
            int overdue = Math.max(0, retDay - dueDay);
            return overdue * finePerDay;
        } catch (Exception e) {
            return 0.0;
        }
    }

    private Book findBook(String bookId) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i] != null && books[i].bookId.equals(bookId)) {
                return books[i];
            }
        }
        return null;
    }

    private Member findMember(String memberId) {
        for (int i = 0; i < memberCount; i++) {
            if (members[i] != null && members[i].memberId.equals(memberId)) {
                return members[i];
            }
        }
        return null;
    }
}

public class Q5_LibraryManagementSystem {
    public static void main(String[] args) {
        Library lib = new Library(10, 5);

        // Add books
        lib.addBook(new Book("B001", "Java Basics", "James", "111", "Programming"));
        lib.addBook(new Book("B002", "OOP Concepts", "Alan", "222", "Programming"));

        // Add members
        lib.addMember(new Member("M001", "Alice", "Student", "2025-01-01"));

        // Issue book
        boolean issued = lib.issueBook("B001", "M001", "2025-09-01", "2025-09-05");
        System.out.println("Book issued: " + issued);

        // Return late
        boolean returned = lib.returnBook("B001", "M001", "2025-09-08");
        System.out.println("Book returned: " + returned);

        // Check fines
        Member m = lib.members[0];
        System.out.println(m.memberName + " total fines: " + m.totalFines);
    }
}