package com.example.tp2.commandes;
import java.util.Optional;

import com.example.tp2.users.Users;

public interface CommandesInterface {

    void newcommande(String nom_commande, String usr_email);
    public Optional<Users>findByUsrEmail(String usr_email);
    Iterable<Commandes>findAll();
    

    
}