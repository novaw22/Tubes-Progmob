package com.example.duniamusik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button Btnlogin;
    TextView clickText;
    EditText loginUsername, loginPassword;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Btnlogin = findViewById(R.id.Btnlogin);
        clickText = findViewById(R.id.linkRegister);
        loginUsername = findViewById(R.id.loginUsername);
        loginPassword = findViewById(R.id.loginPassword);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            loginUsername.setText(extras.getString("username"));
            loginPassword.setText(extras.getString("password"));
        }

        dbHelper = new DBHelper(this);
        Btnlogin.setOnClickListener(v -> {
            String username = loginUsername.getText().toString().trim();
            String password = loginPassword.getText().toString().trim();

            boolean res = dbHelper.checkUser(username, password);
            if (res) {
                Toast.makeText(MainActivity.this, "Login Sukses "+username, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                intent.putExtra("username",username);
                intent.putExtra("email", extras.getString("email"));
                intent.putExtra("phone", extras.getString("phone"));
                startActivity(intent);
            }else {
                Toast.makeText(MainActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
            }
        });

        clickText.setOnClickListener(v -> openRegister());
    }

    public void openRegister() {
        Intent intent1 = new Intent(this, RegisterActivity.class);
        startActivity(intent1);
    }
}