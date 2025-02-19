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
    public Commandes newCommande(String nomCommande, Users users) {
        Commandes commandes = new Commandes();
        commandes.setNomCommande(nomCommande);
        commandes.setUsers(users);
        return c_repo.save(commandes);
        
    }


    public List<Commandes> getCommandesByUsers(Users users){
        return c_repo.findByUsers(users);

        // check this
    }


    @Override
    public List<Commandes> findAll() {
        return c_repo.findAll();
    }

    public List<Commandes> findByUsers(Users  users){
        return c_repo.findByUserEmail(users.getEmail());
    }


    @Override
    public Commandes connected() {
       var usr1 = new Users("Bercy","Stephanie","bercy@gmail.com","1234");
       var com1 = new Commandes("Boissons",usr1);
      return c_repo.save(com1);

    }


    @Override
    public List<Commandes> findByNomCommande(String nomCommande) {
        return c_repo.findByNomCommande(nomCommande);
    }
}
