package se.mah.m11p0121.privateeconomy;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import se.mah.m11p0121.privateeconomy.Common.Common;
import se.mah.m11p0121.privateeconomy.Database.DBManager;

import static android.content.Context.MODE_PRIVATE;

public class ShowResultFragment extends Fragment {
    View myFragment;
    TextView header, result;
    String startDate, endDate;
    DBManager dbManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DBManager(getActivity().getBaseContext());
        dbManager.open();
    }

    public static ShowResultFragment newInstance() {
        return new ShowResultFragment();
    }

    public void setDates(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myFragment = inflater.inflate(R.layout.fragment_show_result, container, false);
        header = myFragment.findViewById(R.id.tv_your_result_is);
        result = myFragment.findViewById(R.id.tv_show_result);

        int income = dbManager.getIncomeAmount(startDate, endDate);
        int expenses = dbManager.getExpenseAmount(startDate, endDate);
        int totalResult = income - expenses;

        SharedPreferences prefs = myFragment.getContext().getSharedPreferences(Common.CURRENCY_PREFERENCE, MODE_PRIVATE);
        String restoredText = prefs.getString(Common.CURRENCY, null);
        String currencyString = "";
        if (restoredText != null) {
            currencyString = prefs.getString(Common.CURRENCY, "No currency defined");
        }

        switch (currencyString) {
            case Common.EUROS:
                currencyString = "€";
                break;
            case Common.POUNDS:
                currencyString = "₤";
                break;
            case Common.DOLLARS:
                currencyString = "$";
                break;
            case Common.SEK:
                currencyString = " kr";
                break;
        }

        header.setText("Your result between the period " + startDate + " and " + endDate + " is:");

        result.setText("Income: " + income + " " + currencyString +
                "\nExpenses: " + expenses + " " + currencyString +
                "\nTotal Result: " + totalResult + " " + currencyString);
        return myFragment;
    }
}
