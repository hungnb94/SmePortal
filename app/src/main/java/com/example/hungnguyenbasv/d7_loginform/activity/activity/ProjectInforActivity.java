package com.example.hungnguyenbasv.d7_loginform.activity.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.hungnguyenbasv.d7_loginform.R;
import com.example.hungnguyenbasv.d7_loginform.activity.model.ListProjectResponse;
import com.google.gson.Gson;

public class ProjectInforActivity extends AppCompatActivity {
    ListProjectResponse.Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_infor);
        Bundle bundle = getIntent().getExtras();
        Gson gson = new Gson();
        data = gson.fromJson(bundle.getString("data"), ListProjectResponse.Data.class);
        Toast.makeText(this, data.getTitle(), Toast.LENGTH_SHORT).show();
    }
}
