package dev.ua._klaidi4_.lmv_lab3.modules;

public class Book {
    private int id;
    private String title;
    private String author;
    private int year;
    private boolean isAvailable;
    public Book() {}
    public Book(String title, String author, int year,  boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.isAvailable = isAvailable;
    }
    public Book(int id, String title, String author, int year, boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.isAvailable = isAvailable;
    }
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

}
