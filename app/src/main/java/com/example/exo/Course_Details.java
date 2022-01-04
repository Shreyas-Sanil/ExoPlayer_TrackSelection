package com.example.exo;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Course_Details extends AppCompatActivity {
    private final List<Video_List> video_lists =  new ArrayList<Video_List>();
    Course_Video_Adapter course_video_adapter;
    RecyclerView recycler_views;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_details);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "SourceSansProBold.otf");
        Typeface typeface2 = Typeface.createFromAsset(getAssets(), "SourceSansProRegular.otf");
        Typeface typeface1 = Typeface.createFromAsset(getAssets(), "sourcesans.otf");

        TextView title = findViewById(R.id.title);
        title.setTypeface(typeface);
        TextView subtitle = findViewById(R.id.subtitle);
        subtitle.setTypeface(typeface2);
        TextView price = findViewById(R.id.price);
        price.setTypeface(typeface);
        TextView desc = findViewById(R.id.desc);
        desc.setTypeface(typeface2);
        TextView textd = findViewById(R.id.textd);
        textd.setTypeface(typeface);
        TextView overview = findViewById(R.id.overview);
        overview.setTypeface(typeface);
       TextView preview_text = findViewById(R.id.preview_text);
        preview_text.setTypeface(typeface);
        TextView text1 = findViewById(R.id.text1);
        text1.setTypeface(typeface);
        TextView disc = findViewById(R.id.disc);
        disc.setTypeface(typeface2);

        TextView tex1,tex2,tex3,tex4,tex5,requirement,requ;
        tex1 = findViewById(R.id.tex1);
        tex2 = findViewById(R.id.tex2);
        tex3 = findViewById(R.id.tex3);
        tex4 = findViewById(R.id.tex4);
        tex5 = findViewById(R.id.tex5);

        ImageButton play = findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Course_Details.this, Video_Player.class);
                startActivity(intent);

            }
        });



        requirement = findViewById(R.id.requirement);
        requ = findViewById(R.id.requ);

        requirement.setTypeface(typeface);
        requ.setTypeface(typeface2);
        tex1.setTypeface(typeface2);
        tex2.setTypeface(typeface2);
        tex3.setTypeface(typeface2);
        tex4.setTypeface(typeface2);
        tex5.setTypeface(typeface2);

        recycler_views = (RecyclerView) findViewById(R.id.recyclerview);
        recycler_views.setNestedScrollingEnabled(false);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler_views.setLayoutManager(mLayoutManager);
        recycler_views.setHasFixedSize(true);
        TextView buy =findViewById(R.id.buy);
        buy.setTypeface(typeface);
        course_video_adapter = new Course_Video_Adapter(this, video_lists);
        recycler_views.setItemAnimator(new DefaultItemAnimator());
        recycler_views.setAdapter(course_video_adapter);
        ImageView image = findViewById(R.id.image);
        ImageView backimg = findViewById(R.id.backimg);
        Glide.with(this)
                .load("https://i.ytimg.com/vi/HIj8wU_rGIU/maxresdefault.jpg")
                .centerCrop()
                .into(image);

        Glide.with(this)
                .load("https://i.ytimg.com/vi/HIj8wU_rGIU/maxresdefault.jpg")
                .centerCrop()
                .into(backimg);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Course_Details.this, Video_Player.class);
                startActivity(intent);

            }
        });

        preview_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Course_Details.this, Video_Player.class);
                startActivity(intent);

            }
        });

        ImageButton back_arrow = findViewById(R.id.back_arrow);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        String jsn = "[{\"video_id\":\"1\",\"video_name\":\"Introduction\",\"subtitle\":\"Video - 05:00 mins\",\"price\":\"₹ 600\",\"thumbnail\":\"https://img.youtube.com/vi/cJLJrLlZ8no/0.jpg\"},{\"video_id\":\"1\",\"video_name\":\"Why Phython\",\"subtitle\":\"Video - 08:00 mins\",\"price\":\"₹ 500\",\"thumbnail\":\"https://i.ytimg.com/vi/_uQrJ0TkZlc/maxresdefault.jpg\"},{\"video_id\":\"1\",\"video_name\":\"Command Line Basics\",\"subtitle\":\"Video - 03:00 mins\",\"price\":\"₹ 300\",\"thumbnail\":\"https://i.ytimg.com/vi/ZXsFEie9GMc/maxresdefault.jpg\"},{\"video_id\":\"1\",\"video_name\":\"Git and Github Overview\",\"subtitle\":\"Video - 10:00 mins\",\"price\":\"₹ 300\",\"thumbnail\":\"https://i.ytimg.com/vi/H7FL9UgoGEI/maxresdefault.jpg\"},{\"video_id\":\"1\",\"video_name\":\"Phython Numbers\",\"subtitle\":\"Video - 08:00 mins\",\"price\":\"₹ 600\",\"thumbnail\":\"https://i.ytimg.com/vi/PnIWl33YMwA/maxresdefault.jpg\"}]";


        try {

            JSONArray feedArray = new JSONArray(jsn);
            for (int i = 0; i < feedArray.length(); i++) {
                JSONObject feedObj = (JSONObject) feedArray.get(i);
                Video_List video_list = new Video_List();

                video_list.Set_Id(feedObj.getString("video_id"));
                video_list.Set_Title(feedObj.getString("video_name"));
                video_list.Set_Subtitle(feedObj.getString("subtitle"));
                video_lists.add(video_list);


            }
            course_video_adapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}