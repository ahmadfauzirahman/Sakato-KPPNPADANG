package com.ahmadfauzirahman.sakato.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ahmadfauzirahman.sakato.PengumumanDetailActiviy;
import com.ahmadfauzirahman.sakato.R;
import com.ahmadfauzirahman.sakato.model.PengumumanModel;

import java.util.ArrayList;
import java.util.List;

public class PengumumanAdapter extends RecyclerView.Adapter<PengumumanAdapter.PengumumanViewHolder> implements Filterable {
    private List<PengumumanModel> mFilteredList;
    private List<PengumumanModel> mArrayList;
    private int rowLayout;
    private Context context;

    public PengumumanAdapter(List<PengumumanModel> pengumumanModelList, int rowLayout, Context context) {
        this.mFilteredList = pengumumanModelList;
        this.mArrayList = pengumumanModelList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @NonNull
    @Override
    public PengumumanViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new PengumumanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PengumumanViewHolder pengumumanViewHolder, int i) {
//        pengumumanViewHolder.mTanggalPengumuman.setText(mFilteredList.get(i).getPengTanggalPengumuman());
        pengumumanViewHolder.mJudulPengumuman.setText(mFilteredList.get(i).getPengJudul());
        pengumumanViewHolder.mPengumuman.setText(mFilteredList.get(i).getPengId());
    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();

                if (charString.isEmpty()) {
                    mFilteredList = mArrayList;
                } else {
                    ArrayList<PengumumanModel> filteredList = new ArrayList<>();

                    for (PengumumanModel pengumumanModel : mArrayList) {
                        if (pengumumanModel.getPengIsi().toLowerCase().contains(charString) ||
                                pengumumanModel.getPengJudul().toLowerCase().contains(charString)) {
                            filteredList.add(pengumumanModel);
                        }
                    }
                    mFilteredList = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mFilteredList = (ArrayList<PengumumanModel>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class PengumumanViewHolder extends RecyclerView.ViewHolder {
        LinearLayout lPengumuman;
        TextView mJudulPengumuman, mTanggalPengumuman, mPengumuman;

        public PengumumanViewHolder(@NonNull final View itemView) {
            super(itemView);
            lPengumuman = (LinearLayout) itemView.findViewById(R.id.lpengumuman);
            mJudulPengumuman = (TextView) itemView.findViewById(R.id.mJudulPengumuman);
            mTanggalPengumuman = (TextView) itemView.findViewById(R.id.mTanggalPenolakan);
            mPengumuman = (TextView) itemView.findViewById(R.id.mPengumuman);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), PengumumanDetailActiviy.class);
                    intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("id", mPengumuman.getText());
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
