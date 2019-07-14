package com.ahmadfauzirahman.sakato;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmadfauzirahman.sakato.model.UserModel;
import com.ahmadfauzirahman.sakato.response.UserResponse;
import com.ahmadfauzirahman.sakato.rest.ApiClient;
import com.ahmadfauzirahman.sakato.rest.ApiInterface;
import com.ahmadfauzirahman.sakato.utils.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginAc extends AppCompatActivity {
    SessionManager sessionManager;
    ApiInterface apiService;
    public final String TAG = LoginAc.class.getSimpleName();

    @BindView(R.id.username)
    EditText etusername;

    @BindView(R.id.password)
    EditText etpassword;

    @BindView(R.id.login_button)
    Button btlogin;

    String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final TextView logotext = findViewById(R.id.logotext);
        final TextView register = findViewById(R.id.register);


        Typeface mediumlogo = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        logotext.setTypeface(mediumlogo);
        sessionManager = new SessionManager(this);
        apiService = ApiClient.getClient().create(ApiInterface.class);
        //btn login
        ButterKnife.bind(this);
        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        // register
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterAc.class);
                startActivity(intent);
            }
        });

    }

    private void loginUser() {
        username = etusername.getText().toString();
        password = etpassword.getText().toString();
        apiService.login(username, password).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    Boolean success = response.body().getStatus();
                    if (success) {
                        Log.d(TAG, "onResponse: Dapat terhubung keserver");
                        System.out.println("Dapatkan Username " + response.body().getData().getPenUsername());
                        UserModel userModel = response.body().getData();
                        sessionManager.createLoginSession(userModel);
                        Log.d(TAG, "onResponse: Data Data Pengguna");
                        Intent intent = new Intent(getApplicationContext(), Splashscreen.class);
                        Toast.makeText(getApplicationContext(), "Berhasil Login", Toast.LENGTH_SHORT).show();
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
                        startActivity(intent);
                        finish();
                    } else {
                        Log.d(TAG, "onResponsePassword: Password Salah & Username Salahj");
                        Toast.makeText(getApplicationContext(), "Password Atau Username", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), LoginAc.class);
                        startActivity(intent);
                    }
                } else {
                    Log.d(TAG, "onResponsePassword: Password Salah & Username Salahj");
                    Toast.makeText(getApplicationContext(), "Password Atau Username", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), LoginAc.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Anda Tidak Terhubung Kejaringan", Toast.LENGTH_SHORT).show();
                Log.e(TAG, t.getLocalizedMessage());
            }
        });
    }

}
