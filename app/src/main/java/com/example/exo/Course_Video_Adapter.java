package com.example.exo;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Handler;
import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Shreyas on 21-06-2017.
 * <p>
 */


public class Course_Video_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Video_List> video_lists;
    private Context context;
    private ProgressDialog pDialog;


    public Course_Video_Adapter(Context context, List<Video_List> video_lists) {
        this.video_lists = video_lists;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_video_list, parent, false);
        return new News_Card_5(view);

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final Video_List feeds = video_lists.get(position);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "SourceSansProBold.otf");
        Typeface typeface2 = Typeface.createFromAsset(context.getAssets(), "SourceSansProRegular.otf");
        Typeface typeface1 = Typeface.createFromAsset(context.getAssets(), "sourcesans.otf");

        ((News_Card_5) holder).title.setText(feeds.Get_Title());
        ((News_Card_5) holder).subtitle.setText(feeds.Get_Subtitle());
        ((News_Card_5) holder).title.setTypeface(typeface2);
        ((News_Card_5) holder).subtitle.setTypeface(typeface2);
        ((News_Card_5) holder).number.setTypeface(typeface1);
        ((News_Card_5) holder).number.setText(String.valueOf(position+1));






    }

    @Override
    public int getItemCount() {
        return video_lists.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }


    private static class News_Card_5 extends RecyclerView.ViewHolder {
        TextView title, subtitle, number;
        RelativeLayout rel;



        News_Card_5(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            subtitle = (TextView) itemView.findViewById(R.id.subtitle);
            number = itemView.findViewById(R.id.number);
            rel = itemView.findViewById(R.id.rel);


        }
    }




}
