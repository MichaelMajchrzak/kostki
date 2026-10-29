package com.example.kostki;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText editTextLiczbaKostek;
    GridLayout gridKostki;
    TextView textViewWyniki;
    ArrayList<Integer> wyniki = new ArrayList<>();
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextLiczbaKostek = findViewById(R.id.editTextLiczbaKostek);
        gridKostki = findViewById(R.id.gridKostki);
        textViewWyniki = findViewById(R.id.textViewWyniki);

        Button buttonLosuj = findViewById(R.id.buttonLosuj);
        Button buttonRosnaco = findViewById(R.id.buttonRosnaco);
        Button buttonMalejaco = findViewById(R.id.buttonMalejaco);

        buttonLosuj.setOnClickListener(v -> losuj());
        buttonRosnaco.setOnClickListener(v -> sortujRosnaco());
        buttonMalejaco.setOnClickListener(v -> sortujMalejaco());
    }

    void losuj() {
        int liczba;
        try {
            liczba = Integer.parseInt(editTextLiczbaKostek.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Wpisz poprawną liczbę", Toast.LENGTH_SHORT).show();
            return;
        }

        if (liczba <= 0 || liczba > 100) {
            Toast.makeText(this, "Liczba kostek musi być od 1 do 100", Toast.LENGTH_SHORT).show();
            return;
        }

        wyniki.clear();
        for (int i = 0; i < liczba; i++) {
            wyniki.add(random.nextInt(6) + 1);
        }
        pokaz();
    }

    void sortujRosnaco() {
        if (wyniki.isEmpty()) {
            Toast.makeText(this, "Najpierw wylosuj kostki", Toast.LENGTH_SHORT).show();
            return;
        }
        Collections.sort(wyniki);
        pokaz();
    }

    void sortujMalejaco() {
        if (wyniki.isEmpty()) {
            Toast.makeText(this, "Najpierw wylosuj kostki", Toast.LENGTH_SHORT).show();
            return;
        }
        Collections.sort(wyniki, Collections.reverseOrder());
        pokaz();
    }

    void pokaz() {
        gridKostki.removeAllViews();
        String tekst = "";

        for (int i = 0; i < wyniki.size(); i++) {
            TextView kostka = new TextView(this);
            kostka.setText(String.valueOf(wyniki.get(i)));
            kostka.setTextSize(24);
            kostka.setGravity(Gravity.CENTER);
            kostka.setBackgroundResource(R.drawable.kostka);

            GridLayout.LayoutParams parametry = new GridLayout.LayoutParams();
            parametry.width = 120;
            parametry.height = 120;
            kostka.setLayoutParams(parametry);

            gridKostki.addView(kostka);

            tekst = tekst + wyniki.get(i);
            if (i < wyniki.size() - 1) {
                tekst = tekst + ", ";
            }
        }

        textViewWyniki.setText(tekst);
    }
}