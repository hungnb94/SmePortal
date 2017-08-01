package com.example.hungnguyenbasv.d7_loginform.activity.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by hung.nguyenba.sv on 7/28/2017.
 */

public class HintAdapter extends ArrayAdapter<Object> {

    public HintAdapter(Context theContext, int theLayoutResId, List<Object> objects) {
        super(theContext, theLayoutResId, objects);
    }

    public HintAdapter(Context theContext, int theLayoutResId, Object[] objects) {
        super(theContext, theLayoutResId, objects);
    }

    @Override
    public int getCount() {
        // don't display last item. It is used as hint.
        int count = super.getCount();
        return count > 0 ? count - 1 : count;
    }
}