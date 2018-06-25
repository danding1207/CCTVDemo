package com.msc.cctvdemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.msc.cctvdemo.R;
import com.msc.cctvdemo.bean.TvData;
import com.msc.cctvdemo.lisenter.OnClickLisenter;

import java.util.List;

public class TVAdapter extends RecyclerView.Adapter<TVAdapter.TVViewHolder> {

    private Context context;
    private List<TvData> data;
    private OnClickLisenter lisenter;

    public TVAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<TvData> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setOnClickLisenter(OnClickLisenter lisenter) {
        this.lisenter = lisenter;
    }

    @NonNull
    @Override
    public TVAdapter.TVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recyclerview_tv, parent, false);
        return new TVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TVAdapter.TVViewHolder holder, final int position) {

        holder.tvTvName.setText(data.get(position).getTvName());
        if(lisenter!=null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lisenter.onClick(v, position);
                }
            });
        }

        Glide
                .with(context)
                .load(data.get(position).getTvIcon())
                .into(holder.ivTvIcon);

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }


    public class TVViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTvName;
        private ImageView ivTvIcon;

        public TVViewHolder(View itemView) {
            super(itemView);
            tvTvName = itemView.findViewById(R.id.tv_tv_name);
            ivTvIcon = itemView.findViewById(R.id.iv_tv_icon);
        }
    }
}
