package com.example.keren.multiplechoicequiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.Vector;

public class YoutubeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        RecyclerView recyclerView;
        Vector<YoutubeVideo> youtubeVideos = new Vector<YoutubeVideo>();

        //Setting the scrollable layout for the videos.
        // RecyclerView used instead of ListView to reduce amount of times needed to write findViewById
        recyclerView = (RecyclerView) findViewById(R.id.rview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));

        // Adding in youtube URLS
        youtubeVideos.add( new YoutubeVideo("<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/ueVnSz_lXEs\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>") );

        youtubeVideos.add( new YoutubeVideo("<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/eTsNIfBR-dc\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>") );

        youtubeVideos.add( new YoutubeVideo("<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/JvXro0dzJY8\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>") );

        youtubeVideos.add( new YoutubeVideo("<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/p7UR7Nipqcs\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>") );

        youtubeVideos.add( new YoutubeVideo("<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/ueVnSz_lXEs\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>") );
        VideoAdapter videoAdapter = new VideoAdapter(youtubeVideos);
        recyclerView.setAdapter(videoAdapter);
    }
}

