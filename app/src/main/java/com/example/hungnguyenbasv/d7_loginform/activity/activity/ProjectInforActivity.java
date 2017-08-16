package com.example.hungnguyenbasv.d7_loginform.activity.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hungnguyenbasv.d7_loginform.R;
import com.example.hungnguyenbasv.d7_loginform.activity.adapter.ProjectInforAdapter;
import com.example.hungnguyenbasv.d7_loginform.activity.model.ListProjectResponse;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class ProjectInforActivity extends AppCompatActivity {
    TextView tvProjectName, tvOwner, tvDescription, tvAddress;
    ImageView ivProjectImage;
    ViewPager pager;
    TabLayout tabLayout;
    ListProjectResponse.Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_infor);


        initView();
        setValue();
        initViewPager();
    }

    private void initViewPager() {
        pager = (ViewPager) findViewById(R.id.view_pager_project_infor);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout_project_infor);

        ProjectInforAdapter adapter = new ProjectInforAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    private void initView() {
        tvProjectName = (TextView) findViewById(R.id.tvProjectName);
        tvOwner = (TextView) findViewById(R.id.tvOwner);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        tvAddress = (TextView) findViewById(R.id.tvAddress);

        ivProjectImage = (ImageView) findViewById(R.id.ivProjectImage);
    }

    private void setValue() {
        Bundle bundle = getIntent().getExtras();
        Gson gson = new Gson();
        data = gson.fromJson(bundle.getString("data"), ListProjectResponse.Data.class);

        tvProjectName.setText(data.getTitle());
        tvOwner.setText(data.getOwner());
        tvDescription.setText(data.getDescription());
        tvAddress.setText(data.getAddress());
        Picasso.with(this)
                .load(data.getImage_url())
                .placeholder(R.drawable.refresh)
                .error(R.drawable.error)
                .into(ivProjectImage);
    }


    public void clickBack(View view) {
        finish();
    }
}
