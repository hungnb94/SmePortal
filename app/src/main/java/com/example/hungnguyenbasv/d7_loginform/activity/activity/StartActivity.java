package com.example.hungnguyenbasv.d7_loginform.activity.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;

import com.example.hungnguyenbasv.d7_loginform.R;

public class StartActivity extends AppCompatActivity {
    TextView tvMessage;
    SharedPreferences sharedPreferences;
    private String token;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        getSupportActionBar().hide();

//        tvMessage = (TextView) findViewById(R.id.tvMessage);

        sharedPreferences = getSharedPreferences(LoginActivity.SHARED_PREFERENCES, MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");

        if (TextUtils.isEmpty(token)) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Check network connection
        if (!isNetworkAvailable()) {
            new AlertDialog.Builder(this, android.R.style.Theme_Holo_Light_Dialog)
                    .setTitle(getString(R.string.network_unavailable))
                    .setMessage(getString(R.string.turn_on_network_connection))
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .show();
        } else {
            Intent intent = new Intent(this, MenuMainActivity.class);
            intent.putExtra("token", token);
            startActivity(intent);
            finish();
        }
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityMgr.getActiveNetworkInfo();
        /// if no network is available networkInfo will be null
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }
}
