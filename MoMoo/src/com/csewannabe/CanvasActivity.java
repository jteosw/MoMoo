package com.csewannabe;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class CanvasActivity extends Activity {
	MainCanvasView MainCanvas;
	Button clearButton;
	Button editButton;
	Button submitButton;
	EditText answerBox;
	Bitmap picture;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		RelativeLayout canvasLayout = new RelativeLayout(this);
		RelativeLayout.LayoutParams rLParams = 
				new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
		
		// Find views
		picture = BitmapFactory.decodeResource(getResources(), R.drawable.list_background);
		
		MainCanvas = new MainCanvasView(this, picture);
		clearButton = new Button(this);
		editButton = new Button(this)ac;
		submitButton = new Button(this);
		answerBox = new EditText(this);
		
		
		setContentView(R.layout.);
	}

	

}
