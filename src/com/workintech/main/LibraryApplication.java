package com.workintech.main;

import com.workintech.model.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;


public class LibraryApplication {

    private static Library library = new Library();
    private static Librarian librarian = new Librarian(1, "Librarian", "librarian@gmail.com", "123", library);


    public static void main(String[] args) {
        addStart();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("------------KÜTÜPHANEYE HOŞGELDİNİZ------------)");
            System.out.println("Giriş yapınız (1: User, 2: Librarian, 3: Çıkış)");
            int userType = scanner.nextInt();
            scanner.nextLine();

            switch (userType) {
                case 1:
                    loginUser(scanner);
                    break;
                case 2:
                    loginLibrarian(scanner);
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Geçersiz seçim. Tekrar deneyin.");
            }
        }

        scanner.close();
    }

    private static void loginUser(Scanner scanner) {
        System.out.println("Kullanıcı adınızı girin:");
        String username = scanner.nextLine();

        System.out.println("Şifrenizi girin:");
        String password = scanner.nextLine();

        User user = library.getUserByUsername(username);

        if (user != null && user.checkPassword(password)) {
            userMenu(scanner, user);
        } else {
            System.out.println("Kullanıcı adı veya şifre hatalı.");
        }
    }

    private static void loginLibrarian(Scanner scanner) {
        System.out.println("Kütüphaneci adınızı girin:");
        String username = scanner.nextLine();

        System.out.println("Şifrenizi girin:");
        String password = scanner.nextLine();

        if (librarian.getName().equals(username) && librarian.checkPassword(password)) {
            librarianMenu(scanner);
        } else {
            System.out.println("Kullanıcı adı veya şifre hatalı.");
        }
    }

    private static void userMenu(Scanner scanner, User user) {
        boolean exit = false;

        while (!exit) {
            System.out.println("1. Kitap Ödünç Al");
            System.out.println("2. Kitap Geri Ver");
            System.out.println("3. Ödünç Alınan Kitapları Görüntüle");
            System.out.println("4. Kitap ID'ye Göre Görüntüle");
            System.out.println("5. Yazarın Kitaplarını Görüntüle");
            System.out.println("6. Kategorideki Kitapları Görüntüle");
            System.out.println("7. Tüm Kitapları Görüntüle");
            System.out.println("8. Çıkış");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    borrowBook(scanner, user);
                    break;
                case 2:
                    returnBook(scanner, user);
                    break;
                case 3:
                    displayBorrowedBooks(user);
                    break;
                case 4:
                    displayBookById(scanner);
                    break;
                case 5:
                    displayBooksByAuthor(scanner);
                    break;
                case 6:
                    displayBooksByCategory(scanner);
                    break;
                case 7:
                    library.getAllBooks();
                    break;
                case 8:
                    exit = true;
                    break;
                default:
                    System.out.println("Geçersiz seçim. Tekrar deneyin.");
            }
        }
    }

    private static void librarianMenu(Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            System.out.println("1. Yeni Kitap Ekle");
            System.out.println("2. Kitap Güncelle");
            System.out.println("3. Kitap Sil");
            System.out.println("4. Kullanıcı Ekle");
            System.out.println("5. Kullanıcı Sil");
            System.out.println("6. Kitap Görüntüle");
            System.out.println("7. Yazarın Kitaplarını Görüntüle");
            System.out.println("8. Kategorideki Kitapları Görüntüle");
            System.out.println("9. Kullanıcı Görüntüle");
            System.out.println("10. Kitap Ödünç Ver");
            System.out.println("11. Tüm Kitapları Görüntüle ");
            System.out.println("12. Çıkış");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addBook(scanner);
                    break;
                case 2:
                    updateBook(scanner);
                    break;
                case 3:
                    removeBook(scanner);
                    break;
                case 4:
                    addUser(scanner);
                    break;
                case 5:
                    removeUser(scanner);
                    break;
                case 6:
                    displayBook(scanner);
                    break;
                case 7:
                    displayBooksByAuthor(scanner);
                    break;
                case 8:
                    displayBooksByCategory(scanner);
                    break;
                case 9:
                    displayUser(scanner);
                    break;
                case 10:
                    issueBook(scanner);
                    break;
                case 11:
                    library.getAllBooks();
                    break;
                case 12:
                    exit = true;
                    break;
                default:
                    System.out.println("Geçersiz seçim. Tekrar deneyin.");
            }
        }
    }

    private static void borrowBook(Scanner scanner, User user) {
        System.out.println("Ödünç almak istediğiniz kitabın ID'sini girin:");
        long bookId = scanner.nextLong();
        scanner.nextLine();

        Book book = library.getBook(bookId);
        if (book != null && book.isAvailable()) {
            user.borrowBook(book);
        } else if(!book.isAvailable()){
            System.out.println("Kitap şuanda "+ library.getWhoHaveBook(bookId) +" kullanıcısında.");
        } else {
            System.out.println("Kitap mevcut değil");
        }
    }

    private static void returnBook(Scanner scanner, User user) {
        System.out.println("Geri vermek istediğiniz kitabın ID'sini girin:");
        long bookId = scanner.nextLong();
        scanner.nextLine();

        Book book = library.getBook(bookId);
        if (book == null) {
            System.out.println("Kitap bulunamadı");
        } else {
            user.returnBook(book);
        }
    }

    private static void displayBorrowedBooks(User user) {
        List<Book> borrowedBooks = user.getBorrowedBooks();
        if (borrowedBooks.isEmpty()) {
            System.out.println("Ödünç alınmış kitap yok.");
        } else {
            borrowedBooks.forEach(book -> System.out.println(book.getName()));
        }
    }

    private static void displayBookById(Scanner scanner) {
        System.out.println("Görüntülemek istediğiniz kitabın ID'sini girin:");
        long bookId = scanner.nextLong();
        scanner.nextLine();

        Book book = library.getBook(bookId);
        if (book != null) {
            System.out.println(book);
        } else {
            System.out.println("Kitap bulunamadı.");
        }
    }

    private static void displayBooksByAuthor(Scanner scanner) {
        System.out.println("Görüntülemek istediğiniz yazarın adını girin:");
        String authorName = scanner.nextLine();

        List<Book> books = library.getBooksByAuthor(authorName);
        if (books.isEmpty()) {
            System.out.println("Bu yazara ait kitap bulunamadı.");
        } else {
            books.forEach(book -> System.out.println(book.getName()));
        }
    }

    private static void displayBooksByCategory(Scanner scanner) {
        System.out.println("Görüntülemek istediğiniz kategoriyi girin:");
        System.out.println("1: JOURNAL, 2: STUDY, 3: SCIENCE_FICTION, 4: FANTASY, 5: ROMANCE, 6: HISTORY, 7: CLASSIC");

        int categoryValue = scanner.nextInt();

        Category newCategory = null;
        for (Category category : Category.values()) {
            if (category.getValue() == categoryValue) {
                newCategory = category;
                break;
            }
        }

        if (newCategory == null) {
            System.out.println("Geçersiz kategori seçimi.");
            return;
        }

        List<Book> books = library.getBooksByCategory(newCategory);
        if (books.isEmpty()) {
            System.out.println("Bu kategoriye ait kitap bulunamadı.");
        } else {
            books.forEach(book -> System.out.println(book.getName()));
        }
    }

    private static void addBook(Scanner scanner) {
        System.out.println("Kitap ID'sini girin:");
        long id = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Kitap adını girin:");
        String name = scanner.nextLine();

        System.out.println("Yazar adını girin:");
        String authorName = scanner.nextLine();

        System.out.println("Kategori seçin:");
        System.out.println("1: JOURNAL, 2: STUDY, 3: SCIENCE_FICTION, 4: FANTASY, 5: ROMANCE, 6: HISTORY, 7: CLASSIC");

        int categoryValue = scanner.nextInt();
        scanner.nextLine();

        Category newCategory = null;
        for (Category category : Category.values()) {
            if (category.getValue() == categoryValue) {
                newCategory = category;
                break;
            }
        }

        if (newCategory == null) {
            System.out.println("Geçersiz kategori seçimi.");
            return;
        }

        System.out.println("Baskı numarasını girin:");
        String edition = scanner.nextLine();

        System.out.println("Satın alma tarihini girin (yyyy-mm-dd):");
        String dateStr = scanner.nextLine();
        LocalDate dateOfPurchase = LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
        Status status = Status.AVAILABLE;

        Book book = new Book(id,new Author(authorName),name,newCategory,edition,dateOfPurchase,status);
        librarian.addBook(book);
        System.out.println("Kitap eklendi: " + book.getName());
    }

    private static void updateBook(Scanner scanner) {
        System.out.println("Güncellemek istediğiniz kitabın ID'sini girin:");
        long id = scanner.nextLong();
        scanner.nextLine();

        Book book = library.getBook(id);
        if (book == null) {
            System.out.println("Kitap bulunamadı.");
            return;
        }

        System.out.println("Yeni kitap adını girin (önceki: " + book.getName() + "):");
        String newName = scanner.nextLine();

        System.out.println("Yeni yazar adını girin (önceki: " + book.getAuthor() + "):");
        String newAuthor = scanner.nextLine();

        System.out.println("Yeni kategori girin (önceki: " + book.getCategory() + "):");

        System.out.println("1: JOURNAL, 2: STUDY, 3: SCIENCE_FICTION, 4: FANTASY, 5: ROMANCE, 6: HISTORY, 7: CLASSIC");

        int categoryValue = scanner.nextInt();

        Category newCategory = null;
        for (Category category : Category.values()) {
            if (category.getValue() == categoryValue) {
                newCategory = category;
                break;
            }
        }

        if (newCategory == null) {
            System.out.println("Geçersiz kategori seçimi.");
            return;
        }
        book.setName(newName);
        book.setAuthor(new Author(newAuthor));
        book.setCategory(newCategory);

        librarian.updateBook(book);
        System.out.println("Kitap güncellendi: " + book.getName());
    }

    private static void removeBook(Scanner scanner) {
        System.out.println("Silmek istediğiniz kitabın ID'sini girin:");
        long id = scanner.nextLong();
        scanner.nextLine();
        librarian.removeBook(id);
    }

    private static void addUser(Scanner scanner) {
        System.out.println("Kullanıcı ID'sini girin:");
        long id = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Kullanıcı adını girin:");
        String username = scanner.nextLine();

        System.out.println("E-posta girin:");
        String email = scanner.nextLine();

        System.out.println("Şifre girin:");
        String password = scanner.nextLine();

        User user = new User(id, username, email, password);
        librarian.addUser(user);
        System.out.println("Kullanıcı eklendi: " + user.getName());
    }

    private static void removeUser(Scanner scanner) {
        System.out.println("Silmek istediğiniz kullanıcının ID'sini girin:");
        long id = scanner.nextLong();
        scanner.nextLine();

        User user = library.getUser(id);
        if (user != null) {
            librarian.removeUser(id);
            System.out.println("Kullanıcı silindi: " + user.getName());
        } else {
            System.out.println("Kullanıcı bulunamadı.");
        }
    }

    private static void displayBook(Scanner scanner) {
        System.out.println("Görüntülemek istediğiniz kitabın ID'sini girin:");
        long bookId = scanner.nextLong();
        scanner.nextLine();

        Book book = librarian.getBook(bookId);
        if (book != null) {
            System.out.println(book);
        } else {
            System.out.println("Kitap bulunamadı.");
        }
    }

    private static void displayUser(Scanner scanner) {
        System.out.println("Görüntülemek istediğiniz kullanıcının ID'sini girin:");
        long userId = scanner.nextLong();
        scanner.nextLine();

        User user = library.getUser(userId);
        if (user != null) {
            System.out.println(user);
        } else {
            System.out.println("Kullanıcı bulunamadı.");
        }
    }

    private static void issueBook(Scanner scanner) {
        System.out.println("Ödünç vermek istediğiniz kitabın ID'sini girin:");
        long bookId = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Kullanıcı ID'sini girin:");
        long userId = scanner.nextLong();
        scanner.nextLine();

        Book book = library.getBook(bookId);
        User user = library.getUser(userId);

        if (book != null && user != null && book.isAvailable()) {
            librarian.issueBook(userId, bookId);
            System.out.println("Kitap ödünç verildi: " + book.getName() + " -> " + user.getName());
        } else {
            System.out.println("Kitap veya kullanıcı bulunamadı ya da kitap mevcut değil.");
        }
    }

    private static void  addStart(){
        library.addBook(new Book(1,new Author("şuaip"),"kitap 1",Category.FANTASY,"11",LocalDate.of(2024,10,10),Status.AVAILABLE));
        library.addBook(new Book(2,new Author("hikmet"),"kitap 2",Category.SCIENCE_FICTION,"12",LocalDate.of(2024,10,10),Status.AVAILABLE));
        library.addBook(new Book(3,new Author("levent"),"kitap 3",Category.ROMANCE,"13",LocalDate.of(2024,10,10),Status.AVAILABLE));
        library.addBook(new Book(4,new Author("fatma"),"kitap 4",Category.ROMANCE,"13",LocalDate.of(2024,10,10),Status.AVAILABLE));
        library.addBook(new Book(5,new Author("kemal"),"kitap 5",Category.JOURNAL,"13",LocalDate.of(2024,10,10),Status.AVAILABLE));
        library.addBook(new Book(6,new Author("kemal"),"kitap 6",Category.SCIENCE_FICTION,"13",LocalDate.of(2024,10,10),Status.AVAILABLE));
        library.addBook(new Book(7,new Author("hikmet"),"kitap 7",Category.STUDY,"13",LocalDate.of(2024,10,10),Status.AVAILABLE));
        library.addBook(new Book(8,new Author("ayşe"),"kitap 8",Category.STUDY,"13",LocalDate.of(2024,10,10),Status.AVAILABLE));

        library.addUser(new User(1,"fevzi","fevzi@gmail.com","123"));
        library.addUser(new User(2,"dogancan","dogancan@gmail.com","123"));
    }
}
