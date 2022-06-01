package com.example.duniamusik;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    ArrayList<ProductModel> productModels = new ArrayList<>();
    Bundle extras;
    ProductRecycleViewAdapter adapter;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.setIntent(intent);
    }

    @Override

    protected void onResume() {
        super.onResume();
        extras = getIntent().getExtras();
        if(extras != null){
            String image = extras.getString("image");
            String nama = extras.getString("nama");
            String harga = extras.getString("harga");
            String description = extras.getString("description");

            adapter.addItem(new ProductModel(
                    image,
                    nama,
                    harga,
                    description
            ));
            Toast.makeText(getApplicationContext(),extras.getString("name"),Toast.LENGTH_LONG);

            getIntent().removeExtra("image");
            getIntent().removeExtra("nama");
            getIntent().removeExtra("harga");
            getIntent().removeExtra("description");

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        adapter = new ProductRecycleViewAdapter(this,productModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void back(View view){
        Intent intent = new Intent(this, DashboardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    public void checkout(View view){
        Toast.makeText(getApplicationContext(),"Berhasil membeli barang",Toast.LENGTH_LONG).show();
    }
}