package com.csewannabe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
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
	boolean doubleTouch;
	private float prevX;
	private float prevY;
	private int newY;
	private float newX;
	private Rect blanksrc;
	private Rect blankdest;
	private Rect bufsrc;
	private Rect bufdest;
	
	public MainCanvasView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
	}
	
	public MainCanvasView(Context context, Bitmap picture) {
		super(context);
		this.picture = picture;
		sHolder = getHolder();
		painter = new Paint(Paint.ANTI_ALIAS_FLAG);
		painter.setColor(Color.BLACK);
		painter.setStyle(Paint.Style.STROKE);
		painter.setStrokeWidth(2);
		setOnTouchListener(this);
		
		src = new Rect(0,0,picture.getWidth(),picture.getHeight());			//default size of pic
		dest = new Rect(100,100, picture.getWidth() * 2 + 100, picture.getHeight() * 2 + 100); //choose the location, left.top.right.btm
		blankMap = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888);
		blanksrc = new Rect(0,0,blankMap.getWidth(), blankMap.getHeight());
		blankdest = new Rect(0,0,blankMap.getWidth(), blankMap.getHeight());
		bmCanvas = new Canvas(blankMap);
		buffer = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888);
		bufsrc = new Rect(0,0,buffer.getWidth(), buffer.getHeight());
		bufdest = new Rect(0,0,buffer.getWidth(), buffer.getHeight());
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
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_MOVE: 
			final int historySize = event.getHistorySize();	
			int numPointers = event.getPointerCount();

			if (numPointers < 2) {
				for (int h = 0; h < historySize; h++) {
					for(int p = 0; p < event.getPointerCount(); p++) {
						path.lineTo(event.getHistoricalX(p, h),event.getHistoricalY(p,h));
						//bmCanvas.drawCircle(event.getHistoricalX(p, h), event.getHistoricalY(p, h), 3,painter);
						path.offset(0, newY);
						bufCanvas.drawPath(path, painter);
						
						path.moveTo(event.getHistoricalX(p, h),event.getHistoricalY(p,h));
				    }
				}
			} else 	if (numPointers > 1) {
				Log.d("TOUCHED", "Am scrolling!");
                float currX = event.getRawX();
                float deltaX = -(currX - prevX);
                newX += deltaX;
                float currY = event.getRawY();
                float deltaY = -(currY - prevY);
                newY += deltaY;
                dest.set(100, 100-newY, 100+picture.getWidth() * 2, 100-newY + picture.getHeight() * 2);
                blankdest.set(0,0-newY, blankMap.getWidth(), blankMap.getWidth() - newY);
                bufdest.set(0,0-newY, buffer.getWidth(), buffer.getWidth() - newY);
                prevX = currX;
                prevY = currY;
			}
			
			break;
			
		
		case MotionEvent.ACTION_DOWN: 
			//bmCanvas.drawCircle(event.getX(), event.getY(), 3,painter);
			bmCanvas.drawPath(path, painter);
				path = new Path();
				path.moveTo(event.getX(), event.getY());
			//Sets the start scroll point
				
				prevX = event.getRawX();
				prevY = event.getRawY();
				break;
		default:
			break;
		
		}
		return true;
	}
		

	public void run() {
		while(isRunning) {
			if(sHolder.getSurface().isValid()) {
				Canvas drawingCanvas = sHolder.lockCanvas();
				drawingCanvas.drawARGB(255, 255, 255, 255);
				drawingCanvas.drawBitmap(picture, src,dest, null);
				drawingCanvas.drawBitmap(blankMap, blanksrc,blankdest, null);
				drawingCanvas.drawBitmap(buffer,  bufsrc, bufdest, null);
				
				
				sHolder.unlockCanvasAndPost(drawingCanvas);
			}
		}
	}


	
}
