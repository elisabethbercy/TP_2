package com.example.tp2.articles;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.tp2.commandes.Commandes;

public interface ArticlesRepository extends CrudRepository<Articles, Long> {

    Optional<Articles> findById(Long id);



    public List<Articles> findAll();

    List<Articles> findByCommandes(Commandes commandes);
    
    @Query("SELECT a FROM Articles a JOIN a.commandes c WHERE c.nomCommande = :nomCommande")
    List<Articles> findByNomCommande(@Param("nomCommande")String nomCommande);

    @Query("SELECT a FROM Articles a JOIN a.commandes c WHERE c.id = :idCommande")
    List<Articles> findByIdCommande(@Param("idCommande") Long idCommande);


    // @Query("SELECT a FROM Articles a WHERE a.commandes.nomCommande = :nomCommande")
    // List<Articles> findByNomCommande(@Param("nomCommande")String nomCommande);
    
}
