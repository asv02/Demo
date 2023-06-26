package com.example.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Response;

@Repository
public interface ResponseRepo extends JpaRepository<Response,Long>{
    
}
