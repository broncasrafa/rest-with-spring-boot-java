package com.rsfrancisco.restwithspringbootjava.domain.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private Double price;
    @Column(name="launch_date", nullable = false)
    private Date lauchDate;

    public Book() {}
    public Book(Long id, String author, String title, Double price, Date lauchDate) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.price = price;
        this.lauchDate = lauchDate;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getLauchDate() {
        return lauchDate;
    }
    public void setLauchDate(Date lauchDate) {
        this.lauchDate = lauchDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) &&
                Objects.equals(author, book.author) &&
                Objects.equals(title, book.title) &&
                Objects.equals(price, book.price) &&
                Objects.equals(lauchDate, book.lauchDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, title, price, lauchDate);
    }
}
