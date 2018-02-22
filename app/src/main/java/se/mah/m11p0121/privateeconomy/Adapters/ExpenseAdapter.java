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
import se.mah.m11p0121.privateeconomy.Model.Expense;
import se.mah.m11p0121.privateeconomy.R;

import static android.content.Context.MODE_PRIVATE;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {
    private static final String TAG = ExpenseAdapter.class.getSimpleName();

    private List<Expense> expenseList;
    private LayoutInflater inflater;
    private DBManager dbManager;
    private Context context;


    public ExpenseAdapter(Context context, List<Expense> expenseList) {
        this.expenseList = expenseList;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        dbManager = new DBManager(context);
        dbManager.open();
    }

    @Override
    public ExpenseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        View view = inflater.inflate(R.layout.expense_layout, parent, false);
        return new ExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExpenseViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder " + position);
        Expense currentObj = expenseList.get(position);
        holder.setData(currentObj, position);
        holder.setListeners();
    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }

    private void removeItem(int position) {
        Log.d(TAG, "Position removed : " + position);
        dbManager.deleteExpense(expenseList.get(position).getId());
        Toast.makeText(context, "Expense succesfully deleted", Toast.LENGTH_LONG).show();
        expenseList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, expenseList.size());
    }

    class ExpenseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image;
        TextView category, title, price, date;
        int position;
        ImageView imgDelete;

        ExpenseViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_expense);
            category = itemView.findViewById(R.id.expense_category);
            title = itemView.findViewById(R.id.expense_title);
            price = itemView.findViewById(R.id.expense_amount);
            date = itemView.findViewById(R.id.expense_date);
            imgDelete = itemView.findViewById(R.id.expense_delete_img);
        }

        void setListeners() {
            imgDelete.setOnClickListener(ExpenseViewHolder.this);
        }

        void setData(Expense currentObj, int position) {
            this.position = position;
            String category = currentObj.getCategory();
            switch (category) {
                case Common.GROCERIES:
                    image.setImageResource(R.drawable.ic_local_grocery_store_black_24dp);
                    break;
                case Common.LEISURE:
                    image.setImageResource(R.drawable.ic_golf_course_black_24dp);
                    break;
                case Common.LIVING:
                    image.setImageResource(R.drawable.ic_home_black_24dp);
                    break;
                case Common.TRAVEL:
                    image.setImageResource(R.drawable.ic_airplanemode_active_black_24dp);
                    break;
                case Common.OTHER:
                    image.setImageResource(R.drawable.ic_monetization_on_black_24dp);
                    break;
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
            this.price.setText(String.valueOf(currentObj.getPrice()) + currencyString);
            this.date.setText(currentObj.getDate());
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "Position clicked : " + position);
            switch (view.getId()) {
                case R.id.expense_delete_img:
                    removeItem(position);
                    break;
            }
        }
    }
}
