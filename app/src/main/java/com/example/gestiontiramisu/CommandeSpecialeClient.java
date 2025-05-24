package com.example.gestiontiramisu;

import java.util.ArrayList;
import java.util.List;

public class CommandeSpecialeClient {

    private String nomClient;
    private List<SousCommande> sousCommandes;

    public CommandeSpecialeClient(String nomClient) {
        this.nomClient = nomClient;
        this.sousCommandes = new ArrayList<>();
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public List<SousCommande> getSousCommandes() {
        return sousCommandes;
    }

    public void setSousCommandes(List<SousCommande> sousCommandes) {
        this.sousCommandes = sousCommandes;
    }
}