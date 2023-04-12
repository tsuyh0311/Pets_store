package com.huawei.petsstore01;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.LinkedList;
import java.util.List;


public class MainPageActivity extends AppCompatActivity {
    private List<shop>mData = null;
    private Context mcontext;
    private  ShopAdapter shopAdapter = null;
    private ListView listView;
    VideoView video ;
    TextView more ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
        video = findViewById(R.id.video);
        more = findViewById(R.id.more);
        listView = (ListView) findViewById(R.id.shoplist);

        play();
        listItem();
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainPageActivity.this, videoPageAvtivity.class);
                startActivity(intent);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(MainPageActivity.this, commodityPageActivity.class);
                startActivity(intent);
            }
        });




    }
    private void play(){
        Uri uri = Uri.parse("android.resource://"+"com.huawei.petsstore01"+"/raw/"+R.raw.video01);
        video.setVideoURI(uri);
        video.start();
        MediaController mediaController = new MediaController(this);
        video.setMediaController(mediaController);
        mediaController.setMediaPlayer(video);
        mediaController.setPadding(220,0,165,1180);

    }
    public void listItem(){
        mcontext = MainPageActivity.this;
        listView = (ListView) findViewById(R.id.shoplist);
        mData = new LinkedList<shop>();
        mData.add(new shop(R.mipmap.ic_item_head,R.mipmap.ic_store_image_1,R.mipmap.ic_store_image_2,R.mipmap.ic_store_image_3,"好养犬舍","距离1000米"));
        mData.add(new shop(R.mipmap.ic_item_head,R.mipmap.ic_store_image_1,R.mipmap.ic_store_image_2,R.mipmap.ic_store_image_3,"好养喵舍","距离900米"));
        shopAdapter = new ShopAdapter(mcontext, (LinkedList<shop>)mData);
        listView.setAdapter(shopAdapter);
    }
}