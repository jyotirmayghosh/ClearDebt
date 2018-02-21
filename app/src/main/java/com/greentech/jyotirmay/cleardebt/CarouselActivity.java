package com.greentech.jyotirmay.cleardebt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class CarouselActivity extends AppCompatActivity {

    CarouselView carouselView;
    int[] sampleImages = {R.drawable.image_1, R.drawable.image_2, R.drawable.image_3, R.drawable.image_4};
    TextView skipTextView, startTextView;
    public static String PREF="done";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carousel);

        carouselView = findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);

        carouselView.setImageListener(imageListener);
        carouselView.isDisableAutoPlayOnUserInteraction();

        skipTextView=findViewById(R.id.tvSkip);
        skipTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent=new Intent(CarouselActivity.this, MainActivity.class);
                startActivity(intent);
                getSharedPreferences("clearDebt", MODE_PRIVATE).edit().putString("login", "done").commit();
                finish();*/
                SharedPreferences preferences = getSharedPreferences("clearDebt", MODE_PRIVATE);
                String valueSaved = preferences.getString("login", "N/A");
                Toast.makeText(CarouselActivity.this, valueSaved, Toast.LENGTH_SHORT).show();
            }
        });

        startTextView=findViewById(R.id.tvStart);
        startTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSharedPreferences("clearDebt", MODE_PRIVATE).edit().putString("login", PREF).commit();
                Intent intent=new Intent(CarouselActivity.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(CarouselActivity.this, "Pref Saved..", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
            /*if (position==0|| position==1|| position==2)
            {
                skipTextView.setVisibility(View.VISIBLE);
                startTextView.setVisibility(View.INVISIBLE);
            }
            else if (position==3)
            {
                skipTextView.setVisibility(View.GONE);
                startTextView.setVisibility(View.VISIBLE);
            }*/
        }
    };
}
