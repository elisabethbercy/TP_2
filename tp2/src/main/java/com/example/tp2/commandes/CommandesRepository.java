package com.example.tp2.commandes;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.tp2.users.Users;

public interface CommandesRepository extends CrudRepository<Commandes, String>{
    
    Optional<Commandes>findById(Long id);

    Optional<Commandes>findByUsrEmail(Users usrEmail);

    //public List<Commandes> findByUsrEmail(@Param("usrEmail") String usrEmail);
    
}
