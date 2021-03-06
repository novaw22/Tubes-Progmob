package com.example.duniamusik;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductRecycleViewAdapter extends RecyclerView.Adapter<ProductRecycleViewAdapter.MyViewHolder>{
    Context context;
    ArrayList<ProductModel> productModels;
    public ProductRecycleViewAdapter(Context context, ArrayList<ProductModel> productModels){
        this.context = context;
        this.productModels = productModels;
    }
    public void addItem(ProductModel newModel){
        productModels.add(newModel);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ProductRecycleViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.cart_row,parent,false);
        return new ProductRecycleViewAdapter.MyViewHolder(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductRecycleViewAdapter.MyViewHolder holder, int position) {
        Context context = holder.thumbnail.getContext();
        int id = context.getResources().getIdentifier(productModels.get(position).thumbnail, "drawable", context.getPackageName());
        holder.thumbnail.setImageResource(id);
        holder.name.setText(productModels.get(position).nama);
        holder.harga.setText(productModels.get(position).harga);
        holder.qty.setText("Qty : x1");

    }

    @Override
    public int getItemCount() {
        return productModels.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView thumbnail;
        TextView name, harga, qty;
        private ProductRecycleViewAdapter adapter;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.product);
            name = itemView.findViewById(R.id.productName);
            harga = itemView.findViewById(R.id.harga);
            qty = itemView.findViewById(R.id.qty);

        }
        public MyViewHolder linkAdapter(ProductRecycleViewAdapter adapter){
            this.adapter = adapter;
            return this;
        }
    }
}
