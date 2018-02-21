package com.greentech.jyotirmay.cleardebt.com.greentech.jyotirmay.cleardebt.fragment;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.greentech.jyotirmay.cleardebt.R;
import com.greentech.jyotirmay.cleardebt.com.greentech.jyotirmay.cleardebt.com.greentech.jyotirmay.cleardebt.recycler_handler.TransactionAdaptor;
import com.greentech.jyotirmay.cleardebt.com.greentech.jyotirmay.cleardebt.com.greentech.jyotirmay.cleardebt.recycler_handler.DataBean;
import com.greentech.jyotirmay.cleardebt.com.greentech.jyotirmay.cleardebt.database.DBHandler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TransectionFragment extends android.support.v4.app.Fragment {

    static TextView dateTextView, nodataTextView, balanceView;
    ConstraintLayout dateLayout;
    RecyclerView dailyTransactionView;


    static DBHandler dbHandler;

    public TransectionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transection, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle(R.string.title_transaction);

        dateTextView = getView().findViewById(R.id.tvSelectedDate);
        nodataTextView = getView().findViewById(R.id.tvNoData);
        dateLayout = getView().findViewById(R.id.layoutDatePicker);
        dailyTransactionView = getView().findViewById(R.id.recycleView);
        balanceView = getView().findViewById(R.id.tvBalance);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int date = calendar.get(Calendar.DATE);
        final int day = calendar.get(Calendar.DAY_OF_WEEK);
        final String dayOfName[] = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        final String monthOfYear[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        dateTextView.setText(dayOfName[day - 1] + " " + monthOfYear[month] + " " + String.valueOf(date));

        dateLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE");
                        Date date1 = new Date(year, month, date - 1);
                        String dayOfWeek = dateFormat.format(date1);
                        dateTextView.setText(dayOfWeek + " " + monthOfYear[month] + " " + date);

                        dbHandler = new DBHandler(getActivity().getApplicationContext());
                        String currentDate = dateTextView.getText().toString();
                        Cursor allTransactionCursor = dbHandler.readTransaction(currentDate);
                        List<DataBean> allDataBean = new ArrayList<>();
                        if (allTransactionCursor != null) {
                            int idIndex = allTransactionCursor.getColumnIndex(DBHandler.COL_ID);
                            int valueIndex = allTransactionCursor.getColumnIndex(DBHandler.COL_VALUE);
                            int categoryIndex = allTransactionCursor.getColumnIndex(DBHandler.COL_CATEGORY);
                            while (allTransactionCursor.moveToNext()) {
                                long IDFromDb = allTransactionCursor.getLong(idIndex);
                                String valueFromDb = allTransactionCursor.getString(valueIndex);
                                String categoryFromDb = allTransactionCursor.getString(categoryIndex);

                                DataBean data = new DataBean();
                                data.setId(IDFromDb);
                                data.setValue(valueFromDb);
                                data.setCategory(categoryFromDb);
                                allDataBean.add(data);
                            }
                            allTransactionCursor.close();
                        }
                        TransactionAdaptor adaptor = new TransactionAdaptor(allDataBean);
                        dailyTransactionView.setAdapter(adaptor);
                        dailyTransactionView.setLayoutManager(new LinearLayoutManager(getContext()));
                    }
                }, year, month, date);
                datePickerDialog.show();
            }
        });

        dbHandler = new DBHandler(getActivity().getApplicationContext());
        String currentDate = dateTextView.getText().toString();
        Cursor allTransactionCursor = dbHandler.readTransaction(currentDate);
        List<DataBean> allDataBean = new ArrayList<>();
        if (allTransactionCursor != null) {
            int idIndex = allTransactionCursor.getColumnIndex(DBHandler.COL_ID);
            int valueIndex = allTransactionCursor.getColumnIndex(DBHandler.COL_VALUE);
            int categoryIndex = allTransactionCursor.getColumnIndex(DBHandler.COL_CATEGORY);
            while (allTransactionCursor.moveToNext()) {
                long IDFromDb = allTransactionCursor.getLong(idIndex);
                String valueFromDb = allTransactionCursor.getString(valueIndex);
                String categoryFromDb = allTransactionCursor.getString(categoryIndex);

                DataBean data = new DataBean();
                data.setId(IDFromDb);
                data.setValue(valueFromDb);
                data.setCategory(categoryFromDb);
                allDataBean.add(data);
            }
            allTransactionCursor.close();
        }
        TransactionAdaptor adaptor = new TransactionAdaptor(allDataBean);
        dailyTransactionView.setAdapter(adaptor);
        dailyTransactionView.setLayoutManager(new LinearLayoutManager(getContext()));

        displayBalance();


    }

    public static void displayBalance() {

        String cash_in_hand = cashInHand();
        String cash_lend_out = cashLendOut();
        String expanses = dailyExp();

        try {
            int deposit = Integer.parseInt(cash_in_hand);
            int lendOut = Integer.parseInt(cash_lend_out);
            int totalTransaction = Integer.parseInt(expanses);
            int totalBalance = deposit - (totalTransaction + lendOut);
            String totalBalanceString = String.valueOf(totalBalance);
            balanceView.setText(totalBalanceString);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }


    public static String cashInHand() {
        Cursor balanceCursor = dbHandler.readTake();
        String totalFromDb = "0";
        int total = 0;
        if (balanceCursor != null) {
            int balanceIndex = balanceCursor.getColumnIndex(DBHandler.COL_VALUE);
            while (balanceCursor.moveToNext()) {
                String amountFromDb = balanceCursor.getString(balanceIndex);

                if (totalFromDb == null) {
                    totalFromDb = "0";
                }

                int amount = Integer.parseInt(amountFromDb);
                total = total + amount;
            }
            balanceCursor.close();
            totalFromDb = String.valueOf(total);
        }
        return totalFromDb;
    }

    public static String cashLendOut() {
        Cursor balanceCursor = dbHandler.readGive();
        String totalFromDb = "0";
        int total = 0;
        if (balanceCursor != null) {
            int balanceIndex = balanceCursor.getColumnIndex(DBHandler.COL_VALUE);
            while (balanceCursor.moveToNext()) {
                String amountFromDb = balanceCursor.getString(balanceIndex);

                if (totalFromDb == null) {
                    totalFromDb = "0";
                }

                int amount = Integer.parseInt(amountFromDb);
                total = total + amount;
            }
            balanceCursor.close();
            totalFromDb = String.valueOf(total);
        }
        return totalFromDb;
    }

    public static String dailyExp() {
        String totalTransactionFromDb = "0";
        Cursor totalTransactionCursor = dbHandler.readTotalTransaction();
        if (totalTransactionCursor != null) {
            int balanceI = totalTransactionCursor.getColumnIndex(DBHandler.COL_TOTAL);
            if (totalTransactionCursor.moveToFirst()) {
                totalTransactionFromDb = totalTransactionCursor.getString(balanceI);

                if (totalTransactionFromDb == null) {
                    totalTransactionFromDb = "0";
                }
            }
            totalTransactionCursor.close();
        }
        return totalTransactionFromDb;
    }
}
