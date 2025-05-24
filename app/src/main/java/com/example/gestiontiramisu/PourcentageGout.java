package com.example.gestiontiramisu;

public class PourcentageGout {
    private String nomGout;
    private int pourcentage;

    public PourcentageGout(String nomGout, int pourcentage) {
        this.nomGout = nomGout;
        this.pourcentage = pourcentage;
    }

    public String getNomGout() {
        return nomGout;
    }

    public void setNomGout(String nomGout) {
        this.nomGout = nomGout;
    }

    public int getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(int pourcentage) {
        this.pourcentage = pourcentage;
    }
}
