package com.example.tp2.commandes;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface CommandesRepository extends CrudRepository<Commandes, Long>{
    
    Optional<Commandes>findById(Long id);

    Optional<Commandes>findByUsr_eail(String usr_email);
    
}
