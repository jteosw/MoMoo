package com.csewannabe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class MainCanvasView extends SurfaceView implements OnTouchListener, Runnable {

	SurfaceHolder sHolder;
	Bitmap picture;
	Paint painter;
	int[] picDimensions;
	private Thread mThread;
	private Rect src;
	private Rect dest;
	private Canvas bmCanvas;
	private boolean isRunning;
	
	public MainCanvasView(Context context, Bitmap picture) {
		super(context);
		picture = this.picture;
		sHolder = getHolder();
		painter = new Paint(Paint.ANTI_ALIAS_FLAG);
		painter.setColor(Color.BLACK);
		setOnTouchListener(this);
		picDimensions = new int[] {picture.getHeight(), picture.getWidth()};
		
		src = new Rect(0,0,picDimensions[0],picDimensions[1]);
		dest = new Rect(0,0,picDimensions[0],picDimensions[1]);
		Bitmap blankMap = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888);
		bmCanvas = new Canvas(blankMap);
	}
	
	public void resume() {
		mThread = new Thread(this);
		mThread.start();
		isRunning = true;
	}
	
	public void pause() {
		isRunning = false;
		
		try {
			mThread.join();
		} catch (InterruptedException e) {}
	}

	public boolean onTouch(View v, MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
			final int historySize = event.getHistorySize();
			for (int h = 0; h < historySize; h++) {
				for(int p = 0; p < event.getPointerCount(); p++) {
					bmCanvas.drawCircle(event.getHistoricalX(p, h), event.getHistoricalY(p, h), 3,painter);
				}
			}
		}


	
}
