package com.gx.hrlj.materialtest;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.gx.hrlj.materialtest.adapter.FruitAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    //google自带的侧滑菜单Design Support库的NavigationView
    private NavigationView navigationView;
    private FloatingActionButton fab;//悬浮按钮
    private Fruit[] fruits = {new Fruit("apple", R.drawable.apple), new Fruit("banana", R.drawable.banana),
            new Fruit("Orange", R.drawable.orange), new Fruit("Watermelon", R.drawable.watermelon),
            new Fruit("Pear", R.drawable.pear), new Fruit("Grape", R.drawable.grape),
            new Fruit("Pineapple", R.drawable.pineapple), new Fruit("Strawberry", R.drawable.strawberry),
            new Fruit("Cherry", R.drawable.cherry), new Fruit("Mango", R.drawable.mango),
            new Fruit("杰少", R.drawable.jieshao)};
    private List<Fruit> fruitList = new ArrayList<>();
    private FruitAdapter adapter;

    private RefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private int mm = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.toobar);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //让最左侧自带的导航按钮HomeAsUp显示出来
            actionBar.setDisplayHomeAsUpEnabled(true);
            //默认的图标是返回箭头，其含义是返回上一个活动在这里重新设图标,
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        //设置首个点击的item
        navigationView.setCheckedItem(R.id.nav_call);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setCheckable(true);
                drawerLayout.closeDrawers();
                switch (menuItem.getItemId()) {
                    case R.id.nav_call:
                        Toast.makeText(MainActivity.this, "you check call", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_friends:
                        Toast.makeText(MainActivity.this, "you check friends", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_mail:
                        Toast.makeText(MainActivity.this, "you check mail", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_location:
                        Toast.makeText(MainActivity.this, "you check location", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_task:
                        Toast.makeText(MainActivity.this, "you check task", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
        fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setElevation(8f);//设置悬浮高度
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "you check 悬浮按钮", Toast.LENGTH_SHORT).show();
                Snackbar.make(v, "delete data!", Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {//在Snackbar上又设置一个按钮
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "you check 悬浮按钮", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });

        initFruits();
        refreshLayout = (RefreshLayout) findViewById(R.id.refreshlayout);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(manager);
        adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);
        refreshLayout.setRefreshHeader(new ClassicsHeader(this));
//        refreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                fruitList.clear();
                for (int i = 0; i < 10; i++) {
                    Random random = new Random();
                    int index = random.nextInt(fruits.length);
                    fruitList.add(fruits[index]);
                }
                mm = 1;
                Log.i("MainActivity", "onRefresh " + fruitList.size() + "");
                adapter.notifyDataSetChanged();
                refreshLayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                if (mm <= 4) {
                    for (int i = 0; i < 10; i++) {
                        Random random = new Random();
                        int index = random.nextInt(fruits.length);
                        fruitList.add(fruits[index]);
                    }
                } else {
                    refreshLayout.finishLoadMore(1000);
                }
                ++mm;
                Log.i("MainActivity", "onLoadMore " + fruitList.size() + "");
                adapter.notifyDataSetChanged();
                refreshLayout.finishLoadMore(1000/*,false*/);//传入false表示加载失败
            }
        });
    }


    private void initFruits() {
        fruitList.clear();
        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.layout_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.backup:
                Toast.makeText(this, "you check backup`!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "you check delete2!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting:
                Toast.makeText(this, "you check setting3!", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home: //Toolbar最左侧返回图标HomeAsUp的箭头按钮，默认android.R.id.home
                //将侧滑菜单展示出来
                drawerLayout.openDrawer(GravityCompat.START);
            default:
        }
        return true;
    }
}
