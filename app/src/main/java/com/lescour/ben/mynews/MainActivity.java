package com.lescour.ben.mynews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.configureToolbar();
    }

    private void configureToolbar() {
        Toolbar toolbar = findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_activity_main_search:
                Toast.makeText(this, "Bouton 'search' non assigné", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_activity_main_notifications:
                Toast.makeText(this, "Bouton 'notifications' non assigné", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_activity_main_help:
                Toast.makeText(this, "Bouton 'help' non assigné", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_activity_main_about:
                Toast.makeText(this, "Bouton 'about' non assigné", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
