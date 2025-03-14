package com.example.bankingsystem;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Cash extends AppCompatActivity {

    RadioButton radioButton_deposit,radioButton_withdrwal;
    Button button_next,button_addDeposit,button_addWithdrwal,button_menu;
    EditText editText_depositAmount,editText_withdrwalDeposit;
    RelativeLayout relativeLayout_deposit,relativeLayout_withdrwal;
    TextView textView_outputDeposit,textView_outputWithdrwal;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cash);

        radioButton_deposit = findViewById(R.id.rb_deposit);
        radioButton_withdrwal = findViewById(R.id.rb_withdrwal);
        button_next = findViewById(R.id.btn_next);
        button_addDeposit = findViewById(R.id.btn_addDeposit);
        button_addWithdrwal = findViewById(R.id.btn_addWithdrwal);
        button_menu = findViewById(R.id.btn_menu);
        editText_depositAmount = findViewById(R.id.et_depositAmount);
        editText_withdrwalDeposit = findViewById(R.id.et_withdrwalAmount);
        textView_outputDeposit = findViewById(R.id.tv_outputDeposit);
        textView_outputWithdrwal = findViewById(R.id.tv_outputWithdrwal);
        relativeLayout_deposit = findViewById(R.id.rl_deposit);
        relativeLayout_withdrwal = findViewById(R.id.rl_withdrwal);

        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (radioButton_deposit.isChecked()){
                    relativeLayout_deposit.setVisibility(View.VISIBLE);
                    button_menu.setVisibility(View.VISIBLE);

                    RelativeLayout.LayoutParams Params = (RelativeLayout.LayoutParams) button_menu.getLayoutParams();
                    Params.addRule(RelativeLayout.BELOW,relativeLayout_deposit.getId());
                    button_menu.setLayoutParams(Params);

                }else {
                    relativeLayout_deposit.setVisibility(View.GONE);
                    button_menu.setVisibility(View.GONE);
                }


                if (radioButton_withdrwal.isChecked()){
                    relativeLayout_withdrwal.setVisibility(View.VISIBLE);
                    button_menu.setVisibility(View.VISIBLE);

                    RelativeLayout.LayoutParams Params = (RelativeLayout.LayoutParams) button_menu.getLayoutParams();
                    Params.addRule(RelativeLayout.BELOW,relativeLayout_withdrwal.getId());
                    button_menu.setLayoutParams(Params);

                }else {
                    relativeLayout_withdrwal.setVisibility(View.GONE);
                    button_menu.setVisibility(View.GONE);
                }

            }
        });

        button_addDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deposit('+');

            }
        });

        button_addWithdrwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                withdrwal('-');

            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void deposit(char operator){

        SharedPreferences shrdpre = getSharedPreferences("Data",MODE_PRIVATE);

        String balance = shrdpre.getString("bal","0");
        String amountdep = editText_depositAmount.getText().toString();

        if (!amountdep.isEmpty()){
            double num1 = Double.parseDouble(amountdep);
            double num2 = Double.parseDouble(balance);

            num2 = num1 + num2;
            textView_outputDeposit.setText("" + num2);
        }else {
            textView_outputDeposit.setText("Please Enter Amount.");
        }
    }

    public void withdrwal(char operator){

        SharedPreferences shrdpre = getSharedPreferences("Data",MODE_PRIVATE);

        String balance = shrdpre.getString("bal","0");
        String amountwit = editText_withdrwalDeposit.getText().toString();

        if (!amountwit.isEmpty()){
            double num1 = Double.parseDouble(amountwit);
            double num2 = Double.parseDouble(balance);

            num2 = num1 - num2;
            textView_outputWithdrwal.setText("" + num2);
        }else {
            textView_outputWithdrwal.setText("Please Enter Amount.");
        }
    }

}