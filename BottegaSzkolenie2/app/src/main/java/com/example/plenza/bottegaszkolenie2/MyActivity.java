package com.example.plenza.bottegaszkolenie2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class MyActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        ViewPager vp = (ViewPager) findViewById(R.id.viewPager);
        vp.setAdapter(new Adapter(getSupportFragmentManager()));
    }

    static class Adapter extends FragmentPagerAdapter {

        Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return TextFragment.create(String.valueOf(position));
        }

        @Override
        public int getCount() {
            return 10;
        }
    }
}
