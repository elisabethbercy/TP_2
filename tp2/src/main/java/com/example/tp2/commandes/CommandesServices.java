package com.example.tp2.commandes;

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
    public void new_commande(String nom_commande, String usr_email) {
        //fetching user email from user repo

       Optional<Users> usr = u_repo.findByEmail(usr_email);

        Commandes usr_com = new Commandes(nom_commande, usr );

        c_repo.save(usr_com);



        throw new UnsupportedOperationException("Unimplemented method 'new_commande'");
    }

    @Override
    public Optional<Users> findByUsr_email(String usr_email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByUsr_email'");
    }

}
