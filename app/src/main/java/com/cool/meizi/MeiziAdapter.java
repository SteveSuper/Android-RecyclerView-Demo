package com.cool.meizi;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.util.List;

public class MeiziAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Meizi> meizis;

    MeiziAdapter(Context context, List<Meizi> meizis) {
        this.context = context;
        this.meizis = meizis;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 3 == 0 ? R.layout.meizi_item_big : R.layout.meizi_item_small;
    }

    @NonNull @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == R.layout.meizi_item_big) {
            return new ViewHolderBig(
                inflater.inflate(R.layout.meizi_item_big, parent, false)
            );
        }
        else {
            return new ViewHolderSmall(
                inflater.inflate(R.layout.meizi_item_small, parent, false)
            );
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Meizi meizi = meizis.get(position);

        if (position % 3 == 0) {
            ViewHolderBig holderBig = (ViewHolderBig) holder;

            Glide
                .with(context)
                .load(meizi.getImageUrl())
                .into(holderBig.image);

            holderBig.title.setText(meizi.getTitle());
            holderBig.name.setText(meizi.getName());
            holderBig.favorites.setText(String.valueOf(meizi.getFavorites()));
            holderBig.comments.setText(String.valueOf(meizi.getComments()));

            holderBig.meiziItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startMeiziActivity(meizi);
                }
            });

        } else {
            ViewHolderSmall holderSmall = (ViewHolderSmall) holder;

            Glide
                .with(context)
                .load(meizi.getImageUrl())
                .into(holderSmall.image);

            holderSmall.title.setText(meizi.getTitle());
            holderSmall.name.setText(meizi.getName());
            holderSmall.favorites.setText(String.valueOf(meizi.getFavorites()));
            holderSmall.comments.setText(String.valueOf(meizi.getComments()));

            holderSmall.meiziItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startMeiziActivity(meizi);
                }
            });
        }
    }

    private void startMeiziActivity(Meizi meizi) {
        Intent intent = new Intent(context, MeiziDetialActivity.class);
        intent.putExtra(MeiziDetialActivity.IMAGE_URL, meizi.getImageUrl());
        intent.putExtra(MeiziDetialActivity.TITLE, meizi.getTitle());
        intent.putExtra(MeiziDetialActivity.NAME, meizi.getName());
        intent.putExtra(MeiziDetialActivity.FAVORITES, meizi.getFavorites());
        intent.putExtra(MeiziDetialActivity.COMMENTS, meizi.getComments());
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return meizis.size();
    }


    class ViewHolderSmall extends RecyclerView.ViewHolder {
        private ConstraintLayout meiziItem;
        private ImageView image;
        private TextView title;
        private TextView name;
        private TextView favorites;
        private TextView comments;

        ViewHolderSmall(View itemView) {
            super(itemView);
            meiziItem = itemView.findViewById(R.id.meizi_item_small);
            image = itemView.findViewById(R.id.meizi_item_small_image);
            title = itemView.findViewById(R.id.meizi_item_small_title);
            name = itemView.findViewById(R.id.meizi_item_small_name);
            favorites = itemView.findViewById(R.id.meizi_item_small_favorites);
            comments = itemView.findViewById(R.id.meizi_item_small_comments);
        }
    }


    class ViewHolderBig extends RecyclerView.ViewHolder {
        private ConstraintLayout meiziItem;
        private ImageView image;
        private TextView title;
        private TextView name;
        private TextView favorites;
        private TextView comments;

        ViewHolderBig(View itemView) {
            super(itemView);
            meiziItem = itemView.findViewById(R.id.meizi_item_big);
            image = itemView.findViewById(R.id.meizi_item_big_image);
            title = itemView.findViewById(R.id.meizi_item_big_title);
            name = itemView.findViewById(R.id.meizi_item_big_name);
            favorites = itemView.findViewById(R.id.meizi_item_big_favorites);
            comments = itemView.findViewById(R.id.meizi_item_big_comments);
        }
    }
}


