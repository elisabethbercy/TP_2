package com.example.tp2.commandes;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


import com.example.tp2.users.Users;

public interface CommandesRepository extends CrudRepository<Commandes, Long>{
    
    Optional<Commandes>findById(Long id);

    public List<Commandes>findByUsrEmail(String email);

    public List<Commandes>findAll();
    
}
