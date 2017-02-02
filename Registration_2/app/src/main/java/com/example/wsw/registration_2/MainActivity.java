package com.example.wsw.registration_2;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView noticeListView;
    private NoticeListAdapter adapter;
    private List<Notice> noticeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noticeListView = (ListView) findViewById(R.id.noticeListView);
        noticeList = new ArrayList<Notice>();
        noticeList.add(new Notice("공지사항입니다.","개발자","2017-01-01"));
        noticeList.add(new Notice("공지사항입니다.","개발자","2017-01-01"));
        noticeList.add(new Notice("공지사항입니다.","개발자","2017-01-01"));
        noticeList.add(new Notice("공지사항입니다.","개발자","2017-01-01"));
        noticeList.add(new Notice("공지사항입니다.","개발자","2017-01-01"));
        adapter = new NoticeListAdapter(getApplicationContext(), noticeList);
        noticeListView.setAdapter(adapter);


        final Button chatButton = (Button) findViewById(R.id.chatButton);
        final Button registscheduleButton = (Button) findViewById(R.id.registscheduleButton);
        final Button statisitcsButton = (Button) findViewById(R.id.statisticsButton);
        final Button scheduleButton = (Button) findViewById(R.id.scheduleButton);
        final LinearLayout notice = (LinearLayout) findViewById(R.id.notice);


        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notice.setVisibility(View.GONE);
                chatButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                registscheduleButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                statisitcsButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                scheduleButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new ChatFragment());
                fragmentTransaction.commit();
            }
        });

        registscheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notice.setVisibility(View.GONE);
                chatButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                registscheduleButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                statisitcsButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                scheduleButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new RegistscheduleFragment());
                fragmentTransaction.commit();
            }
        });

        statisitcsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notice.setVisibility(View.GONE);
                chatButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                registscheduleButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                statisitcsButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                scheduleButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new StatisticsFragment());
                fragmentTransaction.commit();
            }
        });
        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notice.setVisibility(View.GONE);
                chatButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                registscheduleButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                statisitcsButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                scheduleButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new ScheduleFragment());
                fragmentTransaction.commit();
            }
        });
    }
}
