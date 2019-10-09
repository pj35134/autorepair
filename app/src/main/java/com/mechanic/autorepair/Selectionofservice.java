package com.mechanic.autorepair;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Selectionofservice extends AppCompatActivity {
    //List<Product> productList;
    ArrayList<Service> serviceList = new ArrayList<Service>();
    private static final String PRODUCT_URL ="http://192.168.1.69/MyApi/service.php" ;
    //the recyclerviewr
    RecyclerView recyclerView;
    ServiceAdapter adapter;
    GridLayoutManager lLayout;
    CardView cardView;
    CheckBox checkBox;
    String data ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        cardView = findViewById(R.id.cardview);
        checkBox = findViewById(R.id.checkbox);
         lLayout = new GridLayoutManager(Selectionofservice.this, 2);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(lLayout);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //loadProducts();
        //initializing the productlist

        new LongOperation().execute("");


    }

    private void loadProducts(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, PRODUCT_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray products = new JSONArray(response);
                            for(int i =0;i<products.length();i++){
                                JSONObject productObject = products.getJSONObject(i);
                                int id = productObject.getInt("id");
                                String title = productObject.getString("title");
                               // String shortdesc =productObject.getString("shortdesc");
                                double price = productObject.getDouble("price");
                              //  double rating = productObject.getDouble("rating");
                                String image = productObject.getString("image").toString();
                                Service service = new Service(id,title,price,image);
                                serviceList.add(service);
                                Log.i("tag", title);


                            }
                            adapter = new ServiceAdapter(Selectionofservice.this,serviceList);
                            recyclerView.setAdapter(adapter);



                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Selectionofservice.this, ""+e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Selectionofservice.this, ""+error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }


        );
        Volley.newRequestQueue(this).add(stringRequest);







    }


    private class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            loadProducts();
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {

            Log.i("hello",result);
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_add:

                getOrder();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getOrder(){

        ArrayList<String> datas = new ArrayList<>();
        double price;
        int sum=0;

        for (int j=0; j<serviceList.size();j++){

            if (serviceList.get(j).isSelected() == true){


                data = "\n" + serviceList.get(j).getTitle().toString() + "   " + serviceList.get(j).getPrice();
                price = serviceList.get(j).getPrice();
                sum = (int) (sum + price);
                datas.add(data);
                   /* Log.i("datas",datas.get(i));
                    i++;*/

            }


        }




        Intent intent=new Intent(Selectionofservice.this, Order_activity.class);
        intent.putStringArrayListExtra("datas", (ArrayList<String>) datas);
        intent.putExtra("sum",sum);
        startActivity(intent);


    }

}
