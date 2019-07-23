package com.example.secret;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG=MainActivity.class.getSimpleName();
    Button button;
    EditText editText;
    EditText editPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView vulaLink=(TextView) findViewById(R.id.textView);
        vulaLink.setMovementMethod(LinkMovementMethod.getInstance());
        button=(Button) findViewById(R.id.login_button);
        editText =(EditText) findViewById(R.id.username_text);
        editPassword=(EditText) findViewById(R.id.password_text);
    }


    public void launchpasswordpage(View view) {
        Log.d(LOG_TAG,"Sign in Clicked");
    }
    public void onClick(View view){
        Toast toast =Toast.makeText(this,"Logging in...", Toast.LENGTH_SHORT);
        toast.show();
        String name=editText.getText().toString();
        String password=editPassword.getText().toString();
        if(name.length()==0){
            editText.setError("You Must have a user name");
        }
        else if( password.length()==0){
            editPassword.setError("You must have a password");
        }
        else {

            Intent intent = new Intent(this, password_page.class);
            intent.putExtra("name",name);
            startActivity(intent);
        }
    }
}
