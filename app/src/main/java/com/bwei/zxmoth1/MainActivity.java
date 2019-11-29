package com.bwei.zxmoth1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.bwei.zxmoth1.Base.BaseActivity;
import com.bwei.zxmoth1.Fragment.HomeFragment;
import com.bwei.zxmoth1.Fragment.OtherFragment;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
//这是测试推的代码
    private ImageView imageview;
    private TabLayout tablayout;
    private ViewPager viewpager;
    List<Fragment> list=new ArrayList<>();
    List<String> listtit=new ArrayList<>();
    private HomeFragment homeFragment;
    private OtherFragment otherFragment;
    private OtherFragment otherFragment1;


    @Override
    protected void initData() {
        homeFragment = new HomeFragment();
        otherFragment = OtherFragment.getInstance("消息页面");
        otherFragment1 = OtherFragment.getInstance("我的页面");
        list.add(homeFragment);
        list.add(otherFragment);
        list.add(otherFragment1);

        listtit.add("首页");
        listtit.add("消息");
        listtit.add("我的");

        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return listtit.get(position);
            }
        });
        tablayout.setupWithViewPager(viewpager);
    }

    @Override
    protected void initView() {
        imageview = findViewById(R.id.imageview);
        tablayout = findViewById(R.id.tablayout);
        viewpager = findViewById(R.id.viewpager);



        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,100);
            }
        });
    }

    @Override
    protected int laoutid() {
        return R.layout.activity_main;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100&&resultCode==-1){
            Uri data1 = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data1);
                imageview.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
