package com.example.rodolfo.firstapp;

import android.content.Intent;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.rodolfo.firstapp.models.Movie;
import com.example.rodolfo.firstapp.models.MovieAdapter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public ArrayList<Movie> movieList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        Button btn_click = (Button) findViewById(R.id.btnClick);
        Button btn_ListView = (Button) findViewById(R.id.listViewButton);
        final EditText txt_name = (EditText) findViewById(R.id.txtName);

        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        btn_click.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){
                Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_LONG).show();
                Snackbar.make(view, txt_name.getText(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent intentMain = new Intent(getApplicationContext(), Main2Activity.class);
                EditText editText = (EditText) findViewById(R.id.txtName);
                String message = editText.getText().toString();
                intentMain.putExtra("Mensaje", message);
                startActivity(intentMain);

            }
        });

        btn_ListView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                EditText editText = (EditText) findViewById(R.id.txtName);
                String message = editText.getText().toString();


                Movie oMovie = new Movie(
                        message,
                        "caca",
                        "Rodo",
                        "adsadsds",
                        "05/08/54"
                );

                movieList.add(oMovie);
                Intent intentMain = new Intent(getApplicationContext(), movieListActivity.class);
                intentMain.putExtra("movieList", movieList);

                startActivity(intentMain);

            }

        });

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
}
