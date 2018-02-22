package se.mah.m11p0121.privateeconomy.Model;

import java.io.Serializable;

public class Expense implements Serializable {
    private String category;
    private String date;
    private String title;
    private int price;
    private int id;

    public Expense(String category, String date, String title, int price, int id) {
        this.category = category;
        this.date = date;
        this.title = title;
        this.price = price;
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }
}
