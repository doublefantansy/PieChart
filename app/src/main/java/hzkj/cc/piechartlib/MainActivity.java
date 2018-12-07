package hzkj.cc.piechartlib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import hzkj.cc.mypiechart.ClickPieEntryListener;
import hzkj.cc.mypiechart.MyPieChart;
import hzkj.cc.mypiechart.PieEntry;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyPieChart myPieChart = findViewById(R.id.pie_chart);
        final List<PieEntry> list = new ArrayList<>();
//        list.add(new PieEntry(75, getResources().getColor(R.color.gray)));
//        list.add(new PieEntry(65, getResources().getColor(R.color.purple)));
//        list.add(new PieEntry(65, getResources().getColor(R.color.colorPrimary)));
        list.add(new PieEntry("1", 2, getResources().getColor(hzkj.cc.mypiechart.R.color.colorPrimary)));
        list.add(new PieEntry("1", 2, getResources().getColor(hzkj.cc.mypiechart.R.color.myBlue)));
        list.add(new PieEntry("1", 2, getResources().getColor(hzkj.cc.mypiechart.R.color.colorAccent)));
        list.add(new PieEntry("1", 21, getResources().getColor(hzkj.cc.mypiechart.R.color.colorPrimaryDark)));
        list.add(new PieEntry("1", 5, getResources().getColor(hzkj.cc.mypiechart.R.color.black)));
        list.add(new PieEntry("1", 6, getResources().getColor(hzkj.cc.mypiechart.R.color.white)));
        list.add(new PieEntry("1", 7, getResources().getColor(hzkj.cc.mypiechart.R.color.gray)));
        list.add(new PieEntry("1", 8, getResources().getColor(hzkj.cc.mypiechart.R.color.purple)));
        myPieChart.setList(list);
        myPieChart.setClickPieEntryListener(new ClickPieEntryListener() {
            @Override
            public void click(PieEntry p) {
                Log.d("ccnb", p
                        .getNumber() + "");
            }
        });
    }
}
