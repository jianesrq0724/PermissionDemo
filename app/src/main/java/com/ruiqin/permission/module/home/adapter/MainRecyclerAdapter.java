package com.ruiqin.permission.module.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.ruiqin.permission.R;
import com.ruiqin.permission.module.home.bean.MainRecyclerData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ruiqin.shen
 * 类说明：
 */

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.MainRecyclerViewHolder> {

    private List<MainRecyclerData> mMainRecyclerDataList;
    private Context mContext;

    public MainRecyclerAdapter(List<MainRecyclerData> mainRecyclerDataList) {
        mMainRecyclerDataList = mainRecyclerDataList;
    }


    @Override
    public MainRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_main_recycler, parent, false);
        MainRecyclerViewHolder mainRecyclerViewHolder = new MainRecyclerViewHolder(view);
        return mainRecyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(MainRecyclerViewHolder holder, final int position) {
        holder.mTvName.setText(mMainRecyclerDataList.get(position).getMessage());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, mMainRecyclerDataList.get(position).getCls()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMainRecyclerDataList == null ? 0 : mMainRecyclerDataList.size();
    }

    public static class MainRecyclerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_tv_name)
        public TextView mTvName;
        private View mView;

        public MainRecyclerViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            ButterKnife.bind(this, itemView);
        }
    }
}
