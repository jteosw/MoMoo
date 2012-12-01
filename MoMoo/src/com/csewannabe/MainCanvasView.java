package com.csewannabe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View.OnTouchListener;

public class MainCanvasView extends SurfaceView implements OnTouchListener, Runnable {

	SurfaceHolder sHolder;
	Bitmap picture;
	Paint painter;
	int[] picDimensions;
	private Rect src;
	private Rect dest;
	private Canvas bmCanvas;
	
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
		bmCanvas = new Canvas();
	}
	
	

}
