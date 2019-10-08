package com.mechanic.autorepair;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String URL_REGIST = "http://192.168.1.69/android_register_login/register.php";
    EditText name,email ,password;
    Button register,login;
    String user_name,user_email,user_password;
    ProgressBar loading;
    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        login= findViewById(R.id.login);
        preferences = getSharedPreferences("Userinfo", MODE_PRIVATE);
        password=findViewById(R.id.password);
        register = findViewById(R.id.register);
        loading = findViewById(R.id.loading);
        preferences = getSharedPreferences("Userinfo", MODE_PRIVATE);

        if (preferences.getBoolean("rememberme", false)) {
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
            finish();
        }

login.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(MainActivity.this,Login_Activity.class));
    }
});
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                register();
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("username", user_name);
                editor.putString("password", user_password);
                editor.putString("email", user_email);
                editor.apply();

                startActivity(new Intent(MainActivity.this,Login_Activity.class));
            }
        });





    }
    private void register(){
        loading.setVisibility(View.VISIBLE);
        register.setVisibility(View.GONE);
        user_name = name.getText().toString().trim();
        user_email=email.getText().toString().trim();
        user_password=password.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    try{
                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");
                        if(success.equals("1")){

                            Toast.makeText(MainActivity.this, "Register success", Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                        }
                        
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "Register error"+e.toString(), Toast.LENGTH_SHORT).show();
                    loading.setVisibility(View.GONE);
                    register.setVisibility(View.VISIBLE);
                    }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Register error"+error.toString(), Toast.LENGTH_SHORT).show();

                        loading.setVisibility(View.GONE);
                        register.setVisibility(View.VISIBLE);
                    }


    })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("name",user_name);
                params.put("email",user_email);
                params.put("password",user_password);
                return  params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);









    }
}
