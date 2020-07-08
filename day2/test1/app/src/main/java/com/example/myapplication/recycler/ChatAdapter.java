package com.example.myapplication.recycler;

import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    private List<ChatData> mDataset = new ArrayList<>();
    private ChatAdapter.IOnItemClickListener mItemClickListener;

    public static class ChatViewHolder extends RecyclerView.ViewHolder {
        private ImageView person_image;
        private TextView massage;
        private boolean who;
        private View contentView;

        public ChatViewHolder(View v) {
            super(v);
            contentView = v;
            person_image = v.findViewById(R.id.person_image);
            massage = v.findViewById(R.id.chat_massage);
        }

        public void onBind(int position, ChatData data) {
            person_image.setImageResource(R.drawable.ic_launcher_background);
            massage.setText(data.massage);
            if (data.who==1)
                person_image.setImageResource(R.drawable.ic_launcher_foreground);
        }

        public void setOnClickListener(View.OnClickListener listener) {
            if (listener != null) {
                contentView.setOnClickListener(listener);
            }
        }

        public void setOnLongClickListener(View.OnLongClickListener listener) {
            if (listener != null) {
                contentView.setOnLongClickListener(listener);
            }
        }
    }


    public ChatAdapter(List<ChatData> myDataSet) {
        mDataset.addAll(myDataSet);
    }

    public void setOnItemClickListener(IOnItemClickListener listener) {
        mItemClickListener = listener;
    }

    public void addData(int position, ChatData data) {
        mDataset.add(position, data);
        Log.i("TAG","position");
        notifyItemInserted(position);
        if (position != mDataset.size()) {
            //刷新改变位置item下方的所有Item的位置,避免索引错乱
            notifyItemRangeChanged(position, mDataset.size() - position);
        }
    }

    public void removeData(int position) {
        if (null != mDataset && mDataset.size() > position) {
            mDataset.remove(position);
            notifyItemRemoved(position);
            if (position != mDataset.size()) {
                //刷新改变位置item下方的所有Item的位置,避免索引错乱
                notifyItemRangeChanged(position, mDataset.size() - position);
            }
        }
    }

    @Override
    public ChatAdapter.ChatViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        return new ChatAdapter.ChatViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.talk_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, final int position) {
        holder.onBind(position, mDataset.get(position));
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemCLick(position, mDataset.get(position));
                }
            }
        });
        holder.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemLongCLick(position, mDataset.get(position));
                }
                return false;
            }

        });
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface IOnItemClickListener {

        void onItemCLick(int position, ChatData data);

        void onItemLongCLick(int position, ChatData data);
    }
}
