package com.ahmadfauzirahman.sakato;

import android.app.ProgressDialog;
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
import android.widget.EditText;
import android.widget.Toast;

import com.ahmadfauzirahman.sakato.response.PerbendaharaanResponse;
import com.ahmadfauzirahman.sakato.rest.ApiClient;
import com.ahmadfauzirahman.sakato.rest.ApiInterface;
import com.ahmadfauzirahman.sakato.utils.SessionManager;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormBendahara extends AppCompatActivity {
    SessionManager sessionManager;
    ApiInterface apiService =
            ApiClient.getClient().create(ApiInterface.class);
    String vnama, vjabatan, vemail, vnohp;
    private static final String TAG = "FormBendaharaActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_bendahara);
        final EditText namalengkap = (EditText) findViewById(R.id.etNamaBendaharaadd);
        final EditText jabatan = (EditText) findViewById(R.id.etJabatanPerbendaharanadd);
        final EditText email = (EditText) findViewById(R.id.etEmailadd);
        final EditText nohp = (EditText) findViewById(R.id.etNoHpadd);
        final Button btnsave = (Button) findViewById(R.id.btntambahperbendaharaan);
        sessionManager = new SessionManager(this);
        final String stakeholder = sessionManager.getUserDetail().get("penUsername");

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vnama = namalengkap.getText().toString();
                vjabatan = jabatan.getText().toString();
                vemail = email.getText().toString();
                vnohp = nohp.getText().toString();
                if (vjabatan.equals("PPK") || vjabatan.equals("KPA") || vjabatan.equals("Bendahara") || vjabatan.equals("PPSPM")) {
                    save(vnama, vjabatan, vemail, vnohp, stakeholder);
                } else {
                    showdialog("gagal");
                }
            }
        });

    }

    private void save(final String vnama, String vjabatan, String vemail, String vnohp, String kd) {
        System.out.println("Nama " + vnama + " Jabatan " + vjabatan + " No Handphone " + vnohp + " vid " + kd.toString());
        apiService.add(vnama, vjabatan, vemail, vnohp, kd).enqueue(new Callback<PerbendaharaanResponse>() {
            @Override
            public void onResponse(Call<PerbendaharaanResponse> call, Response<PerbendaharaanResponse> response) {
                Log.d(TAG, "onResponse: Berhasil Merubah" + response.body());
                Log.d(TAG, "onResponseDaata: Data" + response.toString());
                if (response.isSuccessful()) {
                    Boolean success = response.body().getStatus();
                    if (success) {
                        showdialog("sukses");

                        Intent intent = new Intent(getApplicationContext(), ListPerbendaharaan.class);
                        startActivity(intent);
                    } else {
                        Log.d(TAG, "onResponseGagal" + response.toString());
                        Toast.makeText(getApplicationContext(), "Tidak Berhasil Merubah Data Perbendaharaan", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), ListPerbendaharaan.class);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Tidak Berhasil Merubah bendahara" + vnama, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), ListPerbendaharaan.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<PerbendaharaanResponse> call, Throwable t) {
                System.out.println("Tidak Konek" + t.getLocalizedMessage());
                showdialog("pending");
            }
        });
    }

    private void showdialog(String id) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
//        AlertDialog.Builder(this, );
//        alertDialogBuilder.B
        LayoutInflater factory = LayoutInflater.from(FormBendahara.this);
        switch (id) {
            case "sukses":
                final View view = factory.inflate(R.layout.sample, null);

                String title = "Berhasil Menambahkan Data Perbendaharaan";
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

                String gagal = "Gagal Menambahkan Data Perbendaharaan";
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
