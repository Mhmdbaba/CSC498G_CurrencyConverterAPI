package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
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

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class ConversionPage extends AppCompatActivity {



    private RadioGroup radio_group;
    private RadioButton radio_button;
    private EditText input;
    private TextView text_view;
    ImageView img;
    TextView tv_sell;
    TextView tv_buy;

    public class DownloadTask extends AsyncTask <String, Void, String>{
        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpsURLConnection http;

            try {
                url = new URL(urls[0]);
                http = (HttpsURLConnection) url.openConnection();

                //Read the output of API
                InputStream in = http.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();
                while(data != -1){
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }


            }catch(Exception e){
                e.printStackTrace();
                return null;
            }
            return result;
        }

        protected void onPostExecute (String s){
            super.onPostExecute(s);
            Log.i("heree", s);

            try{
                /*
                JSONObject json = new JSONObject(s);

                String lbp_buy = json.getString("buy");
                Log.i("buy", String.valueOf(lbp_buy));
                tv_buy.setText(lbp_buy);

                String lbp_sell = json.getString("sell");
                Log.i("sell", String.valueOf(lbp_sell));
                tv_sell.setText(lbp_sell);
                */

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion_page);
        text_view = (TextView) findViewById(R.id.tv_output);
        img = (ImageView) findViewById(R.id.img_hand);
        tv_sell = (TextView) findViewById(R.id.tv_sell);
        tv_buy = (TextView) findViewById(R.id.tv_buy);

        String url = "https://lirarate.org/wp-json/lirarate/v2/fuel?currency=LBP&_ver=t20224115";
        DownloadTask task = new DownloadTask();
        task.execute(url);
    }

    //Function to hide keyboard
    private void hideKeyboard (View view){
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getApplicationWindowToken(),0);
    }

    public void Button(View view){
        //hide the keyboard when pressing button
        hideKeyboard(view);

        Button btn = (Button) view;
        if (btn.getTag().toString().equalsIgnoreCase("back")){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else if (btn.getTag().toString().equalsIgnoreCase("convert")){

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
}