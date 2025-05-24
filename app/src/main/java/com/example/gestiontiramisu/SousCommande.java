package com.example.gestiontiramisu;

import java.util.ArrayList;
import java.util.List;

public class SousCommande {

    private String typeNom;  // Nom du type choisi ou tapé par l'utilisateur
    private List<GoutQuantite> gouts;

    public SousCommande() {
        this.gouts = new ArrayList<>();
    }

    public String getTypeNom() {
        return typeNom;
    }

    public void setTypeNom(String typeNom) {
        this.typeNom = typeNom;
    }

    public List<GoutQuantite> getGouts() {
        return gouts;
    }

    public void setGouts(List<GoutQuantite> gouts) {
        this.gouts = gouts;
    }
}