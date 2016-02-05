package com.example.upam.almacenar;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    private EditText name, age, weight, height;
    private ImageButton see;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.editText);
        age = (EditText) findViewById(R.id.editText2);
        weight = (EditText) findViewById(R.id.editText3);
        height = (EditText) findViewById(R.id.editText4);
        see = (ImageButton) findViewById(R.id.imageButton2);

        SharedPreferences prefs = getSharedPreferences("IMC", Context.MODE_PRIVATE);
        name.setText(prefs.getString("name", " "));
        age.setText(String.valueOf(prefs.getInt("age", 0)));
        weight.setText(String.valueOf(prefs.getInt("weight", 0)));
        height.setText(String.valueOf(prefs.getFloat("height", 0)));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void guardar(View v) {

        Float imc;
        imc = Float.valueOf(weight.getText().toString())/(Float.valueOf(height.getText().toString())*Float.valueOf(height.getText().toString()));
        SharedPreferences prefs = getSharedPreferences("IMC", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString("name", name.getText().toString());
        edit.putInt("age", Integer.parseInt(age.getText().toString()));
        edit.putInt("weight", Integer.parseInt(weight.getText().toString()));
        edit.putFloat("height", Float.parseFloat(height.getText().toString()));
        edit.putFloat("imc-cal",imc);
        edit.commit();

        see.setVisibility(View.VISIBLE);
    }

    public void ver(View v) {
        SharedPreferences prefs = getSharedPreferences("IMC", Context.MODE_PRIVATE);
        String n = prefs.getString("name", " ");
        String a = String.valueOf(prefs.getInt("age", 0));
        String w = String.valueOf(prefs.getInt("weight", 0));
        String h = String.valueOf(prefs.getFloat("height", 0));
        String i = String.valueOf(prefs.getFloat("imc-cal",0));
        Toast.makeText(this,  "Datos: \n" +"Nombre: "+ n +"\nEdad: " + a + "\n Peso: " + w + "\nEstatura" + h + "\nIMC: "+ i, Toast.LENGTH_SHORT).show();
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
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.upam.almacenar/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.upam.almacenar/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
