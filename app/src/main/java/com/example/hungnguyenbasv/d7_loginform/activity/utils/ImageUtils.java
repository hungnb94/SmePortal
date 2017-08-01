package com.example.hungnguyenbasv.d7_loginform.activity.utils;

import android.widget.ImageView;

import com.example.hungnguyenbasv.d7_loginform.R;
import com.example.hungnguyenbasv.d7_loginform.activity.model.RoundedTransformation;
import com.squareup.picasso.Picasso;

/**
 * Created by hung.nguyenba.sv on 8/1/2017.
 */

public class ImageUtils {

    public static void setImageByPicasso(String path, ImageView view) {
        Picasso.with(view.getContext())
                .load(path)
                .placeholder(R.drawable.refresh)
                .error(R.drawable.error)
                .transform(new RoundedTransformation())
                .fit()
                .centerCrop()
                .into(view);
    }
}
