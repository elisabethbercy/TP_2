package com.example.tp2.commandes;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tp2.users.Users;


@Service
public class CommandesServices implements CommandesInterface{

    @Autowired
    private CommandesRepository c_repo;


    @Override
    public Commandes newcommande(String nom_commande, Users users) {
        Commandes commandes = new Commandes();
        commandes.setNom_commande(nom_commande);
        commandes.setUsers(users);
        return c_repo.save(commandes);
        
    }


    public List<Commandes> getCommandesByUsers(Users users){
        return c_repo.findByUsers(users);

        // check this
    }

}
