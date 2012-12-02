package com.csewannabe.selection;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.csewannabe.R;

public class ScoreView extends View {
	
	int mScore;
	int mTotal;
	Paint mPaint;
	Bitmap[] mIconStates = new Bitmap[3];
	Bitmap currentIconState;
	static Rect src;
	static Rect dest = new Rect(0, 0, 80, 80);
	
	public ScoreView (Context context, AttributeSet attrs) {
		super (context, attrs);
		
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setColor(Color.BLACK);
		mPaint.setTextSize(30);
		mIconStates[0] = BitmapFactory.decodeResource(getResources(), R.drawable.green);
		mIconStates[1] = BitmapFactory.decodeResource(getResources(), R.drawable.blue);
		mIconStates[2] = BitmapFactory.decodeResource(getResources(), R.drawable.red);
		
		src = new Rect(0, 0, mIconStates[0].getHeight(), mIconStates[0].getWidth());
	}
	
	public void setScore(int score, int total) {
		mScore = score;
		mTotal = total;
		int percentage = (score*100)/total;
		if(percentage <= 33) {
			currentIconState = mIconStates[2];
		} else if(percentage <= 66) {
			currentIconState = mIconStates[1];
		} else {
			currentIconState = mIconStates[0];
		}
		invalidate();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if(currentIconState != null) {
			canvas.drawBitmap(currentIconState, src, dest, null);
			canvas.drawText(mScore + " " + mTotal, 18, 50, mPaint);
		}
	}
}
