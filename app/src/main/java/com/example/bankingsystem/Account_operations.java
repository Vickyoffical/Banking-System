package com.example.bankingsystem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Account_operations extends AppCompatActivity {

    RadioButton radioButton_details,radioButton_cash,radioButton_loan;
    Button button_select;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_account_operations);

        radioButton_details = findViewById(R.id.rb_details);
        radioButton_cash = findViewById(R.id.rb_cash);
        radioButton_loan = findViewById(R.id.rb_loan);
        button_select = findViewById(R.id.btn_select);

        button_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (radioButton_details.isChecked()){
                    Intent i = new Intent(getApplicationContext(),Information.class);
                    startActivity(i);
                }
                if (radioButton_cash.isChecked()){
                    Intent i = new Intent(getApplicationContext(),Cash.class);
                    startActivity(i);
                }
                if (radioButton_loan.isChecked()){
                    Intent i = new Intent(getApplicationContext(), Loan.class);
                    startActivity(i);
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}