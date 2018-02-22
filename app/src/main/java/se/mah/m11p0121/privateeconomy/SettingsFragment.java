package se.mah.m11p0121.privateeconomy;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import se.mah.m11p0121.privateeconomy.Common.Common;

import static android.content.Context.MODE_PRIVATE;

public class SettingsFragment extends Fragment {
    View myFragment;
    AppCompatSpinner currencySpinner;
    Button apply_settings_btn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myFragment = inflater.inflate(R.layout.fragment_settings, container, false);
        currencySpinner = myFragment.findViewById(R.id.currencySpinner);
        apply_settings_btn = myFragment.findViewById(R.id.apply_settings_btn);

        apply_settings_btn.setOnClickListener(new ButtonListener());

        return myFragment;
    }

    private class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            SharedPreferences.Editor editor = getActivity().getSharedPreferences(Common.CURRENCY_PREFERENCE, MODE_PRIVATE).edit();
            editor.putString(Common.CURRENCY, currencySpinner.getSelectedItem().toString());
            editor.apply();

            Toast.makeText(getContext(), R.string.settings_applied, Toast.LENGTH_LONG).show();
        }
    }
}
