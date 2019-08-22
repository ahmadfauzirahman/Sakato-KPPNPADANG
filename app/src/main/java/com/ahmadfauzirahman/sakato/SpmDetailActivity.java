package com.ahmadfauzirahman.sakato;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmadfauzirahman.sakato.model.SpmModel;
import com.ahmadfauzirahman.sakato.response.SpmResponse;
import com.ahmadfauzirahman.sakato.rest.ApiClient;
import com.ahmadfauzirahman.sakato.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpmDetailActivity extends AppCompatActivity {
    ApiInterface apiService =
            ApiClient.getClient().create(ApiInterface.class);
    private static final String TAG = "SPM DETAIL";
    TextView spmnomor, spmjenis, spmPenolakan;
    Button btnRubah;
    RadioGroup radioGroupNb;
    RadioButton radioButtonNb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spm_detail);
        final String id = getIntent().getStringExtra("id");
        spmnomor = findViewById(R.id.spmnomor);
        spmjenis = findViewById(R.id.spmJenis);
        spmPenolakan = findViewById(R.id.spmPenolakan);
        radioGroupNb = findViewById(R.id.radioGroupNb);

        btnRubah = findViewById(R.id.buttonRubah);
        btnRubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = radioGroupNb.getCheckedRadioButtonId();
                // mencari radio button
                radioButtonNb = findViewById(selectedId);
//                Toast.makeText(getApplicationContext(),
//                        "Kamu Memilih Pilih  " + radioButtonNb.getText(),
//                        Toast.LENGTH_SHORT).show();
                updateStatus(id, radioButtonNb.getText().toString());
            }
        });
        apiService.spmbyid(id).enqueue(new Callback<SpmResponse>() {
            @Override
            public void onResponse(Call<SpmResponse> call, Response<SpmResponse> response) {
//                System.out.println("Response Spm" + response.toString());
                if (response.isSuccessful()) {
                    List<SpmModel> spmModelList = response.body().getData();
                    SpmModel spmModel = spmModelList.get(0);
                    System.out.println("Response Spm" + spmModelList);
                    spmnomor.setText(spmModel.getSpmNomor());
                    spmjenis.setText(spmModel.getSpmJenis());
                    spmPenolakan.setText(spmModel.getSpamPenolakan());
                } else {

                    Toast.makeText(getApplicationContext(), "TIDAK TERHUBUNG KEDALAM SERVER", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SpmResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "TIDAK TERHUBUNG KEDALAM SERVER" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                System.out.println("Data Response" + t.getLocalizedMessage());
                Log.e("On Response Gagal", t.getLocalizedMessage());
            }
        });
    }

    public void updateStatus(String id, String jenis) {
        apiService.updatePending(id, jenis).enqueue(new Callback<SpmResponse>() {
            @Override
            public void onResponse(Call<SpmResponse> call, Response<SpmResponse> response) {
//                System.out.println("Response Spm" + response.toString());
                if (response.isSuccessful()) {
                    List<SpmModel> spmModelList = response.body().getData();
                    SpmModel spmModel = spmModelList.get(0);
                    System.out.println("Response Spm" + spmModelList);
                    spmnomor.setText(spmModel.getSpmNomor());
                    spmjenis.setText(spmModel.getSpmJenis());
                    spmPenolakan.setText(spmModel.getSpamPenolakan());
                } else {

                    Toast.makeText(getApplicationContext(), "TIDAK TERHUBUNG KEDALAM SERVER", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SpmResponse> call, Throwable t) {
//                System.out.println("Data Response" + t.getLocalizedMessage());
                Log.e("On Response Gagal", t.getLocalizedMessage());
            }
        });
    }
}
