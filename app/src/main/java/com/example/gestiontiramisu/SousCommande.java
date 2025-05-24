package com.example.gestiontiramisu;

import java.util.ArrayList;
import java.util.List;

public class SousCommande {

    private int typeIndex;  // Indice du type de contenant (ex: Spinner position)
    private List<GoutQuantite> gouts;

    public SousCommande() {
        this.gouts = new ArrayList<>();
    }

    public SousCommande(int typeIndex, List<GoutQuantite> gouts) {
        this.typeIndex = typeIndex;
        this.gouts = gouts;
    }

    public int getTypeIndex() {
        return typeIndex;
    }

    public void setTypeIndex(int typeIndex) {
        this.typeIndex = typeIndex;
    }

    public List<GoutQuantite> getGouts() {
        return gouts;
    }

    public void setGouts(List<GoutQuantite> gouts) {
        this.gouts = gouts;
    }
}