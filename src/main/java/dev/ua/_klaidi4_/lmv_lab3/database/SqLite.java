package dev.ua._klaidi4_.lmv_lab3.database;

import dev.ua._klaidi4_.lmv_lab3.modules.Book;
import dev.ua._klaidi4_.lmv_lab3.modules.Reservations;
import dev.ua._klaidi4_.lmv_lab3.modules.User;

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
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS reservations(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "user_id INTEGER," +
                    "book_id INTEGER)");
            System.out.println("Таблиця books завантажена.");
            System.out.println("Таблиця user завантажена.");
            System.out.println("Таблиця reservations завантажена.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
    public static void addUser(User user) {
        try {
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO user (name, email) VALUES (?, ?)");
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.executeUpdate();
            pstmt.close();
            System.out.println("Добавлен новый user: " + user.getName() + " Email: " + user.getEmail());
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void addReservations(Reservations reservations) {
        try {
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO reservations (user_id, book_id) VALUES (?, ?)");
            pstmt.setInt(1, reservations.getUser_id().getId());
            pstmt.setInt(2, reservations.getBook_id().getId());
            pstmt.executeUpdate();

            PreparedStatement pstmtUpdate = connection.prepareStatement("UPDATE books SET isAvailable = ? WHERE id = ?");
            pstmtUpdate.setBoolean(1, reservations.getBook_id().setIsAvailable(false));
            pstmtUpdate.setInt(2, reservations.getBook_id().getId());
            pstmtUpdate.executeUpdate();

            pstmtUpdate.close();
            pstmt.close();
            System.out.println("Книгу успішно зарезервовано і її статус оновлено!");
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void removeReservations(Reservations reservations) {
        try {
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM reservations WHERE book_id = ? AND user_id = ?");
            pstmt.setInt(1, reservations.getBook_id().getId());
            pstmt.setInt(2, reservations.getUser_id().getId());
            pstmt.executeUpdate();

            PreparedStatement pstmsUpdate = connection.prepareStatement("UPDATE books SET isAvailable = ? WHERE id = ?");
            pstmsUpdate.setBoolean(1, reservations.getBook_id().setIsAvailable(true));
            pstmsUpdate.setInt(2, reservations.getBook_id().getId());
            pstmsUpdate.executeUpdate();
            pstmt.close();
            pstmsUpdate.close();
            System.out.println("Книгу знято з бронювання");
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static List<Reservations> getReservations() {
        List<Reservations> list = new ArrayList<>();
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM reservations");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int userId = rs.getInt("user_id");
                int bookId = rs.getInt("book_id");

                User user = getUser(userId);
                Book book = getBook(bookId);

                Reservations result = new Reservations(user, book);
                list.add(result);
            }
            rs.close();
            pstmt.close();

            return list;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static User getUser(String email) {
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM user WHERE email  = ?");
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            User result = null;
            while (rs.next()) {
                result = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email")
                );
            }
            rs.close();
            pstmt.close();
            return result;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static User getUser(int id) {
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM user WHERE id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            User result = null;
            while (rs.next()) {
                result = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email")
                );
            }
            rs.close();
            pstmt.close();
            return result;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static Book getBook(int id) {
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM books WHERE id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            Book result = null;
            while (rs.next()) {
                result = new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("year"),
                        rs.getBoolean("isAvailable")
                );
            }
            rs.close();
            pstmt.close();
            return result;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static List<User> getUsers() {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM user");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email")
                );
                users.add(user);
            }
            rs.close();
            pstmt.close();
            return users;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static void removeUser(int id) {
        try{
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM user WHERE id = ?");
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            pstmt.close();
            System.out.println("З таблиці видалено користувача з ID: " + id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void removeBook(int id) {
        try {
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM books WHERE id = ?");
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            pstmt.close();
            System.out.println("З таблиці було видалено книгу з ID: " + id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static boolean isBookExists(int id) {
        boolean exists = false;
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT 1 FROM books WHERE id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            exists = rs.next();
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return exists;
    }
    public static boolean isUserExists(int id) {
        boolean exists = false;
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT 1 FROM user WHERE id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            exists = rs.next();
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return exists;
    }
    public static List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM books");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("year"),
                        rs.getBoolean("isAvailable")
                );
                books.add(book);
            }
            rs.close();
            pstmt.close();
            return books;
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    public static List<Book> searchBooks(String keyword) {
        List<Book> books = new ArrayList<>();

        try (PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM books WHERE title LIKE ? OR author LIKE ? OR year LIKE ?")) {

            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            pstmt.setString(3, "%" + keyword + "%");

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    books.add(new Book(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getInt("year"),
                            rs.getBoolean("isAvailable")
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println("Помилка пошуку: " + e.getMessage());
        }
        return books;
    }
}
