package com.richard.tipcalculator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class MainActivity extends AppCompatActivity implements OnEditorActionListener {

    //define instance variables
    private EditText billAmountEditText;
    private TextView percentTextView;
    private TextView tipTextView;
    private TextView totalTextView;
    private TextView perPersonLabel;
    private TextView perPersonTextView;
    private SeekBar percentSeekBar;
    private RadioGroup roundingRadioGroup;
    private RadioButton noneRadioButton;
    private RadioButton tipRadioButton;
    private RadioButton totalRadioButton;
    private Spinner splitSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get reference to the widgets
        billAmountEditText = (EditText) findViewById(R.id.billAmountEditText);
        percentTextView = (TextView) findViewById(R.id.percentTextView);
        percentSeekBar = (SeekBar) findViewById(R.id.percentSeekBar);
        tipTextView = (TextView) findViewById(R.id.tipTextView);
        totalTextView = (TextView) findViewById(R.id.totalTextView);
        roundingRadioGroup = (RadioGroup) findViewById(R.id.roundingRadioGroup);
        noneRadioButton = (RadioButton) findViewById(R.id.noneRadioButton);
        tipRadioButton = (RadioButton) findViewById(R.id.tipRadioButton);
        totalRadioButton = (RadioButton) findViewById(R.id.totalRadioButton);
        splitSpinner = (Spinner) findViewById(R.id.splitSpinner);
        perPersonLabel = (TextView) findViewById(R.id.perPersonLabel);
        perPersonTextView = (TextView) findViewById(R.id.perPersonTextView);

        //set listeners
        billAmountEditText.setOnEditorActionListener(this); //current class

        SeekBarListener seekBarListener = new SeekBarListener(); //named class
        percentSeekBar.setOnSeekBarChangeListener(seekBarListener);

        //roundingRadioGroup.setOnCheckedChangeListener(radioListener); //anonymous class



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
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        if(actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
            calculateAndDisplay();
        }


        return false;
    }


    //named class
    class SeekBarListener implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            percentTextView.setText(progress  + "\u0025");

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            calculateAndDisplay();
        }
    }


    //anonymous class
/*
    private RadioGroup.OnCheckedChangeListener roundingListener = new RadioGroup.OnCheckedChangeListener({

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
            switch (checkedId) {
                case R.id.noneRadioButton:
                    break;
                case R.id.tipRadioButton:
                    break;
                case R.id.totalRadioButton:
                    break;

            }
            calculateAndDisplay();
        }
    });
*/

    private void calculateAndDisplay() {

    }
}


