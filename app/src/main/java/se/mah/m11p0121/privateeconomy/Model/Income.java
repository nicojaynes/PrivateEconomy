package se.mah.m11p0121.privateeconomy.Model;

public class Income {
    private String category;
    private String date;
    private String title;
    private int amount;

    private int id;

    public Income(String category, String date, String title, int amount, int id) {
        this.category = category;
        this.date = date;
        this.title = title;
        this.amount = amount;
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

    public int getAmount() {
        return amount;
    }

    public int getId() {
        return id;
    }

}
