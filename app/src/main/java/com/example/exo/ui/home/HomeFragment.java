package com.example.exo.ui.home;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.exo.R;
import com.example.exo.Video_List;
import com.example.exo.Video_List_Adapter;
import com.example.exo.databinding.FragmentHomeBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private List<Video_List> video_lists =  new ArrayList<Video_List>();
    Video_List_Adapter video_list_adapter;
    RecyclerView recycler_views;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "SourceSansProBold.otf");
        Typeface typeface2 = Typeface.createFromAsset(getContext().getAssets(), "SourceSansProRegular.otf");
        Typeface typeface1 = Typeface.createFromAsset(getContext().getAssets(), "sourcesans.otf");


        recycler_views = (RecyclerView) root.findViewById(R.id.recyclerview);
        recycler_views.setNestedScrollingEnabled(false);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recycler_views.setLayoutManager(mLayoutManager);
        recycler_views.setHasFixedSize(true);

       TextView  welcome  = root.findViewById(R.id.welcome);
        TextView course_text = root.findViewById(R.id.course_text);
       welcome.setTypeface(typeface1);
        course_text.setTypeface(typeface2);

        ImageView main_image  = root.findViewById(R.id.main_image);

        Glide.with(getContext())
                .load("https://i.ytimg.com/vi/HIj8wU_rGIU/maxresdefault.jpg")
                .centerCrop()
                .skipMemoryCache(true)
                .into(main_image);

        video_list_adapter = new Video_List_Adapter(getContext(), video_lists);
        recycler_views.setItemAnimator(new DefaultItemAnimator());
        recycler_views.setAdapter(video_list_adapter);

        String jsn = "[{\"video_id\":\"1\",\"video_name\":\"Unlimited AWSCertificated Development 2021 - NEW!\",\"subtitle\":\"By Anil Shetty | Ex Microsoft Cloud Developer\",\"price\":\"₹ 600\",\"thumbnail\":\"https://img.youtube.com/vi/cJLJrLlZ8no/0.jpg\"},{\"video_id\":\"1\",\"video_name\":\"Learn Phython Development 2021 - NEW!\",\"subtitle\":\"By VM Bussy | Ex Intel Cloud Developer\",\"price\":\"₹ 500\",\"thumbnail\":\"https://i.ytimg.com/vi/_uQrJ0TkZlc/maxresdefault.jpg\"},{\"video_id\":\"1\",\"video_name\":\"Learn Java Development 2021 - NEW!\",\"subtitle\":\"By Venkateshwara | Ex Apple Cloud Developer\",\"price\":\"₹ 300\",\"thumbnail\":\"https://i.ytimg.com/vi/ZXsFEie9GMc/maxresdefault.jpg\"},{\"video_id\":\"1\",\"video_name\":\"Learn Android Development 2021 - NEW!\",\"subtitle\":\"By Shreyas | Android Developer\",\"price\":\"₹ 300\",\"thumbnail\":\"https://i.ytimg.com/vi/H7FL9UgoGEI/maxresdefault.jpg\"},{\"video_id\":\"1\",\"video_name\":\"Learn Dart Development 2021 \",\"subtitle\":\"By Rahul | Dart Developer\",\"price\":\"₹ 600\",\"thumbnail\":\"https://i.ytimg.com/vi/PnIWl33YMwA/maxresdefault.jpg\"}]";


        try {

            JSONArray feedArray = new JSONArray(jsn);
            for (int i = 0; i < feedArray.length(); i++) {
                JSONObject feedObj = (JSONObject) feedArray.get(i);
                Video_List video_list = new Video_List();

                video_list.Set_Id(feedObj.getString("video_id"));
                video_list.Set_Title(feedObj.getString("video_name"));
                video_list.Set_Subtitle(feedObj.getString("subtitle"));
                video_list.Set_Image(feedObj.getString("thumbnail"));
                video_list.Set_Price(feedObj.getString("price"));
                video_lists.add(video_list);


            }
            video_list_adapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}