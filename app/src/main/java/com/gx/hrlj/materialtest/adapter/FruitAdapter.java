package com.gx.hrlj.materialtest.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gx.hrlj.materialtest.Fruit;
import com.gx.hrlj.materialtest.R;
import com.gx.hrlj.materialtest.TestActivity;

import java.util.List;

/**
 * Created by 777 on 2018/7/7.
 */
public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private Context mContext;
    private List<Fruit> fruitList;

    public FruitAdapter(List<Fruit> fruitList) {
        this.fruitList = fruitList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView iv_fruit;
        TextView tv_fruit;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            iv_fruit = (ImageView) itemView.findViewById(R.id.iv_fruit);
            tv_fruit = (TextView) itemView.findViewById(R.id.tv_fruit);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_fruit, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Fruit fruit = fruitList.get(position);
        holder.tv_fruit.setText(fruit.getName());
//        holder.iv_fruit.setImageResource();
        //Glide是Github上的一个开源加载图片，可以加载本地图片，GIF图，甚至是本地视频
        Glide.with(mContext).load(fruit.getImageId()).into(holder.iv_fruit);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(mContext, TestActivity.class);
                it.putExtra(TestActivity.FURIT_NAME, fruit.getName());
                it.putExtra(TestActivity.FURIT_IMAGE_ID, fruit.getImageId());
                mContext.startActivity(it);
            }
        });

    }

    @Override
    public int getItemCount() {
        return fruitList.size();
    }

}
