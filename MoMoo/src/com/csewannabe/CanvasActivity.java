package com.csewannabe;



import com.csewannabe.selection.ProblemsFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class CanvasActivity extends Activity {
	MainCanvasView MainCanvas;
	ImageButton clearButton;
	ImageButton undoButton;
	ImageButton submitButton;
	Button prevButton;
	Button nextButton;
	EditText answerBox;
	Bitmap picture;
	Intent intent;
	TextView title;
	
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
		picture = BitmapFactory.decodeResource(getResources(), R.drawable.list_background);
		
		MainCanvas = new MainCanvasView(this, picture);
		clearButton = new ImageButton(this);
		undoButton = new ImageButton(this);
		submitButton = new ImageButton(this);
		answerBox = new EditText(this);
		prevButton = new Button(this);
		nextButton = new Button(this);
		title = new TextView(this);
		ImageView header = new ImageView(this);
		ImageView footer = new ImageView(this);
		// Add Main Canvas
		canvasLayout.addView(MainCanvas, params);
		
		
		
		// Add Header Footer
		RelativeLayout.LayoutParams headerParams = 
				new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 150);
		header.setImageResource(R.drawable.headernfooter);
		headerParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		header.setScaleType(ScaleType.FIT_XY);
		canvasLayout.addView(header, headerParams);
		intent = getIntent();
		
		
		footer.setImageResource(R.drawable.headernfooter);
		RelativeLayout.LayoutParams footerParams = 
				new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 150);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		footer.setScaleType(ScaleType.FIT_XY);
		canvasLayout.addView(footer, footerParams);
		
		// Creates clear button
		
		RelativeLayout.LayoutParams clearParams = 
				new RelativeLayout.LayoutParams(250, 100);
		clearButton.setImageResource(R.drawable.clear);
		clearButton.setId(clearButtonId);
		clearButton.setScaleType(ScaleType.FIT_CENTER);
		clearButton.setBackgroundColor(Color.GREEN);
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
				new RelativeLayout.LayoutParams(250, 100);
		undoButton.setImageResource(R.drawable.undo);
		undoButton.setId(undoButtonId);
		undoButton.setScaleType(ScaleType.FIT_CENTER);
		undoButton.setBackgroundColor(Color.GREEN);
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
				new RelativeLayout.LayoutParams(250, 100);
		
		submitButton.setId(submitButtonId);
		submitButton.setImageResource(R.drawable.send);
		submitButton.setScaleType(ScaleType.FIT_XY);
		submitButton.setBackgroundColor(Color.GREEN);
		submitParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		submitParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		canvasLayout.addView(submitButton, submitParams);
		
		submitButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DialogFragment subDialog = new ConfirmSubmissionDialog();
				subDialog.show(getFragmentManager(), "NoticeDialogFragment");
			}
		});
		
		// Creates answer box
		RelativeLayout.LayoutParams answerBoxParams = 
				new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 100);
		
		answerBox.setImeOptions(EditorInfo.IME_ACTION_DONE);
		answerBox.setSingleLine();
		answerBox.setTextSize(15);
		answerBox.setBackgroundColor(Color.WHITE);
		answerBoxParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		answerBoxParams.addRule(RelativeLayout.ABOVE, submitButtonId);
		canvasLayout.addView(answerBox, answerBoxParams);		
		answerBox.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				// TODO Auto-generated method stub
				answerBox.clearFocus();
				return false;
			}
		});
		
		// Creates prev Button
		RelativeLayout.LayoutParams prevParams = 
				new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		prevButton.setText("PREV");
		prevParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		prevParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		canvasLayout.addView(prevButton, prevParams);
		prevButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				intent = getIntent();
				// if PREV_DATA is null, doesn't exist, button shouldn't work
//				String position = intent.getStringExtra(ProblemsFragment.PREV_DATA);
//				startActivity(new Intent(CanvasActivity.this, );
			}
		});
		
		// Creates next Button
		RelativeLayout.LayoutParams nextParams = 
				new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		nextButton.setText("NEXT");
		nextParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		nextParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		canvasLayout.addView(nextButton, nextParams);
		nextButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
//				startActivity(new Intent(CanvasActivity.this, MoMooStart.class));
			}
		});
		
		
		// Create title
		RelativeLayout.LayoutParams titleParams = 
				new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		titleParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		titleParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		title.setText(intent.getStringExtra(ProblemsFragment.CURR_DATA));
		title.setTextSize(30);
		title.setTextColor(Color.DKGRAY);
		canvasLayout.addView(title, titleParams);
		
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
	public class ConfirmSubmissionDialog extends DialogFragment {
		
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			super.onCreateDialog(savedInstanceState);
			// Use builder class to construct dialogs
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setMessage("Confirm Submission?").setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Toast.makeText(CanvasActivity.this, "Your answer has been submitted", Toast.LENGTH_SHORT).show();
				}
			}).setNegativeButton("No", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Toast.makeText(CanvasActivity.this, "You answer was not submitted", Toast.LENGTH_SHORT).show();
				}
			});
			
		return builder.create();
		}
	}
}
