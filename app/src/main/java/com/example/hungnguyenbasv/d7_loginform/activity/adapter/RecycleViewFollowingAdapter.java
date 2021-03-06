package com.example.hungnguyenbasv.d7_loginform.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hungnguyenbasv.d7_loginform.R;
import com.example.hungnguyenbasv.d7_loginform.activity.model.ShowFollowingResponse;
import com.example.hungnguyenbasv.d7_loginform.activity.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hung.nguyenba.sv on 7/28/2017.
 */

public class RecycleViewFollowingAdapter
        extends RecyclerView.Adapter<RecycleViewFollowingAdapter.ViewHolder> {
    private List<ShowFollowingResponse.User> arrayList = new ArrayList<>();
    private Context context;

    public RecycleViewFollowingAdapter(Context context, List<ShowFollowingResponse.User> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // This method will inflate the custom layout and return as viewholder
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());

        View itemView = mInflater.inflate(R.layout.item_row_following, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ShowFollowingResponse.User model = arrayList.get(position);
        ImageUtils.setImageByPicasso(model.getAvatar(), holder.ivAvatar);

        holder.tvName.setText(model.getName());
        holder.tvPosition.setText(model.getRoles());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivAvatar, ivBlock, ivFollowing;
        public TextView tvName, tvPosition;

        public ViewHolder(View itemView) {
            super(itemView);
            ivAvatar = (ImageView) itemView.findViewById(R.id.ivAvatarFollowing);
            ivBlock = (ImageView) itemView.findViewById(R.id.ivBlockFollowing);
            ivFollowing = (ImageView) itemView.findViewById(R.id.ivFollowingFollowing);
            tvName = (TextView) itemView.findViewById(R.id.tvNameFollowing);
            tvPosition = (TextView) itemView.findViewById(R.id.tvPositionFollowing);

            ivBlock.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Click block account", Toast.LENGTH_SHORT).show();
                }
            });
            ivFollowing.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Click following", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
