package com.bwei.zxmoth1.Fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.bwei.zxmoth1.Base.BaseFragment;
import com.bwei.zxmoth1.GsonBean;
import com.bwei.zxmoth1.Main2Activity;
import com.bwei.zxmoth1.MyBaseAdapter;
import com.bwei.zxmoth1.NetUtil;
import com.bwei.zxmoth1.R;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment {

    private PullToRefreshListView listview;
    private int page=1;
    private List<GsonBean.ListdataBean> listdata=new ArrayList<>();
    private LinearLayout linearlayout;

    @Override
    protected void initView(View inflate) {
        listview = inflate.findViewById(R.id.pulltorefresh);


        linearlayout = inflate.findViewById(R.id.linearlayout);
        listview.setMode(PullToRefreshBase.Mode.BOTH);



    }

    @Override
    protected int layoutid() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page=1;
                listdata.clear();
                getdata();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                getdata();

            }
        });
      getdata();

    }
    public void getdata(){

        String httpUrl="";
        if (page==1){
            httpUrl="http://blog.zhaoliang5156.cn/api/news/lawyer.json";
        }else if (page==2){
            httpUrl="http://blog.zhaoliang5156.cn/api/news/lawyer2.json";
        }else {
            httpUrl="http://blog.zhaoliang5156.cn/api/news/lawyer3.json";
        }

        if (NetUtil.getInstance().Hasnet(getActivity())){

            linearlayout.setVisibility(View.GONE);
            listview.setVisibility(View.VISIBLE);
            NetUtil.getInstance().getJson(httpUrl, new NetUtil.MyCallBack() {
                @Override
                public void onGetJson(String json) {
                    GsonBean gsonBean = new Gson().fromJson(json, GsonBean.class);
                    List<GsonBean.ListdataBean> newlistdata = gsonBean.getListdata();
                    listdata.addAll(newlistdata);
                    listview.setAdapter(new MyBaseAdapter(listdata));
                    listview.onRefreshComplete();
                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(getActivity(), Main2Activity.class);

                            intent.putExtra("key",listdata.get(i).getUrl());

                            startActivity(intent);
                        }
                    });
                }
            });
        }else {
            //无网

            listview.setVisibility(View.GONE);
            linearlayout.setVisibility(View.VISIBLE);
        }




    }

}
