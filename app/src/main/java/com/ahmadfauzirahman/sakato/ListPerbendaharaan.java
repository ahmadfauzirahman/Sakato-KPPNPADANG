package com.ahmadfauzirahman.sakato;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ahmadfauzirahman.sakato.adapter.PerbendaharaanAdapter;
import com.ahmadfauzirahman.sakato.model.PerbendaharaanModel;
import com.ahmadfauzirahman.sakato.response.PerbendaharaanResponse;
import com.ahmadfauzirahman.sakato.rest.ApiClient;
import com.ahmadfauzirahman.sakato.rest.ApiInterface;
import com.ahmadfauzirahman.sakato.utils.SessionManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListPerbendaharaan extends AppCompatActivity {
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    SessionManager sessionManager;
    String stakeholder;
    private String TAG = this.getClass().getSimpleName();
    PerbendaharaanAdapter perbendaharaanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_perbendaharaan);
        recyclerView = (RecyclerView) findViewById(R.id.reyperbendaharaan);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swpperbendahraan);
        sessionManager = new SessionManager(this);
        // get staketholder saat login
        stakeholder = sessionManager.getUserDetail().get("penUsername");
        Toast.makeText(getApplicationContext(), "KODE STAKE HOLDER " + stakeholder, Toast.LENGTH_SHORT).show();
        allstakeholder(stakeholder.toString());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Tambah Data Perbendaharaaan", Snackbar.LENGTH_LONG)
//                        .setAction(":", null).show();
                Intent intent = new Intent(getApplicationContext(), FormBendahara.class);
                startActivity(intent);
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                // loading = ProgressDialog.show(context,null,"Sedang mendapatkan berita",true,false);
                allstakeholder(stakeholder.toString());
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void allstakeholder(String id) {
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.reyperbendaharaan);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<PerbendaharaanResponse> call = apiService.bendaharaall(id);
        call.enqueue(new Callback<PerbendaharaanResponse>() {
            @Override
            public void onResponse(Call<PerbendaharaanResponse> call, Response<PerbendaharaanResponse> response) {
                int statusCode = response.code();
                if (response.isSuccessful()) {
                    System.out.println("onResponse: " + response.body().getDataPerbendaharaan());
                    List<PerbendaharaanModel> perbendaharaanModels = response.body().getDataPerbendaharaan();
                    perbendaharaanAdapter = new PerbendaharaanAdapter(perbendaharaanModels, R.layout.list_perbendaharaan, getApplicationContext());
                    recyclerView.setAdapter(perbendaharaanAdapter);
                } else {
                    Toast.makeText(getApplicationContext(), "Anda Tidak Terhubung Kejaringan Internet", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<PerbendaharaanResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_jadwal_seminar, menu);
        MenuItem search = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void search(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                perbendaharaanAdapter.getFilter().filter(newText);
                return true;
            }
        });

    }

}
