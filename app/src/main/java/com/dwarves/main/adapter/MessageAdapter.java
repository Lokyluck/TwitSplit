package com.dwarves.main.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.dwarves.main.R;
import com.dwarves.main.ui.MainActivity;
import com.dwarves.main.utils.CommonUtils;

import java.util.ArrayList;
import java.util.Random;


/**
 * Adapter used to show keywords list
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ItemViewHolder> {
    private final String TAG = this.getClass().getSimpleName();
    private MainActivity mContext;
    private ArrayList<String> subMessageList;

    public MessageAdapter(MainActivity activity, ArrayList<String> list) {
        mContext = activity;
        subMessageList = list;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
            , View.OnLongClickListener {
        private View keywordItemView;
        private int mPos;

        private ItemViewHolder(View view){
            super(view);
            keywordItemView = view;
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
        }

        @Override
        public boolean onLongClick(View v) {
            return true;
        }

        void bindPosition(int pos){
            mPos = pos;
        }
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v0
                = LayoutInflater.from(mContext)
                .inflate(R.layout.recycleview_item, parent, false);
        return new ItemViewHolder(v0);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        View holderView = holder.keywordItemView;
        position = position % subMessageList.size();

        TextView subMessageView = holderView.findViewById(R.id.sub_message_text_view);
        String subMessage = subMessageList.get(position);
        subMessageView.setText(subMessage);

        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        subMessageView.setBackgroundColor(color);

        holder.bindPosition(position);
    }

    @Override
    public int getItemCount() {
        return (subMessageList != null) ? subMessageList.size() : 0;
    }



}
