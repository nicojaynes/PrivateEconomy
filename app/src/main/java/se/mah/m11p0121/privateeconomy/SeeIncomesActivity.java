package se.mah.m11p0121.privateeconomy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import se.mah.m11p0121.privateeconomy.Common.Common;

public class SeeIncomesActivity extends AppCompatActivity {
    private String startMonth, startDay, endMonth, endDay, startDate, endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_incomes);
        CalendarView see_incomes_calendar_startdate = findViewById(R.id.see_incomes_calendar_startdate);
        CalendarView see_incomes_calendar_enddate = findViewById(R.id.see_incomes_calendar_enddate);

        see_incomes_calendar_startdate.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month,
                                            int dayOfMonth) {

                startMonth = String.valueOf(month);
                startDay = String.valueOf(dayOfMonth);

                if(month < 10) {
                    startMonth = "0" + (month + 1);
                }

                if(dayOfMonth < 10) {
                    startDay = "0" + dayOfMonth;
                }
                startDate = year + "-" + startMonth + "-" + startDay;
            }
        });

        see_incomes_calendar_enddate.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month,
                                            int dayOfMonth) {
                endMonth = String.valueOf(month);
                endDay = String.valueOf(dayOfMonth);

                if(month < 10) {
                    endMonth = "0" + (month + 1);
                }

                if(dayOfMonth < 10) {
                    endDay = "0" + dayOfMonth;
                }

                endDate = year + "-" + endMonth + "-" + endDay;
            }
        });

        Button btn_see_incomes = findViewById(R.id.btn_see_incomes);
        btn_see_incomes.setOnClickListener(new ButtonListener());

    }

    private class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(SeeIncomesActivity.this, IncomeListActivity.class);
            if(startDate == null) {
                startDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
            }

            if(endDate == null) {
                endDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
            }
            intent.putExtra(Common.START_DATE, startDate);
            intent.putExtra(Common.END_DATE, endDate);
            startActivity(intent);
        }
    }
}
