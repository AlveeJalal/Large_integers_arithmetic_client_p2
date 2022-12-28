////////////////////////////////////////////////////////
//        Alvee Jalal                                 //
//        IT114, Section 001                          //
//        Dr. Halper                                  //
//        App Project #2                              //
//        Dec. 6, 2022                                //
//                                                    //
//        This app is designed to perform as a        //
//        client to a respected server that           //
//        performs arithmetic with large integers.    //
//        This app (client) will take as input        //
//        the domain name and port # of the server,   //
//        connect to it, take in the desired operation//
//        and numbers, send them to the server, and   //
//        receives back the value calculated, and     //
//        finally disconnects from the server.        //
////////////////////////////////////////////////////////
package com.example.large_integers_arithmetic_client_p2;

//necessary imports 
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    //variables to hold HOSTNAME & PORT. To be sent to another activity for executing tasks.
    public final static String HOSTNAME = "com.example.large_integers_arithmetic_client_p2.HOSTNAME";
    public final static String PORT = "com.example.large_integers_arithmetic_client_p2.PORT";

    @Override
    //onCreate automatically created by Android Studio
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Required thread policy for internet access
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    // get the hostname & port, send the values to the new activity.
    // connect to the server by clicking the button which create a new activity.
    public void connectServer(View view)
    {
        EditText et1;
        EditText et2;
        String hostname;
        int port;
        //the intent for the next activity for calculations
        Intent intent = new Intent(this, CalculateActivity.class);
        // get the hostname and the port

        et1 = (EditText) findViewById(R.id.edit_host);
        hostname = et1.getText().toString();

        et2 = (EditText) findViewById(R.id.edit_port);
        port = Integer.parseInt(et2.getText().toString());

        // start the activity for retrieving operation, integers and doing the calculation.
        // Send it the hostname and port

        intent.putExtra(HOSTNAME, hostname);
        intent.putExtra(PORT,port);

        startActivity(intent);


    }

}

