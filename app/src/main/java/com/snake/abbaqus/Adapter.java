package com.snake.abbaqus;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private Context mContext;
    private List<Children> list = new ArrayList<>();

    public Adapter(Context mContext, List<Children> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.items, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Children children = list.get(position);
        holder.textViewTitle.setText(children.getInnerData().getTitle());
        holder.textViewDesc.setText(children.getInnerData().getPublicDescription());
        holder.comment.setText(children.getInnerData().getCommentScore() + " comments");
        PrettyTime pt = new PrettyTime();
        Date date = new Date(children.getInnerData().getCreatedUtc());
        holder.time.setText(pt.format(date));

        Glide.with(mContext)
                .load(children.getInnerData().getIconImg())
                .into(holder.imageView);


        holder.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveToDB(children.getInnerData().getTitle(), children.getInnerData().getIconImg(), children.getInnerData().getId(), children.getInnerData().getDescription());
            }
        });

        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (children.getInnerData().getUrl() != null) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, "https://www.reddit.com/"+children.getInnerData().getUrl());
                    mContext.startActivity(intent);
                }else{
                    Toast.makeText(mContext, "Could not share this data", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View v1 = LayoutInflater.from(mContext).inflate(R.layout.bottomsheet, null);

                BottomSheetDialog bottomDialog = new BottomSheetDialog(mContext);
                bottomDialog.setContentView(v1);
                bottomDialog.show();

                TextView desc = bottomDialog.findViewById(R.id.desc);
                desc.setText(children.getInnerData().getDescription());

            }
        });

    }

    private void saveToDB(String title, String image, String id, String desc) {
        SQLiteDatabase database = new SQLiteDBHelper(mContext).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SQLiteDBHelper.COLUMN_TITLE, title);
        values.put(SQLiteDBHelper.COLUMN_IMAGE, image);
        values.put(SQLiteDBHelper.COLUMN_DATA_ID, id);
        values.put(SQLiteDBHelper.COLUMN_DESC, desc);
        database.insert(SQLiteDBHelper.TABLE_NAME, null, values);

        Toast.makeText(mContext, "Data Saved ", Toast.LENGTH_LONG).show();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textViewTitle;
        private TextView textViewDesc;
        private ImageView save;
        private ImageView share;
        private TextView comment;
        private TextView time;
        private CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            textViewTitle = itemView.findViewById(R.id.title);
            textViewDesc = itemView.findViewById(R.id.desc);
            save = itemView.findViewById(R.id.save);
            share = itemView.findViewById(R.id.share);
            comment = itemView.findViewById(R.id.comments);
            time = itemView.findViewById(R.id.time);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
