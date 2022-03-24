package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ConversionPage extends AppCompatActivity {

    private RadioGroup radio_group;
    private RadioButton radio_button;
    private EditText input;
    private TextView text_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion_page);
        text_view = (TextView) findViewById(R.id.tv_output);
    }

    public void Convert (View view){
        radio_group = (RadioGroup) findViewById(R.id.radioGroup);
        radio_button = (RadioButton) findViewById(radio_group.getCheckedRadioButtonId());
        String curr = radio_button.getText().toString();
        input = findViewById(R.id.input_number);

        Double output = 0.0;
        if (curr.equalsIgnoreCase("usd")){ //convert to USD
            //output = input....

            //save to database

            text_view.setText("USD" + output);
        }
        else if ( curr.equalsIgnoreCase("lbp")){ //convert to LBP
            //output = input ...

            //save to database

            text_view.setText("LBP" + output);
        }
        else{
            Toast.makeText(this, "Select a currency", Toast.LENGTH_LONG).show();
        }


    }
}