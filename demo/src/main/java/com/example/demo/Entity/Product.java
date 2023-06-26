package com.example.demo.Entity;

import java.security.Timestamp;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "station")
    private String station;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "rate")
    private Integer rate;

    @Column(name = "TransporterId")
    private Integer TransporterId;
 
    @Column(name="Email")
    private String email;

    // @Column(name="Status")
    // private String status;

    // @Column(updatable = false)
    // @CreationTimestamp
    // // @ColumnDefault("CURRENT_TIMESTAMP")
    // public Timestamp timestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Integer getTransporterId() {
        return TransporterId;
    }

    public void setTransporterId(Integer transporterId) {
        TransporterId = transporterId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // public String getStatus() {
    //     return status;
    // }

    // public void setStatus(String status) {
    //     this.status = status;
    // }
    
 

    // public Timestamp getTimestamp() {
    //     return timestamp;
    // }

    // public Product(Long id, String depot, Integer weight, Integer rate, Integer
    // transporterId) {
    // this.id = id;
    // Depot = depot;
    // Weight = weight;
    // Rate = rate;
    // TransporterId = transporterId;
    // }
    // public Product(){

    // }

}
