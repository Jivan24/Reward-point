package com.retailer.points.controller;


import com.retailer.points.service.PurchaseService;
import com.retailer.points.entity.Purchase;
import com.retailer.points.model.PurchaseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping()
    public ResponseEntity<Purchase> add(@Valid @RequestBody PurchaseModel purchaseModel){
        return new ResponseEntity<>(purchaseService.add(purchaseModel), HttpStatus.OK);
    }

}
