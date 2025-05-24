package com.example.gestiontiramisu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SousCommandeAdapter extends RecyclerView.Adapter<SousCommandeAdapter.ViewHolder> {

    private final List<SousCommande> sousCommandes;
    private final Context context;

    public SousCommandeAdapter(Context context, List<SousCommande> sousCommandes) {
        this.context = context;
        this.sousCommandes = sousCommandes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sous_commande, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SousCommande commande = sousCommandes.get(position);

        // Affiche le nom du type dans le Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context,
                android.R.layout.simple_spinner_item,
                List.of("Pot", "Boîte", "Verrine", commande.getTypeNom()));

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.spinner.setAdapter(adapter);

        int index = adapter.getPosition(commande.getTypeNom());
        if (index != -1) {
            holder.spinner.setSelection(index);
        }

        // Gérer les goûts (optionnel à compléter)
        holder.btnSupprimer.setOnClickListener(v -> {
            sousCommandes.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, sousCommandes.size());
        });
    }

    @Override
    public int getItemCount() {
        return sousCommandes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        Spinner spinner;
        LinearLayout layoutGouts;
        Button btnSupprimer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            spinner = itemView.findViewById(R.id.spinnerTypeContenant);
            layoutGouts = itemView.findViewById(R.id.layoutGouts);
            btnSupprimer = itemView.findViewById(R.id.btnSupprimerType);
        }
    }
}