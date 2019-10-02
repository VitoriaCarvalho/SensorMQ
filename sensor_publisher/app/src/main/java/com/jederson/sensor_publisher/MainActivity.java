package com.jederson.sensor_publisher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.hivemq.client.mqtt.MqttGlobalPublishFilter;
import com.hivemq.client.mqtt.datatypes.MqttQos;
import com.hivemq.client.mqtt.mqtt5.Mqtt5BlockingClient;
import com.hivemq.client.mqtt.mqtt5.Mqtt5Client;

import java.util.UUID;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mProximity;
    private static int SENSOR_SENSITIVITY = 4;
    private Mqtt5BlockingClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        client = Mqtt5Client.builder()
                .identifier(UUID.randomUUID().toString())
                .serverHost("10.180.14.64")
                .serverPort(1883)
                .buildBlocking();

        client.connect();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        mSensorManager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        mSensorManager.unregisterListener(this);
    }

    public void onOffSensor(View view) {
        Switch aSwitch = findViewById(R.id.switchSensor);
        if (aSwitch.isChecked()) {
            aSwitch.setText(R.string.desativar_sensor);
            mSensorManager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            aSwitch.setText(R.string.ativar_sensor);
            mSensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            TextView textView = findViewById(R.id.textSensor);
            if (event.values[0] >= -SENSOR_SENSITIVITY && event.values[0] <= SENSOR_SENSITIVITY) {
                // near
                textView.setText("Perto");
                Log.d("Sensibilidade do Sensor", "Perto");

                String topic = "proximidade/vitoria";
                String payload = "perto";
                client.publishWith().topic("proximidade/vitoria").qos(MqttQos.AT_LEAST_ONCE).payload("perto".getBytes()).send();
            } else {
                // far
                textView.setText("Longe");
                Log.d("Sensibilidade do Sensor", "Longe");
                client.publishWith().topic("proximidade/vitoria").qos(MqttQos.AT_LEAST_ONCE).payload("longe".getBytes()).send();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
