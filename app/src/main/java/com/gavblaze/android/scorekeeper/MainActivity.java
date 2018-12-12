package com.gavblaze.android.scorekeeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int mScore1 = 0;
    private int mScore2 = 0;
    private TextView mScoreText1;
    private TextView mScoreText2;
    private static final String SCORE_ONE_KEY = "key_one";
    private static final String SCORE_TWO_KEY = "key_two";
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScoreText1 = findViewById(R.id.score_1);
        mScoreText2 = findViewById(R.id.score_2);


        if (savedInstanceState != null) {
            mScore1 = savedInstanceState.getInt(SCORE_ONE_KEY);
            Log.i(LOG_TAG, "............onCreate()");
            Log.i(LOG_TAG, "......retrivedScore: " + mScore1);
            mScore2 = savedInstanceState.getInt(SCORE_TWO_KEY);

            mScoreText1.setText(String.valueOf(mScore1));
            mScoreText2.setText(String.valueOf(mScore2));
        }


    }

    public void increaseScore(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.increaseTeam1:
                mScore1++;
                mScoreText1.setText(String.valueOf(mScore1));
                break;
            case R.id.increaseTeam2:
                mScore2++;
                mScoreText2.setText(String.valueOf(mScore2));
                break;
            default:
                break;
        }
    }

    public void decreaseScore(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.decreaseTeam1:
                if (mScore1 > 0) mScore1--;
                mScoreText1.setText(String.valueOf(mScore1));
                break;
            case R.id.decreaseTeam2:
                if (mScore2 > 0) mScore2--;
                mScoreText2.setText(String.valueOf(mScore2));
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu.getItem(0).setTitle(getString(R.string.day_mode));
        } else {
            menu.getItem(0).setTitle(getString(R.string.night_mode));
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.night_mode) {
            // Get the night mode state of the app.
            int nightMode = AppCompatDelegate.getDefaultNightMode();
            //Set the theme mode for the restarted activity
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
            // Recreate the activity for the theme change to take effect.
            recreate();
        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SCORE_ONE_KEY, mScore1);
        outState.putInt(SCORE_TWO_KEY, mScore2);

        Log.i(LOG_TAG, "......onSavedInstanceStateCalled()");
        Log.i(LOG_TAG, "......Score1: " + mScore1);
        Log.i(LOG_TAG, "......Score2: " + mScore2);
    }
}
