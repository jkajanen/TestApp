package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    TextView text;
    TextView inputText;
    TextView outputText;
    TextView buttonText;
    int started = 0;

    Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;

        text = (TextView) findViewById(R.id.myTextView);
        inputText = (TextView) findViewById(R.id.editTextTextMultiLine);
        outputText = (TextView) findViewById(R.id.editTextTextMultiLine2);
        buttonText = (TextView) findViewById(R.id.myButton);

        System.out.println(context.getFilesDir());
    }

    public void pushButton(View v) {

        if (started == 0 ) {
            System.out.println("Hello World!");
            text.setText(R.string.my_Started);
            inputText.setText("");
            outputText.setText(R.string.my_Waiting);
            started = 1;
            //buttonText.setText(R.string.my_copy_button);
        }
        else {
            System.out.println("Button clicked!");
            switch (started) {
                case 1:
                    //writeOutput();
                    String text = inputText.getText().toString();
                    outputText.setText(text);
                    System.out.println(text);
                    started = 1;
                    break;
                case 2:
                    //readInput();
                    started = 1;
                    break;
                default:
                    break;
            }
        }
    }

    public void readInput() {
        try {
            InputStream ins = context.openFileInput("test.txt");

            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
            String inputStr = "";

            while ((inputStr = br.readLine()) != null ) {
                System.out.println(inputStr);
                //outputText.setText(inputStr);
            }
            ins.close();
        } catch (IOException e) {
            Log.e("IOException", "Error in read");
        } finally {
            System.out.println("File read!");
        }
    }

    public void writeOutput() {
        try {
            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput("test.txt", Context.MODE_PRIVATE));

            String outString = "";

            outString = "Jotain diipadaabaa \nLisää sitä itseään \nja kolmas rivi vielä";
            osw.write(outString);
            osw.close();
            outputText.setText("");

        } catch (IOException e) {
            Log.e("IOException", "Error in write");
        } finally {
            System.out.println("File written!");
        }
    }
}