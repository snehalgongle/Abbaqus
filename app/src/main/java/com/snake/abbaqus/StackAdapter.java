package com.snake.abbaqus;

import android.content.Context;
import android.media.Image;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class StackAdapter extends BaseAdapter {
    private Context mContext;
    private List<Children> list=new ArrayList<>();

    public StackAdapter(Context mContext, List<Children> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.swipecard, viewGroup, false);
        Children children=list.get(position);

        CardView cardView=itemView.findViewById(R.id.cardView);
        TextView title=itemView.findViewById(R.id.title);
        ImageView imageView = itemView.findViewById(R.id.image);
        TextView desc=itemView.findViewById(R.id.desc);

        title.setText(children.getInnerData().getTitle());
        Glide.with(mContext)
                .load(children.getInnerData().getIconImg())
                .into(imageView);

        desc.setText(children.getInnerData().getDescription());

        return itemView;
    }
}
