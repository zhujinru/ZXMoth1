package com.bwei.zxmoth1;



import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyBaseAdapter extends BaseAdapter {
    List<GsonBean.ListdataBean> listdata;
    public MyBaseAdapter(List<GsonBean.ListdataBean> listdata) {
        this.listdata=listdata;
    }

    @Override
    public int getCount() {
        return listdata.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        int itemViewType = getItemViewType(i);
        if (view==null){
            if (itemViewType==0){
                view= View.inflate(viewGroup.getContext(),R.layout.child1,null);
                viewHolder=new ViewHolder();
                viewHolder.imageView=view.findViewById(R.id.c_imageview);
                viewHolder.textView1=view.findViewById(R.id.c_textview1);
                viewHolder.textView2=view.findViewById(R.id.c_textview2);
                view.setTag(viewHolder);
            }else {
                view= View.inflate(viewGroup.getContext(),R.layout.child2,null);
                viewHolder=new ViewHolder();
                viewHolder.imageView=view.findViewById(R.id.c_imageview);
                viewHolder.textView1=view.findViewById(R.id.c_textview1);
                viewHolder.textView2=view.findViewById(R.id.c_textview2);
                view.setTag(viewHolder);
            }

        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        GsonBean.ListdataBean listdataBean = listdata.get(i);
        String avatar = listdataBean.getAvatar();
        NetUtil.getInstance().getpho(avatar,viewHolder.imageView);
        viewHolder.textView1.setText(listdataBean.getName());
        viewHolder.textView2.setText(listdataBean.getContent());
        return view;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        int type = listdata.get(position).getType();
        if (type==1){
            return 0;
        }else {
            return 1;
        }
    }

    public class ViewHolder{
        private ImageView imageView;
        private TextView textView1;
        private TextView textView2;
    }
}
