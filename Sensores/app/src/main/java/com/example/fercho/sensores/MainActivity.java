package com.example.fercho.sensores;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    TextView texto;
    SensorManager sensorManager;

    private Sensor acelerometro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        texto = (TextView) findViewById(R.id.texto_pantalla);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //creación de una lista con todos los sensores
        List<Sensor> mList = sensorManager.getSensorList(Sensor.TYPE_ALL);

        //for (int i=1; i<mList.size();i++){
        //    texto.append("\n"+mList.get(i).getName()+"n" +mList.get(i).getVendor()+"\n"+mList.get(i).getVersion());
        //}

        //Ahora nos enlazamos con el sensor acelerómetro
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

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
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x=0,y=0,z=0;
        x = sensorEvent.values[0];
        y = sensorEvent.values[1];
        z = sensorEvent.values[2];
        texto.setText("");
        texto.append("x = "+x+" y = "+y+" z = "+z+"\n");
    }

    //Función que actua cuando cambia la presición del sensor
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    @Override
    protected void onResume(){
        super.onResume();
        sensorManager.registerListener(this,acelerometro,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
