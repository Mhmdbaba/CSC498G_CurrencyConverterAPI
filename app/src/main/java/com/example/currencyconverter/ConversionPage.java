package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ConversionPage extends AppCompatActivity {



    private RadioGroup radio_group;
    private RadioButton radio_button;
    private EditText input;
    private TextView text_view;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion_page);
        text_view = (TextView) findViewById(R.id.tv_output);
        img = (ImageView) findViewById(R.id.img_hand);
        String url = "https://localhost:8013/Android/test.php";
    }

    //Function to hide keyboard
    private void hideKeyboard (View view){
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getApplicationWindowToken(),0);
    }

    public void Convert (View view){
        //hide the keyboard when pressing button
        hideKeyboard(view);
        radio_group = (RadioGroup) findViewById(R.id.radioGroup);
        radio_button = (RadioButton) findViewById(radio_group.getCheckedRadioButtonId());
        //get value of selected radio button
        String curr = radio_button.getText().toString();
        //get value entered by user
        input = findViewById(R.id.input_number);
        //set the source of image
        img.setImageResource(R.drawable.hand);

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
            return;
        }


    }
}