package se.mah.m11p0121.privateeconomy;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationItemView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationItemView = findViewById(R.id.navigation);

        bottomNavigationItemView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                switch (item.getItemId()) {
                    case R.id.action_expenses:
                        selectedFragment = ExpenseOverviewFragment.newInstance();
                        break;
                    case R.id.action_incomes:
                        selectedFragment = IncomeOverviewFragment.newInstance();
                        break;
                    case R.id.action_result:
                        selectedFragment = ResultFragment.newInstance();
                        break;
                    case R.id.action_settings:
                        selectedFragment = SettingsFragment.newInstance();
                        break;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.addToBackStack(null);
                transaction.commit();

                return true;
            }
        });

        setDefaultFragment();
    }

    private void setDefaultFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, ResultFragment.newInstance());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void showResultFragment(String startDate, String endDate) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        ShowResultFragment fragment = ShowResultFragment.newInstance();
        fragment.setDates(startDate, endDate);
        transaction.replace(R.id.frame_layout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
