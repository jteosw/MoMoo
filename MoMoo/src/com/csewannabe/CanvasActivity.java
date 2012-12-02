package com.csewannabe;



import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class CanvasActivity extends Activity {
	MainCanvasView MainCanvas;
	ImageButton clearButton;
	ImageButton undoButton;
	ImageButton submitButton;
	EditText answerBox;
	Bitmap picture;

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

		params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		// Creates clear button
		clearButton.setImageResource(R.drawable.clear);
		int clearButtonId = 1;
		clearButton.setId(clearButtonId);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		canvasLayout.addView(clearButton, params);
		clearButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MainCanvas.clear();
			}
		});
		
		// Creates undo button		
		params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		undoButton.setImageResource(R.drawable.undo);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
//		params.addRule(RelativeLayout.RIGHT_OF, clearButtonId);
		canvasLayout.addView(undoButton, params);
		clearButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MainCanvas.undo();
			}
		});
		
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
