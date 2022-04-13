package com.example.duniamusik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button login = (Button) findViewById(R.id.login);
        TextView clickText = (TextView) findViewById(R.id.linkRegister);

        login.setOnClickListener(v -> openDashboard());
        clickText.setOnClickListener(v -> openRegister());
    }
    public void openDashboard() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }
    public void openRegister() {
        Intent intent1 = new Intent(this, RegisterActivity.class);
        startActivity(intent1);
    }
}