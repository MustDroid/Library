package com.emoke;

import java.sql.Date;

public class Book {
    private int id;
    private String title;
    private String autor;
    private int isbn;
    private int fsk;
    private String publishingCompany;
    private Date appearance; //TODO convert to String
    private int amountPages;
    private String language;
    private int idWordCategory;
    private int bookInStock;

    public Book(int id, String title, String autor, int isbn, int fsk, String publishingCompany, Date appearance, int amountPages, String language, int idWordCategory, int bookInStock) {
        this.id = id;
        this.title = title;
        this.autor = autor;
        this.isbn = isbn;
        this.fsk = fsk;
        this.publishingCompany = publishingCompany;
        this.appearance = appearance;
        this.amountPages = amountPages;
        this.language = language;
        this.idWordCategory = idWordCategory;
        this.bookInStock = bookInStock;
    }

    public String getTitle() {
        return title;
    }

    public String getAutor() {
        return autor;
    }

    public int getIsbn() {
        return isbn;
    }

    public int getFsk() {
        return fsk;
    }

    public String getPublishingCompany() {
        return publishingCompany;
    }

    public Date getAppearance() {
        return appearance;
    }

    public int getAmountPages() {
        return amountPages;
    }

    public String getLanguage() {
        return language;
    }

    public int getId() {
        return id;
    }

    public int getBookInStock() {
        return bookInStock;
    }

    public int getIdWordCategory() {
        return idWordCategory;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                " 1.title: " + title + "|" + '\'' +
                " 2.autor: " + autor + "|" + '\'' +
                " 3.isbn: " + isbn + "|" +  '\'' +
                " 4.fsk: " + fsk + "|" +  '\'' +
                " 5.publishingCompany: " + publishingCompany +  "|" + '\'' +
                " 6.appearance: " + appearance + "|" +  '\'' +
                " 7.amountPages: " + amountPages + "|" +  '\'' +
                " 8.language=: " + language +  "|" +  '\'' +
                " 9.idWordCategory: " + idWordCategory +  "|" +  '\'' +
                " 10.bookInStock: " + bookInStock +  "|" +  '\'' +
                '}';
    }
}
