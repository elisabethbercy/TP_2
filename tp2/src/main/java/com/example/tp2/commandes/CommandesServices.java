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

  
    public void newcommande(String nom_commande, String usr_email) {
        //fetching user email from user repo
       //var usr = service.findById(email);
       var  usr = u_repo.findByEmail(usr_email)
       .orElseThrow(() -> new RuntimeException("User not found with email: " + usr_email));
       Commandes usr_com = new Commandes(nom_commande, usr );

      // String email_for_cmd = usr.get().getEmail().toString();
       //usr.get().getEmail()
      
        c_repo.save(usr_com);


       
    }

    @Override
    public Iterable<Commandes> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Optional<Users> findByUsrEmail(String usr_email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByUsrEmail'");
    }

}
