package com.example.bankingsystem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Information extends AppCompatActivity {

    RadioButton radioButton_details,radioButton_balance;
    Button button_show,button_menu;
    TextView textView_name,textView_accNumber,textView_balance;
    RelativeLayout relativeLayout_details,relativeLayout_balance;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_information);

        radioButton_details = findViewById(R.id.rb_showDetails);
        radioButton_balance = findViewById(R.id.rb_checkBalance);
        button_show = findViewById(R.id.btn_show);
        button_menu = findViewById(R.id.btn_menu);
        textView_name = findViewById(R.id.tv_name);
        textView_accNumber = findViewById(R.id.tv_accNumber);
        textView_balance = findViewById(R.id.tv_balance);
        relativeLayout_details = findViewById(R.id.rl_details);
        relativeLayout_balance = findViewById(R.id.rl_balance);

        button_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (radioButton_details.isChecked()){
                    relativeLayout_details.setVisibility(View.VISIBLE);
                    button_menu.setVisibility(View.VISIBLE);

                    RelativeLayout.LayoutParams Params = (RelativeLayout.LayoutParams) button_menu.getLayoutParams();
                    Params.addRule(RelativeLayout.BELOW,relativeLayout_details.getId());
                    button_menu.setLayoutParams(Params);

                    showDetails();
                }else {
                    relativeLayout_details.setVisibility(View.GONE);
                    button_menu.setVisibility(View.GONE);
                }
                if (radioButton_balance.isChecked()){
                    relativeLayout_balance.setVisibility(View.VISIBLE);
                    button_menu.setVisibility(View.VISIBLE);

                    RelativeLayout.LayoutParams Params = (RelativeLayout.LayoutParams) button_menu.getLayoutParams();
                    Params.addRule(RelativeLayout.BELOW,relativeLayout_balance.getId());
                    button_menu.setLayoutParams(Params);

                    showBalance();
                }else{
                    relativeLayout_balance.setVisibility(View.GONE);
                    button_menu.setVisibility(View.GONE);
                }

            }
        });

        button_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),Account_operations.class);
                startActivity(intent);

            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void showDetails(){

        SharedPreferences shrdpre = getSharedPreferences("Data",MODE_PRIVATE);
        String name = shrdpre.getString("nam","No Name Found");
        String num = shrdpre.getString("accnum","No Account Number Found");

        textView_name.setText(name);
        textView_accNumber.setText(num);
    }

    public void showBalance(){

        SharedPreferences shrdpre = getSharedPreferences("Data",MODE_PRIVATE);
        String balance = shrdpre.getString("bal","No Balance Found");

        textView_balance.setText(balance);
    }
}