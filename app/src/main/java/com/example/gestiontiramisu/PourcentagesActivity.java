package com.example.gestiontiramisu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class PourcentagesActivity extends AppCompatActivity implements PourcentagesAdapter.OnGoutActionListener {

    private EditText editTextNomGout, editTextPourcentage;
    private Button buttonAjouter, buttonValider;
    private RecyclerView recyclerView;
    private ArrayList<PourcentageGout> listePourcentages;
    private PourcentagesAdapter adapter;
    private int positionEnCoursDeModification = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pourcentages);

        editTextNomGout = findViewById(R.id.editTextNomGout);
        editTextPourcentage = findViewById(R.id.editTextPourcentage);
        buttonAjouter = findViewById(R.id.buttonAjouter);
        buttonValider = findViewById(R.id.buttonValider);
        recyclerView = findViewById(R.id.recyclerViewPourcentages);

        chargerListe();

        adapter = new PourcentagesAdapter(listePourcentages, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        buttonAjouter.setOnClickListener(v -> {
            String nom = editTextNomGout.getText().toString().trim();
            String pourcent = editTextPourcentage.getText().toString().trim();

            if (TextUtils.isEmpty(nom) || TextUtils.isEmpty(pourcent)) {
                Toast.makeText(this, "Remplissez les deux champs.", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int pourcentage = Integer.parseInt(pourcent);
                PourcentageGout gout = new PourcentageGout(nom, pourcentage);

                if (positionEnCoursDeModification != -1) {
                    listePourcentages.set(positionEnCoursDeModification, gout);
                    adapter.notifyItemChanged(positionEnCoursDeModification);
                    positionEnCoursDeModification = -1;
                } else {
                    listePourcentages.add(gout);
                    adapter.notifyItemInserted(listePourcentages.size() - 1);
                }

                editTextNomGout.setText("");
                editTextPourcentage.setText("");

            } catch (NumberFormatException e) {
                Toast.makeText(this, "Le pourcentage doit être un nombre entier.", Toast.LENGTH_SHORT).show();
            }
        });

        buttonValider.setOnClickListener(v -> {
            int total = 0;
            for (PourcentageGout item : listePourcentages) {
                total += item.getPourcentage();
            }

            if (total != 100) {
                Toast.makeText(this, "Le total doit faire 100 % (actuellement " + total + "%)", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Pourcentages validés !", Toast.LENGTH_SHORT).show();
                sauvegarderListe();
                startActivity(new Intent(PourcentagesActivity.this, CommandesHabituellesActivity.class));
            }
        });
    }

    @Override
    public void onGoutDeleted(int position) {
        listePourcentages.remove(position);
        adapter.notifyItemRemoved(position);
        positionEnCoursDeModification = -1;
    }

    @Override
    public void onGoutEdit(int position) {
        PourcentageGout gout = listePourcentages.get(position);
        editTextNomGout.setText(gout.getNomGout());
        editTextPourcentage.setText(String.valueOf(gout.getPourcentage()));
        positionEnCoursDeModification = position;
    }

    private void sauvegarderListe() {
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        editor.putString("liste_gouts", gson.toJson(listePourcentages));
        editor.apply();
    }

    private void chargerListe() {
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        String json = prefs.getString("liste_gouts", null);
        if (json != null) {
            Gson gson = new Gson();
            listePourcentages = gson.fromJson(json, new com.google.gson.reflect.TypeToken<ArrayList<PourcentageGout>>() {}.getType());
        } else {
            listePourcentages = new ArrayList<>();
        }
    }
}
