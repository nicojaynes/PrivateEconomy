package se.mah.m11p0121.privateeconomy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import se.mah.m11p0121.privateeconomy.Adapters.ExpenseAdapter;
import se.mah.m11p0121.privateeconomy.Common.Common;
import se.mah.m11p0121.privateeconomy.Database.DBManager;
import se.mah.m11p0121.privateeconomy.Model.Expense;

public class ExpenseListActivity extends AppCompatActivity {
    List<Expense> expenseList;
    ExpenseAdapter adapter;
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_list);

        dbManager = new DBManager(getBaseContext());
        dbManager.open();

        String startDate = getIntent().getStringExtra(Common.START_DATE);
        String endDate = getIntent().getStringExtra(Common.END_DATE);

        Log.d("Exp List Start Date:", startDate);
        Log.d("Exp List End Date:", endDate);

        expenseList = dbManager.getExpenses(startDate, endDate);

        RecyclerView recyclerView = findViewById(R.id.lst_expenses);

        adapter = new ExpenseAdapter(getBaseContext(), expenseList);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManagerVertical = new LinearLayoutManager(this);
        linearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManagerVertical);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    protected void onPause() {
        super.onPause();
        dbManager.close();
    }
}