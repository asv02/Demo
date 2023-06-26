package com.example.demo.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
// import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Entity.Product;
import com.example.demo.Entity.Response;
import com.example.demo.Entity.Response.Status;
import com.example.demo.Helper.Helper;
import com.example.demo.Repo.ProductRepo;
import com.example.demo.Repo.ResponseRepo;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ResponseRepo responseRepo;

    public void save(MultipartFile file) {

        try {
            List<Product> products = Helper.convertExcelToListOfProduct(file.getInputStream());            
            this.productRepo.saveAll(products);
            System.out.println("Saved");
        } catch (IOException e) {
            System.out.println("Error is error");
            e.printStackTrace();
        }
    }

    public List<Product> getAllProducts() {
        return this.productRepo.findAll();
    }

    public List<Integer> getAllPrice(String station, Integer weight,Integer loadId) {
        
        List<Product> list = this.productRepo.findByStationAndWeightOrderByRateAsc(station,weight);
        ArrayList<Integer> transport = new ArrayList<>();
        for (Product i : list) {
            transport.add(i.getTransporterId());
        }

        Response res=new Response(loadId, transport, (list.get(0)).getTransporterId(),(list.get(0).getEmail()),Status.ASSIGNED);
        this.responseRepo.save(res);
        System.out.println(list.get(0));
       
        return transport;
        // return list.stream().map(Product::getRate).collect(Collectors.toList());
    }
}
