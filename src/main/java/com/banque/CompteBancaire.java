package com.banque;

import com.banque.exceptions.MontantInvalideException;
import com.banque.exceptions.SoldeInsuffisantException;

public class CompteBancaire {

    private final String iban;
    private String titulaire;
    private double solde;
    private double decouvertAutorise;

    public CompteBancaire(String iban, String titulaire, double solde, double decouvertAutorise) {
        this.iban = iban;
        this.titulaire = titulaire;
        this.solde = solde;
        this.decouvertAutorise = decouvertAutorise;
    }

    public void deposer(double montant) {
        if (montant <= 0) {
            throw new MontantInvalideException("Montant invalide");
        }
        solde += montant;
    }

    public void retirer(double montant) {
        if (montant <= 0) {
            throw new MontantInvalideException("Montant invalide");
        }
        if (solde - montant < -decouvertAutorise) {
            throw new SoldeInsuffisantException("Solde insuffisant");
        }
        solde -= montant;
    }

    public double calculerInterets(double taux) {
        if (taux < 0) {
            throw new IllegalArgumentException("Taux invalide");
        }
        if (solde > 0) {
            return solde * taux;
        }
        return 0;
    }

    public boolean estEnDecouvert() {
        return solde < 0;
    }

    public double getSolde() {
        return solde;
    }

    public String getTitulaire() {
        return titulaire;
    }

    public String getIban() {
        return iban;
    }
}