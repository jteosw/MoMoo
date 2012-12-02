package com.csewannabe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
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
	private Bitmap blankMap;
	private Path path;
	private Bitmap buffer;
	private Canvas bufCanvas;
	
	public MainCanvasView(Context context, Bitmap picture) {
		super(context);
		this.picture = picture;
		sHolder = getHolder();
		painter = new Paint(Paint.ANTI_ALIAS_FLAG);
		painter.setColor(Color.BLACK);
		painter.setStyle(Paint.Style.STROKE);
		painter.setStrokeWidth(2);
		setOnTouchListener(this);
		picDimensions = new int[] {picture.getHeight(), picture.getWidth()};
		
		src = new Rect(0,0,picDimensions[0],picDimensions[1]);			//default size of pic
		dest = new Rect(100,100,picDimensions[0]*2+100,picDimensions[1]*2+100); //choose the location, left.top.right.btm
		blankMap = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888);
		bmCanvas = new Canvas(blankMap);
		buffer = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888);
		bufCanvas = new Canvas(buffer);
		path = new Path();
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
	
	public void undo() {
		path =  new Path();
	}
	
	public void clear() {
		blankMap = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888);
		bmCanvas = new Canvas(blankMap);
		buffer = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888);
		bufCanvas = new Canvas(buffer);
		
	}

	public boolean onTouch(View v, MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_DOWN) {
			//bmCanvas.drawCircle(event.getX(), event.getY(), 3,painter);
			bmCanvas.drawPath(path, painter);
			path = new Path();
			path.moveTo(event.getX(), event.getY());
			
		} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
		
			final int historySize = event.getHistorySize();			
			for (int h = 0; h < historySize; h++) {
				for(int p = 0; p < event.getPointerCount(); p++) {
					path.lineTo(event.getHistoricalX(p, h),event.getHistoricalY(p,h));
					//bmCanvas.drawCircle(event.getHistoricalX(p, h), event.getHistoricalY(p, h), 3,painter);
					bufCanvas.drawPath(path, painter);
					path.moveTo(event.getHistoricalX(p, h),event.getHistoricalY(p,h));
				}
			}
		}
		
		return true;
	}
	
	public void run() {
		while(isRunning) {
			if(sHolder.getSurface().isValid()) {
				Canvas drawingCanvas = sHolder.lockCanvas();
				drawingCanvas.drawARGB(255, 255, 255, 255);
				drawingCanvas.drawBitmap(picture, src,dest, null);
				drawingCanvas.drawBitmap(blankMap, 0,0, null);
				drawingCanvas.drawBitmap(buffer,  0, 0, null);
				
				
				sHolder.unlockCanvasAndPost(drawingCanvas);
			}
		}
	}


	
}
