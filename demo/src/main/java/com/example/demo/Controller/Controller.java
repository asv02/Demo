package com.example.demo.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Entity.Product;
import com.example.demo.Helper.Helper;
import com.example.demo.Service.ProductService;

@RestController
@CrossOrigin("*")
public class Controller {
 
    @Autowired
    private ProductService productService;

    @PostMapping("/product/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        if (Helper.checkExcelFormat(file)) {
            //true

            this.productService.save(file);

            return ResponseEntity.ok(Map.of("message", "File is uploaded and data is saved to database"));


        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file ");
    }

    @GetMapping("/product")
    public List<Product> getAllProduct() {
        return this.productService.getAllProducts();
    }
    
    @GetMapping("/getprice/{station}/{weight}/{loadId}")
    public List<Integer>getAllPrice(@PathVariable("station") String station,@PathVariable("weight") String weight,@PathVariable("loadId") String loadId){
        List<Integer>array=this.productService.getAllPrice(station, Integer.parseInt(weight),Integer.parseInt(loadId));
        return array;
    }
}
