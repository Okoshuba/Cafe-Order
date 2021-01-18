package com.example.cafeorder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OrderDetailActivity extends AppCompatActivity {

    private TextView textViewOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        textViewOrder = findViewById(R.id.textViewOrder);

        Intent intent = getIntent();

        if (intent.hasExtra("order")){
            String order = intent.getStringExtra("order");
            textViewOrder.setText(order);
        } else {
            Intent backToFirst = new Intent(this, MainActivity.class);
            startActivity(backToFirst);
        }
    }
}
