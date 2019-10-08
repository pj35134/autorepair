package com.mechanic.autorepair;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class Login_fragment extends Fragment {
    private static final String URL_LOGIN ="http://192.168.1.69/android_register_login/login.php" ;
    EditText email,password;
    Button login;
    TextView register_textview;
    ImageView profile_pic;
    SharedPreferences preferences;
    String get_username,get_password;
    CheckBox rememberme;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_login_,null);

        email = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        login = view.findViewById(R.id.login);
        rememberme = view.findViewById(R.id.rememberme);
        preferences = getActivity().getSharedPreferences("Userinfo", MODE_PRIVATE);
        register_textview= view.findViewById(R.id.register);
        profile_pic=view.findViewById(R.id.profile_pic);

      /*  if (preferences.getBoolean("rememberme", false)) {
            startActivity(new Intent(getActivity(), HomeActivity.class));
            getActivity().finish();
        }*/


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get_username =email.getText().toString();
                get_password = password.getText().toString();
                if(!get_username.isEmpty() || !get_password.isEmpty()){

                    login(get_username,get_password);
                    if (rememberme.isChecked()) {
                        preferences.edit().putBoolean("rememberme", true).apply();
                    }



                }
                else{

                    email.setError("Please insert email");
                    password.setError("Please insert password");
                }


            }
        });


        register_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),MainActivity.class));
            }
        });
        return view;
    }



    public void login(final String email, final String password){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                            if(success.equals("1")){
                                for(int i=0;i<jsonArray.length();i++){

                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String name = object.getString("name").trim();
                                    String emails = object.getString("email").trim();
                                    String id = object.getString("id").trim();
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putString("username", name);
                                    editor.putString("password", get_password);
                                    editor.putString("email", emails);
                                    editor.putString("id", id);
                                    editor.apply();
                                    Toast.makeText(getActivity(), "Success Login.\n Your name :"+name+"\n Your email"+email, Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getActivity(),Navdrawer.class);
                                    intent.putExtra("name",name);
                                    intent.putExtra("email",emails);
                                    intent.putExtra("id",id);
                                    startActivity(intent);

                                }





                            }
                            else{
                                Toast.makeText(getActivity(), "wrong email or password", Toast.LENGTH_SHORT).show();

                            }






                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "error"+e, Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "error"+error, Toast.LENGTH_SHORT).show();
                    }
                }

        )
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("email",email);
                params.put("password",password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }
}
