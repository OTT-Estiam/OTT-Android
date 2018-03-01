package com.estiam.ott_estiam;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by nono on 01/03/2018.
 */

public class LiveViewPagerAdapter extends PagerAdapter {
    private Context mContext;
    private LayoutInflater layoutInflater;
    private List<Live> lives;
    //private Sy

    public LiveViewPagerAdapter(Context context,LayoutInflater layoutInflater,List<Live> lives) {
        this.layoutInflater = layoutInflater;
        this.mContext = context;
        this.lives = lives;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        View view = layoutInflater.inflate(R.layout.live_widget, container, false);

        ImageView imageView = view.findViewById(R.id.liveImage);
        TextView title = view.findViewById(R.id.liveTitle);

        Picasso.with(mContext)
                .load("http://lorempicsum.com/futurama/800/400/"+String.valueOf(position+1))
                .into(imageView);

        title.setText("Dummy");

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)mContext).launchLive(lives.get(position));
            }
        });

        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return lives.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }

    /*
    public View bindView(){
        View
    }*/
}
