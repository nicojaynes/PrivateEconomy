package se.mah.m11p0121.privateeconomy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ExpenseOverviewFragment extends Fragment{
    View myFragment;

    Button register_expense_btn, see_expenses_btn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static ExpenseOverviewFragment newInstance() {
        return new ExpenseOverviewFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myFragment = inflater.inflate(R.layout.fragment_expenses, container, false);
        register_expense_btn = myFragment.findViewById(R.id.register_expense_btn);
        see_expenses_btn = myFragment.findViewById(R.id.see_expenses_btn);
        register_expense_btn.setOnClickListener(new ButtonListener());
        see_expenses_btn.setOnClickListener(new ButtonListener());
        return myFragment;
    }

    private class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.register_expense_btn:
                    Intent registerIntent = new Intent(getActivity(), RegisterExpenseActivity.class);
                    startActivity(registerIntent);
                    break;
                case R.id.see_expenses_btn:
                    Intent seeIncomesIntent = new Intent(getActivity(), SeeExpensesActivity.class);
                    startActivity(seeIncomesIntent);
            }
        }
    }
}
