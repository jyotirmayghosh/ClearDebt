package com.greentech.jyotirmay.cleardebt.com.greentech.jyotirmay.cleardebt.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greentech.jyotirmay.cleardebt.R;
import com.greentech.jyotirmay.cleardebt.com.greentech.jyotirmay.cleardebt.com.greentech.jyotirmay.cleardebt.recycler_handler.NotificationAdaptor;
import com.greentech.jyotirmay.cleardebt.com.greentech.jyotirmay.cleardebt.com.greentech.jyotirmay.cleardebt.recycler_handler.TransactionAdaptor;
import com.greentech.jyotirmay.cleardebt.com.greentech.jyotirmay.cleardebt.com.greentech.jyotirmay.cleardebt.recycler_handler.DataBean;
import com.greentech.jyotirmay.cleardebt.com.greentech.jyotirmay.cleardebt.database.DBHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {

    RecyclerView notificationView;
    DBHandler dbHandler;

    public NotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        notificationView=getView().findViewById(R.id.recyclerViewNotification);
        dbHandler=new DBHandler(getActivity().getApplicationContext());

        getActivity().setTitle(R.string.title_notifications);

        Cursor allNotificationCursor=dbHandler.readAccount();
        List<DataBean> allDataBean=new ArrayList<>();
        if (allNotificationCursor!=null)
        {
            int valueIndex=allNotificationCursor.getColumnIndex(DBHandler.COL_VALUE);
            int accountIndex=allNotificationCursor.getColumnIndex(DBHandler.COL_ACCOUNT);
            int nameIndex=allNotificationCursor.getColumnIndex(DBHandler.COL_NAME);
            int dateIndex=allNotificationCursor.getColumnIndex(DBHandler.COL_DATE);
            while (allNotificationCursor.moveToNext())
            {
                String valueFromDb = allNotificationCursor.getString(valueIndex);
                String accountFromDb = allNotificationCursor.getString(accountIndex);
                String nameFromDb = allNotificationCursor.getString(nameIndex);
                String dateFromDb = allNotificationCursor.getString(dateIndex);

                DataBean data = new DataBean();
                data.setValue(valueFromDb);
                data.setName(nameFromDb);
                data.setDate(dateFromDb);
                data.setAcc_type(accountFromDb);
                allDataBean.add(data);
            }
            allNotificationCursor.close();
        }
        NotificationAdaptor adaptor=new NotificationAdaptor(allDataBean);
        notificationView.setAdapter(adaptor);
        notificationView.setLayoutManager(new LinearLayoutManager(getContext()));

    }

}
