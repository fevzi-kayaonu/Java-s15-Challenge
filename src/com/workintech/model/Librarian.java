package com.workintech.model;

public class Librarian extends Person {
    private String password;
    private Library library;

    public Librarian(long id, String name, String email, String password, Library library) {
        super(id, name, email);
        this.password = password;
        this.library = library;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public void addUser(User user) {
        library.addUser(user);
        System.out.println("Kulllanıcı eklendi: " + user.getName());
    }

    public void removeUser(long userId) {
        library.removeUser(userId);
    }

    public void addBook(Book book) {
        library.addBook(book);
    }

    public void removeBook(long bookID) {
        library.removeBook(bookID);
    }

    public void updateBook(Book book) {
        library.updateBook(book);
    }

    public Book getBook(long bookID) {
        return library.getBook(bookID);
    }

    public void issueBook(long bookID, long userID) {
        library.issueBook(bookID, userID);
    }

}
