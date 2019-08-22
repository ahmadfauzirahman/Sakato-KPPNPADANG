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
import com.ahmadfauzirahman.sakato.model.InitModel;

import java.util.List;

public class JumlahTiketAdapter extends RecyclerView.Adapter<JumlahTiketAdapter.JumlahTiketViewHolder> {

    private List<InitModel> initModels;
    private int rowLayout;
    private Context context;

    public JumlahTiketAdapter(List<InitModel> initModels, int rowLayout, Context context) {
        this.initModels = initModels;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @NonNull
    @Override
    public JumlahTiketViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new JumlahTiketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JumlahTiketViewHolder jumlahTiketViewHolder, int i) {
        String antrin = "Antrian ";
        jumlahTiketViewHolder.mNomorAntrian.setText(antrin + initModels.get(i).getNomorAntrian());
        String loket = "Front Office ";
        jumlahTiketViewHolder.mNomorLoket.setText(loket + initModels.get(i).getLoket());
    }

    @Override
    public int getItemCount() {
        return initModels.size();
    }

    public class JumlahTiketViewHolder extends RecyclerView.ViewHolder {
        LinearLayout lJumlahLoket;
        TextView mNomorAntrian, mNomorLoket;

        public JumlahTiketViewHolder(@NonNull View itemView) {
            super(itemView);
            lJumlahLoket = (LinearLayout) itemView.findViewById(R.id.lJumlahLoket);
            mNomorAntrian = itemView.findViewById(R.id.mNomorAntrian);
            mNomorLoket = itemView.findViewById(R.id.mNomorLoket);
        }
    }
}
