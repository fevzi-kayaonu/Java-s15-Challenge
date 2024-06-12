package com.workintech.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class User extends Person {
    private String password;
    private List<Book> borrowedBooks = new ArrayList<>();
    private static final int BOOK_LIMIT = 5;

    public User(long id, String name, String email, String password) {
        super(id, name, email);
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public void borrowBook(Book book) {
        if (borrowedBooks.size() < BOOK_LIMIT && book.getStatus() == Status.AVAILABLE) {
            borrowedBooks.add(book);
            book.setStatus(Status.BORROWED);
            System.out.println(getName() + " ödünç aldı " + book.getName());
            System.out.println(getBill(book).toString());
        }
        else if(borrowedBooks.size() >= BOOK_LIMIT ) {
            System.out.println(BOOK_LIMIT+" elinizde bu kadar kitap var daha fazla ödünç alamazsınız.");
        }
    }

    public void returnBook(Book book) {
        if (borrowedBooks.remove(book)) {
            book.setStatus(Status.AVAILABLE);
            System.out.println(getName() +  book.getName() + " geri verdi ");
            System.out.println(getBill(book).toString()+ " faturası alındı ücret geri verildi.");

        } else {
            System.out.println("Ödünç alınan kitaplar arasında bulunamadı.");
        }
    }

    public Bill getBill(Book book) {
        Bill bill = new Bill(book.getID(), this, book, new Date());
        return bill;
    }

    public  List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    @Override
    public String toString() {
        return "User{" +
                " id " + getId() +
                " name " + getName() +
                " email " + getEmail() +
                " borrowedBooks = " + borrowedBooks +
                '}';
    }
}
