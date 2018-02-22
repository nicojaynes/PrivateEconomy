package se.mah.m11p0121.privateeconomy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ResultFragment extends Fragment {
    View myFragment;
    CalendarView calendarStartDate, calendarEndDate;
    Button btn_see_result;
    private String startMonth, startDay, endMonth, endDay, startDate, endDate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static ResultFragment newInstance() {
        return new ResultFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myFragment = inflater.inflate(R.layout.fragment_result, container, false);
        calendarStartDate = myFragment.findViewById(R.id.result_calendar_start_date);
        calendarEndDate = myFragment.findViewById(R.id.result_calendar_end_date);

        calendarStartDate.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

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

        calendarEndDate.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

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

        btn_see_result = myFragment.findViewById(R.id.btn_see_result);
        btn_see_result.setOnClickListener(new ButtonListener());
        return myFragment;
    }

    private class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            MainActivity mainActivity = (MainActivity) getActivity();
            if(startDate == null) {
                startDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
            }

            if(endDate == null) {
                endDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
            }
            mainActivity.showResultFragment(startDate, endDate);
        }
    }
}
