package com.example.administrator.pitchonblurlayout;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.administrator.pitchonblurlayout.view.PitchOnBlurLayoutView;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{
    private PitchOnBlurLayoutView pitchOnBlurLayoutView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pitchOnBlurLayoutView = (PitchOnBlurLayoutView) findViewById(R.id.pitch);
        findViewById(R.id.image1).setOnClickListener(this);
        findViewById(R.id.image2).setOnClickListener(this);
        findViewById(R.id.image3).setOnClickListener(this);
        findViewById(R.id.image4).setOnClickListener(this);
        findViewById(R.id.image5).setOnClickListener(this);
        findViewById(R.id.image6).setOnClickListener(this);
        findViewById(R.id.image7).setOnClickListener(this);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        pitchOnBlurLayoutView.pitchOn(v);

    }
}
