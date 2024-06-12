package com.workintech.model;

import java.util.*;
public class Library {
    private Map<Long, Book> books = new HashMap<>();
    private Map<Long, User> users = new HashMap<>();


    public void addBook(Book book) {
        books.put(book.getID(), book);
    }

    public void removeBook(long bookID) {
        Book book = books.remove(bookID);
        if (book != null) {
            System.out.println("Kitap silindi: " + book.getName());
        } else {
            System.out.println("Kitap bulunamadı.");
        }
    }

    public void updateBook(Book book) {
        books.put(book.getID(), book);
        System.out.println("Kitap güncellendi: " + book.getName());
    }

    public Book getBook(long bookID) {
        return books.get(bookID);
    }

    public List<Book> getBooksByAuthor(String authorName) {
        List<Book> result = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getAuthor().getName().equalsIgnoreCase(authorName)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> getBooksByCategory(Category category) {
        List<Book> result = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getCategory() == category) {
                result.add(book);
            }
        }
        return result;
    }

    public void addUser(User user) {
        users.put(user.getId(), user);
        System.out.println("Kullanıcı eklendi: " + user.getName());
    }

    public void removeUser(long userID) {
        User user = users.remove(userID);
        if (user != null) {
            System.out.println("Kullanıcı silindi: " + user.getName());
        } else {
            System.out.println("Kullanıcı bulunamadı.");
        }
    }

    public User getUser(long userID) {
        return users.get(userID);
    }

    public void issueBook(long bookID, long userID) {
        User user = users.get(userID);
        Book book = books.get(bookID);
        if (user != null && book != null) {
            user.borrowBook(book);
        } else if(user != null ){
            System.out.println("kitap bulunamadı.");
        } else if(book != null){
            System.out.println("kullanıcı bulunamadı.");
        }
    }

    public User getUserByUsername(String username) {
        for (User user : users.values()) {
            if (user.getName().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }

    public String getWhoHaveBook(long bookID) {
        Book book = books.get(bookID);
        for(User user: users.values()){
            if(user.getBorrowedBooks().contains(book)){
                return user.getName();
            }
        }
        return null;
    }
}
