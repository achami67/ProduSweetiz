package com.example.gestiontiramisu;

import java.util.ArrayList;
import java.util.List;

public class CommandeClient {
    private String nomClient;
    private List<GoutQuantite> gouts;
    private int quantiteTotale;
    private List<String> goutsExclus;

    public CommandeClient(String nomClient) {
        this.nomClient = nomClient;
        this.gouts = new ArrayList<>();
        this.goutsExclus = new ArrayList<>();
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public List<GoutQuantite> getGouts() {
        return gouts;
    }

    public void setGouts(List<GoutQuantite> gouts) {
        this.gouts = gouts;
    }

    public int getQuantiteTotale() {
        return quantiteTotale;
    }

    public void setQuantiteTotale(int quantiteTotale) {
        this.quantiteTotale = quantiteTotale;
    }

    public List<String> getGoutsExclus() {
        return goutsExclus;
    }

    public void setGoutsExclus(List<String> goutsExclus) {
        this.goutsExclus = goutsExclus;
    }
}