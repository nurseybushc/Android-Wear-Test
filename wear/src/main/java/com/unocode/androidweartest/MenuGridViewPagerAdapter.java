package com.unocode.androidweartest;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.TextViewCompat;
import android.support.wearable.view.GridPagerAdapter;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Chance on 2/12/2017.
 */

public class MenuGridViewPagerAdapter  extends GridPagerAdapter {

    private Context mContext;
    private Menu mMenu;

    //private int mBackroundRes;

    private OnItemClickListener mListener;

    MenuGridViewPagerAdapter(Activity activity, int menuResource) {
        mMenu = Utils.newMenuInstance(activity);
        activity.getMenuInflater().inflate(menuResource, mMenu);
        mContext = activity;
    }

    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public int getColumnCount(int i) {
        return mMenu.size();
    }

    @Override
    public Object instantiateItem(ViewGroup viewGroup, int row, int col) {
        final MenuItem item = mMenu.getItem(col);

        LinearLayout ll = new LinearLayout(mContext);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        //ll.setPadding(40, 40, 40, 40);

        Drawable drawable = item.getIcon();
        if (drawable != null) {
            WearButton button = new WearButton(mContext);
            button.setImageDrawable(drawable);

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 0);
            lp.weight = 1;
            //lp.setMargins(45, 0, 45, 45);

            ll.addView(button, lp);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onItemClicked(v, item);
                    }
                }
            });
        }

        TextView tv = new TextView(mContext);
        tv.setText(item.getTitle());
        TextViewCompat.setTextAppearance(tv, android.R.style.TextAppearance_Medium);
        tv.setGravity(Gravity.CENTER);
        ll.addView(tv);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ll.setTag(item.getItemId());

        viewGroup.addView(ll, lp);

        return item;
    }

    @Override
    public void destroyItem(ViewGroup viewGroup, int i, int i2, Object o) {

    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        MenuItem item = (MenuItem) o;

        int tag = (Integer) view.getTag();
        return item.getItemId() == tag;
    }

    /*
    public int getBackroundRes() {
        return mBackroundRes;
    }

    public void setBackroundRes(int backroundRes) {
        this.mBackroundRes = backroundRes;
    }

    public OnItemClickListener getOnItemClickedListener() {
        return mListener;
    }
    */

    void setOnItemClickedListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }

    interface OnItemClickListener {
        void onItemClicked(View v, MenuItem item);
    }
}