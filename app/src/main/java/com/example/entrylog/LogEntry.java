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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LogEntry extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4;
    AppCompatButton b1,b2;
    String apiUrl="http://10.0.4.16:3000/api/students";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_entry);
        ed1=(EditText) findViewById(R.id.name);
        ed2=(EditText) findViewById(R.id.adminno);
        ed3=(EditText) findViewById(R.id.sysno);
        ed4=(EditText) findViewById(R.id.dept);
        b1=(AppCompatButton) findViewById(R.id.logout);
        b2=(AppCompatButton)findViewById(R.id.add);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preference=getSharedPreferences("logApp",MODE_PRIVATE);
                SharedPreferences.Editor editor=preference.edit();
                editor.clear();
                editor.apply();
                Intent i=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Value Reading
                String name=ed1.getText().toString();
                String adminNo=ed2.getText().toString();
                String systemNo=ed3.getText().toString();
                String dept=ed4.getText().toString();

                //Creating JSON Object
                JSONObject student=new JSONObject();
                try {
                    student.put("name",name);
                    student.put("admission_number",adminNo);
                    student.put("system_number",systemNo);
                    student.put("department",dept);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                //JSON Object request creation
                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(
                        Request.Method.POST,
                        apiUrl,
                        student,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(getApplicationContext(), "Added sucessfully", Toast.LENGTH_SHORT).show();

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();

                            }
                        }
                );
                //Request Queue
                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(jsonObjectRequest);


            }
        });
    }
}