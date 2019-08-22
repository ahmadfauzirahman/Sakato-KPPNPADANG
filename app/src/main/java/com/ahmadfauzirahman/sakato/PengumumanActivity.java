package com.ahmadfauzirahman.sakato;

import android.content.Intent;
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
import android.widget.Toast;

import com.ahmadfauzirahman.sakato.adapter.PengumumanAdapter;
import com.ahmadfauzirahman.sakato.model.PengumumanModel;
import com.ahmadfauzirahman.sakato.response.PengumumanResponse;
import com.ahmadfauzirahman.sakato.response.TokenResponse;
import com.ahmadfauzirahman.sakato.rest.ApiClient;
import com.ahmadfauzirahman.sakato.rest.ApiInterface;
import com.ahmadfauzirahman.sakato.utils.SessionManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PengumumanActivity extends AppCompatActivity {
    SessionManager sessionManager;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    PengumumanAdapter pengumumanAdapter;
    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengumuman);
        recyclerView = (RecyclerView) findViewById(R.id.reypengumuman);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.spengumuman);
        sessionManager = new SessionManager(this);
        if (!sessionManager.isLoggedIn()) {
            Intent i = new Intent(getApplicationContext(), LoginAc.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(i);
            finish();
        }
        final String token = sessionManager.getToken();
        System.out.println("Token " + token);
        final String stakeholder = sessionManager.getUserDetail().get("penUsername");
        inserttoken(stakeholder, token);
        all();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                // loading = ProgressDialog.show(context,null,"Sedang mendapatkan berita",true,false);
                all();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public void inserttoken(String stakeholder, String token) {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<TokenResponse> call = apiService.inserttoken(stakeholder, token);
        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
//                int statusCode = response.code();
                System.out.println("onResponse: " + response.body().getDataToken());
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                Log.e(TAG, "OnLog" + t.toString());

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_jadwal_seminar, menu);
        MenuItem search = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
        return true;
    }

    private void search(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                pengumumanAdapter.getFilter().filter(newText);
                return true;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void all() {
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.reypengumuman);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<PengumumanResponse> call = apiService.pengumuman();
        call.enqueue(new Callback<PengumumanResponse>() {
            @Override
            public void onResponse(Call<PengumumanResponse> call, Response<PengumumanResponse> response) {
                int statusCode = response.code();
                System.out.println("onResponse: " + response.body().getDataPengumuman());
                if (response.isSuccessful()) {
                    List<PengumumanModel> perbendaharaanModels = response.body().getDataPengumuman();
                    pengumumanAdapter = new PengumumanAdapter(perbendaharaanModels, R.layout.list_pengumuman, getApplicationContext());
                    recyclerView.setAdapter(pengumumanAdapter);
                } else {
                    System.out.println("onResponse: " + response.body().getDataPengumuman());
                    Toast.makeText(getApplicationContext(), "Anda Tidak Terhubung Kejaringan Internet", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PengumumanResponse> call, Throwable t) {
                Log.e(TAG, "OnLog" + t.toString());
            }
        });
    }
}
