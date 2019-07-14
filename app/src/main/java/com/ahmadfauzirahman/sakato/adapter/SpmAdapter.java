package com.ahmadfauzirahman.sakato.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ahmadfauzirahman.sakato.R;
import com.ahmadfauzirahman.sakato.model.SpmModel;

import java.util.List;

public class SpmAdapter extends RecyclerView.Adapter<SpmAdapter.SpmViewHolder> {

    private List<SpmModel> spmModels;
    private int rowLayout;
    private Context context;

    public SpmAdapter(List<SpmModel> spmModels, int rowLayout, Context context) {
        this.spmModels = spmModels;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @NonNull
    @Override
    public SpmViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new SpmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpmViewHolder spmViewHolder, int i) {
        spmViewHolder.mJenisSpm.setText(spmModels.get(i).getJenis());
        spmViewHolder.mTanggalSpm.setText(spmModels.get(i).getSpmTanggalPenolakan());
        spmViewHolder.mStatusSpm.setText(spmModels.get(i).getSpmStatus());
    }

    @Override
    public int getItemCount() {

        return spmModels.size();
    }

    public class SpmViewHolder extends RecyclerView.ViewHolder {

        LinearLayout spmliner;
        TextView mJenisSpm, mTanggalSpm, mStatusSpm;

        public SpmViewHolder(@NonNull View itemView) {

            super(itemView);

            spmliner = (LinearLayout) itemView.findViewById(R.id.lspm);
            mJenisSpm = itemView.findViewById(R.id.mJenisSpm);
            mTanggalSpm = itemView.findViewById(R.id.mTanggalSpm);
            mStatusSpm = itemView.findViewById(R.id.mStatusSpm);

        }
    }
}
