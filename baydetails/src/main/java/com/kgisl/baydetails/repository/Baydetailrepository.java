package com.kgisl.baydetails.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import com.kgisl.baydetails.model.Baydetails;
@Repository

public interface Baydetailrepository extends JpaRepository<Baydetails,Integer> {

    
    Baydetails findByassociatename(String name);
    // Baydetails findbybaytype(String name);

    @Query("SELECT count(floorno) FROM Baydetails")
    Integer selectTotals();


    @Query("SELECT count(baytype) FROM Baydetails")
    Integer selectbaytype();

    @Query("SELECT count(cstatus) FROM Baydetails")
    Integer selectcstatus();
    
    @Query("from Baydetails where cstatus=?1")
    List<Baydetails> findBycstatus(String cstatus);
}
