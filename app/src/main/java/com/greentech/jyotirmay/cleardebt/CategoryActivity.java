package com.greentech.jyotirmay.cleardebt;

import android.app.Activity;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class CategoryActivity extends AppCompatActivity implements View.OnClickListener{

    ConstraintLayout uncategoryLayout, carLayout, clothLayout, eatingLayout, entertainmentLayout, fitnessLayout,
            groceriesLayout, healthLayout, householdLayout, personalLayout, rentLayout, subscriptionLayout;

    ImageView uncategoryView, carView, clothView, eatingView, entertainmentView, fitnessView,
            groceriesView, healthView, houseView,perssonalView, rentView, subscriptionView;

    public static final String CATEGORY= "CATEGORY";
    public static final String UNCATEGORY="Uncategorized";
    public static final String CAR_TRANSPORT="Car & Transport";
    public static final String CLOTH="Dress (Cloth)";
    public static final String EATING_OUT="Eating Out";
    public static final String ENTERTAINMENT="Entertainment";
    public static final String FITNESS="Fitness";
    public static final String GROCERIES="Groceries";
    public static final String HEALTH="Health";
    public static final String HOUSE_HOLD="House Hold";
    public static final String PERSONAL_CARE="Personal Care";
    public static final String RENT="Rent";
    public static final String SUBSCRIPTION="Subscription";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        uncategoryLayout=findViewById(R.id.Uncategorized);
        carLayout=findViewById(R.id.Car_Transport);
        clothLayout=findViewById(R.id.Clothing);
        eatingLayout=findViewById(R.id.Eat_Out);
        entertainmentLayout=findViewById(R.id.Entertainment);
        fitnessLayout=findViewById(R.id.Fitness);
        groceriesLayout=findViewById(R.id.Groceries);
        healthLayout=findViewById(R.id.Health);
        householdLayout=findViewById(R.id.Household);
        personalLayout=findViewById(R.id.Personalcare);
        rentLayout=findViewById(R.id.Rent);
        subscriptionLayout=findViewById(R.id.Subscription);

        uncategoryLayout.setOnClickListener(this);
        carLayout.setOnClickListener(this);
        clothLayout.setOnClickListener(this);
        eatingLayout.setOnClickListener(this);
        entertainmentLayout.setOnClickListener(this);
        fitnessLayout.setOnClickListener(this);
        groceriesLayout.setOnClickListener(this);
        healthLayout.setOnClickListener(this);
        householdLayout.setOnClickListener(this);
        personalLayout.setOnClickListener(this);
        rentLayout.setOnClickListener(this);
        subscriptionLayout.setOnClickListener(this);

        uncategoryView=findViewById(R.id.ckUncategory);
        carView=findViewById(R.id.ckCarTransport);
        clothView=findViewById(R.id.ckCloth);
        eatingView=findViewById(R.id.ckEatOut);
        entertainmentView=findViewById(R.id.ckEntertainment);
        fitnessView=findViewById(R.id.ckFitness);
        groceriesView=findViewById(R.id.ckGroceries);
        healthView=findViewById(R.id.ckHealth);
        houseView=findViewById(R.id.ckHouseHold);
        perssonalView=findViewById(R.id.ckPersonalCare);
        rentView=findViewById(R.id.ckRent);
        subscriptionView=findViewById(R.id.ckSubscription);
    }

    @Override
    public void onClick(View v) {

        Intent intent=new Intent(CategoryActivity.this, AddTransactionActivity.class);
        if (v.getId()==R.id.Uncategorized)
        {
            uncategoryView.setVisibility(v.VISIBLE);
            intent.putExtra("ACTIVITY_CLASS", "CategoryActivity");
            intent.putExtra(CATEGORY, UNCATEGORY);
            setResult(AddTransactionActivity.RESULT_OK, intent);
            finish();
        }
        else if (v.getId()==R.id.Car_Transport)
        {
            carView.setVisibility(v.VISIBLE);
            intent.putExtra("ACTIVITY_CLASS", "CategoryActivity");
            intent.putExtra(CATEGORY, CAR_TRANSPORT);
            setResult(AddTransactionActivity.RESULT_OK, intent);
            finish();
        }
        else if (v.getId()==R.id.Clothing)
        {
            clothView.setVisibility(v.VISIBLE);
            intent.putExtra("ACTIVITY_CLASS", "CategoryActivity");
            intent.putExtra(CATEGORY, CLOTH);
            setResult(AddTransactionActivity.RESULT_OK, intent);
            finish();
        }
        else if (v.getId()==R.id.Eat_Out)
        {
            eatingView.setVisibility(v.VISIBLE);
            intent.putExtra("ACTIVITY_CLASS", "CategoryActivity");
            intent.putExtra(CATEGORY, EATING_OUT);
            setResult(AddTransactionActivity.RESULT_OK, intent);
            finish();
        }
        else if (v.getId()==R.id.Entertainment)
        {
            entertainmentView.setVisibility(v.VISIBLE);
            intent.putExtra("ACTIVITY_CLASS", "CategoryActivity");
            intent.putExtra(CATEGORY, ENTERTAINMENT);
            setResult(AddTransactionActivity.RESULT_OK, intent);
            finish();
        }
        else if (v.getId()==R.id.Fitness)
        {
            fitnessView.setVisibility(v.VISIBLE);
            intent.putExtra("ACTIVITY_CLASS", "CategoryActivity");
            intent.putExtra(CATEGORY, FITNESS);
            setResult(AddTransactionActivity.RESULT_OK, intent);
            finish();
        }
        else if (v.getId()==R.id.Groceries)
        {
            groceriesView.setVisibility(v.VISIBLE);
            intent.putExtra("ACTIVITY_CLASS", "CategoryActivity");
            intent.putExtra(CATEGORY, GROCERIES);
            setResult(AddTransactionActivity.RESULT_OK, intent);
            finish();
        }
        else if (v.getId()==R.id.Health)
        {
            healthView.setVisibility(v.VISIBLE);
            intent.putExtra("ACTIVITY_CLASS", "CategoryActivity");
            intent.putExtra(CATEGORY, HEALTH);
            setResult(AddTransactionActivity.RESULT_OK, intent);
            finish();
        }
        else if (v.getId()==R.id.Household)
        {
            houseView.setVisibility(v.VISIBLE);
            intent.putExtra("ACTIVITY_CLASS", "CategoryActivity");
            intent.putExtra(CATEGORY, HOUSE_HOLD);
            setResult(AddTransactionActivity.RESULT_OK, intent);
            finish();
        }
        else if (v.getId()==R.id.Personalcare)
        {
            perssonalView.setVisibility(v.VISIBLE);
            intent.putExtra("ACTIVITY_CLASS", "CategoryActivity");
            intent.putExtra(CATEGORY, PERSONAL_CARE);
            setResult(AddTransactionActivity.RESULT_OK, intent);
            finish();
        }
        else if (v.getId()==R.id.Rent)
        {
            rentView.setVisibility(v.VISIBLE);
            intent.putExtra("ACTIVITY_CLASS", "CategoryActivity");
            intent.putExtra(CATEGORY, RENT);
            setResult(AddTransactionActivity.RESULT_OK, intent);
            finish();
        }
        else if (v.getId()==R.id.Subscription)
        {
            subscriptionView.setVisibility(v.VISIBLE);
            intent.putExtra("ACTIVITY_CLASS", "CategoryActivity");
            intent.putExtra(CATEGORY, SUBSCRIPTION);
            setResult(AddTransactionActivity.RESULT_OK, intent);
            finish();
        }
    }
}
