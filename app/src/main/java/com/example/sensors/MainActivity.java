package com.example.sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorEventListener2;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    TextView textView;
    SensorManager manager;
    Sensor sensor;
    List<Sensor> sensorList;
    private float changedValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Android Sensor Programming");

        textView = findViewById(R.id.txt_sense);
        manager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

        sensorList = manager.getSensorList(Sensor.TYPE_ALL);
        // textView.setText(sensorList.toString());
        //printSensor();
        //checkSensor();
        //sensorPower();
    }



    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        changedValue = sensorEvent.values[0];
        textView.setText(String.valueOf(changedValue) + " ℃");

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        manager.registerListener(this, manager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE),SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(this);
    }

    //Understanding Sensors
    public void printSensor()
    {
        for(Sensor sensor:sensorList)
        {
            textView.setText(textView.getText()+"\n"+sensor.getName());
        }
    }

    public void checkSensor()
    {
        if(manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null)
        {
            textView.setText("This device has Accelerometer");
        }
        else {
            textView.setText("This device lacks Accelerometer");
        }
    }
    public void sensorPower()
    {
        sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        textView.setText(textView.getText()+"\n"+ String.valueOf(sensor.getPower()));
    }
}