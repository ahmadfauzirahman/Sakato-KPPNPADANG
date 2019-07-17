package com.ahmadfauzirahman.sakato.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ahmadfauzirahman.sakato.R;
import com.ahmadfauzirahman.sakato.adapter.KontrakAdapter;
import com.ahmadfauzirahman.sakato.adapter.SupplierAdapter;
import com.ahmadfauzirahman.sakato.model.KontrakModel;
import com.ahmadfauzirahman.sakato.model.SupplierModel;
import com.ahmadfauzirahman.sakato.response.SupplierResponse;
import com.ahmadfauzirahman.sakato.rest.ApiClient;
import com.ahmadfauzirahman.sakato.rest.ApiInterface;
import com.ahmadfauzirahman.sakato.utils.SessionManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class SupplierFragment extends Fragment {


    public SupplierFragment() {
        // Required empty public constructor
    }

    SwipeRefreshLayout swipeRefreshLayout;
    SessionManager sessionManager;
    String stakeholder;
    View view;
    JSONArray jsonArray;
    JSONObject data;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    public final String TAG = KontrakFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_supplier, container, false);

        // Inflate the layout for this fragment
        sessionManager = new SessionManager(getContext());
        swipeRefreshLayout = view.findViewById(R.id.ssupplier);

        stakeholder = sessionManager.getUserDetail().get("penUsername");
        all(stakeholder);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // loading = ProgressDialog.show(context,null,"Sedang mendapatkan berita",true,false);
                swipeRefreshLayout.setRefreshing(false);
                all(stakeholder);

            }
        });

        return view;
    }

    private void all(String stakeholder) {
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.reysupplier);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        apiService.supbykd(stakeholder).enqueue(new Callback<SupplierResponse>() {
            @Override
            public void onResponse(Call<SupplierResponse> call, Response<SupplierResponse> response) {
                Log.e(TAG, "OnResponse Url" + response.toString());
                System.out.println("OnResponse Data" + response.body().toString());
                if (response.isSuccessful()) {
                    List<SupplierModel> supplierModels = response.body().getDataSupplier();
                    recyclerView.setAdapter(new SupplierAdapter(supplierModels, R.layout.list_supplier, getContext()));
                } else {
                    System.out.println("OnResponse Data" + response.body().toString());
                    Log.e(TAG, "OnError" + response.body().toString());

                    Toast.makeText(getContext(), "Tidak Terhubung KeJaringan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SupplierResponse> call, Throwable t) {
                Log.e(TAG, "OnError" + t.getLocalizedMessage());
                System.out.println("Error Aplikasi" +
                        "" + t.getLocalizedMessage());

            }
        });
    }


}
