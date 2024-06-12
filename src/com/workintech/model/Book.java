package com.workintech.model;

import java.time.LocalDate;
import java.util.Objects;

public class Book {
    private long id;
    private Author author;
    private String name;
    private Category category;
    private String edition;
    private LocalDate dateOfPurchase;
    private Status status;
    private final double price = 50;

    public Book(long id, Author author, String name, Category category, String edition, LocalDate dateOfPurchase, Status status) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.category = category;
        this.edition = edition;
        this.dateOfPurchase = dateOfPurchase;
        this.status = status;
    }



    public void setAuthor(Author author) {
        this.author = author;
    }

    public long getID() {
        return id;
    }

    public Author getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public String getEdition() {
        return edition;
    }

    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }

    public Status getStatus() {
        return status;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isAvailable(){
        if(status==Status.AVAILABLE)
            return true;
      return false;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public void setDateOfPurchase(LocalDate dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name = " + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
