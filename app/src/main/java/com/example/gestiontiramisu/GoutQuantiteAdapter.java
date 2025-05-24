package com.example.gestiontiramisu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class GoutQuantiteAdapter extends RecyclerView.Adapter<GoutQuantiteAdapter.ViewHolder> {

    private final List<GoutQuantite> goutQuantiteList;

    public GoutQuantiteAdapter(List<GoutQuantite> goutQuantiteList) {
        this.goutQuantiteList = goutQuantiteList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        EditText editNomGout;
        EditText editQuantiteGout;
        Button btnSupprimerGout;

        public ViewHolder(View itemView) {
            super(itemView);
            editNomGout = itemView.findViewById(R.id.editNomGout);
            editQuantiteGout = itemView.findViewById(R.id.editQuantiteGout);
            btnSupprimerGout = itemView.findViewById(R.id.btnSupprimerGout);
        }
    }

    @NonNull
    @Override
    public GoutQuantiteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gout_special, parent, false);
        return new GoutQuantiteAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GoutQuantite gout = goutQuantiteList.get(position);
        holder.editNomGout.setText(gout.getNom());
        holder.editQuantiteGout.setText(String.valueOf(gout.getQuantite()));

        holder.btnSupprimerGout.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            goutQuantiteList.remove(pos);
            notifyItemRemoved(pos);
        });
    }

    @Override
    public int getItemCount() {
        return goutQuantiteList.size();
    }
}
