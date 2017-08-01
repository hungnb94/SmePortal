package com.example.hungnguyenbasv.d7_loginform.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hungnguyenbasv.d7_loginform.R;
import com.example.hungnguyenbasv.d7_loginform.activity.model.ProjectModel;

import java.util.ArrayList;

/**
 * Created by hung.nguyenba.sv on 7/28/2017.
 */

public class RecycleViewProjectAdapter
        extends RecyclerView.Adapter<RecycleViewProjectAdapter.MyViewHolder> {
    private ArrayList<ProjectModel> arrayList;
    private Context context;

    public RecycleViewProjectAdapter(Context context, ArrayList<ProjectModel> arrayList) {
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
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ProjectModel model = arrayList.get(position);

        MyViewHolder mainHolder = (MyViewHolder) holder;// holder

//        Bitmap image = BitmapFactory.decodeResource(context.getResources(),
//                model.getImage());// This will convert drawbale image into
//        // bitmap
//
//        // setting title
//        mainHolder.title.setText(model.getTitle());
//
//        mainHolder.imageview.setImageBitmap(image);
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        // View holder for gridview recycler view as we used in listview
        public TextView title;
        public ImageView imageview;


        public MyViewHolder(View view) {
            super(view);

            // Find all views ids
//            this.title = (TextView) view.findViewById(R.id.title);
//            this.imageview = (ImageView) view.findViewById(R.id.image);


        }
    }
}
