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
import com.ahmadfauzirahman.sakato.model.KontrakModel;
import com.ahmadfauzirahman.sakato.response.KontrakResponse;
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
public class KontrakFragment extends Fragment {


    public KontrakFragment() {
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

        view = inflater.inflate(R.layout.fragment_kontrak, container, false);

        // Inflate the layout for this fragment
        sessionManager = new SessionManager(getContext());
        swipeRefreshLayout = view.findViewById(R.id.skontrak);

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


        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.reykontraks);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        apiService.kontrakbykd(stakeholder).enqueue(new Callback<KontrakResponse>() {
            @Override
            public void onResponse(Call<KontrakResponse> call, Response<KontrakResponse> response) {
                Log.e(TAG, "OnResponse Url" + response.toString());
                System.out.println("OnResponse Data" + response.body().toString());
                if (response.isSuccessful()) {
                    List<KontrakModel> kontrakModels = response.body().getDataKontrak();
                    recyclerView.setAdapter(new KontrakAdapter(kontrakModels, R.layout.list_kontrak, getContext()));
                } else {
                    System.out.println("OnResponse Data" + response.body().toString());
                    Log.e(TAG, "OnError" + response.body().toString());

                    Toast.makeText(getContext(), "Tidak Terhubung KeJaringan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<KontrakResponse> call, Throwable t) {
                Log.e(TAG, "OnError" + t.getLocalizedMessage());
                System.out.println("Error Aplikasi" +
                        "" + t.getLocalizedMessage());

            }
        });
    }

}
