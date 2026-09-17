package com.banque;

import com.banque.exceptions.CompteDejaExistantException;
import com.banque.exceptions.CompteInconnuException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestionnaireComptes {

    private Map<String, CompteBancaire> comptes = new HashMap<>();

    public void ajouterCompte(CompteBancaire compte) {
        if (comptes.containsKey(compte.getIban())) {
            throw new CompteDejaExistantException("Compte déjà existant");
        }
        comptes.put(compte.getIban(), compte);
    }

    public CompteBancaire rechercherCompte(String iban) {
        CompteBancaire compte = comptes.get(iban);
        if (compte == null) {
            throw new CompteInconnuException("Compte inconnu");
        }
        return compte;
    }

    public void virement(String ibanSource, String ibanDestination, double montant) {
        CompteBancaire source = rechercherCompte(ibanSource);
        CompteBancaire destination = rechercherCompte(ibanDestination);
        source.retirer(montant);
        destination.deposer(montant);
    }

    public double soldeTotal() {
        double total = 0;
        for (CompteBancaire compte : comptes.values()) {
            total += compte.getSolde();
        }
        return total;
    }

    public List<CompteBancaire> listeComptesEnDecouvert() {
        List<CompteBancaire> resultat = new ArrayList<>();
        for (CompteBancaire compte : comptes.values()) {
            if (compte.estEnDecouvert()) {
                resultat.add(compte);
            }
        }
        return resultat;
    }
}