package com.greentech.jyotirmay.cleardebt;

import android.app.Activity;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.greentech.jyotirmay.cleardebt.com.greentech.jyotirmay.cleardebt.database.DBHandler;

public class AddPayeeActivity extends AppCompatActivity {

    ConstraintLayout changeLayout;
    TextView dateView, categoryView;
    private static String date="";
    private static String category="Uncategorized";
    FloatingActionButton saveFloatingActionButton;
    public static final String ACCOUNT_GIVE="give";
    public static final String ACCOUNT_TAKE="take";
    String ACCOUNT_TYPE = "";
    ImageView imageView;
    EditText valueText, nameText;
    public static final int BORROW_REQUEST_CODE=33;

    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payee);

        valueText=findViewById(R.id.etValue);
        valueText.requestFocus();
        nameText=findViewById(R.id.etLenderName);
        dateView=findViewById(R.id.tvSelectedDate);
        categoryView=findViewById(R.id.tvCatagories);
        imageView=findViewById(R.id.imageView);

        changeLayout=findViewById(R.id.changeCategory);

        dbHandler=new DBHandler(AddPayeeActivity.this);

        changeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), LenderActivity.class);
                startActivityForResult(intent, BORROW_REQUEST_CODE);
            }
        });

        Intent intent=this.getIntent();
        if (intent!=null)
        {
            String fromActivity=intent.getExtras().getString("ACTIVITY_CLASS");
            if (fromActivity.equals("MainActivity"))
            {
                date=intent.getExtras().getString("DATE");
                dateView.setText(date);
            }
        }


        saveFloatingActionButton=findViewById(R.id.addTransectionButton);
        saveFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value=valueText.getText().toString();
                String account=ACCOUNT_TYPE;
                String name=nameText.getText().toString();
                String date=dateView.getText().toString();
                boolean isDataEntered=dbHandler.deposit(value, account, name, date);
                Toast.makeText(AddPayeeActivity.this, "Save to DB.." +isDataEntered, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(AddPayeeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String fromActivity=data.getExtras().getString("ACTIVITY_CLASS");
        if (fromActivity.equals("LenderActivity")) {
            if (requestCode == BORROW_REQUEST_CODE && resultCode == RESULT_OK) {
                category=data.getExtras().getString(CategoryActivity.CATEGORY);
                categoryView.setText(category);

                if (category.equals(LenderActivity.LEND_FROM))
                {
                    imageView.setImageResource(R.drawable.ic_action_round_add);
                    ACCOUNT_TYPE=ACCOUNT_TAKE;
                }
                else if (category.equals(LenderActivity.LEND_TO))
                {
                    ACCOUNT_TYPE=ACCOUNT_GIVE;
                }
            }
            else if (resultCode== Activity.RESULT_CANCELED)
            {
                categoryView.setText(category);
            }
        }
    }
}
