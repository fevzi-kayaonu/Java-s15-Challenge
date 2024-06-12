package com.workintech.model;

import java.util.Date;

public class Bill {
    private long id;
    private User user;
    private Book book;
    private Date dateIssued;

    public Bill(long id, User user, Book book, Date dateIssued) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.dateIssued = dateIssued;
    }

    public long getID() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public Date getDateIssued() {
        return dateIssued;
    }


    @Override
    public String toString() {
        return " Bill {" +
                " id = " + id + '\'' +
                ", user = " + user.getName() +
                ", book = " + book.getName() +
                ", amount = " + book.getPrice() +
                ", dateIssued = " + dateIssued +
                '}';
    }
}
