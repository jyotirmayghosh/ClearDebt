package com.greentech.jyotirmay.cleardebt.com.greentech.jyotirmay.cleardebt.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.greentech.jyotirmay.cleardebt.AddMoneyActivity;
import com.greentech.jyotirmay.cleardebt.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends android.support.v4.app.Fragment {

    public static final String RESULT = "result";
    public static String DATE = "";
    private static final int ADD_PAYEE=55;
    private CalendarView mCalendarView;
    private List<EventDay> mEventDays = new ArrayList<>();
    private FloatingActionButton floatingActionButton, payeeFloatingActionButton;
    MyEventDay myEventDay;


    clickEvents eventCallback;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment instance(MyEventDay day)
    {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putParcelable("DAY_TRANSACTION", day);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof clickEvents)
        {
            eventCallback= (clickEvents) context;
        }
        else
            throw new IllegalStateException("Interface not implemented...");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //myEventDay=getArguments().getParcelable(DATE);
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mCalendarView = getView().findViewById(R.id.calendarView);

        getActivity().setTitle(R.string.app_name);

        floatingActionButton = getView().findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyEventDay myEventDay = new MyEventDay(mCalendarView.getSelectedDate());
                String dateTime= myEventDay.getCalendar().getTime().toString();
                String date = null;
                char d=dateTime.charAt(8);
                if (d!='0')
                {
                    date=dateTime.substring(0, 10);
                }
                else {
                    String subDate = dateTime.substring(0, 7);
                    char z=dateTime.charAt(9);
                    date=subDate+ " " +z;
                }
                eventCallback.addTransaction(date,mCalendarView.getSelectedDate());
            }
        });

        payeeFloatingActionButton=getView().findViewById(R.id.floatingButtonGiveTake);
        payeeFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyEventDay myEventDay = new MyEventDay(mCalendarView.getSelectedDate());
                String dateTime= myEventDay.getCalendar().getTime().toString();
                final String date=dateTime.substring(0, 10);
                eventCallback.addPayee(date);
            }
        });

        Bundle bundle=getArguments();
        if (bundle!=null) {
            myEventDay = bundle.getParcelable(DATE);
            mCalendarView.setDate(myEventDay.getCalendar());
            mEventDays.add(myEventDay);
            mCalendarView.setEvents(mEventDays);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.option_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            /*case R.id.setting:
                Toast.makeText(getContext(), "Settings", Toast.LENGTH_SHORT).show();
                break;*/
            case R.id.addMoney:
            {
                Intent intent=new Intent(getContext(),AddMoneyActivity.class);
                startActivity(intent);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

 /*   @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_TRANSACTION && resultCode == RESULT_OK) {
            MyEventDay myEventDay = data.getParcelableExtra(RESULT);
            mCalendarView.setDate(myEventDay.getCalendar());
            mEventDays.add(myEventDay);
            mCalendarView.setEvents(mEventDays);
        }
    }*/

    public interface clickEvents
    {
        void addTransaction(String date, Calendar day);
        void addPayee(String date);
    }


}
