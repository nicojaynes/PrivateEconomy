package se.mah.m11p0121.privateeconomy.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import se.mah.m11p0121.privateeconomy.Common.Common;
import se.mah.m11p0121.privateeconomy.Database.DBManager;
import se.mah.m11p0121.privateeconomy.Model.Income;
import se.mah.m11p0121.privateeconomy.R;

import static android.content.Context.MODE_PRIVATE;

public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.IncomeViewHolder> {
    private static final String TAG = ExpenseAdapter.class.getSimpleName();

    private List<Income> incomeList;
    private LayoutInflater inflater;
    private DBManager dbManager;
    private Context context;


    public IncomeAdapter(Context context, List<Income> incomeList) {
        this.incomeList = incomeList;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        dbManager = new DBManager(context);
        dbManager.open();
    }

    @Override
    public IncomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        View view = inflater.inflate(R.layout.income_layout, parent, false);
        return new IncomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IncomeViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder " + position);
        Income currentObj = incomeList.get(position);
        holder.setData(currentObj, position);
        holder.setListeners();
    }

    @Override
    public int getItemCount() {
        return incomeList.size();
    }

    private void removeItem(int position) {
        Log.d(TAG, "Position removed : " + position);
        dbManager.deleteIncome(incomeList.get(position).getId());
        Toast.makeText(context, "Income succesfully deleted", Toast.LENGTH_LONG).show();
        incomeList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, incomeList.size());
    }

    class IncomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image;
        TextView category, title, amount, date;
        int position;
        ImageView imgDelete;

        IncomeViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_income);
            category = itemView.findViewById(R.id.income_category);
            title = itemView.findViewById(R.id.income_title);
            amount = itemView.findViewById(R.id.income_amount);
            date = itemView.findViewById(R.id.income_date);
            imgDelete = itemView.findViewById(R.id.income_delete_img);
        }

        void setListeners() {
            imgDelete.setOnClickListener(IncomeViewHolder.this);
        }

        void setData(Income currentObj, int position) {
            this.position = position;
            String category = currentObj.getCategory();
            switch (category) {
                case Common.SALARY:
                    image.setImageResource(R.drawable.ic_monetization_on_black_24dp);
                    break;
                case Common.OTHER:
                    image.setImageResource(R.drawable.ic_monetization_on_black_24dp);
            }

            SharedPreferences prefs = context.getSharedPreferences(Common.CURRENCY_PREFERENCE, MODE_PRIVATE);
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

            this.category.setText(category);
            this.title.setText(currentObj.getTitle());
            this.amount.setText(String.valueOf(currentObj.getAmount()) + currencyString);
            this.date.setText(currentObj.getDate());
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "Position clicked : " + position);
            switch (view.getId()) {
                case R.id.income_delete_img:
                    removeItem(position);
                    break;
            }
        }
    }
}
