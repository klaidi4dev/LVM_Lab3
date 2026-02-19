package dev.ua._klaidi4_.lmv_lab3.database;

import dev.ua._klaidi4_.lmv_lab3.modules.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqLite {
    private static Connection connection;
    public static void connect() {
        try {
            System.out.println("Підключення до бази даних...");
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            System.out.println("Підключення закінчено.");
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS user(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT," +
                    "email TEXT)");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS books(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "title TEXT," +
                    "author TEXT," +
                    "year INTEGER," +
                    "isAvailable BOOLEAN DEFAULT 1)");
            System.out.println("Таблиця books завантажена.");
            System.out.println("Таблиця user завантажена.");
        } catch (SQLDataException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void addBook(Book book) {
        try {
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO books (title, author, year, isAvailable) VALUES (?, ?, ?, ?)");
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setInt(3, book.getYear());
            pstmt.setBoolean(4, book.isAvailable());
            pstmt.executeUpdate();
            pstmt.close();
            System.out.println("Добавлен новая книга: " + book.getTitle() + " Author: " + book.getAuthor() + " Year: " + book.getYear() + " isAvailable: " + book.isAvailable());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void addUser(String name, String email) {
        try {
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO user (name, email) VALUES (?, ?)");
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.executeUpdate();
            pstmt.close();
            System.out.println("Добавлен новый user: " + name + " Email: " + email);
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void getUser(int id) {
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM user WHERE id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("id"));
                System.out.println(rs.getString("name"));
                System.out.println(rs.getString("email"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static String getUser(String email) {
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM user WHERE email  = ?");
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            String result = rs.next() ? rs.getString("email") : null;
            rs.close();
            pstmt.close();
            return result;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static Book getBook(String title) {
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM books WHERE title = ?");
            pstmt.setString(1, title);
            ResultSet rs = pstmt.executeQuery();
            Book result = null;
            if (rs.next()) {
                result = new Book(
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("year"),
                        rs.getBoolean("isAvailable")
                );
            } else {
                System.out.println("Книгу не знайдено");
            }
            pstmt.close();
            rs.close();
            return  result;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM books");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Book book = new Book(
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("year"),
                        rs.getBoolean("isAvailable")
                );
                books.add(book);
            }
            pstmt.close();
            rs.close();
            return books;
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
