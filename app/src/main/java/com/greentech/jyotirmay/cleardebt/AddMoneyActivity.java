package com.greentech.jyotirmay.cleardebt;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.greentech.jyotirmay.cleardebt.com.greentech.jyotirmay.cleardebt.database.DBHandler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddMoneyActivity extends AppCompatActivity {

    EditText addValueText;
    TextView dateView;
    FloatingActionButton addMoneyActionButton;
    ConstraintLayout calenderLayout;

    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money);

        addValueText=findViewById(R.id.etValue);
        addValueText.requestFocus();
        dateView=findViewById(R.id.tvData);

        addMoneyActionButton=findViewById(R.id.addMoneyButton);
        calenderLayout=findViewById(R.id.layoutCalender);

        Calendar calendar = Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int date = calendar.get(Calendar.DATE);
        final int day = calendar.get(Calendar.DAY_OF_WEEK);
        final String dayOfName[]= {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        final String monthOfYear[]={"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        dateView.setText(dayOfName[day-2]+" "+monthOfYear[month]+" "+String.valueOf(date));

        calenderLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddMoneyActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                        SimpleDateFormat dateFormat=new SimpleDateFormat("EEE");
                        Date date1=new Date(year, month, date-1);
                        String dayOfWeek=dateFormat.format(date1);
                        dateView.setText(dayOfWeek+" "+ monthOfYear[month] + " " + date);
                    }
                }, year, month, date);
                datePickerDialog.show();
            }
        });
        dbHandler=new DBHandler(AddMoneyActivity.this);
        addMoneyActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value=addValueText.getText().toString();
                String date=dateView.getText().toString();
                boolean isDataEntered=dbHandler.deposit(value, AddPayeeActivity.ACCOUNT_TAKE, "Cash-in-Hand", date);
                Toast.makeText(AddMoneyActivity.this, "Save to DB.." +isDataEntered, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(AddMoneyActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
