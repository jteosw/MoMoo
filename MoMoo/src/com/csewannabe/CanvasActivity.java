package com.csewannabe;



import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.ImageView.ScaleType;

public class CanvasActivity extends Activity {
	MainCanvasView MainCanvas;
	ImageButton clearButton;
	ImageButton undoButton;
	ImageButton submitButton;
	EditText answerBox;
	Bitmap picture;
	public static final int clearButtonId = 1;
	public static final int undoButtonId = 2;
	public static final int submitButtonId = 3;
	public static final int answerBoxId = 4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		RelativeLayout canvasLayout = new RelativeLayout(this);
		RelativeLayout.LayoutParams params = 
				new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
		
		// Find views
		picture = BitmapFactory.decodeResource(getResources(), R.drawable.green);
		
		MainCanvas = new MainCanvasView(this, picture);
		clearButton = new ImageButton(this);
		undoButton = new ImageButton(this);
		submitButton = new ImageButton(this);
		answerBox = new EditText(this);
		// Add Main Canvas
		
		canvasLayout.addView(MainCanvas, params);

		RelativeLayout.LayoutParams clearParams = 
				new RelativeLayout.LayoutParams(100, 125);
		// Creates clear button
		clearButton.setImageResource(R.drawable.clear);
		clearButton.setId(clearButtonId);
		clearButton.setScaleType(ScaleType.FIT_CENTER);
		clearParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		clearParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		canvasLayout.addView(clearButton, clearParams);
		
		clearButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MainCanvas.clear();
			}
		});
		
		// Creates undo button		
		RelativeLayout.LayoutParams undoParams = 
				new RelativeLayout.LayoutParams(100, 125);
		undoButton.setImageResource(R.drawable.undo);
		undoButton.setId(undoButtonId);
		undoButton.setScaleType(ScaleType.FIT_CENTER);
		undoParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		undoParams.addRule(RelativeLayout.RIGHT_OF, clearButtonId);
		canvasLayout.addView(undoButton, undoParams);
		
		undoButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MainCanvas.undo();
			}
		});
		
		// Creates submit button
		RelativeLayout.LayoutParams submitParams = 
				new RelativeLayout.LayoutParams(100, 125);
		
		submitButton.setId(submitButtonId);
		submitButton.setImageResource(R.drawable.send);
		submitButton.setScaleType(ScaleType.FIT_CENTER);
		submitParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		submitParams.addRule(RelativeLayout.RIGHT_OF, undoButtonId);
		canvasLayout.addView(submitButton, submitParams);
		
		submitButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		// Creates answer box
		RelativeLayout.LayoutParams answerBoxParams = 
				new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 125);
		
		submitParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		submitParams.addRule(RelativeLayout.RIGHT_OF, submitButtonId);
		canvasLayout.addView(answerBox, answerBoxParams);
				
		setContentView(canvasLayout, params);
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MainCanvas.pause();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MainCanvas.resume();
	}

}
