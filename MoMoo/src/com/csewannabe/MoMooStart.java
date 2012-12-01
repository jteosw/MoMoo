package com.csewannabe;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MoMooStart extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.momoo_start);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.momoo_start, menu);
        return true;
    }
    
}
