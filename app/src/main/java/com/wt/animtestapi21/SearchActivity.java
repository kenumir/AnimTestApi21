package com.wt.animtestapi21;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;

public class SearchActivity extends AppCompatActivity {

    public static void show(Context ctx, int x, int y) {
        Intent it = new Intent(ctx, SearchActivity.class);
        it.putExtra("x", x);
        it.putExtra("y", y);
        ctx.startActivity(it);
    }

    private View topView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, 0);
        setContentView(R.layout.activity_search);

        final int x = getIntent().getIntExtra("x", 0);
        final int y = getIntent().getIntExtra("y", 0);

        topView = findViewById(R.id.topView);
        topView.post(new Runnable() {
            @Override
            public void run() {
                int cx = topView.getWidth() -100;
                int cy = topView.getHeight() -100;

                float finalRadius = Math.max(topView.getWidth(), topView.getHeight());

                // create the animator for this view (the start radius is zero)
                Animator circularReveal = ViewAnimationUtils.createCircularReveal(topView, cx, cy, 0, topView.getMeasuredHeight() + 100);
                circularReveal.setInterpolator(new AccelerateInterpolator());
                circularReveal.setDuration(400);
                circularReveal.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });

                // make the view visible and start the animation
                topView.setVisibility(View.VISIBLE);
                circularReveal.start();
            }
        });
    }
}
