package com.ahmadfauzirahman.sakato.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class KontrakAdapter extends RecyclerView.Adapter<KontrakAdapter.KontrakViewHolder> {
    @NonNull
    @Override
    public KontrakViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull KontrakViewHolder kontrakViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class KontrakViewHolder extends RecyclerView.ViewHolder {
        public KontrakViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
