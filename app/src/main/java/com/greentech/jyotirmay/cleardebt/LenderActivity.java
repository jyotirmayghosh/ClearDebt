package com.greentech.jyotirmay.cleardebt;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class LenderActivity extends AppCompatActivity implements View.OnClickListener {

    ConstraintLayout uncategoryLayout, lendToLayout, lendFromLayout;
    ImageView lendToView, lendFromView;

    public static final String CATEGORY= "CATEGORY";
    public static final String UNCATEGORY= "UNCATEGORY";
    public static final String LEND_TO="Lend To";
    public static final String LEND_FROM="Lend From";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lender);

        uncategoryLayout=findViewById(R.id.Uncategorized);
        lendToLayout=findViewById(R.id.LendTo);
        lendFromLayout=findViewById(R.id.LendFrom);

        lendToLayout.setOnClickListener(this);
        lendFromLayout.setOnClickListener(this);
        uncategoryLayout.setOnClickListener(this);

        lendToView=findViewById(R.id.ckLendsTo);
        lendFromView=findViewById(R.id.ckLendsFrom);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(LenderActivity.this, AddPayeeActivity.class);
        if (v.getId()==R.id.Uncategorized)
        {
            lendToView.setVisibility(v.VISIBLE);
            intent.putExtra("ACTIVITY_CLASS", "LenderActivity");
            intent.putExtra(CATEGORY, UNCATEGORY);
            setResult(AddTransactionActivity.RESULT_OK, intent);
            finish();
        }
        else if (v.getId()==R.id.LendTo)
        {
            lendToView.setVisibility(v.VISIBLE);
            intent.putExtra("ACTIVITY_CLASS", "LenderActivity");
            intent.putExtra(CATEGORY, LEND_TO);
            setResult(AddTransactionActivity.RESULT_OK, intent);
            finish();
        }
        else if (v.getId()==R.id.LendFrom)
        {
            lendFromView.setVisibility(v.VISIBLE);
            intent.putExtra("ACTIVITY_CLASS", "LenderActivity");
            intent.putExtra(CATEGORY, LEND_FROM);
            setResult(AddTransactionActivity.RESULT_OK, intent);
            finish();
        }
    }
}
