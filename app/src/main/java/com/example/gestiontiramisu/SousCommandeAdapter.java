package com.example.gestiontiramisu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SousCommandeAdapter extends RecyclerView.Adapter<SousCommandeAdapter.ViewHolder> {

    private final List<SousCommande> sousCommandes;

    public SousCommandeAdapter(List<SousCommande> sousCommandes) {
        this.sousCommandes = sousCommandes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vue = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sous_commande, parent, false);
        return new ViewHolder(vue);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SousCommande sc = sousCommandes.get(position);
        holder.spinnerTypeContenant.setSelection(sc.getTypeIndex());

        holder.layoutGouts.removeAllViews();
        for (GoutQuantite gq : sc.getGouts()) {
            View goutView = LayoutInflater.from(holder.itemView.getContext())
                    .inflate(R.layout.item_gout_special, holder.layoutGouts, false);

            EditText editNom = goutView.findViewById(R.id.editNomGout);
            EditText editQuantite = goutView.findViewById(R.id.editQuantiteGout);
            Button btnSupprimer = goutView.findViewById(R.id.btnSupprimerGout);

            editNom.setText(gq.getNom());
            editQuantite.setText(String.valueOf(gq.getQuantite()));

            btnSupprimer.setOnClickListener(v -> holder.layoutGouts.removeView(goutView));
            holder.layoutGouts.addView(goutView);
        }

        holder.btnAjouterGout.setOnClickListener(v -> {
            View nouveauGout = LayoutInflater.from(holder.itemView.getContext())
                    .inflate(R.layout.item_gout_special, holder.layoutGouts, false);
            Button btn = nouveauGout.findViewById(R.id.btnSupprimerGout);
            btn.setOnClickListener(x -> holder.layoutGouts.removeView(nouveauGout));
            holder.layoutGouts.addView(nouveauGout);
        });

        holder.btnSupprimerType.setOnClickListener(v -> {
            sousCommandes.remove(position);
            notifyItemRemoved(position);
        });
    }

    @Override
    public int getItemCount() {
        return sousCommandes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        Spinner spinnerTypeContenant;
        LinearLayout layoutGouts;
        Button btnAjouterGout, btnSupprimerType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            spinnerTypeContenant = itemView.findViewById(R.id.spinnerTypeContenant);
            layoutGouts = itemView.findViewById(R.id.layoutGouts);
            btnAjouterGout = itemView.findViewById(R.id.btnAjouterGout);
            btnSupprimerType = itemView.findViewById(R.id.btnSupprimerType);
        }
    }
}
