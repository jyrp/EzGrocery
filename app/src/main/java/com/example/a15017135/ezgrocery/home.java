package com.example.a15017135.ezgrocery;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class home extends AppCompatActivity {

    Button btn1;
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        btn1 = ((Button)findViewById(R.id.btn1));
        btn1.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View onClick)
            {
               startActivity(new Intent(home.this, shoppinglist.class));
            }
        });
        btn2 = ((Button)findViewById(R.id.btn2));
        btn2.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View onClick)
            {
             startActivity(new Intent(home.this, location.class));
            }
        });
    }
}
