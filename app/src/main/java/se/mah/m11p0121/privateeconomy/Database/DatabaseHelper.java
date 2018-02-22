package se.mah.m11p0121.privateeconomy.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    static final String TABLE_NAME_1 = "EXPENSES";
    static final String TABLE_NAME_2 = "INCOMES";

    // Table columns
    static final String _ID = "_id";
    static final String CATEGORY = "category";
    static final String DATE = "date";
    static final String TITLE = "title";
    static final String PRICE = "price";
    static final String AMOUNT = "amount";

    // Database Information
    private static final String DB_NAME = "ECONOMY.DB";

    // database version
    private static final int DB_VERSION = 1;

    // Create expenses table query
    private static final String CREATE_TABLE_EXPENSES = "create table " + TABLE_NAME_1 + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CATEGORY + " TEXT NOT NULL, " + DATE + " TEXT NOT NULL, " +
            TITLE + " TEXT NOT NULL, " + PRICE + " INTEGER NOT NULL);";

    // Create income table query
    private static final String CREATE_TABLE_INCOME = "create table " + TABLE_NAME_2 + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CATEGORY + " TEXT NOT NULL, " + DATE + " TEXT NOT NULL, " +
            TITLE + " TEXT NOT NULL, " + AMOUNT + " INTEGER NOT NULL);";

    DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_EXPENSES);
        db.execSQL(CREATE_TABLE_INCOME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_2);
        onCreate(db);
    }
}
