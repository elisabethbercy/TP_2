package com.example.tp2.commandes;
import java.util.List;
import java.util.Optional;

import com.example.tp2.users.Users;

public interface CommandesInterface {

    void newcommande(String nom_commande, Users user);
    
    public List<Commandes> findByUsrEmail(String email);

    Iterable<Commandes>findAll();

    Optional<Commandes> findById(Long id);
    

    
}