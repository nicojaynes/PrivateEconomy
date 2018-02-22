package se.mah.m11p0121.privateeconomy;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import se.mah.m11p0121.privateeconomy.Database.DBManager;

public class RegisterExpenseActivity extends AppCompatActivity {

    private MaterialEditText edtTitle, edtAmount;
    private AppCompatSpinner spinner;
    private DBManager dbManager;
    String currentYearStr, currentMonthStr, currentDayStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_expense);
        CalendarView calendarView = findViewById(R.id.expenseCalendar);
        edtTitle = findViewById(R.id.edtExpenseTitle);
        edtAmount = findViewById(R.id.edtExpenseAmount);
        Button register_expense_to_database_btn = findViewById(R.id.register_expense_to_database_btn);
        spinner = findViewById(R.id.expenseSpinner);

        register_expense_to_database_btn.setOnClickListener(new ButtonListener());

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month,
                                            int dayOfMonth) {
                currentYearStr = String.valueOf(year);
                currentMonthStr = String.valueOf(month);
                currentDayStr = String.valueOf(dayOfMonth);

                if (month < 10) {
                    currentMonthStr = "0" + (month + 1);
                }

                if (dayOfMonth < 10) {
                    currentDayStr = "0" + dayOfMonth;
                }
                Log.d("Change in expense", currentMonthStr);
            }
        });

        dbManager = new DBManager(this);
        dbManager.open();
    }

    private class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String currentDate = currentYearStr + "-" + currentMonthStr + "-" + currentDayStr;
            if (currentYearStr == null) {
                currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

            }
            Log.d("Date registered: ", currentDate);
            //register the date in database
            dbManager.insertExpense(spinner.getSelectedItem().toString()
                    , currentDate
                    , edtTitle.getText().toString()
                    , Integer.parseInt(edtAmount.getText().toString()));
            Toast.makeText(RegisterExpenseActivity.this, R.string.expense_successfully_registered, Toast.LENGTH_LONG).show();
            edtTitle.setText("");
            edtAmount.setText("");
        }
    }
}
