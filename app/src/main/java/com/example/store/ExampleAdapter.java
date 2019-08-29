package com.example.store;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private Context mContext;
    private ArrayList<ExampleItem> mExampleList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public ExampleAdapter(Context context, ArrayList<ExampleItem> exampleList) {
        mContext = context;
        mExampleList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.example_item, parent, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        ExampleItem currentItem = mExampleList.get(position);

        String address = currentItem.getAddress();
        Double longitude = currentItem.getLongitude();
        Double latitude = currentItem.getLatitude();

        String stringdoublelong= Double.toString(longitude);
        String stringdoublelat= Double.toString(latitude);

        holder.mTextViewAddress.setText(address);
        holder.mTextViewLongitude.setText(stringdoublelong);
        holder.mTextViewLatitude.setText(stringdoublelat);
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewAddress;
        public TextView mTextViewLongitude;
        public TextView mTextViewLatitude;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            mTextViewAddress = itemView.findViewById(R.id.text_view_address);
            mTextViewLatitude = itemView.findViewById(R.id.text_view_latitude);
            mTextViewLongitude = itemView.findViewById(R.id.text_view_longitude);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }
}