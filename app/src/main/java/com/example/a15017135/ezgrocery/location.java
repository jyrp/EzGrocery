package com.example.a15017135.ezgrocery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;


public class location extends AppCompatActivity{
    Button btnsearch;
    EditText loc;
    WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);

        wv = ((WebView)findViewById(R.id.wv));
        wv.setWebViewClient(new WebViewClient());
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setBuiltInZoomControls(true);
        loc = ((EditText)findViewById(R.id.etLocation));
        btnsearch = ((Button)findViewById(R.id.btnsearch));
        btnsearch.setOnClickListener(new View.OnClickListener(){
            public void onClick(View onClick)
            {
                String url = "https://www.google.com.sg/maps/place/Singapore+" + loc.getText().toString();
                wv.setWebViewClient(new WebViewClient());
                wv.loadUrl(url);
            }
        });
    }
}
