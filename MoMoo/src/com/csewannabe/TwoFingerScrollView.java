package com.csewannabe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

public class TwoFingerScrollView extends View {
	private Bitmap bmp;
    private Rect imgRect, scrollRect;
    private float prevX, prevY;
    private int newX, newY;

    public TwoFingerScrollView(Context context) {
        super(context);
        bmp = BitmapFactory.decodeResource(getResources(),
                );
        imgRect = new Rect(0, 0, 800, 1300);
        scrollRect = new Rect(0, 0, 800, 1300);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            int numPointers = event.getPointerCount();
            if (numPointers > 1) {
                float currX = event.getRawX();
                float deltaX = -(currX - prevX);
                newX += deltaX;
                float currY = event.getRawY();
                float deltaY = -(currY - prevY);
                newY += deltaY;
                if (newX < 0)
                    newX = 0;
                if (newY < 0)
                    newY = 0;
                if (newX > bmp.getWidth() - 800)
                    newX = bmp.getWidth() - 800;
                if (newY > bmp.getHeight() - 1300)
                    newY = bmp.getHeight() - 1300;
                scrollRect.set(newX, newY, newX + 800, newY + 1300);
                invalidate();
                prevX = currX;
                prevY = currY;
            }
        } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
            prevX = event.getRawX();
            prevY = event.getRawY();
            
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bmp, scrollRect, imgRect, new Paint());
    }
}
}
