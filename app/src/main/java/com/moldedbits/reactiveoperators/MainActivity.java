package com.moldedbits.reactiveoperators;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.moldedbits.reactiveoperators.create.CreateSample;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createSample();
    }

    private void createSample() {
        View view = findViewById(R.id.hello);
        CreateSample.createSample(view);
    }


}
