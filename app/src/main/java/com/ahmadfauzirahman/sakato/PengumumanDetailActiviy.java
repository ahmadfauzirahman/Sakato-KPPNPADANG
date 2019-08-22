package com.ahmadfauzirahman.sakato;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmadfauzirahman.sakato.model.PengumumanModel;
import com.ahmadfauzirahman.sakato.response.PengumumanResponse;
import com.ahmadfauzirahman.sakato.rest.ApiClient;
import com.ahmadfauzirahman.sakato.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PengumumanDetailActiviy extends AppCompatActivity {
    ApiInterface apiService =
            ApiClient.getClient().create(ApiInterface.class);
    private static final String TAG = "Pengumuman Detail Activity";

    TextView mJudulDetailPengumuman, mIsiDetailPengumuman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengumuman_detail_activiy);

        String id = getIntent().getStringExtra("id");
        Toast.makeText(getApplicationContext(), "ID PENGUMUMAN " + id, Toast.LENGTH_SHORT).show();
        mJudulDetailPengumuman = findViewById(R.id.mJudulDetailPengumuman);
        mIsiDetailPengumuman = findViewById(R.id.mIsiDetailPengumuman);

        apiService.pengumumanbyid(id).enqueue(new Callback<PengumumanResponse>() {
            @Override
            public void onResponse(Call<PengumumanResponse> call, Response<PengumumanResponse> response) {
                System.out.println("Pengumuman Response" + response.toString());
                if (response.isSuccessful()) {
                    List<PengumumanModel> pengumumanModelList = response.body().getDataPengumuman();
                    PengumumanModel pengumumanModel = pengumumanModelList.get(0);
                    mIsiDetailPengumuman.setText(pengumumanModel.getPengIsi());
                    mJudulDetailPengumuman.setText(pengumumanModel.getPengJudul());
                } else {
                    Toast.makeText(getApplicationContext(), "TIDAK TERHUBUNG KEDALAM SERVER", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PengumumanResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "TIDAK TERHUBUNG KEDALAM SERVER" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                System.out.println("Data Response" + t.getLocalizedMessage());
            }
        });

    }
}
