package com.example.bankingsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText editText_name,editText_accNumber;
    Button button_create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        editText_name = findViewById(R.id.et_name);
        editText_accNumber = findViewById(R.id.et_accNumber);
        button_create = findViewById(R.id.btn_create);

        button_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name,accNumber;
                int balance = 1000;

                name = editText_name.getText().toString();
                accNumber = editText_accNumber.getText().toString();

                SharedPreferences shrdpre = getSharedPreferences("Data",MODE_PRIVATE);
                SharedPreferences.Editor editor = shrdpre.edit();

                editor.putString("nam",name);
                editor.putString("accnum",accNumber);
                editor.putInt("bal",balance);
                editor.apply();

                Intent intent = new Intent(getApplicationContext(),Account_operations.class);
                Toast.makeText(getApplicationContext(),"Free Rs.1000 Credited to your Account.",Toast.LENGTH_SHORT).show();
                startActivity(intent);

            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}