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
import com.ahmadfauzirahman.sakato.model.KontrakModel;

import java.util.List;

public class KontrakAdapter extends RecyclerView.Adapter<KontrakAdapter.KontrakViewHolder> {


    private List<KontrakModel> kontrakModels;
    private int rowLayout;
    private Context context;

    public KontrakAdapter(List<KontrakModel> kontrakModels, int rowLayout, Context context) {
        this.kontrakModels = kontrakModels;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @NonNull
    @Override
    public KontrakViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new KontrakViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KontrakViewHolder kontrakViewHolder, int i) {
        kontrakViewHolder.mKontrakNomor.setText(kontrakModels.get(i).getKontrakNomor());
        kontrakViewHolder.mKontrakPenolakan.setText(kontrakModels.get(i).getKontrakAlasanPenolakan());
        kontrakViewHolder.mTanggalPenolakan.setText(kontrakModels.get(i).getKontrakTanggalPenolakan());
        kontrakViewHolder.mStatusPenolakan.setText(kontrakModels.get(i).getKontrakStatus());
    }

    @Override
    public int getItemCount() {
        return kontrakModels.size();
    }

    public class KontrakViewHolder extends RecyclerView.ViewHolder {
        LinearLayout lkontrak;
        TextView mKontrakNomor, mKontrakPenolakan, mTanggalPenolakan, mStatusPenolakan;

        public KontrakViewHolder(@NonNull View itemView) {
            super(itemView);
            lkontrak = (LinearLayout) itemView.findViewById(R.id.lkontrak);
            mKontrakNomor = itemView.findViewById(R.id.mKontrakNomor);
            mKontrakPenolakan = itemView.findViewById(R.id.mKontrakPenolakan);
            mTanggalPenolakan = itemView.findViewById(R.id.mTanggalPenolakan);
            mStatusPenolakan = itemView.findViewById(R.id.mStatusPenolakan);
        }
    }
}
