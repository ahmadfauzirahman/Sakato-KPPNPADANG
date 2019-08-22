package com.ahmadfauzirahman.sakato;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmadfauzirahman.sakato.adapter.JumlahTiketAdapter;
import com.ahmadfauzirahman.sakato.adapter.KontrakAdapter;
import com.ahmadfauzirahman.sakato.adapter.PengumumanAdapter;
import com.ahmadfauzirahman.sakato.model.InitModel;
import com.ahmadfauzirahman.sakato.model.PengumumanModel;
import com.ahmadfauzirahman.sakato.response.InitResponse;
import com.ahmadfauzirahman.sakato.response.PengumumanResponse;
import com.ahmadfauzirahman.sakato.response.TokenResponse;
import com.ahmadfauzirahman.sakato.rest.ApiClient;
import com.ahmadfauzirahman.sakato.rest.ApiInterface;
import com.ahmadfauzirahman.sakato.utils.SessionManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    SessionManager sessionManager;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    PengumumanAdapter pengumumanAdapter;
    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.sDashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sessionManager = new SessionManager(this);
        if (!sessionManager.isLoggedIn()) {
            Intent i = new Intent(MainActivity.this, LoginAc.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(i);
            finish();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View view = navigationView.getHeaderView(0);


        // profile
        TextView nama = (TextView) view.findViewById(R.id.namalengkap);
        TextView level = (TextView) view.findViewById(R.id.level);

        nama.setText(sessionManager.getUserDetail().get("penNama").toUpperCase());
//        nama.setText(sessionManager.getUserDetail().get("penNama").toUpperCase());
        level.setText(sessionManager.getUserDetail().get("penLvlAkses").toUpperCase());

        final String token = sessionManager.getToken();
        System.out.println("Token " + token);
        final String stakeholder = sessionManager.getUserDetail().get("penUsername");
        inserttoken(stakeholder, token);
        all();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                // loading = ProgressDialog.show(context,null,"Sedang mendapatkan berita",true,false);
                swipeRefreshLayout.setRefreshing(false);
                inserttoken(stakeholder, token);
                all();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.homer) {
            // Handle the camera action
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);

        } else if (id == R.id.perbendaharaan) {
            Intent intent = new Intent(MainActivity.this, ListPerbendaharaan.class);
            startActivity(intent);
        } else if (id == R.id.pengumuman) {
            Intent intent = new Intent(MainActivity.this, PengumumanActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_send) {
            Intent intent = new Intent(MainActivity.this, ProfileStakeholder.class);
            startActivity(intent);
        } else if (id == R.id.penolakan) {
            Intent intent = new Intent(MainActivity.this, PenolakanActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.logout) {
            String stakeholder = sessionManager.getUserDetail().get("penUsername");
            delete(stakeholder);
            Intent intent = new Intent(MainActivity.this, LoginAc.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void delete(String stake) {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<TokenResponse> call = apiService.deletetoken(stake);
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

    private void all() {
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.reyJumlah);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        apiService.jumlahloket().enqueue(new Callback<InitResponse>() {
            @Override
            public void onResponse(Call<InitResponse> call, Response<InitResponse> response) {
                System.out.println("OnResponse Url" + response.toString());
                System.out.println("OnResponse Data" + response.body().toString());
                if (response.isSuccessful()) {
                    List<InitModel> initModels = response.body().getInit();
                    recyclerView.setAdapter(new JumlahTiketAdapter(initModels, R.layout.list_jumlah, getApplicationContext()));
                } else {
                    System.out.println("OnResponse Data" + response.body().toString());

                    Toast.makeText(getApplicationContext(), "Tidak Terhubung KeJaringan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InitResponse> call, Throwable t) {
                System.out.println("Error Aplikasi" +
                        "" + t.getLocalizedMessage());

            }
        });

    }
}
