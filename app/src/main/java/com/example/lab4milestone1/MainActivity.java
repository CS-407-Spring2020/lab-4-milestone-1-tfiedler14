package com.example.lab4milestone1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button startThread;
    private volatile boolean stopThread = false;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startThread = findViewById(R.id.startButton);
    }

    public void startThread(View view){
        stopThread = false;
        ExampleRunnable runnable = new ExampleRunnable(10){
            @Override
            public void run(){
                for(int i =0; i< seconds; i++){
                    if(stopThread){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run(){
                                startThread.setText("Start");
                            }
                        });
                        return;
                    }
                    if(i==5){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                startThread.setText("50%");
                            }
                        });
                    }
                    Log.d(TAG, "startThread " + i);
                    try{
                        Thread.sleep(1000);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        startThread.setText("Start");
                    }
                });
            }
        };
        new Thread(runnable).start();



    }

    public void stopThread(View view){
        stopThread = true;
    }

}
