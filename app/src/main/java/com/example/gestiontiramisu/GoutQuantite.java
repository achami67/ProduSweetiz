package com.example.gestiontiramisu;

public class GoutQuantite {
    private String nom;
    private int quantite;
    private boolean goutFixe; // champ ajouté pour indiquer si la quantité est fixe (déjà définie)

    public GoutQuantite(String nom, int quantite) {
        this.nom = nom;
        this.quantite = quantite;
        this.goutFixe = quantite > 0; // on considère qu’un goût est fixe s’il a une quantité non nulle
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public boolean isGoutFixe() {
        return goutFixe;
    }

    public void setGoutFixe(boolean goutFixe) {
        this.goutFixe = goutFixe;
    }
}