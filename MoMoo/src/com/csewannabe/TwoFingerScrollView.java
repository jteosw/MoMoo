package com.example.androidgraphics;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class TwoFingerScrollView extends SurfaceView implements Runnable, OnTouchListener{
	SurfaceHolder sHolder;
	Bitmap picture;
	Paint painter;
	int[] picDimensions;
	private Thread mThread;
	private Rect src;
	private Rect dest;
	private Rect scrollRect;
	private Rect imgRect;
	private Canvas bmCanvas;
	private boolean isRunning;
	private Bitmap blankMap;
	private Path path;
    private float prevX, prevY;
    private int newX, newY;

    public TwoFingerScrollView(Context context) {
        super(context);
        this.picture = BitmapFactory.decodeResource(getResources(), R.drawable.zachary_iqbal);;
		sHolder = getHolder();
		painter = new Paint(Paint.ANTI_ALIAS_FLAG);
		painter.setColor(Color.BLACK);
		painter.setStyle(Paint.Style.STROKE);
		painter.setStrokeWidth(2);
		setOnTouchListener(this);
		picDimensions = new int[] {picture.getHeight(), picture.getWidth()};
		
        imgRect = new Rect(0, 0, 800, 1300);
        scrollRect = new Rect(0, 0, 800, 1300);
		
		src = new Rect(0,0,picDimensions[0],picDimensions[1]);
		dest = new Rect(100,100,picDimensions[0]*2+100,picDimensions[1]*2+100);
		blankMap = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888);
		bmCanvas = new Canvas(blankMap);
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
						bmCanvas.drawPath(path, painter);
						
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
                scrollRect.set(0, newY, 800, newY + 1300);
                prevX = currX;
                prevY = currY;
			}
			
			break;
			
		
		case MotionEvent.ACTION_DOWN: 
			//bmCanvas.drawCircle(event.getX(), event.getY(), 3,painter);
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
				drawingCanvas.drawBitmap(picture, scrollRect, imgRect, null);
				drawingCanvas.drawBitmap(blankMap, 0, -newY, null);
				sHolder.unlockCanvasAndPost(drawingCanvas);
			}
		}
	}


	
}
