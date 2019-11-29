package com.bwei.zxmoth1.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bwei.zxmoth1.Base.BaseFragment;
import com.bwei.zxmoth1.R;

public class OtherFragment extends BaseFragment {

    private TextView o_textview;

    @Override
    protected void initView(View inflate) {
        o_textview = inflate.findViewById(R.id.o_textview);
    }

    @Override
    protected int layoutid() {
        return R.layout.fragment_other;
    }

    @Override
    protected void initData() {
        Bundle arguments = getArguments();
        o_textview.setText(arguments.getString("key"));
    }

    public static OtherFragment getInstance(String value) {
        OtherFragment otherFragment = new OtherFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key",value);
        otherFragment.setArguments(bundle);
        return otherFragment;
    }
}
