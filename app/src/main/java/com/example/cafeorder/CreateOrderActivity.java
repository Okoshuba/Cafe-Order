package com.example.cafeorder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CreateOrderActivity extends AppCompatActivity {

    private TextView textViewClient;
    private TextView textViewAddition;
    private CheckBox checkBoxMilk;
    private CheckBox checkBoxSugar;
    private CheckBox checkBoxLemon;
    private Spinner spinnerTea;
    private Spinner spinnerCoffee;

    private String drink;

    private String name;
    private String password;

    private StringBuilder stringBuilder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);

        //Получаем значение name и password для обращения по имени в строке и последующей передаче
        Intent intent = getIntent();

        if (intent.hasExtra("name") && intent.hasExtra("password")){
            name = intent.getStringExtra("name");
            password = intent.getStringExtra("password");
        } else{
            name = getString(R.string.default_name);
            password = getString(R.string.default_password);
        }

        //Получить значение drink
        drink = getString(R.string.buttonTea);

        //Даем значения переменным//

        textViewClient = findViewById(R.id.textViewClient);
        //Формируем текст приветстивия
        String hello = String.format(getString(R.string.textViewClient), name);
        textViewClient.setText(hello);

        textViewAddition = findViewById(R.id.textViewAdditions);
        checkBoxMilk = findViewById(R.id.checkboxMilk);
        checkBoxSugar = findViewById(R.id.checkboxSugar);
        checkBoxLemon = findViewById(R.id.checkboxLemon);
        spinnerTea = findViewById(R.id.spinnerTea);
        spinnerCoffee = findViewById(R.id.spinnerCoffee);

        stringBuilder = new StringBuilder();
    }

    public void onClickChangeDrink(View view) {
        RadioButton button = (RadioButton) view;
        int id = button.getId();

        if (id == R.id.radioTea){
            drink = getString(R.string.buttonTea);
            spinnerTea.setVisibility(View.VISIBLE);
            spinnerCoffee.setVisibility(View.INVISIBLE);
            checkBoxLemon.setVisibility(View.VISIBLE);
        } else if (id == R.id.radioCoffee) {
            drink = getString(R.string.buttonCoffee);
            spinnerTea.setVisibility(View.INVISIBLE);
            spinnerCoffee.setVisibility(View.VISIBLE);
            checkBoxLemon.setVisibility(View.INVISIBLE);
        }

        String additions = String.format(getString(R.string.textViewAddition), drink);
        textViewAddition.setText(additions);
    }

    public void onClickSendOrder(View view) {
        stringBuilder.setLength(0);

        if (checkBoxMilk.isChecked()){
            stringBuilder.append(getString(R.string.checkMilk)).append(" ");
        }
        if (checkBoxSugar.isChecked()){
            stringBuilder.append(getString(R.string.checkSugar)).append(" ");
        }
        if (checkBoxLemon.isChecked() && drink.equals(getString(R.string.buttonTea))){
            stringBuilder.append(getString(R.string.checkLimon)).append(" ");
        }

        String optionOfDrink = "";
        if (drink.equals(getString(R.string.buttonTea))){
            optionOfDrink = spinnerTea.getSelectedItem().toString();
        } else {
            optionOfDrink = spinnerCoffee.getSelectedItem().toString();
        }

        String order = String.format(getString(R.string.order), name, password, drink, optionOfDrink);
        String additions;
        if (stringBuilder.length() > 0){
            additions = "\n" + getString(R.string.additions) + stringBuilder.toString();
        } else {
            additions = "";
        }

        String fullOrder = order + additions;
        Intent intent = new Intent(this, OrderDetailActivity.class);
        intent.putExtra("order", fullOrder);
        startActivity(intent);

    }
}
