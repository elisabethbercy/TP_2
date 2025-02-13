package com.example.tp2.commandes;

import java.util.List;
import java.util.Optional;

import com.example.tp2.users.Users;

public interface CommandesInterface {

    void new_commande(String nom_commande, String usr_email);
    public Optional<Users>findByUsr_email(String usr_email);
    List<Commandes> getAllCommandes();

    
}