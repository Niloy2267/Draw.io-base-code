import java.util.*;

class Book {
    int bookId;
    String title;
    String author;
    boolean available;

    Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.available = true;
    }
}

class Member {
    int memberId;
    String name;

    Member(int memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }
}

class BorrowRecord {
    int memberId;
    int bookId;

    BorrowRecord(int memberId, int bookId) {
        this.memberId = memberId;
        this.bookId = bookId;
    }
}

public class LibraryManagementSystem {
    static Scanner sc = new Scanner(System.in);
    static List<Book> books = new ArrayList<>();
    static List<Member> members = new ArrayList<>();
    static List<BorrowRecord> borrowed = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Search Book");
            System.out.println("4. Add Member");
            System.out.println("5. View Members");
            System.out.println("6. Borrow Book");
            System.out.println("7. Return Book");
            System.out.println("8. Exit");
            System.out.print("Select an option: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> addBook();
                case 2 -> viewBooks();
                case 3 -> searchBook();
                case 4 -> addMember();
                case 5 -> viewMembers();
                case 6 -> borrowBook();
                case 7 -> returnBook();
                case 8 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    static void addBook() {
        System.out.print("Enter book ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter title: ");
        String title = sc.nextLine();
        System.out.print("Enter author: ");
        String author = sc.nextLine();
        books.add(new Book(id, title, author));
        System.out.println("Book added successfully.");
    }

    static void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books found.");
            return;
        }
        for (Book b : books) {
            String status = b.available ? "Available" : "Borrowed";
            System.out.println(b.bookId + " | " + b.title + " by " + b.author + " | " + status);
        }
    }

    static void searchBook() {
        sc.nextLine();
        System.out.print("Enter title to search: ");
        String title = sc.nextLine().toLowerCase();
        boolean found = false;
        for (Book b : books) {
            if (b.title.toLowerCase().contains(title)) {
                System.out.println(b.bookId + " | " + b.title + " by " + b.author);
                found = true;
            }
        }
        if (!found) System.out.println("No matching book found.");
    }

    static void addMember() {
        System.out.print("Enter member ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        members.add(new Member(id, name));
        System.out.println("Member added successfully.");
    }

    static void viewMembers() {
        if (members.isEmpty()) {
            System.out.println("No members found.");
            return;
        }
        for (Member m : members) {
            System.out.println(m.memberId + " | " + m.name);
        }
    }

    static void borrowBook() {
        System.out.print("Enter member ID: ");
        int memberId = sc.nextInt();
        System.out.print("Enter book ID: ");
        int bookId = sc.nextInt();

        Book book = null;
        for (Book b : books) {
            if (b.bookId == bookId) {
                book = b;
                break;
            }
        }

        if (book == null || !book.available) {
            System.out.println("Book not available.");
            return;
        }

        book.available = false;
        borrowed.add(new BorrowRecord(memberId, bookId));
        System.out.println("Book borrowed successfully.");
    }

    static void returnBook() {
        System.out.print("Enter member ID: ");
        int memberId = sc.nextInt();
        System.out.print("Enter book ID: ");
        int bookId = sc.nextInt();

        for (BorrowRecord r : borrowed) {
            if (r.memberId == memberId && r.bookId == bookId) {
                borrowed.remove(r);
                for (Book b : books) {
                    if (b.bookId == bookId) {
                        b.available = true;
                        break;
                    }
                }
                System.out.println("Book returned successfully.");
                return;
            }
        }
        System.out.println("No such borrowed record found.");
    }
}
