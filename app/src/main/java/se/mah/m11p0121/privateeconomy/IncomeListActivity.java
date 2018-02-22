package se.mah.m11p0121.privateeconomy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import se.mah.m11p0121.privateeconomy.Adapters.IncomeAdapter;
import se.mah.m11p0121.privateeconomy.Common.Common;
import se.mah.m11p0121.privateeconomy.Database.DBManager;
import se.mah.m11p0121.privateeconomy.Model.Income;

public class IncomeListActivity extends AppCompatActivity {
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_list);

        dbManager = new DBManager(getBaseContext());
        dbManager.open();

        String startDate = getIntent().getStringExtra(Common.START_DATE);
        String endDate = getIntent().getStringExtra(Common.END_DATE);

        Log.d("Income List Start Date:", startDate);
        Log.d("Income List End Date:", endDate);

        ArrayList<Income> incomesList = dbManager.getIncomes(startDate, endDate);

        RecyclerView recyclerView = findViewById(R.id.lst_income);

        IncomeAdapter adapter = new IncomeAdapter(getBaseContext(), incomesList);
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
