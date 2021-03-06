package com.mtatepultro.trakus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainMenu extends ActionBarActivity {

    private Button mStartButton;
    private Button mSettingsButton;
    private Button mStoreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        mStartButton = (Button)findViewById(R.id.buttonTrakus);
        mSettingsButton = (Button)findViewById(R.id.buttonSettings);
        mStoreButton = (Button)findViewById(R.id.buttonStore);

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTrakus();
            }
        });
        mSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingsScreen();
            }
        });
        mStoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeScreen();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
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

    protected void startTrakus() {
        Intent intent = new Intent(this, Trakus.class);
        startActivity(intent);
    }

    protected void settingsScreen() {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    protected void storeScreen() {
        Intent intent = new Intent(this, Store.class);
        startActivity(intent);
    }
}
