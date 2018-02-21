package com.greentech.jyotirmay.cleardebt;

import android.app.Activity;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.greentech.jyotirmay.cleardebt.com.greentech.jyotirmay.cleardebt.database.DBHandler;
import com.greentech.jyotirmay.cleardebt.com.greentech.jyotirmay.cleardebt.fragment.MyEventDay;

import java.util.Calendar;

public class AddTransactionActivity extends AppCompatActivity {

    ConstraintLayout changeLayout;
    TextView dateView, categoryView;
    EditText valueText;
    private static String category = "Uncategorized";
    FloatingActionButton saveFloatingActionButton;
    DBHandler dbHandler;
    public static final int CATEGORY_REQUEST_CODE = 100;
    public static String DATE="";
    Calendar transactionDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*Intent intent=getIntent();
        String PARENT=intent.getExtras().getString("ACTIVITY");
//        Log.d("PARENT", PARENT);

        if (PARENT==null) {
            super.onCreate(savedInstanceState);
        }
        else if (PARENT=="ACTIVITY_NAME") {
            String COLOR = intent.getExtras().getString(CategoryActivity.CATEGORY);
            //int COLOR_CODE = Integer.parseInt(COLOR);
            Log.d("Color", COLOR);
            if (COLOR.equals(CategoryActivity.UNCATEGORY)) {
                this.setTheme(R.style.AppTheme);
            } else if (COLOR.equals(CategoryActivity.CAR_TRANSPORT)) {
                this.setTheme(R.style.PurpleAppTheme);
            } else if (COLOR == CategoryActivity.CLOTH) {
                this.setTheme(R.style.PinkAppTheme);
            } else if (COLOR == CategoryActivity.EATING_OUT) {
                this.setTheme(R.style.RedAppTheme);
            }
        }
        else
            throw new IllegalStateException("received null");*/

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        valueText = findViewById(R.id.etValue);
        valueText.requestFocus();
        dateView = findViewById(R.id.tvSelectedDate);
        categoryView = findViewById(R.id.tvCatagories);

        dbHandler = new DBHandler(AddTransactionActivity.this);

        changeLayout = findViewById(R.id.changeCategory);
        changeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
                startActivityForResult(intent, CATEGORY_REQUEST_CODE);
            }
        });

        saveFloatingActionButton = findViewById(R.id.addTransectionButton);
        saveFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = valueText.getText().toString();
                String category = categoryView.getText().toString();
                String date = dateView.getText().toString();
                boolean isDataEntered = dbHandler.enterCategory(value, category, date);
                Toast.makeText(AddTransactionActivity.this, "Saved successfully" + isDataEntered, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddTransactionActivity.this, MainActivity.class);
                intent.putExtra(DATE, transactionDay);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        Intent intent = this.getIntent();
        if (intent != null) {
            String fromActivity = intent.getExtras().getString("ACTIVITY_CLASS");
            if (fromActivity.equals("MainActivity")) {
                DATE = intent.getExtras().getString("DATE");
                transactionDay= (Calendar) intent.getSerializableExtra(MainActivity.CALENDER);
            }
        }

        dateView.setText(DATE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String fromActivity = data.getExtras().getString("ACTIVITY_CLASS");
        if (fromActivity.equals("CategoryActivity")) {
            if (requestCode == CATEGORY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
                category = data.getExtras().getString(CategoryActivity.CATEGORY);
                categoryView.setText(category);
            }
            else if (resultCode==Activity.RESULT_CANCELED)
            {
                categoryView.setText(category);
            }
        }
    }
}
