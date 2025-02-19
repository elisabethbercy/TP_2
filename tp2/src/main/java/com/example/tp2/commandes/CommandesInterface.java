package com.example.tp2.commandes;
import java.util.List;

import com.example.tp2.users.Users;

public interface CommandesInterface {

    Commandes newCommande(String nomCommande, Users users);

    List<Commandes> findByUsers(Users users);

    List<Commandes> getCommandesByUsers(Users users);

    List<Commandes> findByNomCommande(String nomCommande);

    List<Commandes> findAll();

    Commandes connected();
}
