package com.example.currencyconverter;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.currencyconverter.api.ApiClient;
import com.example.currencyconverter.api.CurrencyApi;
import com.example.currencyconverter.api.CurrencyResponse;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText etAmount;
    private Spinner spSource, spTarget;
    private Button btnConvert;
    private ImageButton btnSwap;
    private TextView tvRate, tvResult;

    private CurrencyApi api;

    private Map<String, Double> currentRates = new HashMap<>();

    private final ArrayList<String> currencyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAmount = findViewById(R.id.etAmount);
        spSource = findViewById(R.id.spSource);
        spTarget = findViewById(R.id.spTarget);
        btnConvert = findViewById(R.id.btnConvert);
        btnSwap = findViewById(R.id.btnSwap);
        tvRate = findViewById(R.id.tvRate);
        tvResult = findViewById(R.id.tvResult);

        api = ApiClient.getClient().create(CurrencyApi.class);

        loadCurrencies("USD");

        btnConvert.setOnClickListener(v -> {
            loadCurrencies(spSource.getSelectedItem().toString());
        });

        btnSwap.setOnClickListener(v -> {

            int sourcePos = spSource.getSelectedItemPosition();
            int targetPos = spTarget.getSelectedItemPosition();

            spSource.setSelection(targetPos);
            spTarget.setSelection(sourcePos);

            loadCurrencies(spSource.getSelectedItem().toString());

        });



    }

    private void loadCurrencies(String baseCurrency) {

        api.getRates(baseCurrency).enqueue(new Callback<CurrencyResponse>() {

            @Override
            public void onResponse(Call<CurrencyResponse> call,
                                   Response<CurrencyResponse> response) {

                if (response.isSuccessful() && response.body() != null) {

                    currentRates.clear();
                    currentRates.putAll(response.body().getRates());

                    currencyList.clear();
                    currencyList.addAll(currentRates.keySet());

                    Collections.sort(currencyList);

                    ArrayAdapter<String> adapter =
                            new ArrayAdapter<>(
                                    MainActivity.this,
                                    android.R.layout.simple_spinner_item,
                                    currencyList
                            );

                    adapter.setDropDownViewResource(
                            android.R.layout.simple_spinner_dropdown_item);

                    String selectedTarget = null;

                    if (spTarget.getSelectedItem() != null) {
                        selectedTarget = spTarget.getSelectedItem().toString();
                    }

                    spSource.setAdapter(adapter);
                    spTarget.setAdapter(adapter);

                    int sourceIndex = currencyList.indexOf(baseCurrency);

                    if (sourceIndex >= 0)
                        spSource.setSelection(sourceIndex);

                    if (selectedTarget != null) {

                        int targetIndex = currencyList.indexOf(selectedTarget);

                        if (targetIndex >= 0)
                            spTarget.setSelection(targetIndex);

                    } else {

                        int inrIndex = currencyList.indexOf("INR");

                        if (inrIndex >= 0)
                            spTarget.setSelection(inrIndex);

                    }
                    convertCurrency();

                } else {

                    Toast.makeText(MainActivity.this,
                            "Failed to fetch exchange rates",
                            Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<CurrencyResponse> call,
                                  Throwable t) {

                Toast.makeText(MainActivity.this,
                        "Network Error : " + t.getMessage(),
                        Toast.LENGTH_LONG).show();

            }

        });

    }

    private void convertCurrency() {

        String amountText = etAmount.getText().toString().trim();

        if (TextUtils.isEmpty(amountText)) {

            etAmount.setError("Enter an amount");
            return;

        }

        double amount = Double.parseDouble(amountText);

        String target = spTarget.getSelectedItem().toString();

        Double rate = currentRates.get(target);

        if (rate == null) {

            Toast.makeText(this,
                    "Exchange rate unavailable",
                    Toast.LENGTH_SHORT).show();

            return;

        }

        double converted = amount * rate;

        tvRate.setText("Exchange Rate : 1 "
                + spSource.getSelectedItem().toString()
                + " = "
                + rate
                + " "
                + target);

        tvResult.setText("Converted Amount : "
                + String.format("%.2f", converted)
                + " "
                + target);

    }
}