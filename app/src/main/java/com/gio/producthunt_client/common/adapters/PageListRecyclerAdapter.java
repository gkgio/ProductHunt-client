package com.gio.producthunt_client.common.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gio.producthunt_client.R;
import com.gio.producthunt_client.common.eventbus.Bus;
import com.gio.producthunt_client.common.eventbus.events.main.OpenPageEvent;
import com.gio.producthunt_client.model.Post;
import com.jakewharton.rxbinding.view.RxView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Георгий on 10.03.2017.
 * gio.com
 */

public class PageListRecyclerAdapter extends RecyclerView.Adapter<PageListRecyclerAdapter.CategoryItemViewHolder> {

    private List<Post> postList;
    private Context context;
    private Bus bus;

    public PageListRecyclerAdapter(Context context, Bus bus) {
        this.context = context;
        this.bus = bus;
        postList = new ArrayList<>();
    }

    @Override
    public CategoryItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.posts_list_item, parent, false);
        return new CategoryItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryItemViewHolder holder, int position) {

        final Post post = getPost(position);

        // отображаем данные
        holder.tvPostTitle.setText(post.getName());
        holder.tvPostDescription.setText(post.getTagline());
        holder.tvPostVotes.setText(String.valueOf(post.getVotesCount()));

        // отображаем каритнку
        Glide.with(context)
                .load(post.getThumbnail().getImageUrl())
                .centerCrop()
                .placeholder(R.drawable.ic_default_image)
                .crossFade()
                .into(holder.ivPost);


        RxView.clicks(holder.itemView).subscribe(aVoid -> {
            bus.send(new OpenPageEvent(post));
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    private Post getPost(int position) {
        return postList.get(position);
    }

    public void update(List<Post> postList) {
        this.postList.clear();
        this.postList.addAll(postList);
        notifyDataSetChanged();
    }

    // view holder class ======================
    class CategoryItemViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPost;
        TextView tvPostTitle;
        TextView tvPostDescription;
        TextView tvPostVotes;

        CategoryItemViewHolder(View itemView) {
            super(itemView);
            tvPostTitle = (TextView) itemView.findViewById(R.id.tvPostTitle);
            tvPostDescription = (TextView) itemView.findViewById((R.id.tvPostDescription));
            tvPostVotes = (TextView) itemView.findViewById(R.id.tvPostVotes);
            ivPost = (ImageView) itemView.findViewById(R.id.ivPost);
        }
    }
}
