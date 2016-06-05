package com.example.akshithreddy.b15_weather;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.akshithreddy.b15_weather.Interfaces.WeatherTaskCallback;
import com.example.akshithreddy.b15_weather.Parser.Info;
import com.example.akshithreddy.b15_weather.Tasks.WeatherTask;
import com.example.akshithreddy.b15_weather.common.Wconstants;

public class MainActivity extends AppCompatActivity {
    private Button getbtn;
    private EditText cityET;
    private EditText stateET;
    private TextView resultTv;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init() {
        getbtn = (Button) findViewById(R.id.button);
        cityET = (EditText) findViewById(R.id.cityET);
        stateET = (EditText) findViewById(R.id.stateET);
        resultTv = (TextView) findViewById(R.id.resultTv);

    }

    @Override
    public void onResume() {
        super.onResume();
        getbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder result=new StringBuilder();
                String city = cityET.getText().toString();
                String state = stateET.getText().toString();
                String url = Wconstants.URL_HEAD + state + "/" + city + Wconstants.URL_TAIL;
                String[] urlArray = {url};
                WeatherTask weatherTask = new WeatherTask(weatherTaskCallback);
                weatherTask.execute(urlArray);
                pd=ProgressDialog.show(MainActivity.this,"Loading...","please wait....");

            }
        });
    }

    WeatherTaskCallback weatherTaskCallback = new WeatherTaskCallback() {

        @Override
        public void getWeatherData(Info info) {
             String message="Temp in c="+info.getCurrent_observation().getTemp_c()+"temp in f="+info.getCurrent_observation().getTemp_f();
                    resultTv.setText(message);
            if (pd.isShowing()){
                pd.dismiss();
            }



        }
    };
}
