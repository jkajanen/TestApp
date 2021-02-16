package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView text;
    TextView inputText;
    TextView outputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.myTextView);
        inputText = findViewById(R.id.editTextInputIField);
        outputText = findViewById(R.id.myOutputTextView);


    }

    public void pushButton(View v) {
        System.out.println("Hello World!");
        text.setText("Started!");
        inputText.setText("");
        outputText.setText("Waiting input...");
    }
}