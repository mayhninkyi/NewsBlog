package com.mhk.newsblog;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {
    ArrayList<NewsModel> models=new ArrayList<>();
    Context context;

    public NewsAdapter(ArrayList<NewsModel> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, final int position) {
        holder.title.setText(models.get(position).title);
        holder.author.setText(models.get(position).author);
        holder.date.setText(models.get(position).date);
        Glide.with(context)
                .load(models.get(position).imageUrl)
                .into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailActivity.newUrl=models.get(position).newsUrl;
                Intent intent=new Intent(context,DetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class NewsHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView title,author,date;

        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.n_image);
            title=itemView.findViewById(R.id.n_title);
            author=itemView.findViewById(R.id.n_author);
            date=itemView.findViewById(R.id.n_date);
        }
    }
}
