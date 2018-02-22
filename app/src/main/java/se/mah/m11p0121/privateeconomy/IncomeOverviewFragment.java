package se.mah.m11p0121.privateeconomy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class IncomeOverviewFragment extends Fragment {
    View myFragment;

    Button register_income_btn, see_incomes_btn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static IncomeOverviewFragment newInstance() {
        return new IncomeOverviewFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myFragment = inflater.inflate(R.layout.fragment_income, container, false);
        register_income_btn = myFragment.findViewById(R.id.register_income_btn);
        see_incomes_btn = myFragment.findViewById(R.id.see_incomes_btn);
        register_income_btn.setOnClickListener(new ButtonListener());
        see_incomes_btn.setOnClickListener(new ButtonListener());
        return myFragment;
    }

    private class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.register_income_btn:
                    Intent registerIntent = new Intent(getActivity(), RegisterIncomeActivity.class);
                    startActivity(registerIntent);
                    break;
                case R.id.see_incomes_btn:
                    Intent seeIncomesIntent = new Intent(getActivity(), SeeIncomesActivity.class);
                    startActivity(seeIncomesIntent);
            }
        }
    }
}
