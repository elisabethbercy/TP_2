package com.example.tp2.commandes;
import java.util.List;
import java.util.Optional;

import com.example.tp2.users.Users;

public interface CommandesInterface {

    Commandes newCommande(String nomCommande, Users users);

    List<Commandes> findByUsers(Users users);

    List<Commandes> getCommandesByUsers(Users users);

    List<Commandes> findByNomCommande(String nomCommande);

    List<Commandes> findAll();

    Optional<Commandes> findById(Long id);

    Commandes connected();
}
