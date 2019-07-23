package com.example.secret;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class password_page extends AppCompatActivity {
    Button logOut_button;
    public static Boolean confirmlogout=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_page);
        TextView textView=(TextView) findViewById(R.id.username_name);
        String name=getIntent().getStringExtra("name");
        textView.setText("Hello "+name);

        //log out
        logOut_button=(Button) findViewById(R.id.signout_button);
        //saveToFile();
        //loadFile();

    }


    public void logOut(View view) {

        alertConfirmation();

    }
    public void alertConfirmation(){
        final AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Log Out");
        builder.setMessage("Are you sure you want to log out?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                confirmlogout=true;

                finish();
            }
        });
        builder.setNegativeButton("Cancel",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

                confirmlogout=false;
                dialogInterface.dismiss();

            }
        });
        builder.create().show();
        Toast toast =Toast.makeText(this,"Loading...", Toast.LENGTH_SHORT);
        toast.show();


    }
    //write to file

    public void saveToFile(View view) {
        Toast toast =Toast.makeText(this,"Saving...", Toast.LENGTH_SHORT);
        toast.show();
        ImageButton saveButton= (ImageButton)findViewById(R.id.saveBttn);
        saveButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                EditText saveName=(EditText) findViewById(R.id.storing_name);
                String sName=saveName.getText().toString();
                EditText saveUser=(EditText) findViewById(R.id.storing_username);
                String sUser=saveUser.getText().toString();
                EditText savePass=(EditText) findViewById(R.id.storing_password);
                String sPass=savePass.getText().toString();


                try {

                    FileOutputStream fos = openFileOutput(sName+".txt", Context.MODE_PRIVATE);
                    fos.write(" Username : ".getBytes());
                    fos.write(sUser.getBytes());
                    fos.write("  Password : ".getBytes());
                    fos.write(sPass.getBytes());
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }


//loadfromfile
    public void loadFile(View view) {
        ImageButton openFileButton=(ImageButton) findViewById(R.id.loadFileBttn);

        openFileButton.setOnClickListener(new View.OnClickListener(){

        public void onClick(View v){
            EditText openName = (EditText) findViewById(R.id.searching_password);
            String sName = openName.getText().toString();
            showOnText(sName);
        }
        });
    }

    private void showOnText(String fName){
        try {
            Toast toast =Toast.makeText(this,"Opening...", Toast.LENGTH_SHORT);
            toast.show();
            FileInputStream fis =openFileInput(fName+".txt");
            BufferedReader reader =new BufferedReader(new InputStreamReader(new DataInputStream(fis)));
            TextView text=(TextView) findViewById(R.id.fileView);
            String line;
            while((line=reader.readLine()) !=null){
                text.append(line);

            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
