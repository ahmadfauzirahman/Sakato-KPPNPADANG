package com.ahmadfauzirahman.sakato;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmadfauzirahman.sakato.model.StakeholderModel;
import com.ahmadfauzirahman.sakato.response.StakeholderResponse;
import com.ahmadfauzirahman.sakato.rest.ApiClient;
import com.ahmadfauzirahman.sakato.rest.ApiInterface;
import com.ahmadfauzirahman.sakato.utils.SessionManager;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileStakeholder extends AppCompatActivity {
    SessionManager sessionManager;
    ApiInterface apiService =
            ApiClient.getClient().create(ApiInterface.class);
    String kd, em, desk, nm, sk;
    private static final String TAG = "Profile Stake Holder";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_stakeholder);

        sessionManager = new SessionManager(this);
        final String stakeholder = sessionManager.getUserDetail().get("penUsername");
        final TextView kodestakeholder = findViewById(R.id.etKodeStakeholder);
        final TextView email = findViewById(R.id.etEmailStakeholder);
        final TextView deskripsi = findViewById(R.id.etDeskripsi);
        final TextView nama = findViewById(R.id.etNamaStakeholder);
        final Button btnstakeholder = findViewById(R.id.btnstakeholder);
        kodestakeholder.setText(stakeholder);
        apiService.profile(stakeholder).enqueue(new Callback<StakeholderResponse>() {
            @Override
            public void onResponse(Call<StakeholderResponse> call, Response<StakeholderResponse> response) {
                System.out.println("Perbendaharaan Response" + response.toString());
                if (response.isSuccessful()) {
                    List<StakeholderModel> stakeholderModels = response.body().getDataStakeHolder();
                    StakeholderModel stakeholderModel = stakeholderModels.get(0);
                    email.setText(stakeholderModel.getStakeEmail());
                    deskripsi.setText(stakeholderModel.getStake());
                    nama.setText(stakeholderModel.getStakeNama());

                    btnstakeholder.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            em = email.getText().toString();
                            kd = deskripsi.getText().toString();
                            nm = nama.getText().toString();
                            edit(kd, em, nm, kodestakeholder.toString());
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "TIDAK TERHUBUNG KEDALAM SERVER", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StakeholderResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "TIDAK TERHUBUNG KEDALAM SERVER" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                System.out.println("Data Response" + t.getLocalizedMessage());
            }
        });
    }

    private void edit(String kd, String em, String nm, String kodestakeholder) {
        apiService.edit(nm, kodestakeholder, em, kd).enqueue(new Callback<StakeholderResponse>() {
            @Override
            public void onResponse(Call<StakeholderResponse> call, Response<StakeholderResponse> response) {
                Log.d(TAG, "onResponse: Berhasil Merubah" + response.body());
                Log.d(TAG, "onResponseDaata: Data" + response.toString());
                if (response.isSuccessful()) {
                    Boolean success = response.body().getStatus();
                    if (success) {
                        showdialog("sukses");

                    } else {
                        Log.d(TAG, "onResponseGagal" + response.toString());
                        Toast.makeText(getApplicationContext(), "Tidak Berhasil Data Merubah Data Profile", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), ProfileStakeholder.class);
                        startActivity(intent);
                    }
                } else {
                    Log.d(TAG, "onResponseGagal" + response.toString());
                    Toast.makeText(getApplicationContext(), "Tidak Berhasil Merubah Profile", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), ProfileStakeholder.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<StakeholderResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Gagal terhubung dengan server", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onResponseGagal" + t.getLocalizedMessage());
            }
        });
    }

    private void showdialog(String id) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
//        AlertDialog.Builder(this, );
//        alertDialogBuilder.B
        LayoutInflater factory = LayoutInflater.from(ProfileStakeholder.this);
        switch (id) {
            case "sukses":
                final View view = factory.inflate(R.layout.sample, null);

                String title = "Berhasil Merubah Data Perbendaharaan";
                SpannableStringBuilder ssBuilder = new SpannableStringBuilder(title);
                ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#ffffff"));
                ssBuilder.setSpan(
                        foregroundColorSpan,
                        0,
                        title.length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                );
                alertDialogBuilder
                        .setTitle(ssBuilder)
                        .setView(view)
                        .setIcon(R.drawable.ceria)
                        .setCancelable(false);
                break;
            case "gagal":
                final View view1 = factory.inflate(R.layout.gagal, null);

                String gagal = "Gagal Merubah Data";
                SpannableStringBuilder gagalss = new SpannableStringBuilder(gagal);
                ForegroundColorSpan foregroundColorSpangagal = new ForegroundColorSpan(Color.parseColor("#ffffff"));
                gagalss.setSpan(
                        foregroundColorSpangagal,
                        0,
                        gagal.length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                );
                alertDialogBuilder
                        .setTitle(gagalss)
                        .setView(view1)
                        .setIcon(R.drawable.ceria)
                        .setCancelable(false);
                break;
            default:
                final View view2 = factory.inflate(R.layout.gagal, null);

                String pending = "Tidak Terhubung Kedalam Jaringan";
                SpannableStringBuilder jaringantext = new SpannableStringBuilder(pending);
                ForegroundColorSpan jaringanwarna = new ForegroundColorSpan(Color.parseColor("#ffffff"));
                jaringantext.setSpan(
                        jaringanwarna,
                        0,
                        pending.length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                );
                alertDialogBuilder
                        .setTitle(jaringantext)
                        .setView(view2)
                        .setIcon(R.drawable.ceria)
                        .setCancelable(false);
                break;
        }

        // membuat alert dialog dari builder
        final AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#862588")));
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                alertDialog.dismiss();
                timer.cancel();
            }
        }, 2000);
    }
}
