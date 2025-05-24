package com.example.gestiontiramisu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;

public class CommandesHabituellesActivity extends AppCompatActivity {
    private LinearLayout layoutCommandes;
    private Button buttonAjouterCommande, buttonValiderCommandes;
    private List<CommandeClient> sauvegarde = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commandes_habituelles);

        layoutCommandes = findViewById(R.id.layoutCommandes);
        buttonAjouterCommande = findViewById(R.id.buttonAjouterCommande);
        buttonValiderCommandes = findViewById(R.id.buttonValiderCommandes);

        chargerCommandes();

        if (sauvegarde.isEmpty()) {
            ajouterCommandeClient(null);
        } else {
            for (CommandeClient client : sauvegarde) {
                ajouterCommandeClient(client);
            }
        }

        buttonAjouterCommande.setOnClickListener(v -> ajouterCommandeClient(null));

        buttonValiderCommandes.setOnClickListener(v -> {
            sauvegarderCommandes();
            startActivity(new Intent(this, CommandesSpecialesActivity.class));
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        sauvegarderCommandes();  // Sauvegarde automatique en quittant l’activité
    }

    private void ajouterCommandeClient(CommandeClient commande) {
        View commandeView = LayoutInflater.from(this).inflate(R.layout.item_commande_client, layoutCommandes, false);
        EditText editNomClient = commandeView.findViewById(R.id.editNomClient);
        EditText editQuantiteTotale = commandeView.findViewById(R.id.editQuantiteTotale);
        EditText editExclusions = commandeView.findViewById(R.id.editExclusions);
        LinearLayout layoutGouts = commandeView.findViewById(R.id.layoutGoutsClient);
        Button btnSupprimerClient = commandeView.findViewById(R.id.btnSupprimerCommande);

        if (commande != null) {
            editNomClient.setText(commande.getNomClient());
            if (commande.getQuantiteTotale() > 0)
                editQuantiteTotale.setText(String.valueOf(commande.getQuantiteTotale()));
            if (commande.getGoutsExclus() != null)
                editExclusions.setText(String.join(",", commande.getGoutsExclus()));
            for (GoutQuantite gq : commande.getGouts()) {
                View goutView = LayoutInflater.from(this).inflate(R.layout.item_gout_special, layoutGouts, false);
                EditText nom = goutView.findViewById(R.id.editNomGout);
                EditText qte = goutView.findViewById(R.id.editQuantiteGout);
                Button btnSup = goutView.findViewById(R.id.btnSupprimerGout);
                nom.setText(gq.getNom());
                qte.setText(String.valueOf(gq.getQuantite()));
                btnSup.setOnClickListener(x -> layoutGouts.removeView(goutView));
                layoutGouts.addView(goutView);
            }
        }

        // Retirer le bouton "Ajouter un goût" comme demandé
        commandeView.findViewById(R.id.btnAjouterGoutClient).setVisibility(View.GONE);

        btnSupprimerClient.setOnClickListener(v -> layoutCommandes.removeView(commandeView));
        layoutCommandes.addView(commandeView);
    }

    private void sauvegarderCommandes() {
        sauvegarde.clear();

        for (int i = 0; i < layoutCommandes.getChildCount(); i++) {
            View commandeView = layoutCommandes.getChildAt(i);
            EditText editNomClient = commandeView.findViewById(R.id.editNomClient);
            EditText editQuantiteTotale = commandeView.findViewById(R.id.editQuantiteTotale);
            EditText editExclusions = commandeView.findViewById(R.id.editExclusions);
            LinearLayout layoutGouts = commandeView.findViewById(R.id.layoutGoutsClient);

            String nomClient = editNomClient.getText().toString().trim();

            int quantiteTotale = 0;
            try {
                quantiteTotale = Integer.parseInt(editQuantiteTotale.getText().toString().trim());
            } catch (NumberFormatException ignored) {}

            List<String> exclusions = new ArrayList<>();
            String exclu = editExclusions.getText().toString().trim();
            if (!exclu.isEmpty()) {
                for (String gout : exclu.split(",")) {
                    exclusions.add(gout.trim());
                }
            }

            List<GoutQuantite> gouts = new ArrayList<>();
            for (int j = 0; j < layoutGouts.getChildCount(); j++) {
                View goutView = layoutGouts.getChildAt(j);
                EditText nom = goutView.findViewById(R.id.editNomGout);
                EditText qte = goutView.findViewById(R.id.editQuantiteGout);
                try {
                    int quantite = Integer.parseInt(qte.getText().toString().trim());
                    gouts.add(new GoutQuantite(nom.getText().toString().trim(), quantite));
                } catch (NumberFormatException ignored) {}
            }

            if (!nomClient.isEmpty()) {
                CommandeClient cc = new CommandeClient(nomClient);
                cc.setGouts(gouts);
                cc.setQuantiteTotale(quantiteTotale);
                cc.setGoutsExclus(exclusions);
                sauvegarde.add(cc);
            }
        }

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        prefs.edit().putString("commandes_habituelles", new Gson().toJson(sauvegarde)).apply();
    }

    private void chargerCommandes() {
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        String json = prefs.getString("commandes_habituelles", null);
        if (json != null) {
            sauvegarde = new Gson().fromJson(json, new TypeToken<List<CommandeClient>>() {}.getType());
        }
    }
}