package com.example.demo.Entity;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Response {
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
	private Long id;
 
    @Column(name="loadId")
    private Integer loadId;

    @Column(name="transporterId")
    private ArrayList<Integer> transporterId;
    
    @Column(name = "Transporter")
    private Integer Transporter;

    @Column(name = "Email")
    private String email;

    @UpdateTimestamp
    @Column(name = "AssignedTime")
    private Timestamp AssignedTime;

    @Enumerated(EnumType.STRING)
    public Status status;

    public enum Status {
        EXPIRED, ASSIGNED, REJECTED, COMPLETED;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLoadId() {
        return loadId;
    }
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLoadId(Integer loadId) {
        this.loadId = loadId;
    }

    public ArrayList<Integer> getTransporterId() {
        return transporterId;
    }



    public void setTransporterId(ArrayList<Integer> transporterId) {
        this.transporterId = transporterId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public Response(Integer loadId, ArrayList<Integer> transporterId, Integer transporter, String email,
            Status status) {
        this.loadId = loadId;
        this.transporterId = transporterId;
        Transporter = transporter;
        this.email = email;
        this.status = status;
    }

    public Response(){
        //Default constructor 
    }
    public Integer getTransporter() {
        return Transporter;
    }

    public void setTransporter(Integer transporter) {
        Transporter = transporter;
    }  
}
