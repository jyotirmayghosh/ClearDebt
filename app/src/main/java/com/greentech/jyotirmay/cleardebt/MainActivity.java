package com.greentech.jyotirmay.cleardebt;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.applandeo.materialcalendarview.CalendarView;
import com.greentech.jyotirmay.cleardebt.com.greentech.jyotirmay.cleardebt.fragment.HomeFragment;
import com.greentech.jyotirmay.cleardebt.com.greentech.jyotirmay.cleardebt.fragment.MyEventDay;
import com.greentech.jyotirmay.cleardebt.com.greentech.jyotirmay.cleardebt.fragment.NotificationFragment;
import com.greentech.jyotirmay.cleardebt.com.greentech.jyotirmay.cleardebt.fragment.TransectionFragment;

import java.util.Calendar;

import static com.greentech.jyotirmay.cleardebt.com.greentech.jyotirmay.cleardebt.fragment.HomeFragment.RESULT;

public class MainActivity extends AppCompatActivity implements HomeFragment.clickEvents {

    public static final int DAY_REQUEST_CODE=200;
    private static final int ADD_TRANSACTION = 44;
    public static Calendar dayCalendar;
    public static String CALENDER="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, new HomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new HomeFragment()).commit();
                    return true;
                case R.id.navigation_transaction:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new TransectionFragment()).commit();
                    return true;
                /*case R.id.navigation_dashboard:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new HomeFragment()).commit();
                    return true;*/
                case R.id.navigation_notifications:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new NotificationFragment()).commit();
                    return true;
                /*case R.id.navigation_me:
                    //getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new UserFragment()).commit();
                    return true;*/
            }
            return false;
        }
    };

    @Override
    public void addTransaction(String date, Calendar day) {
        Intent intent=new Intent(MainActivity.this, AddTransactionActivity.class);
        intent.putExtra("ACTIVITY_CLASS", "MainActivity");
        intent.putExtra("DATE", date);
        intent.putExtra(CALENDER, day);
        startActivityForResult(intent,DAY_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DAY_REQUEST_CODE && resultCode == RESULT_OK) {
            dayCalendar= (Calendar) data.getSerializableExtra(AddTransactionActivity.DATE);
            MyEventDay myEventDay = new MyEventDay(dayCalendar, R.drawable.ic_transection_done);
            Bundle bundle=new Bundle();
            bundle.putParcelable(HomeFragment.DATE,myEventDay);
            HomeFragment homeFragment=new HomeFragment();
            homeFragment.setArguments(bundle);
//            mCalendarView.setDate(myEventDay.getCalendar());
//            mEventDays.add(myEventDay);
//            mCalendarView.setEvents(mEventDays);
        }
    }

    @Override
    public void addPayee(String date) {
        Intent intent=new Intent(MainActivity.this, AddPayeeActivity.class);
        intent.putExtra("ACTIVITY_CLASS", "MainActivity");
        intent.putExtra("DATE", date);
        MainActivity.this.startActivity(intent);
    }
}
