package com.mechanic.autorepair;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.android.volley.VolleyLog.TAG;

/**
 * Created by Belal on 10/18/2017.
 */


public class ModelsAdapter extends RecyclerView.Adapter<ModelsAdapter.ModelsViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;
    Bitmap mIcon_val;

    //we are storing all the products in a list
    private     ArrayList<Model> modelList = new ArrayList<Model>();

    //getting the context and product list with constructor
    public ModelsAdapter(Context mCtx, List<Model> modelList) {
        this.mCtx = mCtx;
        this.modelList = (ArrayList<Model>) modelList;
    }

    @Override
    public ModelsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_layout, parent,false);
        return new ModelsViewHolder(view);
    }



    @Override
    public void onBindViewHolder(ModelsViewHolder holder, int position) {
        //getting the product of the specified position
        final Model model = modelList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(model.getTitle());
        /*holder.textViewShortDesc.setText(product.getShortdesc());
        holder.textViewRating.setText(String.valueOf(product.getRating()));
        holder.textViewPrice.setText(String.valueOf(product.getPrice()));*/
        Log.i("tagg",model.getImage());

       /* Glide.with(mCtx)
                .load(product.getImage())
                .into(holder.imageView);*/
        Picasso.get().load(model.getImage()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Model_Info.class);
                intent.putExtra("title",model.getTitle());
                intent.putExtra("image",model.getImage());
                mCtx.startActivity(intent);
            }
        });
       /* Glide.with(mCtx).
                load(product.getImage()).
                error(R.drawable.ic_directions_bike_black_24dp).
                into(holder.imageView);*/
    }


    @Override
    public int getItemCount() {
        return modelList.size();
    }


    class ModelsViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice;
        ImageView imageView;

        public ModelsViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
          /*  textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);*/
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}