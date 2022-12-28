package com.example.large_integers_arithmetic_client_p2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.*;
import java.math.*;
import java.io.*;
import java.net.*;

public class CalculateActivity extends AppCompatActivity {

    private Socket socket = null;
    private PrintWriter out = null;
    private Scanner in = null;
    private TextView tv;


    //Get the host name and port # from MainActivity. Connect to server when this Activity
    // (CalculateActivity) is loaded, Let user enter values and operation,
    // and send the data to the server.
    //retrieves and displays the calculated data from server then disconnects.
    @Override
    //onCreate automatically created by Android Studio
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        int port;
        String hostname;

        //retrieve host name as a String from MainActivity
        Intent intent = getIntent();
        hostname = intent.getStringExtra(MainActivity.HOSTNAME);

        // retrieve port from MainActivity as a string. 4000 is default port #

        port = intent.getIntExtra(MainActivity.PORT, 4000);

        tv = (TextView) findViewById(R.id.text_answer);

        //attempt to connect to server
        try{

            socket = new Socket(hostname, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new Scanner (new InputStreamReader(socket.getInputStream()));

            tv.setText("Connected to server.");
        }

        //handles all general IO problems. Displays error to TextView
        catch(IOException e)
        {
            tv.setText("Problem: " + e.toString());
            socket = null;
        }

    }

    //send operation and bigIntegers to the server and receive and display the result
    // in the TextView
    public void calculateResult(View view)
    {
        //necessary variables for displaying
        EditText et1;
        EditText et2;
        EditText et3;
        String int1;
        String int2;
        String operation;
        String result;

        //checks if connected to server. Displays this message if we are not.
        if(socket==null)
        {
            tv.setText("Not connected.");
        }

        //take input if we're connected. send to server. retrieve results and display
        else {

            et1 = (EditText) findViewById(R.id.edit_operation);
            operation = (et1.getText().toString());

            et2 = (EditText) findViewById(R.id.edit_big_int_1);
            int1 = (et2.getText().toString());

            et3 = (EditText) findViewById(R.id.edit_big_int_2);
            int2 = (et3.getText().toString());

            try {
                //send both BigInts and operation to server

                out.println(operation);
                out.println(int1);
                out.println(int2);

                //receive the calculated result or error message from the server

                    result = in.nextLine();
                    tv.setText(result);

                //display the result or error message from the server

                //closing the connection to the server after the operation is done

                out.close();
                in.close();
                socket.close();

                // confirms disconnection

                socket = null;
            }

            //handling exceptions for sockets
            catch (IOException e)
            {
                tv.setText("Problem: " + e.toString());
            }
        }

    }




}