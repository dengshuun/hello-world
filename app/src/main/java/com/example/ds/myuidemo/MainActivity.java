package com.example.ds.myuidemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.TimeZone;

import common.App;
import common.RecyclerViewAdapter;

/**
 * 主界面
 *
 * @author dengshun
 *
 */
public class MainActivity extends Activity implements OnClickListener {

    private static final String TAG = "Launcher";

    private TextView tvTime;
    private TextView tvDate;
    private TextView tvWeek;

    private RecyclerView mRecyclerView;
    private ItemTouchHelper mItemTouchHelper;
    private RecyclerViewAdapter mAdapter;
    private ArrayList<App> apps;
    GridLayoutManager mLayoutManager;

    App radio;
    App media;
    App btPhone;
    App navigate;
    App setting;
    App phoneConnection;
    App onlineEntertainment;
    App drivingRecord;
    App calendar;
    App voice;

    String mTime;
    final String DEFAULT_TIME_FORMAT = "HH:mm";
    final String DEFAULT_DATE_FORMAT = "yyyy.MM.dd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        timeHandler.post(updateTime);
    }

    private void initView() {

        ArrayList<Integer> list = new ArrayList<>();
        Log.d("111","list.size() : " + list.size());

        tvTime = (TextView) findViewById(R.id.txt_time);
        tvDate = (TextView) findViewById(R.id.txt_date);
        tvWeek = (TextView) findViewById(R.id.txt_week);

        radio = new App(R.drawable.img_radio, R.string.txt_radio);
        media = new App(R.drawable.img_media, R.string.txt_media);
        media.setPackageName("com.hwatong.media.common");
        btPhone = new App(R.drawable.img_btphone, R.string.txt_btphone);
        navigate = new App(R.drawable.img_navigate, R.string.txt_navigate);
        setting = new App(R.drawable.img_setting, R.string.txt_setting);
        phoneConnection = new App(R.drawable.img_phone_connection, R.string.txt_phone_connection);
        onlineEntertainment = new App(R.drawable.img_online_entertainment, R.string.txt_online_entertainment);
        drivingRecord = new App(R.drawable.img_driving_record, R.string.txt_driving_record);
        calendar = new App(R.drawable.img_calendar, R.string.txt_calendar);
        voice = new App(R.drawable.img_voice, R.string.txt_voice);

        apps = new ArrayList<>();

        apps.add(radio);
        apps.add(media);
        apps.add(btPhone);
        apps.add(navigate);
        apps.add(setting);
        apps.add(phoneConnection);
        apps.add(onlineEntertainment);
        apps.add(drivingRecord);
        apps.add(calendar);
        apps.add(voice);

        mRecyclerView = (RecyclerView) findViewById(R.id.grid_recycler);
        mLayoutManager = new GridLayoutManager(this, 5, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerViewAdapter(this, apps);
        mRecyclerView.setAdapter(mAdapter);

        mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int dragFlags = 0;
                int swipeFlags = 0;
                if (recyclerView.getLayoutManager() instanceof LinearLayoutManager || recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                    dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                    swipeFlags = ItemTouchHelper.RIGHT;
                }
                return makeMovementFlags(dragFlags,swipeFlags);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                Collections.swap(apps, from, to);
                mAdapter.notifyItemMoved(from, to);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                apps.remove(position);
                mAdapter.notifyItemRemoved(position);
            }

            @Override
            public boolean isLongPressDragEnabled() {
                return false;
            }
        });

        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
        mAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view) {
                App app = apps.get(mRecyclerView.getChildAdapterPosition(view));
                startApp(app.getPackageName());
            }

            @Override
            public void onItemLongClick(View view) {
                mItemTouchHelper.startDrag(mRecyclerView.getChildViewHolder(view));
            }

        });
    }

    /**
     * 主界面时间、日期、星期刷新
     */
    private Handler timeHandler = new Handler();// 创建Handler
    @SuppressLint("SimpleDateFormat")
    private Runnable updateTime = new Runnable() {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            timeHandler.postDelayed(updateTime, 5000);

            SimpleDateFormat timeFormat = new SimpleDateFormat(DEFAULT_TIME_FORMAT);
            timeFormat.setTimeZone(TimeZone.getTimeZone("GMT+08"));// 时区设置
            mTime = timeFormat.format(new Date());// 获取当前时间
            tvTime.setText(mTime);

            SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+08"));// 时区设置
            mTime = dateFormat.format(new Date());// 获取当前时间
            tvDate.setText(mTime);

            Calendar cal = Calendar.getInstance();
            cal.setTimeZone(TimeZone.getTimeZone("GMT+08"));// 时区设置
            tvWeek.setText(getWeek(cal.get(Calendar.DAY_OF_WEEK)));
        }
    };

    private String getWeek(int week) {
        switch (week) {
            case 1:
                return "星期日";
            case 2:
                return "星期一";
            case 3:
                return "星期二";
            case 4:
                return "星期三";
            case 5:
                return "星期四";
            case 6:
                return "星期五";
            case 7:
                return "星期六";

            default:
                break;
        }
        return "星期一";
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

    }

    private void startApp(String app) {
        Intent intent = getApplicationContext().getPackageManager().getLaunchIntentForPackage(app);
        if (intent != null) {
            try {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            } catch (Exception e) {
                Log.e(TAG, "startApp error: " + e);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

}
