package com.greentech.jyotirmay.cleardebt.com.greentech.jyotirmay.cleardebt.com.greentech.jyotirmay.cleardebt.recycler_handler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.greentech.jyotirmay.cleardebt.R;
import com.greentech.jyotirmay.cleardebt.com.greentech.jyotirmay.cleardebt.database.DBHandler;
import com.greentech.jyotirmay.cleardebt.com.greentech.jyotirmay.cleardebt.fragment.TransectionFragment;

import java.util.List;

/**
 * Created by Jyotirmay on 19-Jan-18.
 */

public class TransactionAdaptor extends RecyclerView.Adapter<TransactionAdaptor.DataViewHolder> {

    private List<DataBean> transactionBeanList;
    DBHandler handler;
    public TransactionAdaptor(List<DataBean> transactionBean)
    {
        transactionBeanList=transactionBean;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        handler=new DBHandler(parent.getContext());

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View listItemView = layoutInflater.inflate(R.layout.recycle_transaction_item, parent,false);
        DataViewHolder viewHolder=new DataViewHolder(listItemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {
        DataBean dataBean=transactionBeanList.get(position);
        holder.idTextView.setText(String.valueOf(dataBean.getId()));
        holder.valueTextView.setText(String.valueOf(dataBean.getValue()));
        holder.categoryTextView.setText(String.valueOf(dataBean.getCategory()));
    }

    @Override
    public int getItemCount() {
        return transactionBeanList.size();
    }


    public class DataViewHolder extends RecyclerView.ViewHolder
    {
        public TextView valueTextView, categoryTextView, idTextView;
        public ImageButton deleteImageButton;

        public DataViewHolder(View itemView) {
            super(itemView);

            valueTextView=itemView.findViewById(R.id.tvValue);
            categoryTextView=itemView.findViewById(R.id.tvCategory);
            idTextView=itemView.findViewById(R.id.tvID);
            deleteImageButton=itemView.findViewById(R.id.imgbtnDel);

            deleteImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    DataBean dataBean = transactionBeanList.get(position);
                    handler.removeTransaction(dataBean.getId());
                    transactionBeanList.remove(position);
                    notifyItemRemoved(position);
                    TransectionFragment.displayBalance();
                }
            });
        }
    }
}

