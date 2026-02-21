package dev.ua._klaidi4_.lmv_lab3.modules;

public class Reservations {
    private User user_id;
    private Book book_id;
    public Reservations() {}
    public Reservations(User user_id, Book book_id) {
        this.user_id = user_id;
        this.book_id = book_id;
    }
    public User getUser_id() {
        return user_id;
    }
    public Book getBook_id() {
        return book_id;
    }
}
