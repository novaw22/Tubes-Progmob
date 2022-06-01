package com.example.duniamusik;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    EditText registerUsername, registerEmail, registerPhone, registerPassword;
    Button buttonRegister;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper = new DBHelper(this);
        registerEmail = findViewById(R.id.email);
        registerUsername = findViewById(R.id.registerUsername);
        registerPhone = findViewById(R.id.phone);
        registerPassword = findViewById(R.id.registerPassword);
        buttonRegister = findViewById(R.id.register);
        buttonRegister.setOnClickListener(v -> {
            String email = registerEmail.getText().toString().trim();
            String username = registerUsername.getText().toString().trim();
            String phone = registerPhone.getText().toString().trim();
            String password = registerPassword.getText().toString().trim();

            ContentValues values = new ContentValues();
            boolean check = validationInfo(username, password, email, phone);

            if (!check) {
                Toast.makeText(RegisterActivity.this, "Terdapat data yang salah!", Toast.LENGTH_SHORT).show();
            } else {
                values.put(DBHelper.row_email, email);
                values.put(DBHelper.row_username, username);
                values.put(DBHelper.row_phone, phone);
                values.put(DBHelper.row_password, password);
                dbHelper.insertData(values);

                Toast.makeText(RegisterActivity.this, "Registrasi Sukses", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("password", password);
                intent.putExtra("email", email);
                intent.putExtra("phone", phone);
                startActivity(intent);
            }
        });
    }

    private Boolean validationInfo(String username, String password, String email, String phone) {
        String valPhone = "^[0-9]{12,13}$";
        String valEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (email.length()==0) {
            registerEmail.requestFocus();
            registerEmail.setError("Email tidak boleh kosong!");
            return false;
        }else if (!email.matches(valEmail)) {
            registerEmail.requestFocus();
            registerEmail.setError("Format Email salah!");
            return false;
        }else if (dbHelper.checkEmail(email)) {
            registerEmail.requestFocus();
            registerEmail.setError("Email sudah terdaftar!");
            return false;
        }else if (username.length()==0) {
            registerUsername.requestFocus();
            registerUsername.setError("Username tidak boleh kosong!");
            return false;
        }else if (dbHelper.checkUsername(username)) {
            registerUsername.requestFocus();
            registerUsername.setError("Username sudah terdaftar!");
            return false;
        }else if (phone.length()==0) {
            registerPhone.requestFocus();
            registerPhone.setError("Nomor telepon tidak boleh kosong!");
            return false;
        }else if (phone.length()<12 || phone.length()>13|| !phone.matches(valPhone)) {
            registerPhone.requestFocus();
            registerPhone.setError("Format nomor telepon 12-13 digit angka!");
            return false;
        }else if (password.length()<8) {
            registerPassword.requestFocus();
            registerPassword.setError("Password minimal 8 karakter!");
            return false;
        }else {
            return true;
        }
    }
}