package com.gx.hrlj.materialtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by 777 on 2018/7/14.
 */
public class TestActivity extends AppCompatActivity {

    public static final String FURIT_NAME = "furit_name";
    public static final String FURIT_IMAGE_ID = "furit_image_id";
    private String furitname;
    private int furitImageId;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Intent it = getIntent();
        furitname = it.getStringExtra(FURIT_NAME);
        furitImageId = it.getIntExtra(FURIT_IMAGE_ID, 0);
        initview();

    }

    private void initview() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ImageView iv_furit = (ImageView) findViewById(R.id.iv_fruit_image);
        TextView tv_furit = (TextView) findViewById(R.id.tv_fruit_name);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle(furitname);
        Glide.with(this).load(furitImageId).into(iv_furit);
        String fruitString = generateFruitContent(furitname);
        tv_furit.setText(fruitString);
    }


    private String generateFruitContent(String name) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 500; i++) {
            builder.append(name);
        }
        return builder.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}