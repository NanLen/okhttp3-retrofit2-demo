package com.android.http.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.http.R;
import com.android.http.bus.RxBus;
import com.android.http.bus.event.SwitchCityEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.Unbinder;

/**
 * Created by liyanan on 16/5/4.
 */
public class SwitchCityActivity extends AppCompatActivity {
    @BindView(R.id.lv_city)
    ListView lvCity;
    private Unbinder unbinder;
    private List<String> cities;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_city);
        unbinder = ButterKnife.bind(this);
        buildData();
        initList();
    }

    private void buildData() {
        cities = new ArrayList<>();
        cities.add("北京");
        cities.add("上海");
        cities.add("广州");
        cities.add("深圳");
        cities.add("杭州");
        cities.add("天津");
        cities.add("武汉");
        cities.add("重庆");
    }

    private void initList() {
        lvCity.setAdapter(new CityAdapter(this, cities));
    }

    @OnItemClick(R.id.lv_city)
    void onItemClick(int position) {
        RxBus.getInstance().send(new SwitchCityEvent(cities.get(position)));
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    class CityAdapter extends BaseAdapter {
        private Context context;
        private List<String> cities;

        public CityAdapter(Context context, List<String> cities) {
            this.context = context;
            this.cities = cities;
        }

        @Override
        public int getCount() {
            return cities == null ? 0 : cities.size();
        }

        @Override
        public String getItem(int position) {
            return cities.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = new TextView(context);
            textView.setText(getItem(position));
            textView.setTextSize(20);
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(0, 16, 0, 16);
            textView.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
            return textView;
        }
    }
}
