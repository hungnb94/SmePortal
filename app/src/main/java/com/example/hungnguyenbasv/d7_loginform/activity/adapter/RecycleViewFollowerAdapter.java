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
 * Created by hung.nguyenba.sv on 7/31/2017.
 */

public class RecycleViewFollowerAdapter
        extends RecyclerView.Adapter<RecycleViewFollowerAdapter.ViewHolder> {

    private List<ShowFollowingResponse.User> arrayList = new ArrayList<>();
    private Context context;

    public RecycleViewFollowerAdapter(Context context, List<ShowFollowingResponse.User> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());

        View itemView = mInflater.inflate(R.layout.item_row_follower, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ShowFollowingResponse.User model = arrayList.get(position);
//        Picasso.with(context)
//                .load(model.getAvatar())
//                .placeholder(R.drawable.refresh)
//                .error(R.drawable.error)
//                .into(holder.ivAvatar);
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
            ivAvatar = (ImageView) itemView.findViewById(R.id.ivAvatarFollower);
            ivBlock = (ImageView) itemView.findViewById(R.id.ivBlock);
            ivFollowing = (ImageView) itemView.findViewById(R.id.ivFollowing);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvPosition = (TextView) itemView.findViewById(R.id.tvPosition);

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
