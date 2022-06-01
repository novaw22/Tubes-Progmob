package com.example.duniamusik;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailProduct extends AppCompatActivity {
    TextView price, name, description;
    Bundle extras;
    ImageView gambar;

    @Override
    protected void onResume() {
        super.onResume();
        extras = getIntent().getExtras();
        if(extras != null){
            price.setText(extras.getString("price"));
            name.setText(extras.getString("name"));
            Context context = gambar.getContext();
            description.setText(extras.getString("description"));
            int id = context.getResources().getIdentifier(extras.getString("image"), "drawable", context.getPackageName());
            gambar.setImageResource(id);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        price = findViewById(R.id.price);
        gambar = findViewById(R.id.image);
        name = findViewById(R.id.product_name);
        description = findViewById(R.id.description);
    }

    public void buy(View view){
        Intent intent = new Intent(getApplicationContext(),CartActivity.class);
        intent.putExtra("nama",extras.getString("name"));
        intent.putExtra("harga",extras.getString("price"));
        intent.putExtra("image",extras.getString("image"));
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    public void back(View view){
        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
        startActivity(intent);
    }

}