package com.ahmadfauzirahman.sakato.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Filter;

import com.ahmadfauzirahman.sakato.ProfilePerbendaharaan;
import com.ahmadfauzirahman.sakato.R;
import com.ahmadfauzirahman.sakato.config.ServerConfig;
import com.ahmadfauzirahman.sakato.model.PerbendaharaanModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PerbendaharaanAdapter extends RecyclerView.Adapter<PerbendaharaanAdapter.PerbendaharaanViewHolder> implements Filterable {

    private List<PerbendaharaanModel> mFilteredList;
    private List<PerbendaharaanModel> mArrayList;
    private int rowLayout;
    private Context context;

    public PerbendaharaanAdapter(List<PerbendaharaanModel> perbendaharaanModelList, int rowLayout, Context context) {
        this.mFilteredList = perbendaharaanModelList;
        this.mArrayList = perbendaharaanModelList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @NonNull
    @Override
    public PerbendaharaanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new PerbendaharaanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PerbendaharaanViewHolder perbendaharaanViewHolder, int i) {
        perbendaharaanViewHolder.namalengkap.setText(mFilteredList.get(i).getPenNamaLengkap());
        perbendaharaanViewHolder.jabatan.setText(mFilteredList.get(i).getPenJabatan());
        perbendaharaanViewHolder.id_perbendaharaan.setText(mFilteredList.get(i).getPenId());
        perbendaharaanViewHolder.gender.setText(mFilteredList.get(i).getPenGender());
        Picasso.get().load(ServerConfig.IMAGE_FOLDER).into(perbendaharaanViewHolder.image);
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
                    ArrayList<PerbendaharaanModel> filteredList = new ArrayList<>();

                    for (PerbendaharaanModel perbendaharaanModel : mArrayList) {
                        if (perbendaharaanModel.getPenNamaLengkap().toLowerCase().contains(charString) ||
                                perbendaharaanModel.getPenJabatan().toLowerCase().contains(charString)) {
                            filteredList.add(perbendaharaanModel);
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
                mFilteredList = (ArrayList<PerbendaharaanModel>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    public class PerbendaharaanViewHolder extends RecyclerView.ViewHolder {
        LinearLayout pBendaharaaan;
        TextView namalengkap, jabatan, id_perbendaharaan,gender;
        ImageView image;

        public PerbendaharaanViewHolder(@NonNull final View itemView) {
            super(itemView);
            pBendaharaaan = (LinearLayout) itemView.findViewById(R.id.lPerbendaharaan);
            image = (ImageView) itemView.findViewById(R.id.gambarprofile);
            namalengkap = (TextView) itemView.findViewById(R.id.namalengkap);
            jabatan = (TextView) itemView.findViewById(R.id.jabatan);
            id_perbendaharaan = (TextView) itemView.findViewById(R.id.id_perbendaharaan);
            gender = (TextView) itemView.findViewById(R.id.gender);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), ProfilePerbendaharaan.class);
                    intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("id",id_perbendaharaan.getText());
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
