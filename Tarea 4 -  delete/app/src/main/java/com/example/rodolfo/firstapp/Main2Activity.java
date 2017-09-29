package com.example.rodolfo.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.rodolfo.firstapp.R.layout.activity_main2;


public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main2);

//        Intent intent = getIntent();
//        String message = intent.getStringExtra("Mensaje");
//        TextView txtShow = (TextView) findViewById(R.id.txtView);
//        Button btn_close = (Button) findViewById(R.id.btnBack);
//        txtShow.setText(message);
//
//        btn_close.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                finish();
//            }
//        });
    }


//
//    Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
//    EditText editText = (EditText) findViewById(R.id.txtName);
//    String message = editText.getText().toString();
//                intent.putExtra("mensaje",message);
//    startActivity(intent);

}
