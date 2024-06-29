package com.example.entrylog;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText ed1,ed2;
    AppCompatButton b1;
    SharedPreferences loginPreference;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        loginPreference=getSharedPreferences("logApp",MODE_PRIVATE);
        editor=loginPreference.edit();
        String username=loginPreference.getString("uname",null);
        ed1=(EditText) findViewById(R.id.uname);
        ed2=(EditText) findViewById(R.id.pass);
        b1=(AppCompatButton) findViewById(R.id.logbtn);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName=ed1.getText().toString();
                String passWord=ed2.getText().toString();
                if(userName.equals("admin") & passWord.equals("1234")) {

                    SharedPreferences preference=getSharedPreferences("logApp",MODE_PRIVATE);
                    SharedPreferences.Editor editor=preference.edit();
                    editor.putString("uname","admin");
                    editor.apply();
                    Intent i = new Intent(getApplicationContext(), LogEntry.class);
                    startActivity(i);
                }
                else
                    Toast.makeText(getApplicationContext(),"Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        });
    }
}