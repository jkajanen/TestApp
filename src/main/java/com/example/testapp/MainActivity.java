package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    TextView text;
    EditText inputText;
    TextView outputText;
    TextView buttonText;
    int started = 0;
    int i = 0;

    Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;

        text = (TextView) findViewById(R.id.myTextView);
        inputText = (EditText) findViewById(R.id.editTextTextMultiLine);
        outputText = (TextView) findViewById(R.id.editTextTextMultiLine2);
        buttonText = (TextView) findViewById(R.id.myButton);

        inputText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                buttonText.setText("Clear Fields");
                System.out.println("AfterTextChanged");
                //String autotext = inputText.getText().toString();
                //outputText.setText(autotext);
                outputText.setText(inputText.getText().toString());
            }
        });
        System.out.println(context.getFilesDir());
    }

    public void pushButton(View v) {

        if (started == 0 ) {
            System.out.println("Hello World!");
            text.setText(R.string.my_Started);
            inputText.setText("");
            outputText.setText(R.string.my_Waiting);
            started = 1;
            buttonText.setText(R.string.my_copy_button);
        }
        else {
            System.out.println("Button clicked!");
            switch (started) {
                case 1:
                    //writeOutput();
                    outputText.setText("");
                    /*String text = inputText.getText().toString();
                    outputText.setText(text);
                    System.out.println(text);
                    if (++i >= 5) {
                        buttonText.setText(R.string.my_change_button);
                        started = 2;
                        i = 0;
                    }*/
                    inputText.setText("");
                    break;
                case 2:
                    autoCopy();

                    break;
                default:
                    break;
            }
        }
    }

    public void autoCopy() {
        started = 1;
        //buttonText.setText(R.string.my_copy_button);
        }

    public void readScannerInput(View v) {
        Scanner scan;
        scan = new Scanner(System.in);
        System.out.println("In readScannerInput");
        while ( scan.hasNextLine() ) {
            System.out.println("In readScannerInput loop");
            String text = inputText.getText().toString();
            outputText.setText(text);
            System.out.println(text);
        }
        scan.close();

    }

    public void readWriteInput(View v) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String inputStr;
            System.out.println("Entered readWriteInput()");
            /*while (inputStr.equals("")) {
                inputStr = br.readLine();
                System.out.println(inputStr);
                outputText.setText(inputStr);

            }*/
            inputStr = br.readLine();
            //inputStr = context.getText(R.id.editTextTextMultiLine).toString();
            System.out.println(inputStr);
            //inputStr =  inputText.getText().toString();
            //outputText.setText(inputStr);
        } catch (IOException e) {
            Log.e("IOException", "Error in read");
        } finally {
            System.out.println("Exiting readWriteInput()");
        }
    }

    public void readInput(View v) {
        try {
            InputStream ins = context.openFileInput("test.txt");

            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
            String inputStr;

            while ((inputStr = br.readLine()) != null ) {
                System.out.println(inputStr);
                outputText.setText(inputStr);
            }
            ins.close();
        } catch (IOException e) {
            Log.e("IOException", "Error in read");
        } finally {
            System.out.println("File read!");
        }
    }

    public void writeOutput(View v) {
        try {
            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput("test.txt", Context.MODE_PRIVATE));

            String outString;

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