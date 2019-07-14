package com.ahmadfauzirahman.sakato.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class SpmAdapter extends RecyclerView.Adapter<SpmAdapter.SpmViewHolder> {
    @NonNull
    @Override
    public SpmViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SpmViewHolder spmViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class SpmViewHolder extends RecyclerView.ViewHolder {
        public SpmViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
