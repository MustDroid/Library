package com.emoke;

import java.util.List;

public interface IBookRepository {
    List<Book> getAllBooks();
    void addBook(Book book);

    List<Book> getBooksByCategory(int idWordCategory);
    void rentBook(int id);
}
