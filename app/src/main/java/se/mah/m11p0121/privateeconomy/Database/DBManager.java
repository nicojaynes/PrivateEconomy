package se.mah.m11p0121.privateeconomy.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import se.mah.m11p0121.privateeconomy.Model.Expense;
import se.mah.m11p0121.privateeconomy.Model.Income;

public class DBManager {

    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public void open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void insertExpense(String category, String date, String title, int price) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.CATEGORY, category);
        contentValue.put(DatabaseHelper.DATE, date);
        contentValue.put(DatabaseHelper.TITLE, title);
        contentValue.put(DatabaseHelper.PRICE, price);
        database.insert(DatabaseHelper.TABLE_NAME_1, null, contentValue);
    }

    public void insertIncome(String category, String date, String title, int amount) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.CATEGORY, category);
        contentValue.put(DatabaseHelper.DATE, date);
        contentValue.put(DatabaseHelper.TITLE, title);
        contentValue.put(DatabaseHelper.AMOUNT, amount);
        database.insert(DatabaseHelper.TABLE_NAME_2, null, contentValue);
    }

    public ArrayList<Expense> getExpenses(String startDate, String endDate) {
        int categoryIndex, dateIndex, titleIndex, priceIndex, idIndex;

        Cursor cursor = database.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME_1 +
                " WHERE DATE(" + DatabaseHelper.DATE + ") BETWEEN '" +
                startDate + "' AND '" + endDate + "' ORDER BY " + DatabaseHelper.DATE, null);

        ArrayList<Expense> expenses = new ArrayList<>();
        categoryIndex = cursor.getColumnIndex(DatabaseHelper.CATEGORY);
        dateIndex = cursor.getColumnIndex(DatabaseHelper.DATE);
        titleIndex = cursor.getColumnIndex(DatabaseHelper.TITLE);
        priceIndex = cursor.getColumnIndex(DatabaseHelper.PRICE);
        idIndex = cursor.getColumnIndex(DatabaseHelper._ID);

        for(int i=0; i< cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            expenses.add(i, new Expense(cursor.getString(categoryIndex),
                    cursor.getString(dateIndex),
                    cursor.getString(titleIndex),
                    cursor.getInt(priceIndex),
                    cursor.getInt(idIndex)));
        }
        cursor.close();
        return expenses;
    }

    public ArrayList<Income> getIncomes(String startDate, String endDate) {
        int categoryIndex, dateIndex, titleIndex, amountIndex, idIndex;

        Cursor cursor = database.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME_2 +
                " WHERE " + DatabaseHelper.DATE + " BETWEEN '" +
                startDate + "' AND '" + endDate + "' ORDER BY " + DatabaseHelper.DATE, null);

        ArrayList<Income> incomes = new ArrayList<>();
        categoryIndex = cursor.getColumnIndex(DatabaseHelper.CATEGORY);
        dateIndex = cursor.getColumnIndex(DatabaseHelper.DATE);
        titleIndex = cursor.getColumnIndex(DatabaseHelper.TITLE);
        amountIndex = cursor.getColumnIndex(DatabaseHelper.AMOUNT);
        idIndex = cursor.getColumnIndex(DatabaseHelper._ID);

        for(int i=0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            incomes.add(i, new Income(cursor.getString(categoryIndex),
                    cursor.getString(dateIndex),
                    cursor.getString(titleIndex),
                    cursor.getInt(amountIndex),
                    cursor.getInt(idIndex)));
        }

        cursor.close();
        return incomes;
    }

    public int getExpenseAmount(String startDate, String endDate) {
        Cursor cursor = database.rawQuery("SELECT SUM ("
                + DatabaseHelper.PRICE + ") FROM " + DatabaseHelper.TABLE_NAME_1 + " WHERE " + DatabaseHelper.DATE
                + " BETWEEN '" +  startDate + "' AND '" + endDate + "'", null);
        cursor.moveToFirst();
        int i = cursor.getInt(0);
        cursor.close();
        return i;
    }

    public int getIncomeAmount(String startDate, String endDate) {
        Cursor cursor = database.rawQuery("SELECT SUM ("
                + DatabaseHelper.AMOUNT + ") FROM " + DatabaseHelper.TABLE_NAME_2 + " WHERE " + DatabaseHelper.DATE
                + " BETWEEN '" +  startDate + "' AND '" + endDate + "'", null);
        cursor.moveToFirst();
        int i = cursor.getInt(0);
        cursor.close();
        return i;
    }

    public void deleteExpense(int id) {
        database.execSQL("DELETE FROM " + DatabaseHelper.TABLE_NAME_1 + " WHERE " + DatabaseHelper._ID + " = " + id);
    }

    public void deleteIncome(int id) {
        database.execSQL("DELETE FROM " + DatabaseHelper.TABLE_NAME_2 + " WHERE " + DatabaseHelper._ID + " = " + id);
    }
}