package com.example.hungnguyenbasv.d7_loginform.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hungnguyenbasv.d7_loginform.R;
import com.example.hungnguyenbasv.d7_loginform.activity.activity.ProjectInforActivity;
import com.example.hungnguyenbasv.d7_loginform.activity.model.ListProjectResponse;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by hung.nguyenba.sv on 7/28/2017.
 */

public class RecycleViewProjectAdapter
        extends RecyclerView.Adapter<RecycleViewProjectAdapter.MyViewHolder> {
    private List<ListProjectResponse.Data> arrayList;
    private Context context;

    public RecycleViewProjectAdapter(Context context, List<ListProjectResponse.Data> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // This method will inflate the custom layout and return as viewholder
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());

        ViewGroup mainGroup = (ViewGroup) mInflater.inflate(
                R.layout.item_row_project, parent, false);
        MyViewHolder listHolder = new MyViewHolder(mainGroup);
        return listHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        ListProjectResponse.Data model = arrayList.get(position);

        MyViewHolder mainHolder = (MyViewHolder) holder;// holder

//        // setting title
        mainHolder.tvProjectName.setText(model.getTitle());
        Picasso.with(context)
                .load(model.getImage_url())
                .placeholder(R.drawable.refresh)
                .error(R.drawable.error)
                .into(mainHolder.ivProjectImage);

        // set on item click listener
        mainHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProjectInforActivity.class);
                ListProjectResponse.Data data = arrayList.get(position);
                Gson gson = new Gson();
                intent.putExtra("data", gson.toJson(data));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        // View holder for gridview recycler view as we used in listview
        public TextView tvProjectName;
        public ImageView ivProjectImage;


        public MyViewHolder(View view) {
            super(view);

            // Find all views ids
            this.tvProjectName = (TextView) view.findViewById(R.id.tvProjectName);
            this.ivProjectImage = (ImageView) view.findViewById(R.id.ivProjectImage);
        }
    }
}
