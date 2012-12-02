package com.csewannabe;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MoMooStart extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.momoo_start);
    }
   
    public void startAssignment(View view) {
    	startActivity(new Intent(this, SelectionActivity.class));
    }
    
    public void openCanvas(View view) {
    	startActivity(new Intent(this, CanvasActivity.class));
    }
    
    
}
