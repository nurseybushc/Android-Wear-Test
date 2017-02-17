package com.unocode.androidweartest;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.DotsPageIndicator;
import android.support.wearable.view.GridViewPager;
import android.support.wearable.view.WatchViewStub;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
    private GridViewPager pager;
    private DotsPageIndicator dotsIndicator;
    private MenuGridViewPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mAdapter = new MenuGridViewPagerAdapter(MainActivity.this, R.menu.main_menu);
        mAdapter.setOnItemClickedListener(new MenuGridViewPagerAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(View v, MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.start_game:
                        //startActivity(new Intent(MainActivity.this, GameActivity.class));
                        Toast.makeText(getApplicationContext(), "startActivity(Game)",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.highscore:
                        //startActivity(new Intent(MainActivity.this, HighScoreActivity.class));
                        Toast.makeText(getApplicationContext(), "startActivity(HighScore)",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.about:
                        //startActivity(new Intent(MainActivity.this, AboutActivity.class));
                        Toast.makeText(getApplicationContext(), "startActivity(About)",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                pager = (GridViewPager) stub.findViewById(R.id.pager);

                //dots page indicator doesnt work
                dotsIndicator = (DotsPageIndicator)findViewById(R.id.page_indicator);
                dotsIndicator.setPager(pager);

                //commenting this did nothing
                /*final Resources res = getResources();
                pager.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
                    @Override
                    public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
                        // Adjust page margins:
                        //   A little extra horizontal spacing between pages looks a bit
                        //   less crowded on a round display.
                        final boolean round = insets.isRound();
                        int rowMargin = res.getDimensionPixelOffset(R.dimen.page_row_margin);
                        int colMargin = res.getDimensionPixelOffset(round ?
                                R.dimen.page_column_margin_round : R.dimen.page_column_margin);
                        pager.setPageMargins(rowMargin, colMargin);
                        return insets;
                    }
                });*/

                pager.setAdapter(mAdapter);
            }
        });
    }
}