package com.example.temperatureconverter;

import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends Activity {
    private EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (EditText) findViewById(R.id.textBox);
    }

    public void onClick(View view) {
        boolean flag = false;
        switch (view.getId()) {
            case R.id.checkButton:


                if (text.getText().length() == 0) {
                    Toast.makeText(this, "Please enter a valid employeeId ", Toast.LENGTH_LONG).show();
                    return;
                }
                String inputValue = text.getText().toString();
                flag = findTextInFile(inputValue);

                break;
        }

        if (flag) {

            Toast.makeText(this, "Go", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "No Go", Toast.LENGTH_LONG).show();
        }
    }

    private boolean findTextInFile(String text) {
        boolean found = false;
        String filename = "AMEX.txt";

        String downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
        String file = downloadDir + File.separator + filename;

        try {
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(
                    openFileInput(file)));
            String inputString;

            while ((inputString = inputReader.readLine()) != null) {
                if (inputString.contains(text)) {
                    found = true;
                    break;
                }
            }

        } catch (IOException e) {
            Log.d("Sanky",e.toString() + e.getCause() + e.getMessage() );
        }


        return found;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
