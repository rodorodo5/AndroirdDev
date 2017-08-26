package com.example.rodolfo.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        String message = intent.getStringExtra("Mensaje");
        TextView textView = (TextView) findViewById(R.id.textView1);


    }


//
//    Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
//    EditText editText = (EditText) findViewById(R.id.txtName);
//    String message = editText.getText().toString();
//                intent.putExtra("mensaje",message);
//    startActivity(intent);

}
