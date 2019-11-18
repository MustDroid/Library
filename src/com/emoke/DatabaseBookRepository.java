package com.emoke;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseBookRepository implements IBookRepository{
    private final Connection connection;

    public DatabaseBookRepository(Connection connection) {
        this.connection = connection;
        createTableIfNotExists();
    }

    public void createTableIfNotExists() {
        Statement st = null;
        try {
            st = connection.createStatement();
            st.execute("CREATE TABLE IF NOT EXISTS `books` (\n" +
                    "  `id` int(11) NOT NULL,\n" +
                    "  `title` varchar(50) NOT NULL,\n" +
                    "  `autor` varchar(100) NOT NULL,\n" +
                    "  `category` varchar(50) NOT NULL,\n" +
                    "  `isbn` int(20) NOT NULL,\n" +
                    "  `fsk` int(20) NOT NULL,\n" +
                    "  `publishingCompany` varchar(100) NOT NULL,\n" +
                    "  `appearance` date NOT NULL,\n" +
                    "  `amountPages` int(20) NOT NULL,\n" +
                    "  `language` varchar(200) NOT NULL,\n" +
                    "  `col` int(20) NOT NULL,\n" +
                    "  `row` int(20) NOT NULL,\n" +
                    "  `isAvailable` tinyint(1) NOT NULL\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();

        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM books");
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                books.add(getBookFromResultSet(rs));
            }

            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return books;
    }

    @Override
    public void addBook(Book book) {
        try {
            PreparedStatement st = connection.prepareStatement("INSERT INTO books (title, autor, isbn, fsk, publishingCompany, appearance, amountPages, language,IdWordCategory, bookInStock)" +
                    " VALUES(?, ?, ?, ?, ?, ?, ?, ?,?,?)");
            st.setString(1, book.getTitle());
            st.setString(2, book.getAutor());
            st.setInt(3, book.getIsbn());
            st.setInt(4, book.getFsk());
            st.setString(5, book.getPublishingCompany());
            st.setDate(6, book.getAppearance());
            st.setInt(7, book.getAmountPages());
            st.setString(8, book.getLanguage());
            st.setInt(9,book.getIdWordCategory());
            st.setInt(10,book.getBookInStock());

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Book> getBooksByCategory(int idWordCategory) {
        List<Book> books = new ArrayList<>();

        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM books WHERE idWordCategory=?");
            st.setInt(1, idWordCategory);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                books.add(getBookFromResultSet(rs));
            }

            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return books;
    }

    @Override
    public void rentBook(int id) {
        try {
            PreparedStatement st = connection.prepareStatement("UPDATE books SET bookInStock=bookInStock-1 WHERE id=?");
            st.setInt(1,id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private Book getBookFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String title = rs.getString("title");
        String autor = rs.getString("autor");
        int isbn = rs.getInt("isbn");
        int fsk = rs.getInt("fsk");
        String publishingCompany = rs.getString("publishingCompany");
        Date appearance = rs.getDate("appearance");
        int amountPages = rs.getInt("amountPages");
        String language = rs.getString("language");
        //int bookInStock = rs.getInt("bookInStock");
        int idWordCategory = rs.getInt("idWordCategory");
        int bookInStock = rs.getInt("bookInStock");

        return new Book(id,title,autor,isbn,fsk,publishingCompany,appearance,amountPages,language, idWordCategory, bookInStock);
    }
}
