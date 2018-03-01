package com.estiam.ott_estiam;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private LiveViewPagerAdapter mLiveViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private List<Live> lives = new ArrayList<>();
    //private Button btnSkip, btnNext;
    //private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        viewPager = findViewById(R.id.view_pager_live);
        dotsLayout = findViewById(R.id.layoutDots);

        // adding bottom dots
        addBottomDots(0);

        Live liveHLS = new Live();
        Live liveDASH = new Live();

        liveHLS.setType(Type.HLS);
        liveDASH.setType(Type.DASH);

        lives.add(new Live());
        lives.add(new Live());
        lives.add(new Live());
        lives.add(new Live());

        mLiveViewPagerAdapter = new LiveViewPagerAdapter(this, (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE),lives);
        viewPager.setAdapter(mLiveViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);


    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[lives.size()];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    /*
    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
        finish();
    }*/

    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            // changing the next button text 'NEXT' / 'GOT IT'
            /*
            if (position == layouts.length - 1) {
                // last page. make button text to GOT IT
                btnNext.setText(getString(R.string.start));
                btnSkip.setVisibility(View.GONE);
            } else {
                // still pages are left
                btnNext.setText(getString(R.string.next));
                btnSkip.setVisibility(View.VISIBLE);
            }*/
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    public void launchLive(Live live){
        Intent intent = new Intent(this,PlayerActivity.class);
        if(live.getType()!=null)
            intent.putExtra("type",live.getType());
        else
            intent.putExtra("type", Type.DASH);
        startActivity(intent);
    }
}
