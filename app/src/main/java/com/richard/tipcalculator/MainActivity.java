package com.richard.tipcalculator;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity implements OnEditorActionListener {

    //define instance variables
    private EditText billAmountEditText;
    private TextView percentTextView;
    private TextView tipTextView;
    private TextView totalTextView;
    private TextView perPersonLabel;
    private TextView perPersonTextView;
    private SeekBar percentSeekBar;
    private Button percentButton;

    private String billAmountString;
    private String tipAmountString;
    private String totalAmountString;

    private SharedPreferences savedValues;


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
        percentButton = (Button) findViewById(R.id.percentButton);


        //set listeners
        billAmountEditText.setOnEditorActionListener(this); //current class

        SeekBarListener seekBarListener = new SeekBarListener(); //named class
        percentSeekBar.setOnSeekBarChangeListener(seekBarListener);

        percentButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                calculateAndDisplay();
            }
        });


        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);


        //roundingRadioGroup.setOnCheckedChangeListener(radioListener); //anonymous class


        //adapter for spinner

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
        //billAmountString = billAmountEditText.getText().toString();

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
            //calculateAndDisplay();
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
        billAmountString = billAmountEditText.getText().toString();

        float billAmount;

        if (billAmountString.equals("")) {
            billAmount = 0;
        }
        else {
            billAmount = Float.parseFloat(billAmountString);
        }

        String percentString = percentTextView.getText().toString();
        percentString = percentString.substring(0, percentString.length()-1);
        float percent = Float.parseFloat(percentString);

        //calculate tip and total
        float tipAmount = billAmount * percent / 100.0f;
        tipAmountString = Float.toString(tipAmount);
        float totalAmount = billAmount + tipAmount;
        totalAmountString = Float.toString(totalAmount);

        //set tip text
        NumberFormat tip = NumberFormat.getCurrencyInstance();
        tipTextView.setText(tip.format(tipAmount));

        //set total text
        NumberFormat total = NumberFormat.getCurrencyInstance();
        totalTextView.setText(total.format(totalAmount));

        billAmountEditText.setText(billAmountString);
    }


    @Override
    protected void onPause() {
        Editor editor = savedValues.edit();
        editor.putString("billAmountString", billAmountString);
        editor.commit();

        super.onPause();
    }

    @Override
    protected void onResume() {
        billAmountString = savedValues.getString("billAmountString", "");
        billAmountEditText.setText(billAmountString);
        calculateAndDisplay();

        super.onResume();
    }

}


