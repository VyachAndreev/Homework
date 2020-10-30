package com.andreev.homework.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andreev.homework.R;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private ItemViewHolder.IListener listener;
    private List<String> data;
    public ItemAdapter(List <String> data, ItemViewHolder.IListener listener){
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View layout = inflater.inflate(R.layout.item, parent, false);

        return new ItemViewHolder(layout, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.textView.setText(data.get(position));
        if (position % 2 == 0) {
            holder.textView.setTextColor(Color.BLUE);
        } else {
            holder.textView.setTextColor(Color.RED);
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
