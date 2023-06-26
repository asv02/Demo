package com.example.demo.Repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.Entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
// @Query("SELECT p FROM Product p WHERE p.station = :station AND p.weight = :weight ORDER BY p.rate")
    List<Product> findByStationAndWeightOrderByRateAsc(@PathVariable("station") String station,@PathVariable("weight") Integer weight);

}                 
