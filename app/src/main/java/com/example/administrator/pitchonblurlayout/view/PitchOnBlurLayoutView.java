package com.example.administrator.pitchonblurlayout.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.administrator.pitchonblurlayout.R;

/**
 * Created by Administrator on 15-6-1.
 */
public class PitchOnBlurLayoutView extends RelativeLayout implements View.OnClickListener{
    private View blurView;
    private ImageView pitchOnView;
    private ImageView showView;
    private Rect rect;
    private Drawable drawable;
    private LinearLayout.LayoutParams layoutParams;
    @SuppressLint("ResourceAsColor")
    public PitchOnBlurLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        blurView =new ImageView(context);
        blurView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        blurView.setBackgroundColor(R.color.blur);
        blurView.setVisibility(View.GONE);
        blurView.setOnClickListener(this);
        showView = new ImageView(context);
        showView.setOnClickListener(this);
        showView.setVisibility(View.GONE);
    }
    public void pitchOn(View view){
        if(view instanceof ImageView){
            show(view);
        }

    }
    @SuppressLint("ResourceAsColor")
    private void show(View view){
        rect = new Rect();
        view.getGlobalVisibleRect(rect);
        Rect rect1 = new Rect();
        getGlobalVisibleRect(rect1);
        pitchOnView = (ImageView) view;
        blurView.setVisibility(View.VISIBLE);
        showView.layout(rect.left, rect.top, rect.right, rect.bottom);
        drawable=((ImageView) view).getDrawable();
        showView.setImageDrawable(drawable);
        layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        layoutParams.width=rect.right-rect.left;
        layoutParams.height=rect.bottom-rect.top;
        showView.setVisibility(View.VISIBLE);
        ((ImageView) view).setImageDrawable(null);
        ValueAnimator  moveX = ObjectAnimator.ofFloat(showView, "translationX", rect.left,(rect1.right-rect1.left-(rect.right-rect.left))/2);
        ValueAnimator  moveY = ObjectAnimator.ofFloat(showView, "translationY",rect.top,(rect1.bottom-rect1.top-(rect.bottom-rect.top))/2);
        ValueAnimator  scaleX = ObjectAnimator.ofFloat(showView, "scaleX", 1f, 1.4f);
        ValueAnimator  scaleY = ObjectAnimator.ofFloat(showView, "scaleY", 1f, 1.4f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(moveX).with(moveY).with(scaleX).with(scaleY);
        animSet.setDuration(500);
        animSet.start();
    }
    private void dismiss(final View view){
        Rect rect1 = new Rect();
        showView.getGlobalVisibleRect(rect1);
        blurView.setVisibility(View.GONE);
        ValueAnimator  moveX = ObjectAnimator.ofFloat(showView, "translationX",rect1.left,rect.left);
        ValueAnimator  moveY = ObjectAnimator.ofFloat(showView, "translationY",rect1.top,rect.top);
        ValueAnimator  scaleX = ObjectAnimator.ofFloat(showView, "scaleX", 1.4f,1f);
        ValueAnimator  scaleY = ObjectAnimator.ofFloat(showView, "scaleY", 1.4f, 1f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(moveX).with(moveY).with(scaleX).with(scaleY);
        animSet.setDuration(500);
        animSet.start();
        animSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                pitchOnView.setImageDrawable(drawable);
                showView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        addView(blurView);
        addView(showView);
    }

    @Override
    public void onClick(View v) {
         dismiss(v);
    }
}
