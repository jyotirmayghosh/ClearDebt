package com.greentech.jyotirmay.cleardebt.com.greentech.jyotirmay.cleardebt.com.greentech.jyotirmay.cleardebt.recycler_handler;

import android.app.ListFragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.greentech.jyotirmay.cleardebt.AddPayeeActivity;
import com.greentech.jyotirmay.cleardebt.R;
import com.greentech.jyotirmay.cleardebt.com.greentech.jyotirmay.cleardebt.database.DBHandler;

import java.util.List;

/**
 * Created by Jyotirmay on 19-Jan-18.
 */

public class NotificationAdaptor extends RecyclerView.Adapter<NotificationAdaptor.DataViewHolder>{

    private List<DataBean> notificationBeansList;
    DBHandler handler;
    public NotificationAdaptor(List<DataBean> notificationDataBeans) {
        notificationBeansList=notificationDataBeans;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View listItemView = layoutInflater.inflate(R.layout.recycle_notification_item, parent,false);
        NotificationAdaptor.DataViewHolder viewHolder=new NotificationAdaptor.DataViewHolder(listItemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {
        DataBean dataBean=notificationBeansList.get(position);
        holder.valueTextView.setText(String.valueOf(dataBean.getValue()));
        holder.dateTextView.setText(String.valueOf(dataBean.getDate()));
        holder.idTextView.setText(String.valueOf(dataBean.getId()));

        String acc_type=dataBean.getAcc_type();
        if (acc_type.equals(AddPayeeActivity.ACCOUNT_GIVE))
        {
            holder.giveImageView.setVisibility(View.VISIBLE);
            holder.nameTextView.setText("To " +String.valueOf(dataBean.getName()));
        }
        else if (acc_type.equals(AddPayeeActivity.ACCOUNT_TAKE)|| acc_type.equals(null))
        {
            holder.takeImageView.setVisibility(View.VISIBLE);
            holder.nameTextView.setText("From " +String.valueOf(dataBean.getName()));
        }
    }

    @Override
    public int getItemCount() {
        return notificationBeansList.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder
    {
        public TextView valueTextView, dateTextView, nameTextView, idTextView;
        public ImageView takeImageView, giveImageView;
        public ImageButton deleteImageButton;

        public DataViewHolder(View itemView) {
            super(itemView);

            valueTextView=itemView.findViewById(R.id.tvAdd);
            dateTextView=itemView.findViewById(R.id.tvDateN);
            nameTextView=itemView.findViewById(R.id.tvName);
            idTextView=itemView.findViewById(R.id.tvNID);
            takeImageView=itemView.findViewById(R.id.imgAdd);
            giveImageView=itemView.findViewById(R.id.imgSub);
            deleteImageButton=itemView.findViewById(R.id.imageButtonDelete);

            /*deleteImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    DataBean dataBean = notificationBeansList.get(position);
                    handler.removeDeposit(dataBean.getId());
                    notificationBeansList.remove(position);
                    notifyItemRemoved(position);
                }
            });*/
        }
    }
}
