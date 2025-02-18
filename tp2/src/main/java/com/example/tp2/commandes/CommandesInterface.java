package com.example.tp2.commandes;
import java.util.List;

import com.example.tp2.users.Users;

public interface CommandesInterface {


    Commandes newcommande(String nom_commande, Users users);

    // List<Commandes> findByUsers(Users users);

    List<Commandes> getCommandesByUsers(Users users);

 

    
}