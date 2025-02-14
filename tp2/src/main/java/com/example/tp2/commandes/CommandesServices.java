package com.example.tp2.commandes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tp2.users.UserRepository;
import com.example.tp2.users.Users;

@Service
public class CommandesServices implements CommandesInterface{

    @Autowired
    private CommandesRepository c_repo;
    @Autowired
    private UserRepository u_repo;

    @Override
    public Iterable<Commandes> findAll() {
        var list = c_repo.findAll();

        return list;
        // . throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }


    @Override
    public void newcommande(String nom_commande, Users user) {
        Commandes user_com = new Commandes(nom_commande, user);

        //add commande to Users List<Commandes>
        user.addCommande(user_com);
        // saving commande
        c_repo.save(user_com);
        //save/update user Users List<Commandes>
        u_repo.save(user);
    }

    @Override
    public List<Commandes> findByUsrEmail(String email) {
        return c_repo.findByUsrEmail(email);
    }


    @Override
    public Optional<Commandes> findById(Long id) {
        return c_repo.findById(id);
    }


}
