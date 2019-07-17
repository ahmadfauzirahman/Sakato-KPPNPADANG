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
import com.ahmadfauzirahman.sakato.model.SupplierModel;

import java.util.List;

public class SupplierAdapter extends RecyclerView.Adapter<SupplierAdapter.SupplierViewHolder> {

    private List<SupplierModel> supplierModels;
    private int rowLayout;
    private Context context;


    public SupplierAdapter(List<SupplierModel> supplierModels, int rowLayout, Context context) {
        this.supplierModels = supplierModels;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @NonNull
    @Override
    public SupplierViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new SupplierViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SupplierViewHolder supplierViewHolder, int i) {
        supplierViewHolder.mSupplierNama.setText(supplierModels.get(i).getSupplierNama());
        supplierViewHolder.mSupplierAlasan.setText(supplierModels.get(i).getSupplierAlasan());
        supplierViewHolder.mSupplierTanggal.setText(supplierModels.get(i).getSupplierTanggalPenolakan());
        supplierViewHolder.mStatusSupplier.setText(supplierModels.get(i).getSupplierStatus());
    }

    @Override
    public int getItemCount() {
        return supplierModels.size();
    }

    public class SupplierViewHolder extends RecyclerView.ViewHolder {
        LinearLayout lsupplier;
        TextView mSupplierNama, mSupplierAlasan, mSupplierTanggal, mStatusSupplier;

        public SupplierViewHolder(@NonNull View itemView) {
            super(itemView);
            lsupplier = (LinearLayout) itemView.findViewById(R.id.lsupplier);
            mSupplierNama = itemView.findViewById(R.id.mSupplierNama);
            mSupplierAlasan = itemView.findViewById(R.id.mSupplierAlasan);
            mSupplierTanggal = itemView.findViewById(R.id.mSupplierTanggal);
            mStatusSupplier = itemView.findViewById(R.id.mStatusSupplier);


        }
    }
}
