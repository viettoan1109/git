package com.example.banhangonline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.banhangonline.R;
import com.example.banhangonline.model.LoaiVe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

// Custom từng dòng listview trên thanh công cụw

public class LoaiVeAdapter extends BaseAdapter {
    ArrayList<LoaiVe> arrayListLoaive;
    Context context;
    int layout;

    public LoaiVeAdapter(ArrayList<LoaiVe> arrayListLoaive, Context context) {
        this.arrayListLoaive = arrayListLoaive;
        this.context = context;
    }

    public class ViewHolder {
        TextView txtTenloaisp;
        ImageView imgLoaisp;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        viewHolder = new ViewHolder();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.dong_listview_loaive, viewGroup, false);
        viewHolder.txtTenloaisp = view.findViewById(R.id.textviewLoaive);
        viewHolder.imgLoaisp = view.findViewById(R.id.imageviewLoaive);
        view.setTag(viewHolder);
        viewHolder = (ViewHolder) view.getTag();
        LoaiVe loaiVe = (LoaiVe) getItem(position);
        viewHolder.txtTenloaisp.setText(loaiVe.getTenloaisp());
        Picasso.get().load(loaiVe.getHinhanhsp()).into(viewHolder.imgLoaisp);
        return view;

       /* LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=layoutInflater.inflate(layout,null);
        TextView tvTenloaisp=view.findViewById(R.id.textviewLoaive);
        ImageView imgloaisp=view.findViewById(R.id.imageviewLoaive);
        tvTenloaisp.setText(arrayListLoaive.get(position).getTenloaisp());
        Picasso.get().load(arrayListLoaive.get(position).getHinhanhsp()).centerCrop().resize(150,150).into(imgloaisp);
        return view;*/
    }
}
