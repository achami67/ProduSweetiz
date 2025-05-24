package com.example.gestiontiramisu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PourcentagesAdapter extends RecyclerView.Adapter<PourcentagesAdapter.ViewHolder> {

    private final List<PourcentageGout> pourcentages;
    private final OnGoutActionListener actionListener;

    public interface OnGoutActionListener {
        void onGoutDeleted(int position);
        void onGoutEdit(int position);
    }

    public PourcentagesAdapter(List<PourcentageGout> pourcentages, OnGoutActionListener actionListener) {
        this.pourcentages = pourcentages;
        this.actionListener = actionListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNomGout, txtPourcentage;
        ImageButton btnEditGout, btnDeleteGout;

        public ViewHolder(View itemView) {
            super(itemView);
            txtNomGout = itemView.findViewById(R.id.txtNomGout);
            txtPourcentage = itemView.findViewById(R.id.txtPourcentage);
            btnEditGout = itemView.findViewById(R.id.btnEditGout);
            btnDeleteGout = itemView.findViewById(R.id.btnDeleteGout);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_gout_pourcentage, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PourcentageGout item = pourcentages.get(position);
        holder.txtNomGout.setText(item.getNomGout());
        holder.txtPourcentage.setText(item.getPourcentage() + " %");

        holder.btnDeleteGout.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            if (actionListener != null && pos != RecyclerView.NO_POSITION) {
                actionListener.onGoutDeleted(pos);
            }
        });

        holder.btnEditGout.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            if (actionListener != null && pos != RecyclerView.NO_POSITION) {
                actionListener.onGoutEdit(pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pourcentages.size();
    }
}